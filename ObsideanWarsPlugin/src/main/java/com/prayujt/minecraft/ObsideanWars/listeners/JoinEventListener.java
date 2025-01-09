package com.prayujt.minecraft.ObsideanWars.listeners;

import com.prayujt.minecraft.ObsideanWars.PluginCore;

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

        if (!player.getInventory().contains(Material.DIAMOND_PICKAXE)) {
            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta meta = pickaxe.getItemMeta();
            if (meta != null) {
                meta.addEnchant(Enchantment.DURABILITY, 3, true);

                pickaxe.setItemMeta(meta);
            }
            player.getInventory().addItem(pickaxe);
        }

        if (!player.getInventory().contains(Material.COOKED_BEEF)) {
            ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
            player.getInventory().addItem(steak);
        }
    }
}
