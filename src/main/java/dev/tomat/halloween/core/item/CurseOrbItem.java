package dev.tomat.halloween.core.item;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.entity.projectile.EntitySnowball;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class CurseOrbItem extends Item {
    public CurseOrbItem(int id) {
        super(id);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        itemstack.consumeItem(entityplayer);
        world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            world.entityJoinedWorld(new EntitySnowball(world, entityplayer));
        }

        return itemstack;
    }


    @Override
    public int getColorFromDamage(int i) {
        return 0xFF00AA;
    }
}
