package dev.tomat.halloween.core.item;

import dev.tomat.halloween.HalloweenMod;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeHelper;

public final class ItemRegistry {
    private static int ITEM_ID_START = 21000;

    public static final Item CURSE_ORB;
    public static final Item HALLOWEEN_CANDY;
    public static final Item MEGA_CANDY;
    public static final Item HAUNT_MALEV;
    public static final Item HAUNT_DIABOL;
    public static final Item MONSTER_DETECTOR;

    static {
        CURSE_ORB = ItemHelper.createItem(HalloweenMod.MOD_ID, new CurseOrbItem(ITEM_ID_START++), "curse_orb", "CurseOrb.png");
        HALLOWEEN_CANDY = ItemHelper.createItem(HalloweenMod.MOD_ID, new HalloweenCandyItem(HalpLibe.addModId(HalloweenMod.MOD_ID, "halloween_candy"), ITEM_ID_START++, 1, false, 16), "halloween_candy", "HalloweenCandy.png");
        MEGA_CANDY = ItemHelper.createItem(HalloweenMod.MOD_ID, new ItemFood(HalpLibe.addModId(HalloweenMod.MOD_ID, "halloween_candy"), ITEM_ID_START++, 5, false), "mega_candy", "MegaCandy.png");
        HAUNT_MALEV = ItemHelper.createItem(HalloweenMod.MOD_ID, new Item(ITEM_ID_START++), "haunt_malev", "HauntMalev.png").setMaxStackSize(64);
        HAUNT_DIABOL = ItemHelper.createItem(HalloweenMod.MOD_ID, new Item(ITEM_ID_START++), "haunt_diabol", "HauntDiabol.png").setMaxStackSize(64);
        MONSTER_DETECTOR = ItemHelper.createItem(HalloweenMod.MOD_ID, new MonsterDetectorItem(ITEM_ID_START++), "monster_detector", "MonsterDetector.png").setMaxStackSize(1);
    }

    public static void initialize() {
        assert CURSE_ORB != null;
        assert HALLOWEEN_CANDY != null;
        assert MEGA_CANDY != null;
        assert HAUNT_MALEV != null;
        assert HAUNT_DIABOL != null;
        assert MONSTER_DETECTOR != null;

        RecipeHelper.Crafting.createShapelessRecipe(
                new ItemStack(MEGA_CANDY, 1),
                new Object[]{
                        new ItemStack(HALLOWEEN_CANDY, 1, 0),
                        new ItemStack(HALLOWEEN_CANDY, 1, 1),
                        new ItemStack(HALLOWEEN_CANDY, 1, 2),
                        new ItemStack(HALLOWEEN_CANDY, 1, 3),
                        new ItemStack(HALLOWEEN_CANDY, 1, 4)
                }
        );

        RecipeHelper.Crafting.createRecipe(
                MONSTER_DETECTOR,
                1,
                new Object[]{
                        "MD",
                        "DM",
                        'M',
                        HAUNT_MALEV,
                        'D',
                        HAUNT_DIABOL
                }
        );
    }
}
