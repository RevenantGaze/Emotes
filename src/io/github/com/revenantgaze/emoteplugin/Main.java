package io.github.com.revenantgaze.emoteplugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.com.revenantgaze.emoteplugin.commands.ArgueCmd;
import io.github.com.revenantgaze.emoteplugin.commands.EmoteCmd;
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

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	public Plugin instance;

	public static ConfigManager cm;
	
	File defaultConfigFile = new File(this.getDataFolder(), "config.yml");
	File emoteConfigFile = new File(this.getDataFolder(), "emotes.yml");
	
	FileConfiguration emoteConfig = YamlConfiguration
			.loadConfiguration(emoteConfigFile);
	FileConfiguration defaultConfig = YamlConfiguration
			.loadConfiguration(defaultConfigFile);

	@Override
	public void onEnable() {
		
		if (!defaultConfigFile.exists()) {

			getLogger().info("Generating default config file...");

			this.saveResource("config.yml", false);

			this.getConfig()
			.options()
			.header("Default config file for Emotes " + this.getDescription().getVersion());

			ConfigurationSection cooldownSection = this.getConfig().createSection("cooldown");
				
				this.getConfig().set("cooldown.cooldown", 10);
				this.getConfig().set("cooldown.default", 10);
				this.getConfig().set("emotes-distance", 40);
				this.getConfig().set("use-default-emotes", true);
				this.getConfig().set("show-target-warning", true);
				this.getConfig().set("no-permission-message", "You do not have permission to use this emote!");

			saveConfig();

			getLogger().info("Config.yml has been generated!");

		}

		else {

			getLogger().info("Found config.yml");

		}
		
		if (!emoteConfigFile.exists()) {

			getLogger().info("Generating emotes config file...");

			this.getEmoteConfig()
			.options()
			.header("Custom emote configuration file\n"
					+ "Set ''use-default-emotes'' to false in the main config if you do not want to have any other emotes than the ones you are adding yourself\n"
					+ "All the emotes you create will be added to ''emotes-list'' and will be returned when doing /emote\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "Sample setup:\n"
					+ "When adding messages, start it from after the players name like shown below (message and sp-message)\n"
					+ "message = emote message with a target\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "sp-message = emote message without a target\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "emotes:\n"
					+ "  kiss:\n"
					+ "    description: Kiss!\n"
					+ "    usage: /emote use kiss\n"
					+ "    permission: emotes.emote.kiss\n"
					+ "    message: <s> kisses <t>! N'taww!\n"
					+ "    sp-message-bt: <s> blows a kiss into the air. Who is able to catch it?\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "When doing an emote with a target, <s> is the Sender, <t> is the Target\n"
					+ "When doing the emote without a target, <s> is the Sender\n"
					+ "--------------------------------------------------------------------------------------------------------\n"
					+ "According to this setup, this message will equal: <s> kisses <t>! N'taww!)\n");

			ConfigurationSection emoteSection = this.getEmoteConfig().createSection("emotes");
			ConfigurationSection emoteListSection = this.getEmoteConfig().createSection(
						"emotes-list");

			this.saveEmoteConfig();

			getLogger().info("Emotes.yml has been generated!");

		}

		else {

			getLogger().info("Found emotes.yml");

		}

		boolean checkDefaultUse = this.getConfig().getBoolean(
				"use-default-emotes");

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
	
	public FileConfiguration getConfig() {

		if (defaultConfig == null) {

			this.reloadConfig();

		}

		return defaultConfig;

	}

}
