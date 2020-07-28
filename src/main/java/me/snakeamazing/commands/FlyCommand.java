package me.snakeamazing.commands;


import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.SimpleFly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.unnamed.gui.button.SimpleButton;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;
import team.unnamed.gui.menu.MenuBuilder;

public class FlyCommand implements CommandExecutor{

    private SimpleFly plugin;
    public void openFlyGui(Player player){

        MenuBuilder builder = new MenuBuilder(plugin.getConfig().getString("GuiTitle"), 3)
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
                        11,
                        new ItemBuilder(Material.SKULL)
                            .name(plugin.getConfig().getString("FlyOther"))
                            .lore(
                                    new LoreBuilder()
                                        .addLine(plugin.getConfig().getString("FlyOtherLore"))
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
                                        eventPlayer.closeInventory();
                                    } else {
                                        for (Player all : Bukkit.getOnlinePlayers()){
                                            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                            all.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("FlyDisabledEveryone"));
                                            all.setAllowFlight(false);
                                        }
                                        eventPlayer.closeInventory();
                                    }
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
                                    eventPlayer.closeInventory();
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
        player.openInventory(builder.build());
    }

    public FlyCommand(SimpleFly plugin) {

        this.plugin = plugin;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

       if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.YELLOW + "["+ ChatColor.AQUA + "SimpleFly"+ ChatColor.YELLOW + "]" + ChatColor.WHITE + " You must be a player to run this command.");

            return true;
        } else {
           Player p = (Player) sender;
           openFlyGui(p);
       }
        return true;
    }
}
