package me.snakeamazing.menus;

import me.snakeamazing.SimpleFly;
import me.snakeamazing.listeners.FlyEvent;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.button.SimpleButton;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;
import team.unnamed.gui.item.type.SkullBuilder;
import team.unnamed.gui.menu.MenuBuilder;

public class MainMenu implements Menu {

    private SimpleFly plugin;

    public MainMenu(SimpleFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public MenuBuilder create(Player player) {
        return new MenuBuilder(plugin.getConfig().getString("messages.gui.title"), 3)
                .fillItem(
                        new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 9)
                                .name("&7")
                                .lore(
                                        new LoreBuilder()
                                                .addLine("")
                                )
                                .build()
                )
                .fillSlots(0, 26)
                .cancelFill(true)
                .addItem(
                        13,
                        new ItemBuilder(Material.FEATHER)
                                .name(plugin.getConfig().getString("messages.gui.feather"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("messages.gui.feather-lore"))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        15,
                        new ItemBuilder(Material.BEACON)
                                .name(plugin.getConfig().getString("messages.gui.fly-all"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("messages.gui.fly-all-lore"))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        22,
                        new ItemBuilder(Material.BARRIER)
                                .name(plugin.getConfig().getString("messages.gui.exit"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("messages.gui.exit-lore"))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        11,
                        new SkullBuilder(Material.SKULL_ITEM, 1, (byte) 3).offlinePlayer((OfflinePlayer) player)
                                .name(plugin.getConfig().getString("messages.gui.fly-other"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("messages.gui.fly-other-lore"))
                                                .colorize()
                                )
                                .buildSkull()
                )
                .addButton(
                        22,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.closeInventory();
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.NOTE_BASS, 1, 1);
                                    return true;
                                }
                        )
                )
                .addButton(
                        15,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    if (eventPlayer.hasPermission("simplefly.flyall") || eventPlayer.hasPermission("simplefly.*")) {
                                        if (!eventPlayer.getAllowFlight()) {
                                            for (Player all : Bukkit.getOnlinePlayers()) {
                                                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                                all.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-enabled-everyone"));
                                                all.setAllowFlight(true);
                                                all.setFlying(true);
                                                all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
                                                all.sendTitle(plugin.getConfig().getString("messages.titles.fly-all-enabled"), plugin.getConfig().getString("messages.subtitles.fly-all-enabled"));
                                            }
                                        } else {
                                            for (Player all : Bukkit.getOnlinePlayers()) {
                                                Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                                all.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-disabled-everyone"));
                                                all.setAllowFlight(false);
                                                all.setFlying(false);
                                                all.playSound(all.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
                                                all.sendTitle(plugin.getConfig().getString("messages.titles.fly-all-disabled"), plugin.getConfig().getString("messages.subtitles.fly-all-disabled"));
                                            }
                                        }
                                        eventPlayer.closeInventory();
                                        return true;
                                    } else {
                                        eventPlayer.closeInventory();
                                        eventPlayer.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission"));
                                    }
                                    return false;
                                }
                        )
                )
                .addButton(
                        11,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    if (eventPlayer.hasPermission("simplefly.flyother") || eventPlayer.hasPermission("simplefly.*")){
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
                                            .open(eventPlayer);
                                    return true;
                                    } else {
                                        eventPlayer.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.no-permission"));
                                        eventPlayer.closeInventory();
                                    }
                                    return true;
                                }
                        )
                )
                .addButton(
                        13,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    if (!eventPlayer.getAllowFlight()) {
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                        eventPlayer.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-enabled"));
                                        eventPlayer.setAllowFlight(true);
                                        eventPlayer.setFlying(true);
                                        eventPlayer.playSound(eventPlayer.getLocation(), Sound.LEVEL_UP, 1, 1);
                                        eventPlayer.sendTitle(plugin.getConfig().getString("messages.titles.fly-enabled"), plugin.getConfig().getString("messages.subtitles.fly-enabled"));
                                    } else {
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                        eventPlayer.sendMessage(plugin.getConfig().getString("messages.prefix") + plugin.getConfig().getString("messages.commands.fly-disabled"));
                                        eventPlayer.setAllowFlight(false);
                                        eventPlayer.setFlying(false);
                                        eventPlayer.playSound(eventPlayer.getLocation(), Sound.NOTE_BASS_GUITAR, 1, 1);
                                        eventPlayer.sendTitle(plugin.getConfig().getString("messages.titles.fly-disabled"), plugin.getConfig().getString("messages.subtitles.fly-disabled"));
                                    }
                                    eventPlayer.closeInventory();
                                    return true;
                                }
                        )

                );

    }
}

