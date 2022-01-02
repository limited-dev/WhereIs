package de.limited_dev.whereis;

import de.limited_dev.whereis.listeners.PlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onLoad(){
        // Plugin onload logic
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager mgr = Bukkit.getPluginManager();
        mgr.registerEvents(new PlayerListeners(), this);

        System.out.println("Loaded WhereIs");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
}
