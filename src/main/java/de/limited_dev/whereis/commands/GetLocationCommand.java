package de.limited_dev.whereis.commands;

import de.limited_dev.whereis.features.LocationSaver;
import de.limited_dev.whereis.features.PlayerCacher;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GetLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sendUsage(sender);
            return false;
        }
        Player wantedPlayer = Bukkit.getPlayer(args[0]);
        if(wantedPlayer == null){
            List<Integer> wantedCorrds = LocationSaver.locationPlayers.get(PlayerCacher.getUUIDFromName(args[0]));
            //System.out.println(wantedCorrds.get(0));// X
            //System.out.println(wantedCorrds.get(1));// Y
            //System.out.println(wantedCorrds.get(2));// Z
            sender.sendMessage("Player " + args[0] + ":\n" +
                    "Current World: " + LocationSaver.locationPlayersWorld.get(PlayerCacher.getUUIDFromName(args[0])) + "\n" +
                    "Coords " + LocationSaver.locationPlayers.get(PlayerCacher.getUUIDFromName(args[0])));
            if(!(sender instanceof Player)){
                return false;
            }
            sendRequest((Player) sender, wantedCorrds.get(0), wantedCorrds.get(1), wantedCorrds.get(2), LocationSaver.locationPlayersWorld.get(PlayerCacher.getUUIDFromName(args[0])));
            return false;
        }
            sender.sendMessage("Player " + args[0] + ":\n" +
                    "Current World: " + wantedPlayer.getWorld().getName() + "\n" +
                    "Coords [" + wantedPlayer.getLocation().getBlockX() + ", " + wantedPlayer.getLocation().getBlockY() + ", " + wantedPlayer.getLocation().getBlockZ() + "]");
            if(!(sender instanceof Player)){
                return false;
            }
        sendRequest((Player) sender, wantedPlayer.getLocation().getBlockX(), wantedPlayer.getLocation().getBlockY(), wantedPlayer.getLocation().getBlockZ(), wantedPlayer.getWorld().getName());

        return false;
    }
    public void sendUsage(CommandSender sender){
        sender.sendMessage("§7Usage:§9 /getlocation <Name Of Player>");
    }
    //player.teleport(Bukkit.getWorld(worldName).getSpawnLocation())
    public void sendRequest(Player player, int x, int y, int z, String worldName) {
        TextComponent messageTp = new TextComponent(net.md_5.bungee.api.ChatColor.GREEN + "[TELEPORT]");
        messageTp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ctp " + worldName + " " + x + " " + y + " " + z));
        player.spigot().sendMessage(messageTp);
    }
}
