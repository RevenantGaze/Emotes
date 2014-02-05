package io.github.com.revenantgaze.emoteplugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.com.revenantgaze.emoteplugin.commands.ArgueCmd;
import io.github.com.revenantgaze.emoteplugin.commands.EmotesCmd;
import io.github.com.revenantgaze.emoteplugin.commands.FacepalmCmd;
import io.github.com.revenantgaze.emoteplugin.commands.FlailCmd;
import io.github.com.revenantgaze.emoteplugin.commands.GlompCmd;
import io.github.com.revenantgaze.emoteplugin.commands.GrumbleCmd;
import io.github.com.revenantgaze.emoteplugin.commands.HateCmd;
import io.github.com.revenantgaze.emoteplugin.commands.HugCmd;
import io.github.com.revenantgaze.emoteplugin.commands.KickCmd;
import io.github.com.revenantgaze.emoteplugin.commands.KissCmd;
import io.github.com.revenantgaze.emoteplugin.commands.LoveCmd;
import io.github.com.revenantgaze.emoteplugin.commands.PokeCmd;
import io.github.com.revenantgaze.emoteplugin.commands.SmackCmd;
import io.github.com.revenantgaze.emoteplugin.commands.TeabagCmd;
import io.github.com.revenantgaze.emoteplugin.commands.WaveCmd;
import io.github.com.revenantgaze.emoteplugin.commands.WhistleCmd;
import io.github.com.revenantgaze.emoteplugin.commands.WinkCmd;
import io.github.com.revenantgaze.emoteplugin.unimplemented.EmoteCmd;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	public Plugin instance;

	public static ConfigManager cm;

	File emoteConfigFile = new File(this.getDataFolder(), "emotes.yml");
	FileConfiguration emoteConfig = YamlConfiguration
			.loadConfiguration(emoteConfigFile);

	@Override
	public void onEnable() {

		if (this.getConfig().getBoolean("use-default-emotes") == true) {

			getCommand("emotes").setExecutor(new EmotesCmd(this));
			getCommand("kiss").setExecutor(new KissCmd(this));
			getCommand("hug").setExecutor(new HugCmd(this));
			getCommand("wave").setExecutor(new WaveCmd(this));
			getCommand("glomp").setExecutor(new GlompCmd(this));
			getCommand("wink").setExecutor(new WinkCmd(this));
			getCommand("ekick").setExecutor(new KickCmd(this));
			getCommand("teabag").setExecutor(new TeabagCmd(this));
			getCommand("flail").setExecutor(new FlailCmd(this));
			getCommand("grumble").setExecutor(new GrumbleCmd(this));
			getCommand("argue").setExecutor(new ArgueCmd(this));
			getCommand("love").setExecutor(new LoveCmd(this));
			getCommand("hate").setExecutor(new HateCmd(this));
			getCommand("poke").setExecutor(new PokeCmd(this));
			getCommand("smack").setExecutor(new SmackCmd(this));
			getCommand("facepalm").setExecutor(new FacepalmCmd(this));
			getCommand("whistle").setExecutor(new WhistleCmd(this));
			getCommand("emote").setExecutor(new EmoteCmd(this));

			getLogger().info("Default emotes is enabled!");

		}

		else {

			getCommand("emotes").setExecutor(new EmotesCmd(this));
			getCommand("emote").setExecutor(new EmoteCmd(this));

			getLogger().info("Default emotes is disabled!");

		}
		
		File configFile = new File(getDataFolder() + File.separator + "config.yml");
		File emotesConfigFile = new File(getDataFolder() + File.separator + "emotes.yml");
		
		if (!configFile.exists()) {

			getLogger().info("Generating default config file...");

			this.getConfig().options().copyDefaults(true);

			this.getEmoteConfig()
			.options()
			.header("Default config file for Emotes " + this.getDescription().getVersion());

			ConfigurationSection cooldownSection = this.getConfig().createSection("cooldown");
				
				this.getConfig().set("cooldown.cooldown", 10);
				this.getConfig().set("cooldown.default", 10);
				this.getConfig().set("emotes-distance", 40);
				this.getConfig().set("use-default-emotes", true);

			saveConfig();

			getLogger().info("Config.yml has been generated!");

		}

		else {

			getLogger().info("Found config.yml");

		}
		
		if (!emotesConfigFile.exists()) {

			getLogger().info("Generating emotes config file...");

			File emoteConfigFile = new File(this.getDataFolder(), "emotes.yml");
			FileConfiguration emoteConfig = YamlConfiguration
					.loadConfiguration(emoteConfigFile);
			
			emoteConfigFile = new File(getDataFolder(), "emotes.yml");

			this.saveResource("emotes.yml", false);

			this.getEmoteConfig()
			.options()
			.header("Custom emote configuration file\n"
					+ "Set ''use-default-emotes'' to false in the main config if you do not want to have any other emotes than the ones you are adding yourself\n"
					+ "All the emotes you create will be added to ''emotes-list'' and will be returned when doing /emote\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "Sample setup:\n"
					+ "When adding messages, start it from after the players name like shown below (sp-message and mp-message)\n"
					+ "sp-message = single player message\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "mp-message-bp = multiplayer message before target\n"
					+ "Which is the message between the emote sender's name and the target player's name\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "mp-message-ap = multiplayer message after target\n"
					+ "Which is the message after the target player's name\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "emotes:\n"
					+ "  teabag:\n"
					+ "    description: Teabag!\n"
					+ "    usage: /emote teabag\n"
					+ "    permission: emotes.commands.teabag\n"
					+ "    sp-message: argues incoherently into the air!\n"
					+ "    mp-message-bt: teabags\n"
					+ "    mp-message-at: ! How embarassing!\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "Emote without a target: <emote sender> <sp-message>\n"
					+ "Emote with a target: <emote sender> <mp-message-bt> <target> <mp-message-at>\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "According to this setup, this message will equal: <player> teabags <target>! How embarassing!)\n");

			ConfigurationSection emoteSection = this.getEmoteConfig().createSection("emotes");
			ConfigurationSection emoteListSection = this.getEmoteConfig().createSection(
						"emotes-list");

			this.saveEmoteConfig();

			getLogger().info("Emotes.yml has been generated!");

		}

		else {

			getLogger().info("Found emotes.yml");

		}

		FileConfiguration config = getConfig();

		boolean checkDefaultUse = this.getConfig().getBoolean(
				"use-default-emotes");

		getLogger().info(
				"Emotes v" + this.getDescription().getVersion()
						+ " has been enabled!");

	}

	@Override
	public void onDisable() {

		getLogger().info("Emotes has been disabled!");

	}

	public void reloadEmoteConfig() {

		if (emoteConfigFile == null) {

			emoteConfigFile = new File(this.getDataFolder(), "emotes.yml");

		}

		emoteConfig = YamlConfiguration.loadConfiguration(emoteConfigFile);

		InputStream emoteConfigStream = this.getResource("emotes.yml");

		if (emoteConfigStream != null) {

			YamlConfiguration blocksDefault = YamlConfiguration
					.loadConfiguration(emoteConfigStream);
			emoteConfig.setDefaults(blocksDefault);

		}

	}

	public void saveEmoteConfig() {

		try {

			emoteConfig.save(emoteConfigFile);

		}

		catch (IOException ex) {

			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Could not save config to " + emoteConfigFile, ex);

		}

	}

	public FileConfiguration getEmoteConfig() {

		if (emoteConfig == null) {

			this.reloadEmoteConfig();

		}

		return emoteConfig;

	}

}
