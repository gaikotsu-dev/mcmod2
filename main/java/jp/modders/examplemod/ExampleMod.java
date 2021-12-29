package jp.modders.examplemod;

import jp.modders.examplemod.config.ConfigHolder;
import jp.modders.examplemod.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Cadiboo
 */
@Mod(ExampleMod.MODID)
public final class ExampleMod {

	public static final String MODID = "examplemod";

	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public ExampleMod() {
		LOGGER.debug("Hello from Example Mod!");

		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Register Deferred Registers (Does not need to be before Configs)
		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		// Register Configs (Does not need to be after Deferred Registers)
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
	}

}
