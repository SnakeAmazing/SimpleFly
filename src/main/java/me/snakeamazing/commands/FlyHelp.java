package me.snakeamazing.commands;

import me.snakeamazing.SimpleFly;
import me.snakeamazing.utils.File;
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
                    player.sendMessage(plugin.getConfig().getString("messages.help.help-spacers"));
                    player.sendMessage(plugin.getConfig().getString("messages.help.help-title"));
                    player.sendMessage(plugin.getConfig().getString("messages.help.fly-command"));
                    player.sendMessage(plugin.getConfig().getString("messages.help.fly-other-command"));
                    player.sendMessage(plugin.getConfig().getString("messages.help.fly-all-command"));
                    player.sendMessage(plugin.getConfig().getString("messages.help.reload-command"));
                    player.sendMessage(plugin.getConfig().getString("messages.help.help-spacers"));

                } else {

                    player.sendMessage(config.getString("messages.prefix")+ config.getString("messages.no-permission"));
                }
        return true;
    }

}



