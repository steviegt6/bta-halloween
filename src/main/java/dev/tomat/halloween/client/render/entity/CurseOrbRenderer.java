package dev.tomat.halloween.client.render.entity;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.Color;
import org.lwjgl.opengl.GL11;

public class CurseOrbRenderer extends EntityRenderer<Entity> {
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float renderPartialTicks) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        Color tint = new Color();
        tint.setARGB(0xFF00AA);
        GL11.glColor3f(tint.getRed() / 255F, tint.getGreen() / 255F, tint.getBlue() / 255F);
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glEnable(32826);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.loadTexture("/assets/halloween/mobs/CurseOrbEntity.png");
        GL11.glRotatef(180.0F - this.renderDispatcher.viewLerpYaw, 0, 1, 0);
        GL11.glRotatef(-this.renderDispatcher.viewLerpPitch, 1, 0, 0);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0, 1, 0);
        tessellator.addVertexWithUV(-0.5, -0.25, 0, 0, 1);
        tessellator.addVertexWithUV(0.5, -0.25, 0, 1, 1);
        tessellator.addVertexWithUV(0.5, 0.75, 0, 1, 0);
        tessellator.addVertexWithUV(-0.5, 0.75, 0.0, 0, 0);
        tessellator.draw();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }
}
