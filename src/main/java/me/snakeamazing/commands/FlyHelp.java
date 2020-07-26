package me.snakeamazing.commands;

import me.snakeamazing.SimpleFly;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FlyHelp implements CommandExecutor {

    private SimpleFly plugin;

    public FlyHelp(SimpleFly plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        FileConfiguration config = plugin.getConfig();
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatColor.YELLOW + "["+ ChatColor.AQUA + "SimpleFly"+ ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");

                return false;
            }

                Player p = (Player) sender;
                if (p.hasPermission("simplefly.*") || (p.hasPermission("simplefly.fly"))) {
                    p.sendMessage(plugin.getConfig().getString("Help Spacers"));
                    p.sendMessage(plugin.getConfig().getString("Help Title"));
                    p.sendMessage(plugin.getConfig().getString("Fly Command"));
                    p.sendMessage(plugin.getConfig().getString("FlyOther Command"));
                    p.sendMessage(plugin.getConfig().getString("FlyAll Command"));
                    p.sendMessage(plugin.getConfig().getString("Reload Command"));
                    p.sendMessage(plugin.getConfig().getString("Help Spacers"));

                } else {

                    p.sendMessage(config.getString("Prefix")+ config.getString("NoPerms"));
                }
        return true;
    }

}



