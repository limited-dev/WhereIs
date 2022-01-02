package de.limited_dev.whereis.features;

import de.limited_dev.whereis.utils.PlayerStorage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerCacher {

    public static HashMap<String, UUID> playerByName = new HashMap<String, UUID>();
    public static HashMap<UUID, String> playerByUUID = new HashMap<UUID, String>();

    public static void LoadPlayerChache(){
        ConfigurationSection conf = PlayerStorage.config.getConfigurationSection("playerbyname");
        if (conf == null) {
            return;
        }
        for(String s : conf.getKeys(false)){
            playerByName.put(s, UUID.fromString(conf.getString(s)));
        }
        conf = PlayerStorage.config.getConfigurationSection("playerbyuuid");
        if (conf == null) {
            return;
        }
        for(String s: conf.getKeys(false)){
            playerByUUID.put(UUID.fromString(s), conf.getString(s));
        }
    }


    public static void SavePlayerCache(){
        PlayerStorage.config.set("playerbyname", null);
        PlayerStorage.config.set("playerbyuuid", null);
        for(String s : playerByName.keySet()){
            PlayerStorage.config.set("playerbyname." + s, playerByName.get(s).toString());
        }
        for(UUID u : playerByUUID.keySet()){
            PlayerStorage.config.set("playerbyuuid." + u.toString(), playerByUUID.get(u));
        }
        try{
            PlayerStorage.config.save(PlayerStorage.file);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static UUID getUUIDFromName(String name){
        return playerByName.get(name);
    }

    public static String getNameFromUUID(UUID uuid){
        return playerByUUID.get(uuid);
    }

}
