package com.prayujt.minecraft.FireballTrident.listeners;

import com.prayujt.minecraft.FireballTrident.PluginCore;
import com.prayujt.minecraft.FireballTrident.kit.PlayerKit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEventListener implements Listener {
    public JoinEventListener(PluginCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerKit.apply(event.getPlayer());
    }
}
