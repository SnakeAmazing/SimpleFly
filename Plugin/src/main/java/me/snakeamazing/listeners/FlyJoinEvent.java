package me.snakeamazing.listeners;

import me.snakeamazing.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlyJoinEvent implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (SimpleFly.joinaction.equalsIgnoreCase("enabled")) {

            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));

            if (player.hasPermission("simplefly.*") || (player.hasPermission("simplefly.fly"))) {
                player.setAllowFlight(true);
                player.setFlying(true);

            } else {
                if (SimpleFly.joinaction.equalsIgnoreCase("disabled")) {
                    Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                    player.setAllowFlight(false);
                    player.setFlying(false);

                }
            }
        }
    }
}
