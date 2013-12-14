package io.github.com.revenantgaze.emoteplugin.commands;

import io.github.com.revenantgaze.emoteplugin.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmotesCmd implements CommandExecutor {
	
	public Main plugin;
	
	public EmotesCmd(Main instance) {
		
		plugin = instance;
	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		
		Player you = (Player) sender;
		
		int Cooldown = plugin.getConfig().getInt("cooldown");
		int DefaultCooldown = plugin.getConfig().getInt("default-cooldown");
		
		if ((cmd.getName().equalsIgnoreCase("emotes") && you.hasPermission("emotes.command.emotes.use"))) {
			
			if (args.length == 0) {
				
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Usage: /emotes <list|cooldown>");
				
				return true;
			}
			
			else if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("list")) {
		
						you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Available commands: /kiss, /hug, /wave, /glomp, /wink, /ekick, /teabag, /argue, /flail, /grumble.");
						you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Usage: /<emote> <player> or /<emote>");
				
					return true;
					
				}
				
				else if (args[0].equalsIgnoreCase("cooldown")) {
					
						you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Usage: /emotes cooldown <change|show|reset>");
					
					return true;
					
				}
				
				return true;
				
			}
			
			if (args.length == 2) {
				
				if (args[0].equalsIgnoreCase("cooldown")) {
					
					if (args[1].equalsIgnoreCase("change")) {
				
							you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Usage: /emotes cooldown change <seconds>");
				
						return true;
						
					}
					
					else if (args[1].equalsIgnoreCase("show")) {
					
							you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "The cooldown for emote usage is" + " " + Cooldown + " seconds!");
					
						return true;
						
					}
					
					
					else if (args[1].equalsIgnoreCase("reset")) {		
						
							plugin.getConfig().set("cooldown", DefaultCooldown);
							plugin.saveConfig();
						
							you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "The cooldown for emote usage is now reset to default!");
						
						return true;
						
					}
					
					return true;
					
				}
				
				return true;
				
			}	
			
			else if (args.length == 3) {
				
				if (args[0].equalsIgnoreCase("cooldown")) {
					
					if (args[1].equalsIgnoreCase("change")) {
				
							int NewCooldown = Integer.parseInt(args[2]);
				
							plugin.getConfig().set("cooldown", NewCooldown);
							plugin.saveConfig();
				
							you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "The cooldown for emote usage is now" + " " + NewCooldown + " seconds!");
				
						return true;
					
					}
					
					return true;
					
				}
				
				return true;
				
			}				
			
			if (args.length > 3) {
				
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Too many arguments!");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE + "Usage: /emotes <list|cooldown>");
				
				return true;
				
			}

		}
		
		else {
			
			you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "You don't have permission");
			
			return true;
			
		}
		
		return true;
		
	}

}
