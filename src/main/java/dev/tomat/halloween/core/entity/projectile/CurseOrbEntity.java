package dev.tomat.halloween.core.entity.projectile;

import dev.tomat.halloween.mixin.accessors.EntitySnowballAccessor;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.entity.projectile.EntitySnowball;
import net.minecraft.core.world.World;

public class CurseOrbEntity extends EntitySnowball {
    public CurseOrbEntity(World world, EntityLiving thrower) {
        super(world, thrower);
    }

    @Override
    public void playerTouch(EntityPlayer player) {
        if (!(((EntitySnowballAccessor) this).getInGroundSnowball() || thrower != player || (((EntitySnowballAccessor) this).getShakeSnowball() > 0)))
            return;

        world.playSoundAtEntity(this, "random.pop", 0.2f, ((random.nextFloat() - random.nextFloat()) * 0.7f + 1.0f) * 2.0f);
        player.onItemPickup(this, 1);
        remove();
    }

    @Override
    public String getEntityTexture() {
        return "/assets/halloween/mobs/CurseOrbEntity.png";
    }
}
