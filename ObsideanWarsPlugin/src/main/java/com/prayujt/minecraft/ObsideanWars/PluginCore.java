package com.prayujt.minecraft.ObsideanWars;

import com.prayujt.minecraft.ObsideanWars.listeners.JoinEventListener;
import com.prayujt.minecraft.ObsideanWars.listeners.RespawnEventListener;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends JavaPlugin {

    @Override
    public void onEnable(){
        // Fired when server plugin is first enabled
        JoinEventListener joinEventListener = new JoinEventListener(this);
        RespawnEventListener respawnEventListener = new RespawnEventListener(this);
        getLogger().info("ObsideanWarsPlugin has been enabled.");
    }

    @Override
    public void onDisable(){
        // Fired when the server stops and disables all plugins
    }
}
