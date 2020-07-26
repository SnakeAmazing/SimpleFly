package me.snakeamazing.flycommand;


import me.snakeamazing.listeners.FlyEvent;
import me.snakeamazing.simplefly.SimpleFly;
import me.snakeamazing.utils.Utils;
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

        MenuBuilder builder = new MenuBuilder(Utils.chat(plugin.getConfig().getString("Gui Title")), 3)
                .fillItem(
                        new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE, 1)
                                .name(Utils.chat("&7"))
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
                                .name(Utils.chat(Utils.chat(plugin.getConfig().getString("Feather"))))
                                .lore(
                                        new LoreBuilder()
                                                .addLine(Utils.chat(plugin.getConfig().getString("Feather Lore")))
                                                .colorize()
                                )
                                .build()
                )
                .addItem(
                        11,
                        new ItemBuilder(Material.PLAYER_HEAD)
                            .name(Utils.chat(plugin.getConfig().getString("Fly Other")))
                            .lore(
                                    new LoreBuilder()
                                        .addLine(Utils.chat(plugin.getConfig().getString("Fly Other Lore")))
                                        .colorize()
                            )
                        .build()
                )
                .addItem(
                        15,
                        new ItemBuilder(Material.BEACON)
                        .name(Utils.chat(plugin.getConfig().getString("Fly All")))
                        .lore(
                                new LoreBuilder()
                                .addLine(Utils.chat(plugin.getConfig().getString("Fly All Lore")))
                                .colorize()
                        )
                        .build()
                )
                .addButton(
                        15,
                        new SimpleButton(
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);

                                    if(!eventPlayer.getAllowFlight()){
                                        for (Player all : Bukkit.getOnlinePlayers()){
                                            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                            all.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix")) + Utils.chat(plugin.getConfig().getString("Fly Enabled Everyone")));
                                            all.setAllowFlight(true);
                                        }

                                    } else {
                                        for (Player all : Bukkit.getOnlinePlayers()){
                                            Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                            all.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix")) + Utils.chat(plugin.getConfig().getString("Fly Disabled Everyone")));
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
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    eventPlayer.sendMessage(Utils.chat("&aPer ara funciona :D"));
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
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                    if (!eventPlayer.getAllowFlight()){
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                        eventPlayer.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix")) + Utils.chat(plugin.getConfig().getString("Fly Enabled")));
                                        eventPlayer.setAllowFlight(true);
                                    } else {
                                        Bukkit.getServer().getPluginManager().callEvent(new FlyEvent(eventPlayer));
                                        eventPlayer.sendMessage(Utils.chat(plugin.getConfig().getString("Prefix")) + Utils.chat(plugin.getConfig().getString("Fly Disabled")));
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
