package com.prayujt.minecraft.FireballTrident;

import com.prayujt.minecraft.FireballTrident.listeners.JoinEventListener;
import com.prayujt.minecraft.FireballTrident.listeners.RespawnEventListener;
import com.prayujt.minecraft.FireballTrident.listeners.FireballEventListener;
import com.prayujt.minecraft.FireballTrident.listeners.DeathEventListener;
import com.prayujt.minecraft.FireballTrident.commands.DuelCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends JavaPlugin {

    @Override
    public void onEnable(){
        // Fired when server plugin is first enabled
        new FireballEventListener(this);
        new JoinEventListener(this);
        new RespawnEventListener(this);
        new DeathEventListener(this);

		getCommand("duel").setExecutor(new DuelCommand(this));

        getLogger().info("FireballTridentPlugin has been enabled.");
    }

    @Override
    public void onDisable(){
        // Fired when the server stops and disables all plugins
    }
}
