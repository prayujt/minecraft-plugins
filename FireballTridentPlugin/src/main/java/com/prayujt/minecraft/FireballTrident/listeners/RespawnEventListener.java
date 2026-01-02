package com.prayujt.minecraft.FireballTrident.listeners;

import com.prayujt.minecraft.FireballTrident.PluginCore;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class RespawnEventListener implements Listener {
    public RespawnEventListener(PluginCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);

        if (!player.getInventory().contains(Material.TRIDENT)) {
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

        if (!player.getInventory().contains(Material.FIRE_CHARGE)) {
            ItemStack fireCharge = new ItemStack(Material.FIRE_CHARGE, 64);
            player.getInventory().addItem(fireCharge);
        }

        if (!player.getInventory().contains(Material.BOW)) {
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
        
        if (!player.getInventory().contains(Material.SPECTRAL_ARROW)) {
            player.getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW, 64));
        }
    }
}
