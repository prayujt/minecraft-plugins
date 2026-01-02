package com.prayujt.minecraft.FireballTrident.listeners;

import com.prayujt.minecraft.FireballTrident.PluginCore;
import com.prayujt.minecraft.FireballTrident.kit.PlayerKit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEventListener implements Listener {
    public RespawnEventListener(PluginCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
		PlayerKit.apply(event.getPlayer());
    }
}
