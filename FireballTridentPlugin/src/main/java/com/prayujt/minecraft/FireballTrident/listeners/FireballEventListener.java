package com.prayujt.minecraft.FireballTrident.listeners;

import com.prayujt.minecraft.FireballTrident.PluginCore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FireballEventListener implements Listener {
    public FireballEventListener(PluginCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType() == Material.FIRE_CHARGE) {
            event.setCancelled(true);
            Fireball fireball = player.getWorld().spawn(player.getLocation().add(new Vector(0.0D, 2.0D, 0.0D)), Fireball.class);
            fireball.setBounce(true);
            fireball.setShooter(player);
            fireball.setYield(2.0f);

            removeItem(player.getInventory(), Material.FIRE_CHARGE, 1);
        }
    }

    public void removeItem(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (stack == null) continue;
            if (type == stack.getType()) {
                int newAmount = stack.getAmount() - amount;
                if (newAmount > 0) {
                    stack.setAmount(newAmount);
                    break;
                }
                else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
}
