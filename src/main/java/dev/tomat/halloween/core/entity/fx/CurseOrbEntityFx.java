package dev.tomat.halloween.core.entity.fx;

import dev.tomat.halloween.core.item.ItemRegistry;
import net.minecraft.core.entity.fx.EntitySlimeFX;
import net.minecraft.core.util.helper.Color;
import net.minecraft.core.world.World;

public class CurseOrbEntityFx extends EntitySlimeFX {
    public CurseOrbEntityFx(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, ItemRegistry.CURSE_ORB);
        Color tint = new Color();
        tint.setARGB(0xFF00AA);
        particleRed = tint.getRed() / 255f;
        particleGreen = tint.getGreen() / 255f;
        particleBlue = tint.getBlue() / 255f;
    }
}
