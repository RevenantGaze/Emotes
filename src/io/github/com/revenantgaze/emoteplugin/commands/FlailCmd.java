package io.github.com.revenantgaze.emoteplugin.commands;

import io.github.com.revenantgaze.emoteplugin.Main;
import io.github.com.revenantgaze.emoteplugin.cooldowns.Cooldown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlailCmd implements CommandExecutor {
	
	public Main plugin;
	
	public FlailCmd(Main instance) {
		
		plugin = instance;
	
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		
		int CooldownValue = plugin.getConfig().getInt("Cooldown");
		
		if (cmd.getName().equalsIgnoreCase("flail")) {
		
			Player you = (Player) sender;
			
			if (Cooldown.tryCooldown(you, "MagicHand", (CooldownValue * 1000))) {
		
				if ((args.length == 0)){
				
					String user1 = you.getName();
						
						Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN + user1 + " flails!");
					
						return true;
				}
		
				else if (args.length < 0){
			
					you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "Usage: /<emote>");
					
					return true;
			
				}
			
			}
			
			else {
				
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "You have " + (Cooldown.getCooldown(you, "MagicHand") / 1000) + " seconds left.");
			
				return true;
				
			}
			
		}
		
		else {
			
			sender.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED + "You don't have permisison");
			
			return true;
			
		}
		
		return true;
		
	}
	
}