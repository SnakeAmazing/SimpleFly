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

        sender.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("Reload"));

        try{
            this.plugin.reloadConfig();
            sender.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("ReloadDone"));
            return true;
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "An exception ocurred while reloading the plugin", e);

            sender.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("ReloadFailed"));

            return true;
        }
    }
}
