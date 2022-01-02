package de.limited_dev.whereis.features;

import de.limited_dev.whereis.utils.LocationStorage;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LocationSaver {

    public static HashMap<UUID, List<Integer>> locationPlayers = new HashMap<UUID, List<Integer>>();
    public static HashMap<UUID, String> locationPlayersWorld = new HashMap<UUID, String>();

    public static void LoadLocation(){
        ConfigurationSection conf = LocationStorage.config.getConfigurationSection("users");
        if(conf == null){
            return;
        }
        for(String s : conf.getKeys(false)){
            locationPlayers.put(UUID.fromString(s), conf.getIntegerList(s));
        }
        conf = LocationStorage.config.getConfigurationSection("worlds");
        if(conf == null){
            return;
        }
        for(String s : conf.getKeys(false)){
            locationPlayersWorld.put(UUID.fromString(s), conf.getString(s));
        }
    }

    public static void SaveLocation(){
        LocationStorage.config.set("users", null);
        LocationStorage.config.set("worlds", null);
        for(UUID u : locationPlayers.keySet()) {
            LocationStorage.config.set("users." + u.toString(), locationPlayers.get(u));
        }
        for(UUID u : locationPlayersWorld.keySet()){
            LocationStorage.config.set("worlds." + u.toString(), locationPlayersWorld.get(u));
        }
        try{
            LocationStorage.config.save(LocationStorage.file);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
