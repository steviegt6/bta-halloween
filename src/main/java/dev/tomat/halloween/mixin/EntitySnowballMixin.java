package dev.tomat.halloween.mixin;

import dev.tomat.halloween.core.entity.projectile.CurseOrbEntity;
import net.minecraft.core.entity.projectile.EntitySnowball;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntitySnowball.class)
public class EntitySnowballMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;spawnParticle(Ljava/lang/String;DDDDDD)V"), remap = false)
    public void tick(World world, String particle, double x, double y, double z, double motionX, double motionY, double motionZ) {
        //noinspection ConstantValue
        if (((Object) this) instanceof CurseOrbEntity)
            particle = "halloween:curse_orb";

        world.spawnParticle(particle, x, y, z, motionX, motionY, motionZ);
    }
}
