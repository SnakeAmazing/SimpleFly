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

                Player player = (Player) sender;
                if (player.hasPermission("simplefly.*") || (player.hasPermission("simplefly.fly"))) {
                    player.sendMessage(plugin.getConfig().getString("HelpSpacers"));
                    player.sendMessage(plugin.getConfig().getString("HelpTitle"));
                    player.sendMessage(plugin.getConfig().getString("FlyCommand"));
                    player.sendMessage(plugin.getConfig().getString("FlyOtherCommand"));
                    player.sendMessage(plugin.getConfig().getString("FlyAllCommand"));
                    player.sendMessage(plugin.getConfig().getString("ReloadCommand"));
                    player.sendMessage(plugin.getConfig().getString("HelpSpacers"));

                } else {

                    player.sendMessage(config.getString("Prefix")+ config.getString("NoPerms"));
                }
        return true;
    }

}



