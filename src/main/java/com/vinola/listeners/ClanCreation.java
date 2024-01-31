package com.vinola.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vinola.services.PointService;

import net.sacredlabyrinth.phaed.simpleclans.events.CreateClanEvent;

public class ClanCreation implements Listener{
    @EventHandler
    public void onClanCreation(CreateClanEvent e){
        PointService ps = new PointService();
        ps.criar(e.getClan(), 100);
    }
}
