package me.snakeamazing.menus;

import me.snakeamazing.SimpleFly;
import me.snakeamazing.listeners.FlyEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.button.SimpleButton;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;
import team.unnamed.gui.item.type.SkullBuilder;
import team.unnamed.gui.menu.MenuBuilder;

public class MainMenu implements Menu{

    private SimpleFly plugin;

    public MainMenu(SimpleFly plugin){
        this.plugin = plugin;
    }

    @Override
    public MenuBuilder create(Player player) {
        return new MenuBuilder(plugin.getConfig().getString("GuiTitle"), 3)
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
                                .name(plugin.getConfig().getString("Feather"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("FeatherLore"))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        15,
                        new ItemBuilder(Material.BEACON)
                                .name(plugin.getConfig().getString("FlyAll"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("FlyAllLore"))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        22,
                        new ItemBuilder(Material.BARRIER)
                                .name(plugin.getConfig().getString("Exit"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("ExitLore"))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        13,
                        new SkullBuilder(Material.SKULL_ITEM, 1, (byte) 3).offlinePlayer(player)
                               // .name(plugin.getConfig().getString("FlyOther"))
                              //  .lore(
                              //          new LoreBuilder()
                                //                .addLine(plugin.getConfig().getString("FlyOtherLore"))
                              //                  .colorize()
                              //  )
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
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);

                                    if(!eventPlayer.getAllowFlight()){
                                        for (Player all : Bukkit.getOnlinePlayers()){
                                            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                            all.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyEnabledEveryone"));
                                            all.setAllowFlight(true);
                                        }
                                    } else {
                                        for (Player all : Bukkit.getOnlinePlayers()){
                                            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                            all.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyDisabledEveryone"));
                                            all.setAllowFlight(false);
                                        }
                                    }
                                    eventPlayer.closeInventory();
                                    return true;
                                }
                        )
                )
                .addButton(
                        11,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    eventPlayer.openInventory(new FlyOtherMenu().create(player).build());
                                    return true;
                                }
                        )
                )
                .addButton(
                        13,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    if (!eventPlayer.getAllowFlight()){
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                        eventPlayer.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyEnabled"));
                                        eventPlayer.setAllowFlight(true);
                                    } else {
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                        eventPlayer.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyDisabled"));
                                        eventPlayer.setAllowFlight(false);
                                    }
                                    eventPlayer.closeInventory();
                                    return true;
                                }
                        )

                );

    }

public class FlyOtherMenu implements Menu{

    @Override
    public MenuBuilder create(Player player) {
        return new MenuBuilder(plugin.getConfig().getString("GuiTitle"), 6)
                .addItem(
                        53,
                        new ItemBuilder(Material.ANVIL)
                                .name("&eSearch for a player")
                                .lore(
                                        new LoreBuilder()
                                                .addLine("&7Click to filter")
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        49,
                        new ItemBuilder(Material.BARRIER)
                                .name(plugin.getConfig().getString("Exit"))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(plugin.getConfig().getString("ExitLore"))
                                                .colorize()
                                )
                                .build()
                )
                .addButton(
                        53,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.sendMessage("test");
                                    return true;
                                }
                        )
                )
                .addButton(
                        49,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.closeInventory();
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.NOTE_BASS, 1, 1);
                                    return true;
                                }
                        )
                );
        }
    }
}

