package me.snakeamazing.commands;

import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyAll implements CommandExecutor {

    private SimpleFly plugin;

    public FlyAll(SimpleFly plugin){

        this.plugin = plugin;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "SimpleFly" + ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");

            return false;
        }
        Player player = (Player) sender;
        if (player.hasPermission("simplefly.*") || player.hasPermission("simplefly.flyall")) {
        if (!player.getAllowFlight()) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                all.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-enabled-everyone"));
                all.setAllowFlight(true);
                all.setFlying(true);
                all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
                all.sendTitle(plugin.getConfig().getString("messages.titles.fly-all-enabled"), plugin.getConfig().getString("messages.subtitles.fly-all-enabled"));
            }
        } else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                all.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-disabled-everyone"));
                all.setAllowFlight(false);
                all.setFlying(false);
                all.playSound(all.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
                all.sendTitle(plugin.getConfig().getString("messages.titles.fly-all-disabled"), plugin.getConfig().getString("messages.subtitles.fly-all-disabled"));
            }
            return false;
          }
        } else {
            player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission"));
        }
        return false;
    }
}
