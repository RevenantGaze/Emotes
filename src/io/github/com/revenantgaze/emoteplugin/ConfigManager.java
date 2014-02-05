package io.github.com.revenantgaze.emoteplugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

	public Main plugin;

	public ConfigManager(Main instance) {

		plugin = instance;

	}

	File emoteConfigFile;
	FileConfiguration emoteConfig;

	public void saveEmoteConfig() {

		try {

			emoteConfig.save(emoteConfigFile);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void setEmoteDefaults() {

		if (plugin.getConfig().getBoolean("use-default-emotes") == true) {

			try {

				String[] defaultList = { "argue", "facepalm", "flail", "glomp",
						"grumble", "hate", "hug", "kick", "kiss", "love",
						"poke", "smack", "teabag", "wave", "whistle", "wink" };

				emoteConfig.set("emotes-list", defaultList);

			}

			catch (Exception e) {

				e.printStackTrace();

			}

		}

		else {

			try {

				String[] defaultList = {};

				emoteConfig.set("emotes-list", defaultList);

			}

			catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	public void addNewEmoteUsageString(String emoteName, String emoteUsage) {

		emoteConfig.set("emotes." + emoteName + ".usage", emoteUsage);

	}

	public void addNewEmoteDescriptionString(String emoteName,
			String emoteDescription) {

		emoteConfig.set("emotes." + emoteName + ".description",
				emoteDescription);

	}

	public void addNewEmotePermissionString(String emoteName,
			String emotePermission) {

		emoteConfig.set("emotes." + emoteName + ".permission",
				emotePermission);

	}

	public void addNewEmoteSpMessageString(String emoteName,
			String emoteSpMessage) {

		emoteConfig.set("emotes." + emoteName + ".sp-message",
				emoteSpMessage);

	}

	public void addNewEmoteMpMessageBtString(String emoteName,
			String emoteMpMessageBt) {

		emoteConfig.set("emotes." + emoteName + ".mp-message-bt",
				emoteMpMessageBt);

	}

	public void addNewEmoteMpMessageAtString(String emoteName,
			String emoteMpMessageAt) {

		emoteConfig.set("emotes." + emoteName + ".mp-message-at",
				emoteMpMessageAt);

	}

}
