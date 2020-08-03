package me.snakeamazing.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.snakeamazing.SimpleFly;
import me.snakeamazing.listeners.FlyEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FlyOther implements CommandExecutor {

    private SimpleFly plugin;

    public FlyOther(SimpleFly plugin){
        this.plugin = plugin;
    }



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0){
            sender.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.specify-player"));
            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.YELLOW + "["+ ChatColor.AQUA + "SimpleFly"+ ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");
            return false;
        }

        Player other = Bukkit.getPlayer(args[0]);
        Player player = (Player) sender;



            if (other == null){
                player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.player-offline").replace("%target%", args[0]));
                return true;
            }
        if (player.hasPermission("simplefly.*") || player.hasPermission("simplefly.flyother")){
            if (!other.getAllowFlight()){
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                other.setAllowFlight(true);

                player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-enabled-other").replace("%target%", args[0]));

                String withOutPlaceholders2 = plugin.getConfig().getString("messages.commands.fly-enabled-by");
                String withPlaceholders2 = PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), withOutPlaceholders2);

                other.sendMessage(plugin.getConfig().getString("messages.prefix") + withPlaceholders2);

                other.sendTitle(plugin.getConfig().getString("messages.titles.fly-other-enabled").replace("%player_name%", player.getName()), plugin.getConfig().getString("messages.subtitles.fly-other-enabled").replace("%player_name%", player.getName()));

            } else {
                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                other.setAllowFlight(false);
                // THE MESSAGE THAT THE COMMAND EXECUTOR GETS
                player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-disabled-other").replace("%target%", args[0]));

                String withOutPlaceholders3 = plugin.getConfig().getString("messages.commands.fly-disabled-by");
                String withPlaceholders3 = PlaceholderAPI.setPlaceholders(player.getPlayer(), withOutPlaceholders3);
                // THE MESSAGE THAT THE PLAYER GETS
                other.sendMessage(plugin.getConfig().getString("messages.prefix") + withPlaceholders3);

                other.sendTitle(plugin.getConfig().getString("messages.titles.fly-other-disabled").replace("%player_name%", player.getName()), plugin.getConfig().getString("messages.subtitles.fly-other-disabled").replace("%player_name%", player.getName()));
            }
        } else {
            player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission"));
        }
        return false;
    }
}
