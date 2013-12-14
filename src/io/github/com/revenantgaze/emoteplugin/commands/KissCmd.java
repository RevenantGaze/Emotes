package io.github.com.revenantgaze.emoteplugin.commands;

import java.util.HashMap;

import io.github.com.revenantgaze.emoteplugin.Main;
import io.github.com.revenantgaze.emoteplugin.cooldowns.Cooldown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class KissCmd implements CommandExecutor {
	
	public Main plugin;
	
	public KissCmd(Main instance) {
		
		plugin = instance;
	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		
		int CooldownValue = plugin.getConfig().getInt("Cooldown");
	            
	        if (cmd.getName().equalsIgnoreCase("kiss")) {
	        	
	        	if (sender instanceof Player) {
	            	
	        	Player you = (Player) sender;
	            	
	            	if (Cooldown.tryCooldown(you, "MagicHand", (CooldownValue * 1000))) {
		        	
						if ((args.length == 1) && (Bukkit.getPlayerExact(args[0]) != null)) {
						
							Player target = sender.getServer().getPlayer(args[0]);
							
							String user1 = you.getName();
							String user2 = target.getName();
									
								Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN + user1 + " kisses " + user2 + "! N'taww!");
								
								return true;
						}
						
						else if (args.length == 0) {
						
							String user1 = you.getName();
						
								Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN + user1 + " blows a kiss into the air! Who is able to catch it?");
							
								return true;
						}
							
						else if (args.length > 1) {
								
							you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "Too many arguments!");
							
							return false;
								
						}
					
						else {
						
							you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "This player is not online!");
							
							return true;
								
						}
							
					}
			        
	        	}
	        	
	        	else {
	    			
		        	sender.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "You can't use emotes from the console!");
				
		        	return true;
				
		        }
	        	
	        }
		
		return false;
		
	}
	
}