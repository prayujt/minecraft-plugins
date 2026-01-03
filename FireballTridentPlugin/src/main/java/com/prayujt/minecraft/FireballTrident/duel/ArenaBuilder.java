package com.prayujt.minecraft.FireballTrident.duel;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public final class ArenaBuilder {
    private ArenaBuilder() {}

    public static void buildPlatform(Location center, int size, Material material) {
        World w = center.getWorld();
        if (w == null) return;

        int half = size / 2;

        int y = center.getBlockY();
        int cx = center.getBlockX();
        int cz = center.getBlockZ();

        int minX = cx - half;
        int maxX = cx + half - 1;
        int minZ = cz - half;
        int maxZ = cz + half - 1;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                Block b = w.getBlockAt(x, y, z);
                b.setType(material, false);
            }
        }
    }
}
