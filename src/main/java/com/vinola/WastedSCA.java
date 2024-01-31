package com.vinola;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.vinola.commands.PontosCmd;
import com.vinola.hook.Placeholder;
import com.vinola.listeners.ClanCreation;
import com.vinola.listeners.ClanDelete;
import com.vinola.listeners.Death;

public class WastedSCA extends JavaPlugin{

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new ClanCreation(), this);
        getServer().getPluginManager().registerEvents(new ClanDelete(), this);
    }

    public void registerCommands(){
        getCommand("cb").setExecutor(new PontosCmd());
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("WastedSCA ligado com sucesso!");
        registerEvents();
        registerCommands();
        if (getServer().getPluginManager().getPlugin("SimpleClans") == null) {
            getLogger().warning("No SimpleClans, disabling WastedSCA");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Placeholder.registerHook();
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("WastedSCA desligado com sucesso!");
    }    
}
