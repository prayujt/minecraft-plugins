package com.prayujt.minecraft;

import com.prayujt.minecraft.listeners.JoinEventListener;
import com.prayujt.minecraft.listeners.RespawnEventListener;
import com.prayujt.minecraft.listeners.FireballEventListener;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends JavaPlugin {

    @Override
    public void onEnable(){
        // Fired when server plugin is first enabled
        FireballEventListener fireballListener = new FireballEventListener(this);
        JoinEventListener joinListener = new JoinEventListener(this);
        RespawnEventListener respawnListener = new RespawnEventListener(this);

        getLogger().info("FireballTridentPlugin has been enabled.");
    }

    @Override
    public void onDisable(){
        // Fired when the server stops and disables all plugins
    }
}
