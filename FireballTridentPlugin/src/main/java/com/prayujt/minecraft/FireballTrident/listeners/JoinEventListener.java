package com.prayujt.minecraft.FireballTrident.listeners;

import com.prayujt.minecraft.FireballTrident.PluginCore;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class JoinEventListener implements Listener {
    public JoinEventListener(PluginCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);

        if (!player.getInventory().contains(Material.TRIDENT)) {
            ItemStack trident = new ItemStack(Material.TRIDENT);
            ItemMeta meta = trident.getItemMeta();
            if (meta != null) {
                meta.addEnchant(Enchantment.LOYALTY, 3, true);
                meta.addEnchant(Enchantment.DURABILITY, 3, true);
                meta.addEnchant(Enchantment.CHANNELING, 1, true);

                trident.setItemMeta(meta);
            }
            player.getInventory().addItem(trident);
        }

        if (!player.getInventory().contains(Material.FIRE_CHARGE)) {
            ItemStack fireCharge = new ItemStack(Material.FIRE_CHARGE, 64);
            player.getInventory().addItem(fireCharge);
        }
    }
}
