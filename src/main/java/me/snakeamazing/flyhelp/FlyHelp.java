package me.snakeamazing.flyhelp;

import me.snakeamazing.simplefly.SimpleFly;
import me.snakeamazing.utils.Utils;
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
                    p.sendMessage(Utils.chat("&7&m---------------------------------------------------"));
                    p.sendMessage(Utils.chat("                        &bSimpleFly &eHelp       "));
                    p.sendMessage(Utils.chat("&8· &b/fly &7&m--&r&7> &eEnable or Disable flight mode!"));
                    p.sendMessage(Utils.chat("&8· &b/flyall &7&m--&r&7> &eEnable or Disable flight mode for everyone!"));
                    p.sendMessage(Utils.chat("&8· &b/flyto &7&m--&r&7> &eEnable or Disable flight mode to another player!"));
                    p.sendMessage(Utils.chat("&8· &b/flyreload &7&m--&r&7> &eReload the Config files!"));
                    p.sendMessage(Utils.chat("&7&m---------------------------------------------------"));

                } else {

                    p.sendMessage(Utils.chat(config.getString("Prefix")+ Utils.chat(config.getString("NoPerms"))));
                }
        return true;
    }

}



