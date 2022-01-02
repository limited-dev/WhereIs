package de.limited_dev.whereis;

import de.limited_dev.whereis.commands.GetLocationCommand;
import de.limited_dev.whereis.commands.TeleportCommand;
import de.limited_dev.whereis.features.LocationSaver;
import de.limited_dev.whereis.features.PlayerCacher;
import de.limited_dev.whereis.features.TablistManager;
import de.limited_dev.whereis.listeners.PlayerListeners;
import de.limited_dev.whereis.utils.LocationStorage;
import de.limited_dev.whereis.utils.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private TablistManager tablistmgr;

    private LocationStorage locationStorage;
    private PlayerStorage playerStorage;

    @Override
    public void onLoad(){
        // Plugin onload logic
        instance = this;
        locationStorage = new LocationStorage();
        playerStorage = new PlayerStorage();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager mgr = Bukkit.getPluginManager();
        mgr.registerEvents(new PlayerListeners(), this);

        tablistmgr = new TablistManager();

        getCommand("getlocation").setExecutor(new GetLocationCommand());
        getCommand("ctp").setExecutor(new TeleportCommand());

        LocationStorage.base();
        LocationSaver.LoadLocation();

        PlayerStorage.base();
        PlayerCacher.LoadPlayerChache();

        System.out.println("Loaded WhereIs");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LocationSaver.SaveLocation();
        PlayerCacher.SavePlayerCache();
    }

    public static Main getInstance() {
        return instance;
    }

    public LocationStorage getLocationStorage() {
        return locationStorage;
    }

    public TablistManager getTablistmgr() {
        return tablistmgr;
    }

    public PlayerStorage getPlayerStorage() {
        return playerStorage;
    }
}
