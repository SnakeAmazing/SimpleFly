package me.snakeamazing.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + "You must specify a Player");
            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.YELLOW + "["+ ChatColor.AQUA + "SimpleFly"+ ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");
            return false;
        }

        Player other = Bukkit.getPlayer(args[0]);
        Player player = (Player) sender;

        if (player.hasPermission("simplefly.*") || (player.hasPermission("simplefly.fly.other"))){

            if (other == null){
                String withOutPlaceholders3 = plugin.getConfig().getString("PlayerOffline");
                String withPlaceholders3 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders3);
                player.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders3);
                return true;
            }
            if (!other.getAllowFlight()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                other.setAllowFlight(true);

                String withOutPlaceholders4 = plugin.getConfig().getString("FlyEnabledBy");
                String withPlaceholders4 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders4);

                player.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders4);

                String withOutPlaceholders = plugin.getConfig().getString("FlyEnabledOther");
                String withPlaceholders = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders);

                other.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders);

            } else {
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                other.setAllowFlight(false);

                String withOutPlaceholders5 = plugin.getConfig().getString("FlyDisabledBy");
                String withPlaceholders5 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders5);

                // THE MESSAGE THAT THE COMMAND EXECUTOR GETS
                player.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders5);

                String withOutPlaceholders2 = plugin.getConfig().getString("FlyDisabledOther");
                String withPlaceholders2 = PlaceholderAPI.setPlaceholders(player.getPlayer(), withOutPlaceholders2);
                // THE MESSAGE THAT THE PLAYER GETS
                other.sendMessage(plugin.getConfig().getString("Prefix") + withPlaceholders2);
            }
        }
        return false;
    }
}
