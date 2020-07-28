package me.snakeamazing.commands;

import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyAll implements CommandExecutor {

    private SimpleFly plugin;

    public FlyAll(SimpleFly plugin){

        this.plugin = plugin;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.YELLOW + "["+ ChatColor.AQUA + "SimpleFly"+ ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");

            return false;
        }
        Player player = (Player) sender;
        if(!player.getAllowFlight()){
            for (Player all : Bukkit.getOnlinePlayers()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                all.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyEnabledEveryone"));
                all.setAllowFlight(true);
            }

        } else {
            for (Player all : Bukkit.getOnlinePlayers()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                all.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyDisabledEveryone"));
                all.setAllowFlight(false);
            }

        }
        if (!player.hasPermission("simplefly.*") || (!player.hasPermission("simplefly.fly"))){
            player.sendMessage(plugin.getConfig().getString("NoPerms"));
        }

        return true;
    }

}
