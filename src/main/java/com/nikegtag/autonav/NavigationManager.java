package com.nikegtag.autonav;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class NavigationManager {

    private static Vec3d target = null;
    private static boolean active = false;

    public static void setTarget(double x, double y, double z) {
        target = new Vec3d(x, y, z);
        active = true;
    }

    public static void tick(MinecraftClient client) {
        if (!active || target == null || client.player == null) return;

        ClientPlayerEntity player = client.player;

        Vec3d pos = player.getPos();
        Vec3d diff = target.subtract(pos);

        double distance = diff.length();

        // Stop when close enough
        if (distance < 2.0) {
            stop(client);
            return;
        }

        double dx = diff.x;
        double dy = diff.y;
        double dz = diff.z;

        // Rotate player toward target
        double yaw = Math.toDegrees(Math.atan2(dz, dx)) - 90F;
        player.setYaw((float) yaw);

        boolean isFlying = player.getAbilities().flying;

        if (isFlying) {
            fly(client, dy);
        } else {
            walk(client);
        }
    }

    private static void walk(MinecraftClient client) {
        // Simple forward movement
        client.options.forwardKey.setPressed(true);
    }

    private static void fly(MinecraftClient client, double dy) {

        client.options.forwardKey.setPressed(true);

        // Vertical control
        if (dy > 1) {
            client.options.jumpKey.setPressed(true);
            client.options.sneakKey.setPressed(false);
        } else if (dy < -1) {
            client.options.sneakKey.setPressed(true);
            client.options.jumpKey.setPressed(false);
        } else {
            client.options.jumpKey.setPressed(false);
            client.options.sneakKey.setPressed(false);
        }
    }

    private static void stop(MinecraftClient client) {
        active = false;
        target = null;

        client.options.forwardKey.setPressed(false);
        client.options.jumpKey.setPressed(false);
        client.options.sneakKey.setPressed(false);
    }
}
