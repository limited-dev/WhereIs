package de.limited_dev.whereis.features;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistManager {

    public void setPlayerList(Player player){
        String worldType;
        World world = player.getWorld();
        if(world.isNatural()){ //OVERWORLD
            worldType = ChatColor.GREEN + world.getName();
        } else if (world.hasCeiling()){ //NETHER
            worldType = ChatColor.DARK_RED + world.getName();
        } else{ //END
            worldType = ChatColor.DARK_PURPLE + world.getName();
        }
        player.setPlayerListHeaderFooter(
                "Current World: " + worldType,

                "\nWorld Types:\n" +
                        "" + ChatColor.GREEN + "Overworld" + ChatColor.RESET + " | " + ChatColor.DARK_RED + "Nether" + ChatColor.RESET + " | " + ChatColor.DARK_PURPLE + "End");
    }

    public void setPlayerTeams(Player player){
        Scoreboard scoreboard = player.getScoreboard();
        Team WorldOn;
        for(Player target : Bukkit.getOnlinePlayers()){
            String worldName = target.getWorld().getName();
            WorldOn = scoreboard.getTeam(worldName);
            if(WorldOn == null){
                WorldOn = scoreboard.registerNewTeam(worldName);
            }
            ChatColor WorldColor = getWorldColor(target.getWorld());
            WorldOn.setColor(WorldColor);
            WorldOn.addEntry(target.getName());
        }
    }
    private ChatColor getWorldColor(World world){
        if(world.isNatural()){ //OVERWORLD
            //worldTeam.setPrefix("[" + ChatColor.GREEN + world.getName() + ChatColor.RESET + "] ");
            //worldTeam.setColor(ChatColor.GREEN);
            return ChatColor.GREEN;
        } else if (world.hasCeiling()){ //NETHER
            //worldTeam.setPrefix("[" + ChatColor.DARK_RED + world.getName() + ChatColor.RESET + "] ");
            return ChatColor.DARK_RED;
        } else{ //END
            //worldTeam.setPrefix("[" + ChatColor.DARK_PURPLE + world.getName() + ChatColor.RESET + "] ");
            return ChatColor.DARK_PURPLE;
        }
    }
}
