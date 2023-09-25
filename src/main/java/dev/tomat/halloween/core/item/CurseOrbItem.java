package dev.tomat.halloween.core.item;

import net.minecraft.core.item.Item;

public class CurseOrbItem extends Item {
    public CurseOrbItem(int id) {
        super(id);
    }

    @Override
    public int getColorFromDamage(int i) {
        return 0xFF00AA;
    }
}
