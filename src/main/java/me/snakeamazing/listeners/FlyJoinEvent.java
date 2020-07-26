package me.snakeamazing.listeners;

import me.snakeamazing.simplefly.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlyJoinEvent implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (SimpleFly.joinaction.equalsIgnoreCase("enabled")) {

            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(p));

            if (p.hasPermission("simplefly.*") || (p.hasPermission("simplefly.fly"))) {
                p.setAllowFlight(true);

            } else {
                if (SimpleFly.joinaction.equalsIgnoreCase("disabled")) {
                    Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(p));
                    p.setAllowFlight(false);

                }
            }
        }
    }
}
