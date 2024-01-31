package com.vinola.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vinola.services.PointService;

import net.sacredlabyrinth.phaed.simpleclans.events.DisbandClanEvent;

public class ClanDelete implements Listener{
    @EventHandler
    public void onClanDelete(DisbandClanEvent e){
        PointService ps = new PointService();
        ps.deletar(e.getClan());
    }
}
