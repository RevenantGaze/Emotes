package io.github.com.revenantgaze.emoteplugin.commands;

import io.github.com.revenantgaze.emoteplugin.Cooldown;
import io.github.com.revenantgaze.emoteplugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
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
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		int CooldownValue = plugin.getConfig().getInt("cooldown.cooldown");

		if (cmd.getName().equalsIgnoreCase("whistle")) {

			if (sender instanceof Player) {

				Player you = (Player) sender;

				Long lastEmote = Cooldown.lastEmote.get(you.getName());

				int emotesDistance = plugin.getConfig().getInt(
						"emotes-distance");

				int distanceSquared = emotesDistance * emotesDistance;

				if (lastEmote == null
						|| lastEmote + (CooldownValue * 1000) < System
								.currentTimeMillis()) {

					if (args.length == 1
							&& (Bukkit.getPlayerExact(args[0]) != null)) {

						Player target = sender.getServer().getPlayer(args[0]);

						String senderName = you.getName();
						String targetName = target.getName();

						World senderWorld = you.getWorld();

						for (Player p : Bukkit.getOnlinePlayers()) {

							World targetWorld = p.getWorld();

							if (senderWorld == targetWorld) {

								if (you.getLocation().distanceSquared(
										p.getLocation()) < distanceSquared) {

									p.sendMessage(ChatColor.GREEN + senderName
											+ " whistles at " + targetName
											+ "!");

								}

							}

						}

					}

					else if (args.length == 0) {

						String senderName = you.getName();

						World senderWorld = you.getWorld();

						for (Player p : Bukkit.getOnlinePlayers()) {

							World targetWorld = p.getWorld();

							if (senderWorld == targetWorld) {

								if (you.getLocation().distanceSquared(
										p.getLocation()) < distanceSquared) {

									p.sendMessage(ChatColor.GREEN + senderName
											+ " whistles a tune!");

								}

							}

						}

					}

					else if (args.length > 1) {

						you.sendMessage(ChatColor.RED + "Too many arguments!");
						you.sendMessage(ChatColor.RED
								+ "Usage: /whistle <player>");

						return true;

					}

					else {

						you.sendMessage(ChatColor.RED
								+ "This player is not online!");

						return true;

					}

					Cooldown.lastEmote.put(you.getName(),
							System.currentTimeMillis());

					return true;

				}

				else {

					you.sendMessage(ChatColor.RED
							+ "You have "
							+ (CooldownValue - ((System.currentTimeMillis() - (Cooldown.lastEmote
									.get(you.getName()))) / 1000))
							+ " seconds left before you can use another emote.");

					return true;

				}

			}

			else {

				sender.sendMessage(ChatColor.RED
						+ "You can't use emotes from the console!");

				return true;

			}

		}

		return false;

	}

}