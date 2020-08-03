package me.snakeamazing.commands;

import me.snakeamazing.SimpleFly;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public class ReloadCommand implements CommandExecutor {

    private SimpleFly plugin;

    public ReloadCommand(SimpleFly plugin){
        this.plugin = plugin;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("simplefly.*") || sender.hasPermission("simplefly.reload")) {

            sender.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.reloadmessages.reload"));

            try {
                this.plugin.reloadConfig();
                sender.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.reloadmessages.reload-done"));
                return true;
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "An exception ocurred while reloading the plugin", e);

                sender.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.reloadmessages.reload-failed"));

                return true;
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission")) ;
        }
        return true;
    }
}
