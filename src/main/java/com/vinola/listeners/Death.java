package com.vinola.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.vinola.WastedSCA;
import com.vinola.services.PointService;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class Death implements Listener{
    SimpleClans sc = SimpleClans.getInstance();
    PointService ps = new PointService();
    private final static WastedSCA main = WastedSCA.getPlugin(WastedSCA.class);

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage(null);
        Player killer = (Player) e.getEntity().getKiller();
        Player victim = (Player) e.getEntity();

        if (killer == null || victim == null){
            return;
        }

        if ((sc.getClanManager().getClanByPlayerName(killer.getDisplayName()) == null) || (sc.getClanManager().getClanByPlayerName(victim.getDisplayName()) == null)){
            return;
        }

        ps.depositar(sc.getClanManager().getClanByPlayerName(killer.getDisplayName()), 10);
        ps.retirar(sc.getClanManager().getClanByPlayerName(victim.getDisplayName()), 5);
        killer.sendMessage(main.getConfig().getString("prefix")
            .replace("&", "§") + " " + main.getConfig().getString("mensagens.abate")
                .replace("&", "§")
                .replace("%victim%", victim.getName())
                .replace("%victimcname%", sc.getClanManager().getClanByPlayerName(victim.getDisplayName()).getName())
                .replace("%victimctag%", sc.getClanManager().getClanByPlayerName(victim.getDisplayName()).getTag())
                .replace("%killer%", killer.getName())
                .replace("%killercname%", sc.getClanManager().getClanByPlayerName(killer.getDisplayName()).getName())
                .replace("%killerctag%", sc.getClanManager().getClanByPlayerName(killer.getDisplayName()).getTag())
                .replace("%killercpontos%", String.valueOf(ps.ver(sc.getClanManager().getClanByPlayerName(killer.getDisplayName())))));
        victim.sendMessage(main.getConfig().getString("prefix")
            .replace("&", "§") + " " + main.getConfig().getString("mensagens.morte")
                .replace("&", "§")
                .replace("%victim%", victim.getName())
                .replace("%victimcname%", sc.getClanManager().getClanByPlayerName(victim.getDisplayName()).getName())
                .replace("%victimctag%", sc.getClanManager().getClanByPlayerName(victim.getDisplayName()).getTag())
                .replace("%killer%", killer.getName())
                .replace("%killercname%", sc.getClanManager().getClanByPlayerName(killer.getDisplayName()).getName())
                .replace("%killerctag%", sc.getClanManager().getClanByPlayerName(killer.getDisplayName()).getTag())
                .replace("%victimcpontos", String.valueOf(ps.ver(sc.getClanManager().getClanByPlayerName(victim.getDisplayName())))));
    }
}
