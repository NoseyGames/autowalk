package com.nikegtag.autonav;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class AutoNavMod implements ModInitializer {

    public static final String MOD_ID = "autonav";

    @Override
    public void onInitialize() {

        GotoCommand.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            NavigationManager.tick(client);
        });

        System.out.println("welcome to autonav, hope this isnt breaking the rules, give me a warning if it is because i js wanna know.");
    }
}
