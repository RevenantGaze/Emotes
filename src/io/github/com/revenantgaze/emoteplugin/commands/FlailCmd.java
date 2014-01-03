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
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		int CooldownValue = plugin.getConfig().getInt("cooldown.cooldown");

		if (cmd.getName().equalsIgnoreCase("flail")) {

			if (sender instanceof Player) {

				Player you = (Player) sender;

				Long lastEmote = Cooldown.lastEmote.get(you.getName());

				if (lastEmote == null
						|| lastEmote + (CooldownValue * 1000) < System
								.currentTimeMillis()) {

					if ((args.length == 0)) {

						String user1 = you.getName();

						Bukkit.getServer().broadcastMessage(
								ChatColor.GOLD + "[Emotes] " + ChatColor.GREEN
										+ user1 + " flails!");

						return true;
						
					}

					else if (args.length < 0) {

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.RED + "Usage: /<emote>");

						return true;

					}

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

			else {

				sender.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED
						+ "You can't use emotes from the console!");

				return true;

			}

		}

		return false;

	}

}