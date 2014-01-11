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
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player you = (Player) sender;

		int Cooldown = plugin.getConfig().getInt("cooldown.cooldown");
		int DefaultCooldown = plugin.getConfig().getInt("cooldown.default");

		if ((cmd.getName().equalsIgnoreCase("emotes") && you
				.hasPermission("emotes.command.emotes.use"))) {

			if (args.length == 0) {

				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.AQUA
						+ "Available commands:");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "/emotes");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "/emotes list " + ChatColor.GREEN
						+ "[1|2]");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "/emotes cooldown " + ChatColor.GREEN
						+ "[CHANGE|SHOW|RESET]");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "/emotes cooldown change " + ChatColor.GREEN
						+ "[SECONDS]");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "/emotes version");

				return true;
			}

			else if (args.length == 1) {

				if (args[0].equalsIgnoreCase("list")) {

					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.AQUA + "Available emotes:");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/kiss " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/hug " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/wave " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/glomp " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/wink " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/ekick " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/teabag " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "/argue " + ChatColor.GREEN
							+ "<player>");
					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "Page " + ChatColor.RED
							+ "1" + ChatColor.BLUE + " of " + ChatColor.RED
							+ "2");

					return true;

				}

				else if (args[0].equalsIgnoreCase("cooldown")) {

					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE
							+ "Usage: /emotes cooldown <change|show|reset>");

					return true;

				}

				else if (args[0].equalsIgnoreCase("version")
						&& you.hasPermission("emotes.command.emotes.version")) {

					you.sendMessage(ChatColor.GOLD + "[Emotes] "
							+ ChatColor.BLUE + "Emotes version "
							+ ChatColor.RED
							+ plugin.getDescription().getVersion());

					return true;

				}

				return true;

			}

			else if (args.length == 2) {

				if (args[0].equalsIgnoreCase("list")) {

					if (args[1].equalsIgnoreCase("1")) {

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.AQUA + "Available emotes:");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/kiss " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/hug " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/wave " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/glomp " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/wink " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/ekick " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/teabag " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/argue " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "Page " + ChatColor.RED
								+ "1" + ChatColor.BLUE + " of " + ChatColor.RED
								+ "2");

					}

					else if (args[1].equalsIgnoreCase("2")) {

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.AQUA + "Available emotes:");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/flail");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/grumble "
								+ ChatColor.GREEN + "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/facepalm");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/hate " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/love " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/poke " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/smack " + ChatColor.GREEN
								+ "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "/whistle "
								+ ChatColor.GREEN + "<player>");
						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE + "Page " + ChatColor.RED
								+ "2" + ChatColor.BLUE + " of " + ChatColor.RED
								+ "2");

						return true;

					}

				}

				else if (args[0].equalsIgnoreCase("cooldown")) {

					if (args[1].equalsIgnoreCase("change")
							&& you.hasPermission("emotes.command.emotes.cooldown.change")) {

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE
								+ "Usage: /emotes cooldown change <seconds>");

						return true;

					}

					else if (args[1].equalsIgnoreCase("show")
							&& you.hasPermission("emotes.command.emotes.cooldown.show")) {

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE
								+ "The cooldown for emote usage is" + " "
								+ Cooldown + " seconds!");

						return true;

					}

					else if (args[1].equalsIgnoreCase("reset")
							&& you.hasPermission("emotes.command.emotes.cooldown.default")) {

						plugin.getConfig().set("cooldown.cooldown",
								DefaultCooldown);
						plugin.saveConfig();

						you.sendMessage(ChatColor.GOLD
								+ "[Emotes] "
								+ ChatColor.BLUE
								+ "The cooldown for emote usage is now reset to default!");

						return true;

					}

					return true;

				}

				return true;

			}

			else if (args.length == 3) {

				if (args[0].equalsIgnoreCase("cooldown")) {

					if (args[1].equalsIgnoreCase("change")
							&& you.hasPermission("emotes.command.emotes.cooldown.change")) {

						int NewCooldown = Integer.parseInt(args[2]);

						plugin.getConfig()
								.set("cooldown.cooldown", NewCooldown);
						plugin.saveConfig();

						you.sendMessage(ChatColor.GOLD + "[Emotes] "
								+ ChatColor.BLUE
								+ "The cooldown for emote usage is now" + " "
								+ NewCooldown + " seconds!");

						return true;

					}

					return true;

				}

				return true;

			}

			else if (args.length > 3) {

				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "Too many arguments!");
				you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.BLUE
						+ "Usage: /emotes <list|cooldown>");

				return true;

			}

		}

		else {

			you.sendMessage(ChatColor.GOLD + "[Emotes] " + ChatColor.RED
					+ "You don't have permission");

			return true;

		}

		return true;

	}

}
