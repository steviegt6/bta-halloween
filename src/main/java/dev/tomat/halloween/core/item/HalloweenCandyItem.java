package dev.tomat.halloween.core.item;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemFoodStackable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class HalloweenCandyItem extends ItemFoodStackable {
    public static final String[] CANDY_COLOR_NAMES = new String[]{"red", "green", "yellow", "purple", "orange"};
    public static final int[] DYE_COLORS = new int[]{12804428, 4312372, 14602026, 8073150, 15435844};

    public HalloweenCandyItem(String name, int id, int healAmount, boolean favouriteWolfFood, int maxStackSize) {
        super(name, id, healAmount, favouriteWolfFood, maxStackSize);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.health < 20 && itemstack.consumeItem(entityplayer) && world.rand.nextInt(3) == 0) {
            entityplayer.heal(this.healAmount);
        }

        return itemstack;
    }

    @Override
    public String getLanguageKey(ItemStack itemStack) {
        return super.getKey() + "." + CANDY_COLOR_NAMES[itemStack.getItemDamageForDisplay()];
    }

    @Override
    public int getColorFromDamage(int i) {
        if (i < 0)
            i = 0;
        if (i > 4)
            i = 4;

        return DYE_COLORS[i];
    }
}
