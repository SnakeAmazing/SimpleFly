package me.snakeamazing.commands;


import me.snakeamazing.SimpleFly;
import me.snakeamazing.menus.MainMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor{

    private SimpleFly plugin;

    public FlyCommand(SimpleFly plugin) {

        this.plugin = plugin;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

       if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.YELLOW + "["+ ChatColor.AQUA + "SimpleFly"+ ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");

            return true;
        } else {
           Player player = (Player) sender;
           player.openInventory(new MainMenu(plugin).create(player).build());
        }
        return true;
    }
}
