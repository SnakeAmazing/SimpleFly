package me.snakeamazing.commands;

import me.snakeamazing.simplefly.SimpleFly;
import me.snakeamazing.utils.Utils;
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

        sender.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix") + Utils.chat(plugin.getConfig().getString("Reload"))));

        try{
            this.plugin.reloadConfig();
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix") + Utils.chat(plugin.getConfig().getString("Reload Done"))));
            return true;
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "An exception ocurred while reloading the plugin", e);

            sender.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix") + Utils.chat(plugin.getConfig().getString("Reload Failed"))));

            return true;
        }
    }
}
