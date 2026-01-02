package com.prayujt.minecraft.FireballTrident.kit;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class PlayerKit {

    private PlayerKit() {}

    public static void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItemInOffHand(null);

        player.setGameMode(GameMode.SURVIVAL);

        giveTrident(player);
        // giveFireCharges(player);
        giveBow(player);
        giveSpectralArrows(player);
    }

    private static void giveTrident(Player player) {
        if (player.getInventory().contains(Material.TRIDENT)) return;

        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();
        if (meta != null) {
            meta.addEnchant(Enchantment.LOYALTY, 3, true);
            meta.addEnchant(Enchantment.DURABILITY, 3, true);
            meta.addEnchant(Enchantment.CHANNELING, 1, true);
            // meta.addEnchant(Enchantment.RIPTIDE, 1, true);
            trident.setItemMeta(meta);
        }
        player.getInventory().addItem(trident);
    }

    private static void giveFireCharges(Player player) {
        if (player.getInventory().contains(Material.FIRE_CHARGE)) return;
        player.getInventory().addItem(new ItemStack(Material.FIRE_CHARGE, 64));
    }

    private static void giveBow(Player player) {
        if (player.getInventory().contains(Material.BOW)) return;

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        if (meta != null) {
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
            meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
            bow.setItemMeta(meta);
        }
        player.getInventory().addItem(bow);
    }

    private static void giveSpectralArrows(Player player) {
        if (player.getInventory().contains(Material.SPECTRAL_ARROW)) return;
        player.getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW, 64));
    }
}
