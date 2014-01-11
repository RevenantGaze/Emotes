package io.github.com.revenantgaze.emoteplugin.commands;

import io.github.com.revenantgaze.emoteplugin.Main;
import io.github.com.revenantgaze.emoteplugin.cooldowns.Cooldown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgueCmd implements CommandExecutor {

	public Main plugin;

	public ArgueCmd(Main instance) {

		plugin = instance;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		int CooldownValue = plugin.getConfig().getInt("cooldown.cooldown");

		if (sender instanceof Player) {

			if (cmd.getName().equalsIgnoreCase("argue")) {

				Player you = (Player) sender;

				Long lastEmote = Cooldown.lastEmote.get(you.getName());

				if (lastEmote == null
						|| lastEmote + (CooldownValue * 1000) < System
								.currentTimeMillis()) {

					if ((args.length == 1)
							&& (Bukkit.getPlayerExact(args[0]) != null)) {

						Player target = sender.getServer().getPlayer(args[0]);

						String user1 = you.getName();
						String user2 = target.getName();

						Bukkit.getServer()
								.broadcastMessage(
										ChatColor.GOLD + "[Emotes] "
												+ ChatColor.GREEN + user1
												+ " argues with " + user2 + "!");

					}

					else if (args.length == 0) {

						String user1 = you.getName();

						Bukkit.getServer().broadcastMessage(
								ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN
										+ user1
										+ " argues incoherently into the air!");

					}

					else if (args.length > 1) {
	
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.RED + "Too many arguments!");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.RED + "Usage: /argue <player>");
	
						return true;
	
					}

					else {

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.RED + "This player is not online!");

						return true;

					}

					Cooldown.lastEmote.put(you.getName(),
							System.currentTimeMillis());

					return true;

				}

				else {

					you.sendMessage(ChatColor.GOLD
							+ "[Emotes] "
							+ ChatColor.RED
							+ "You have "
							+ (CooldownValue - ((System.currentTimeMillis() - (Cooldown.lastEmote
									.get(you.getName()))) / 1000))
							+ " seconds left.");

					return true;

				}

			}

			return true;

		}

		else {

			sender.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED
					+ "You can't use emotes from the console!");

			return true;

		}

	}

}