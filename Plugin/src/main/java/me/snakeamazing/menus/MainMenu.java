package me.snakeamazing.menus;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.snakeamazing.SimpleFly;
import me.snakeamazing.listeners.FlyEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.SimplePluginManager;
import team.unnamed.gui.item.DefaultItemClickable;
import team.unnamed.gui.item.lore.LoreBuilder;
import team.unnamed.gui.item.type.DefaultItemBuilder;
import team.unnamed.gui.item.type.ItemBuilder;
import team.unnamed.gui.menu.MenuBuilder;

public class MainMenu implements Menu {

    private SimpleFly plugin;

    public MainMenu(SimpleFly plugin) {
        this.plugin = plugin;
    }

    //ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
    //SkullMeta skullMeta = (SkullMeta) item.getItemMeta();

    @Override
    public MenuBuilder create(Player player) {
        return MenuBuilder.newBuilder(plugin.getConfig().getString("messages.gui.title"), 3)
                .addItem(
                        new DefaultItemClickable(
                            13,
                            ItemBuilder.newBuilder(Material.FEATHER)
                                .name(plugin.getConfig().getString("messages.gui.feather"))
                                .lore(plugin.getConfig().getStringList("messages.gui.feather-lore"))
                                .build(),
                                click -> {
                                    if (!player.getAllowFlight()) {
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                                        player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-enabled"));
                                        player.setAllowFlight(true);
                                        player.setFlying(true);
                                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                        player.sendTitle(plugin.getConfig().getString("messages.titles.fly-enabled"), plugin.getConfig().getString("messages.subtitles.fly-enabled"));
                                    } else {
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                                        player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-disabled"));
                                        player.setAllowFlight(false);
                                        player.setFlying(false);
                                        player.playSound(player.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
                                        player.sendTitle(plugin.getConfig().getString("messages.titles.fly-disabled"), plugin.getConfig().getString("messages.subtitles.fly-disabled"));
                                    }
                                    player.closeInventory();
                                    return true;
                                }))
                .addItem(
                        new DefaultItemClickable(
                                15,
                                ItemBuilder.newBuilder(Material.BEACON)
                                    .name(plugin.getConfig().getString("messages.gui.fly-all"))
                                    .lore(plugin.getConfig().getStringList("messages.gui.fly-all-lore"))
                                    .build(),
                                click -> {
                                    if (player.hasPermission("simplefly.flyall") || player.hasPermission("simplefly.*")) {
                                        if (!player.getAllowFlight()) {
                                            for (Player all : Bukkit.getOnlinePlayers()) {
                                                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                                                all.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-enabled-everyone"));
                                                all.setAllowFlight(true);
                                                all.setFlying(true);
                                                all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
                                                all.sendTitle(plugin.getConfig().getString("messages.titles.fly-all-enabled"), plugin.getConfig().getString("messages.subtitles.fly-all-enabled"));
                                            }
                                        } else {
                                            for (Player all : Bukkit.getOnlinePlayers()) {
                                                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(player));
                                                all.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-disabled-everyone"));
                                                all.setAllowFlight(false);
                                                all.setFlying(false);
                                                all.playSound(all.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
                                                all.sendTitle(plugin.getConfig().getString("messages.titles.fly-all-disabled"), plugin.getConfig().getString("messages.subtitles.fly-all-disabled"));
                                            }
                                        }
                                        player.closeInventory();
                                        return true;
                                    } else {
                                        player.closeInventory();
                                        player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission"));
                                    }
                                    return false;
                                }
                        ))
                .addItem(
                        new DefaultItemClickable(
                                11,
                                ItemBuilder.newBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                                .name(plugin.getConfig().getString("messages.gui.fly-other"))
                                .lore(plugin.getConfig().getStringList("messages.gui.fly-other-lore"))
                                .build(),
                                click -> {
                                    if (player.hasPermission("simplefly.flyother") || player.hasPermission("simplefly.*")){
                                        new AnvilGUI.Builder()
                                                .onComplete((playerAnvil, text) -> {
                                                    if (text.length() > 0) {
                                                            playerAnvil.performCommand("flyto " + text);
                                                            return AnvilGUI.Response.close();
                                                    } else {
                                                        return AnvilGUI.Response.text("Error");
                                                        }
                                                    })
                                                    .text("Insert Player Name")
                                                    .item(new ItemStack(Material.PAPER))
                                                    .plugin(plugin)
                                                    .open(player);
                                            return true;
                                        } else {
                                            player.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission"));
                                            player.closeInventory();
                                        }
                                        return true;
                                    }))
                .addItem(
                        new DefaultItemClickable(
                                22,
                                ItemBuilder.newBuilder(Material.BARRIER)
                                    .name(plugin.getConfig().getString("messages.gui.exit"))
                                    .lore(plugin.getConfig().getStringList("messages.gui.exit-lore"))
                                    .build(),
                                click -> {
                                    player.closeInventory();
                                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                                    return true;
                                }));
    }
}

