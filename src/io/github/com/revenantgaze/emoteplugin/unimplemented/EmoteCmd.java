package io.github.com.revenantgaze.emoteplugin.unimplemented;

import java.util.List;

import io.github.com.revenantgaze.emoteplugin.ConfigManager;
import io.github.com.revenantgaze.emoteplugin.Cooldown;
import io.github.com.revenantgaze.emoteplugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class EmoteCmd implements CommandExecutor {

	public Main plugin;

	public EmoteCmd(Main instance) {

		plugin = instance;

	}

	public static ConfigManager cm;

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (cmd.getName().equalsIgnoreCase("emote")) {

			if (sender instanceof Player) {

				Player senderPlayer = (Player) sender;

				if (args.length == 0) {

					List<String> customEmoteList = plugin.getEmoteConfig()
							.getStringList("emotes-list");

					for (String s : customEmoteList) {

						senderPlayer.sendMessage(s);

					}

					return true;

				}

				else if (args.length > 2) {

					if (args[0].equalsIgnoreCase("new")) {

						String newEmoteName = args[1];

						List<String> customEmotes = plugin.getEmoteConfig()
								.getStringList("emotes-list");

						ConfigurationSection newEmoteSection = plugin
								.getEmoteConfig().getConfigurationSection(
										"emotes." + newEmoteName);

						if (newEmoteSection == null) {

							customEmotes.add(newEmoteName);

							plugin.getEmoteConfig().set("emotes-list",
									customEmotes);

							newEmoteSection = plugin.getEmoteConfig()
									.createSection("emotes." + newEmoteName);

							StringBuilder emoteMessageBuilder = new StringBuilder();

							for (int i = 2; i < args.length; i++) {

								emoteMessageBuilder.append(args[i]);
								emoteMessageBuilder.append(" ");

							}

							String emoteMessage = emoteMessageBuilder
									.toString();

							newEmoteSection.set("description", "");
							newEmoteSection.set("usage", "/emote "
									+ newEmoteName + " <target>");
							newEmoteSection.set("permission", "emotes.emote."
									+ newEmoteName);
							newEmoteSection.set("message", emoteMessage);
							newEmoteSection.set("sp-message", "");

							plugin.saveEmoteConfig();

							senderPlayer.sendMessage(ChatColor.AQUA + "Emote "
									+ newEmoteName + " added!");
							senderPlayer
									.sendMessage(ChatColor.AQUA
											+ "Do /emote <name> edit <message> to set the emote message!");
							senderPlayer
									.sendMessage(ChatColor.AQUA
											+ "Use <s> where the sender's name will be shown and \n"
											+ "<t> where the target's name will be shown!");

							return true;

						}

						else {

							senderPlayer.sendMessage(ChatColor.RED
									+ "This command does already exist");

							return true;

						}

					}

					if (args.length == 3) {

						String emoteName = args[1];

						if ((senderPlayer.hasPermission("emotes.emote."
								+ emoteName) || senderPlayer.isOp())) {

							if (args[0].equalsIgnoreCase("use")) {

								String senderName = senderPlayer.getName();
								String targetName = args[2];

								ConfigurationSection customEmoteSection = plugin
										.getEmoteConfig()
										.getConfigurationSection(
												"emotes." + emoteName);

								if (customEmoteSection == null) {

									senderPlayer.sendMessage(ChatColor.RED
											+ "This emote does not exist!");

									return true;

								}

								else {

									Long lastEmote = Cooldown.lastEmote
											.get(senderPlayer.getName());

									int CooldownValue = plugin.getConfig()
											.getInt("cooldown.cooldown");

									if (lastEmote == null
											|| lastEmote
													+ (CooldownValue * 1000) < System
														.currentTimeMillis()) {

										int emotesDistance = plugin.getConfig()
												.getInt("emotes-distance");

										int distanceSquared = emotesDistance
												* emotesDistance;

										World senderWorld = senderPlayer
												.getWorld();

										for (Player p : Bukkit
												.getOnlinePlayers()) {

											World targetWorld = p.getWorld();

											if (senderWorld == targetWorld) {

												if (senderPlayer
														.getLocation()
														.distanceSquared(
																p.getLocation()) < distanceSquared) {

													String emoteMessage = plugin
															.getEmoteConfig()
															.getString(
																	"emotes."
																			+ emoteName
																			+ ".message");
													String emoteMessageWithName = emoteMessage
															.replace("<s>",
																	senderName);
													String emoteMessageWithNames = emoteMessageWithName
															.replace("<t>",
																	targetName);

													p.sendMessage(emoteMessageWithNames);

												}

											}

										}

										Cooldown.lastEmote.put(
												senderPlayer.getName(),
												System.currentTimeMillis());

										return true;

									}

									return true;

								}

							}

						}
						
						else {
							
							String noPermMessage = plugin.getEmoteConfig().getString("no-permission-message");
							
							senderPlayer.sendMessage(ChatColor.RED + noPermMessage);
							
						}

					}

					else if (args.length >= 4) {

						String emoteName = args[1];

						if ((senderPlayer.hasPermission("emotes.emote."
								+ emoteName) || senderPlayer.isOp())) {

							if (args[0].equalsIgnoreCase("use")) {

								ConfigurationSection customEmoteSection = plugin
										.getEmoteConfig()
										.getConfigurationSection(
												"emotes." + emoteName);

								if (customEmoteSection == null) {

									senderPlayer.sendMessage(ChatColor.RED
											+ "This emote does not exist!");

									return true;

								}

								else {

									senderPlayer.sendMessage(ChatColor.RED
											+ "Too many arguments!");
									senderPlayer
											.sendMessage(ChatColor.RED
													+ plugin.getEmoteConfig()
															.getString(
																	"emotes."
																			+ emoteName
																			+ ".usage"));

									return true;

								}

							}

							return true;

						}

					}

					return true;

				}

				else if (args.length == 2) {

					if (args[0].equalsIgnoreCase("use")) {

						String emoteName = args[1];
						String senderName = senderPlayer.getName();

						ConfigurationSection customEmoteSection = plugin
								.getEmoteConfig().getConfigurationSection(
										"emotes." + emoteName);

						if (customEmoteSection == null) {

							senderPlayer.sendMessage(ChatColor.RED
									+ "This emote does not exist!");

							return true;

						}

						else {

							Long lastEmote = Cooldown.lastEmote
									.get(senderPlayer.getName());

							int CooldownValue = plugin.getConfig().getInt(
									"cooldown.cooldown");

							if (lastEmote == null
									|| lastEmote + (CooldownValue * 1000) < System
											.currentTimeMillis()) {

								int emotesDistance = plugin.getConfig().getInt(
										"emotes-distance");

								int distanceSquared = emotesDistance
										* emotesDistance;

								World senderWorld = senderPlayer.getWorld();

								for (Player p : Bukkit.getOnlinePlayers()) {

									World targetWorld = p.getWorld();

									if (senderWorld == targetWorld) {

										if (senderPlayer.getLocation()
												.distanceSquared(
														p.getLocation()) < distanceSquared) {

											String emoteMessage = plugin
													.getEmoteConfig()
													.getString(
															"emotes."
																	+ emoteName
																	+ ".sp-message");
											String emoteMessageWithName = emoteMessage
													.replace("<s>", senderName);

											p.sendMessage(emoteMessageWithName);

										}

									}

								}

								Cooldown.lastEmote.put(senderPlayer.getName(),
										System.currentTimeMillis());

								return true;

							}

							return true;

						}

					}

					else if (args[0].equalsIgnoreCase("new")) {

						String newEmoteName = args[1];

						List<String> customEmotes = plugin.getEmoteConfig()
								.getStringList("emotes-list");

						ConfigurationSection newEmoteSection = plugin
								.getEmoteConfig().getConfigurationSection(
										"emotes." + newEmoteName);

						if (newEmoteSection == null) {

							customEmotes.add(newEmoteName);

							plugin.getEmoteConfig().set("emotes-list",
									customEmotes);

							newEmoteSection = plugin.getEmoteConfig()
									.createSection("emotes." + newEmoteName);
							newEmoteSection.set("description", "");
							newEmoteSection.set("usage", "/emote "
									+ newEmoteName + " <target>");
							newEmoteSection.set("permission", "emotes.emote."
									+ newEmoteName);
							newEmoteSection.set("message", "");
							newEmoteSection.set("sp-message", "");

							plugin.saveEmoteConfig();

							senderPlayer.sendMessage(ChatColor.AQUA + "Emote "
									+ newEmoteName + " added!");
							senderPlayer
									.sendMessage(ChatColor.AQUA
											+ "Do /emote <name> edit <message> to set the emote message!");
							senderPlayer
									.sendMessage(ChatColor.AQUA
											+ "Use <s> where the sender's name will be shown and \n"
											+ "<t> where the target's name will be shown!");

							return true;

						}

						else {

							senderPlayer.sendMessage(ChatColor.RED
									+ "This command does already exist");

							return true;

						}

					}

					return true;

				}

			}

			return true;

		}

		return true;

	}

}
