package dev.tomat.halloween.mixin;

import dev.tomat.halloween.HalloweenMod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.SoundPool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(SoundManager.class)
public class SoundManagerMixin {
    private static final String[] MOD_SOUNDS = new String[] {
            "detecthi.ogg",
            "detectlo.ogg",
    };

    @Inject(method = "loadModAudio", at = @At("TAIL"), remap = false)
    private static void loadModAudio(String s, SoundPool soundPool, CallbackInfo ci) {
        for (String sound : MOD_SOUNDS) {
            InputStream stream = HalloweenMod.class.getClassLoader().getResourceAsStream("assets/halloween/sound/" + sound);
            Path tempPath = FabricLoader.getInstance().getGameDir().resolve(".fabric").resolve("tmp").resolve("halloween").resolve(sound);

            if (!tempPath.toFile().exists())
            {
                //noinspection ResultOfMethodCallIgnored
                tempPath.toFile().getParentFile().mkdirs();
            }
            else {
                //noinspection ResultOfMethodCallIgnored
                tempPath.toFile().delete();
            }

            try {
                assert stream != null;
                Files.copy(stream, tempPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            soundPool.addSound("halloween/" + sound, tempPath.toFile());
        }
    }
}
