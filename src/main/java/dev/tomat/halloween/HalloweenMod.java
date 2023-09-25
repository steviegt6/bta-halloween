package dev.tomat.halloween;

import dev.tomat.halloween.core.item.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.SoundManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HalloweenMod implements ModInitializer {
    public static final String MOD_ID = "halloween";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public HalloweenMod() {
        ItemRegistry.initialize();
    }

    @Override
    public void onInitialize() { }
}
