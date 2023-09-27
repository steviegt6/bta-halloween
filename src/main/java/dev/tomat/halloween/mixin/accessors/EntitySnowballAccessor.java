package dev.tomat.halloween.mixin.accessors;

import net.minecraft.core.entity.projectile.EntitySnowball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntitySnowball.class)
public interface EntitySnowballAccessor {
    @Accessor(remap = false)
    boolean getInGroundSnowball();

    @Accessor(remap = false)
    int getShakeSnowball();
}
