package dev.tomat.halloween.mixin;

import com.mojang.nbt.CompoundTag;
import dev.tomat.halloween.core.item.MonsterDetectorItem;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.EntityArmoredZombie;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin extends Entity {
    @Shadow(remap = false) public InventoryPlayer inventory;
    private int monsterDetectorCooldown = 0;

    public EntityPlayerMixin(World world) {
        super(world);
    }

    @Inject(method = "onLivingUpdate", at = @At("TAIL"), remap = false)
    public void onLivingUpdate(CallbackInfo ci) {
        if (Arrays.stream(inventory.mainInventory).noneMatch(itemStack -> itemStack != null && itemStack.getItem() instanceof MonsterDetectorItem))
            return;

        if (monsterDetectorCooldown-- > 0)
            return;

        double distance = 100;
        boolean scary = false;

        List<Entity> monsters = world.getEntitiesWithinAABB(EntityMonster.class, bb.expand(32, 32, 32));
        for (Entity monsterEntity : monsters) {
            EntityMonster monster = (EntityMonster) monsterEntity;
            double mobDistance = monster.distanceTo(this);
            if (mobDistance < distance) {
                distance = mobDistance;

                if (monster.hasCurrentTarget() && monster.getCurrentTarget() == this)
                    scary = true;
            }
        }

        if (distance <= 32) {
            world.playSoundEffect(SoundType.WORLD_SOUNDS, x, y, z, scary ? "halloween.detecthi" : "halloween.detectlo", 0.75f, 1.0f);

            if (distance >= 24)
                monsterDetectorCooldown = 40;
            else if (distance >= 16)
                monsterDetectorCooldown = 20;
            else if (distance >= 12)
                monsterDetectorCooldown = 15;
            else if (distance >= 6)
                monsterDetectorCooldown = 10;
            else
                monsterDetectorCooldown = 7;
        }
        else {
            monsterDetectorCooldown = 80;
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
    }
}
