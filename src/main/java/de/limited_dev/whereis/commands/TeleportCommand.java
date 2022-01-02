package de.limited_dev.whereis.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ///tp world x y z
        ///cmd 0 1 2 3
        //TODO Improve this pos
        if(!(args.length == 4)){
            sendUsage(sender);
            return false;
        }
        if(!(sender instanceof Player)){
            return false;
        }
        String worldName = args[0];
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int z = Integer.parseInt(args[3]);
        Player player = (Player) sender;
        Location loc = new Location(Bukkit.getWorld(worldName), x, y, z, 0, 0);
        player.teleport(loc);

        return false;
    }
    public void sendUsage(CommandSender sender){
        sender.sendMessage("§7Usage:§9 /ctp <world name> <x> <y> <z>");
    }
}
