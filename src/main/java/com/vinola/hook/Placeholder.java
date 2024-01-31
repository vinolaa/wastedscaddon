package com.vinola.hook;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.vinola.services.PointService;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class Placeholder extends PlaceholderExpansion{

    SimpleClans sc = SimpleClans.getInstance();
    PointService ps = new PointService();

    @Override
    public String getAuthor() {
        return "Vinola";
    }

    @Override
    public String getIdentifier() {
        return "wastedsca";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String identifier) {

        if (offlinePlayer != null && offlinePlayer.isOnline()){
            Player player = offlinePlayer.getPlayer();
            if (sc.getClanManager().getClanByPlayerName(player.getName()) == null) return "§cSem clã";
            if (identifier.equals("pontos")){
                Double pontos = ps.ver(sc.getClanManager().getClanPlayer(player).getClan());
                return pontos.toString();
            }
        }
        return null;
    }

    public static void registerHook() {
        new Placeholder().register();
    }
    
}
