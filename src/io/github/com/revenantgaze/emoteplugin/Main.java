package io.github.com.revenantgaze.emoteplugin;

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

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Main extends JavaPlugin {
	
	public Plugin instance;
	
	@Override
    public void onEnable(){

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
		
		FileConfiguration config = getConfig();

        config.options().copyDefaults(true);
        saveConfig();
		
		getLogger().info("Emotes v" + this.getDescription().getVersion() + " has been enabled!");			

    }
 
    @Override
    public void onDisable() {
    	
    	getLogger().info("Emotes has been disabled!");
    	
    }

}
