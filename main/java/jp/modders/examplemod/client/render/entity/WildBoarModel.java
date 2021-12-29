package jp.modders.examplemod.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import jp.modders.examplemod.entity.WildBoarEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class WildBoarModel extends EntityModel<WildBoarEntity>
{
    public ModelRenderer headModel = new ModelRenderer(this, 0, 0);
    public ModelRenderer body;
    public ModelRenderer legBackRight;
    public ModelRenderer legBackLeft;
    public ModelRenderer legFrontRight;
    public ModelRenderer legFrontLeft;

    public WildBoarModel()
    {
        this.textureHeight = 32;
        this.textureWidth =64;

        int p_i225948_1_ = 6;
        float p_i225948_2_ = 0.0F;
        boolean p_i225948_3_ = false;
        float p_i225948_4_ = 4.0F;
        float p_i225948_5_ = 4.0F;
        float p_i225948_6_ = 2.0F;
        float p_i225948_7_ = 2.0F;
        int p_i225948_8_ = 24;

        this.headModel.addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, p_i225948_2_);
        this.headModel.setRotationPoint(0.0F, (float)(18 - p_i225948_1_), -6.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, p_i225948_2_);
        this.body.setRotationPoint(0.0F, (float)(17 - p_i225948_1_), 2.0F);
        this.legBackRight = new ModelRenderer(this, 0, 16);
        this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, (float)p_i225948_1_, 4.0F, p_i225948_2_);
        this.legBackRight.setRotationPoint(-3.0F, (float)(24 - p_i225948_1_), 7.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 16);
        this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, (float)p_i225948_1_, 4.0F, p_i225948_2_);
        this.legBackLeft.setRotationPoint(3.0F, (float)(24 - p_i225948_1_), 7.0F);
        this.legFrontRight = new ModelRenderer(this, 0, 16);
        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, (float)p_i225948_1_, 4.0F, p_i225948_2_);
        this.legFrontRight.setRotationPoint(-3.0F, (float)(24 - p_i225948_1_), -5.0F);
        this.legFrontLeft = new ModelRenderer(this, 0, 16);
        this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, (float)p_i225948_1_, 4.0F, p_i225948_2_);
        this.legFrontLeft.setRotationPoint(3.0F, (float)(24 - p_i225948_1_), -5.0F);
    }

    @Override
    public void setRotationAngles(WildBoarEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.headModel.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.headModel.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        legBackRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        legBackLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        legFrontRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        legFrontLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}

