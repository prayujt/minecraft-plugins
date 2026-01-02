package com.prayujt.minecraft.FireballTrident.commands;

import com.prayujt.minecraft.FireballTrident.duel.ArenaBuilder;
import com.prayujt.minecraft.FireballTrident.kit.PlayerKit;
import com.prayujt.minecraft.FireballTrident.util.Messages;
import com.prayujt.minecraft.FireballTrident.PluginCore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class DuelCommand implements CommandExecutor {

    private static final AtomicInteger ARENA_INDEX = new AtomicInteger(0);

    private static final int PLATFORM_SIZE = 75;
    private static final int EDGE_BUFFER = 5;
    private static final int ARENA_SPACING = 140;
    private static final int ARENA_Y = 200;

	private final PluginCore plugin;

    public DuelCommand(PluginCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this.");
            return true;
        }

        Player p1 = (Player) sender;

        if (args.length != 1) {
            p1.sendMessage(Messages.info("Usage: /duel <player>"));
            return true;
        }

        Player p2 = Bukkit.getPlayerExact(args[0]);
        if (p2 == null) {
            p1.sendMessage(Messages.error("Player not found (must be online): " + args[0]));
            return true;
        }

        if (p1.getUniqueId().equals(p2.getUniqueId())) {
            p1.sendMessage(Messages.error("You can't duel yourself."));
            return true;
        }

        World world = p1.getWorld();

        int idx = ARENA_INDEX.getAndIncrement();
        int gridW = 10;
        int gx = idx % gridW;
        int gz = idx / gridW;

        int centerX = gx * ARENA_SPACING;
        int centerZ = gz * ARENA_SPACING;

        Location center = new Location(world, centerX + 0.5, ARENA_Y, centerZ + 0.5);

        ArenaBuilder.buildPlatform(center, PLATFORM_SIZE, Material.SMOOTH_STONE);

        int half = PLATFORM_SIZE / 2;

        double zNorth = centerZ - half + EDGE_BUFFER + 0.5;
        double zSouth = centerZ + half - EDGE_BUFFER - 0.5;

        Location spawn1 = new Location(world, centerX + 0.5, ARENA_Y + 1, zNorth, 180f, 0f);
        Location spawn2 = new Location(world, centerX + 0.5, ARENA_Y + 1, zSouth, 0f, 0f);

        p1.teleport(spawn1);
        p2.teleport(spawn2);

        PlayerKit.apply(p1);
        PlayerKit.apply(p2);

        p1.sendMessage(Messages.success("Duel started vs " + p2.getName() + "!"));
        p2.sendMessage(Messages.success("Duel started vs " + p1.getName() + "!"));

        return true;
    }
}
