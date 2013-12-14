package io.github.com.revenantgaze.emoteplugin.commands;

import io.github.com.revenantgaze.emoteplugin.Main;
import io.github.com.revenantgaze.emoteplugin.cooldowns.Cooldown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhistleCmd implements CommandExecutor {
	
	public Main plugin;
	
	public WhistleCmd(Main instance) {
		
		plugin = instance;
	
	}
	
 	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
 		
		int CooldownValue = plugin.getConfig().getInt("Cooldown");
		
		if (cmd.getName().equalsIgnoreCase("whistle")) {
			
			Player you = (Player) sender;
			
			if (Cooldown.tryCooldown(you, "MagicHand", (CooldownValue * 1000))) {
	            	
	            if (args.length == 1 && (Bukkit.getPlayerExact(args[0]) != null)){
	            	  	
	    			Player target = sender.getServer().getPlayer(args[0]);
	    						
	    		    String user1 = you.getName();
	    		    String user2 = target.getName();
	    								
	    		    	Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN + user1 + " whistles at " + user2 + "!");
	    						
	    		        return true;	
	    						
	    		}	
	            	
	            else if (args.length == 0) {
	    				
	    			String user1 = you.getName();	
	    				
	    		        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN + user1 + " whistles a tune!");    		        
	    			
	    		        return true;
	    					
	    		            
	    		}
	            	
	            else {
	    				
					you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "This player is not online!");
			
					return true;
		
	            }
	            
	        }
			
			else {
				
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "You have " + (Cooldown.getCooldown(you, "MagicHand") / 1000) + " seconds left.");
			
				return true;
				
			}
		
		}
				
		else {
			
			sender.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "You don't have permission!");
			
			return true;
			
		}

	}
	
}