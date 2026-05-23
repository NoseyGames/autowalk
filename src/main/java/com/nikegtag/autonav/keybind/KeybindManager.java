package com.nikegtag.autonav.keybind;

import com.nikegtag.autonav.gui.GotoScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {

    private static KeyBinding openGuiKey;

    public static void register() {

        openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.autonav.open_gui",
                        GLFW.GLFW_KEY_G,
                        "category.autonav"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new GotoScreen());
                }
            }
        });
    }
}
