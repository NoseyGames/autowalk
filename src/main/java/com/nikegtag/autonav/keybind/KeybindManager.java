package com.nikegtag.autonav.keybind;

import com.nikegtag.autonav.gui.GotoScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {

    private static KeyBinding openGuiKey;

    public static void register() {

        // Register keybind (G key)
        openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.autonav.open_gui",
                        GLFW.GLFW_KEY_G,
                        "category.autonav"
                )
        );

        // Listen for key presses every tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (openGuiKey.wasPressed()) {

                MinecraftClient mc = MinecraftClient.getInstance();

                if (mc.player != null) {
                    mc.setScreen(new GotoScreen());
                } else {
                    if (mc.player != null) {
                        mc.player.sendMessage(
                                Text.literal("You must be in a world to use AutoNav"),
                                false
                        );
                    }
                }
            }
        });
    }
}
