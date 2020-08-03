package me.snakeamazing;

import me.snakeamazing.commands.FlyAll;
import me.snakeamazing.commands.FlyCommand;
import me.snakeamazing.commands.FlyHelp;
import me.snakeamazing.commands.FlyOther;
import me.snakeamazing.listeners.FlyJoinEvent;
import me.snakeamazing.commands.ReloadCommand;
import me.snakeamazing.utils.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.gui.listeners.MenuListeners;

public class SimpleFly extends JavaPlugin{

    PluginDescriptionFile file = getDescription();
    public String version = "(" + file.getVersion() + ")";
    public String nombre = ChatColor.YELLOW + "[" + ChatColor.AQUA + file.getName() + ChatColor.YELLOW + "]";

    public static String joinaction;
    private final File config = new File(this, "config");
    public void onEnable(){
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.WHITE +" Ha sido activado correctamente!" + version);
        registerCommands();
        registerEvents();


        joinaction = getConfig().getString("Player join, fly is enabled/disabled/none");

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            Bukkit.getConsoleSender().sendMessage(nombre + " PlaceholderAPI found, hooking into it.");

        } else {
            throw new RuntimeException("Couldn't find PlaceholderAPI. Plugin can not work without it");
        }

    }

    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(nombre + " Ha sido desactivado correctamente");
    }

    public void registerCommands(){
        this.getCommand("simplefly").setExecutor(new FlyHelp(this));
        this.getCommand("flyall").setExecutor(new FlyAll(this));
        this.getCommand("flyother").setExecutor(new FlyOther(this));
        this.getCommand("fly reload").setExecutor(new ReloadCommand(this));
        this.getCommand("fly").setExecutor(new FlyCommand(this));

    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new FlyJoinEvent(), this);
        pm.registerEvents(new MenuListeners(), this);
    }
    public File getConfig(){
        return config;

    }

}
