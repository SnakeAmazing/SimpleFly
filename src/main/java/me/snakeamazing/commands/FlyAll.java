package me.snakeamazing.commands;

import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.simplefly.SimpleFly;
import me.snakeamazing.utils.Utils;
import org.bukkit.Bukkit;
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
            sender.sendMessage(" You must be a player to run this command");

            return false;
        }
        Player p = (Player) sender;
        if(!p.getAllowFlight()){
            for (Player all : Bukkit.getOnlinePlayers()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(p));
                all.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix")) + Utils.chat(plugin.getConfig().getString("Fly Enabled Everyone")));
                all.setAllowFlight(true);
            }

        } else {
            for (Player all : Bukkit.getOnlinePlayers()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(p));
                all.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix")) + Utils.chat(plugin.getConfig().getString("Fly Disabled Everyone")));
                all.setAllowFlight(false);
            }

        }
        if (!p.hasPermission("simplefly.*") || (!p.hasPermission("simplefly.fly"))){
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }

        return true;
    }

}
