package de.limited_dev.whereis.listeners;

import de.limited_dev.whereis.Main;
import de.limited_dev.whereis.features.LocationSaver;
import de.limited_dev.whereis.features.PlayerCacher;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GREEN + "--> " + event.getPlayer().getName() + " joined the game");
        Main.getInstance().getTablistmgr().setPlayerList(player);
        Main.getInstance().getTablistmgr().setPlayerTeams(player);
        PlayerCacher.playerByName.put(player.getName(), player.getUniqueId());
        PlayerCacher.playerByUUID.put(player.getUniqueId(), player.getName());
        PlayerCacher.SavePlayerCache();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.RED + "<-- " + player.getName() + " left the game");
        UUID uuidOfQuitPlayer = player.getUniqueId();
        String WorldOfQuitPlayer = player.getWorld().getName();
        //XYZ
        List<Integer> locationList = new ArrayList<Integer>();
        locationList.add(player.getLocation().getBlockX());
        locationList.add(player.getLocation().getBlockY());
        locationList.add(player.getLocation().getBlockZ());
        LocationSaver.locationPlayers.put(uuidOfQuitPlayer, locationList);
        LocationSaver.locationPlayersWorld.put(uuidOfQuitPlayer, WorldOfQuitPlayer);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        String deathMessage = event.getDeathMessage();
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        Main.getInstance().getTablistmgr().setPlayerList(player);
        Main.getInstance().getTablistmgr().setPlayerTeams(player);
    }
}
