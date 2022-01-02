package de.limited_dev.whereis.utils;

import de.limited_dev.whereis.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationStorage {

    public static File file;
    public static FileConfiguration config;

    public static void base() {
        Main main = Main.getInstance();
        File dir = new File("./plugins/whereis/");
        if (!dir.exists()) {
            main.getDataFolder().mkdirs();
        }
        file = new File(dir, "location.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
}
