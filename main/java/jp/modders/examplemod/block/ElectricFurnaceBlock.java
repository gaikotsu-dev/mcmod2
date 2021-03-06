package jp.modders.examplemod.block;

import jp.modders.examplemod.tileentity.ElectricFurnaceTileEntity;
import jp.modders.examplemod.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * @author Cadiboo
 */
public class ElectricFurnaceBlock extends HorizontalBlock {

	public ElectricFurnaceBlock(final Properties properties) {
		super(properties);
		// Set the default values for our blockstate properties
		this.setDefaultState(this.getDefaultState()
				.with(HORIZONTAL_FACING, Direction.NORTH)
		);
	}

	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		// Always use TileEntityType#create to allow registry overrides to work.
		return ModTileEntityTypes.ELECTRIC_FURNACE.get().create();
	}

	/**
	 * Called on the logical server when a BlockState with a TileEntity is replaced by another BlockState.
	 * We use this method to drop all the items from our tile entity's inventory and update comparators near our block.
	 *
	 * @deprecated Call via {@link BlockState#onReplaced(World, BlockPos, BlockState, boolean)}
	 * Implementing/overriding is fine.
	 */
	@Override
	public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (oldState.getBlock() != newState.getBlock()) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof ElectricFurnaceTileEntity) {
				final ItemStackHandler inventory = ((ElectricFurnaceTileEntity) tileEntity).inventory;
				for (int slot = 0; slot < inventory.getSlots(); ++slot)
					InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
			}
		}
		super.onReplaced(oldState, worldIn, pos, newState, isMoving);
	}

	/**
	 * Called when a player right clicks our block.
	 * We use this method to open our gui.
	 *
	 * @deprecated Call via {@link BlockState#onBlockActivated(World, PlayerEntity, Hand, BlockRayTraceResult)} whenever possible.
	 * Implementing/overriding is fine.
	 */
	@Override
	public ActionResultType onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			final TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof ElectricFurnaceTileEntity)
				NetworkHooks.openGui((ServerPlayerEntity) player, (ElectricFurnaceTileEntity) tileEntity, pos);
		}
		return ActionResultType.SUCCESS;
	}

	/**
	 * Makes the block face the player when placed
	 */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	/**
	 * We return the redstone calculated from our inventory
	 *
	 * @deprecated call via {@link BlockState#getComparatorInputOverride(World, BlockPos)} whenever possible.
	 * Implementing/overriding is fine.
	 */
	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		final TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof ElectricFurnaceTileEntity)
			return ItemHandlerHelper.calcRedstoneFromInventory(((ElectricFurnaceTileEntity) tileEntity).inventory);
		return super.getComparatorInputOverride(blockState, worldIn, pos);
	}

	/**
	 * Called from inside the constructor {@link Block#Block(Properties)} to add all the properties to our blockstate
	 */
	@Override
	protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(HORIZONTAL_FACING);
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate.
	 * If inapplicable, returns the passed blockstate.
	 *
	 * @deprecated call via {@link BlockState#rotate(Rotation)} whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate.
	 * If inapplicable, returns the passed blockstate.
	 *
	 * @deprecated call via {@link BlockState#mirror(Mirror)} whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
	}

}
