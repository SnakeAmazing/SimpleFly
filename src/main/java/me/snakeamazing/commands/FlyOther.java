package me.snakeamazing.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyOther implements CommandExecutor {

    private SimpleFly plugin;

    public FlyOther(SimpleFly plugin){
        this.plugin = plugin;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player other = Bukkit.getPlayer(args[0]);
        Player p = (Player) sender;

        if (p.hasPermission("simplefly.*") || (p.hasPermission("simplefly.fly.other"))){

            if (other == null){
                String withOutPlaceholders3 = plugin.getConfig().getString("Player Offline");
                String withPlaceholders3 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders3);
                p.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders3);
                return true;
            }
            if (!other.getAllowFlight()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(p));
                other.setAllowFlight(true);

                String withOutPlaceholders4 = plugin.getConfig().getString("Fly Enabled By");
                String withPlaceholders4 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders4);

                p.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders4);

                String withOutPlaceholders = plugin.getConfig().getString("Fly Enabled Other");
                String withPlaceholders = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders);

                other.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders);

            } else {
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(p));
                other.setAllowFlight(false);

                String withOutPlaceholders5 = plugin.getConfig().getString("Fly Disabled By");
                String withPlaceholders5 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders5);

                // THE MESSAGE THAT THE COMMAND EXECUTOR GETS
                p.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders5);

                String withOutPlaceholders2 = plugin.getConfig().getString("Fly Disabled Other");
                String withPlaceholders2 = PlaceholderAPI.setPlaceholders(p.getPlayer(), withOutPlaceholders2);
                // THE MESSAGE THAT THE PLAYER GETS
                other.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders2);
            }
        }
        return false;
    }
}
