package com.vinola.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vinola.WastedSCA;
import com.vinola.services.PointService;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class PontosCmd implements CommandExecutor{
    SimpleClans sc = SimpleClans.getInstance();
    PointService ps = new PointService();
    private final static WastedSCA main = WastedSCA.getPlugin(WastedSCA.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(main.getConfig().getString("prefix")
            .replace("&", "§") + " " + main.getConfig().getString("mensagens.apenas-jogadores")
            .replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0 || args.length > 1){
            player.sendMessage(main.getConfig().getString("prefix")
            .replace("&", "§") + " " + main.getConfig().getString("mensagens.uso-correto")
            .replace("&", "§"));
            return true;
        }

        if (args[0].equalsIgnoreCase("ver")){
            if (sc.getClanManager().getClanByPlayerName(player.getDisplayName()) == null){
                player.sendMessage(main.getConfig().getString("prefix")
                .replace("&", "§") + " " + main.getConfig().getString("mensagens.sem-cla")
                .replace("&", "§"));
                return true;
            }
            Double pontos = ps.ver(sc.getClanManager().getClanByPlayerName(player.getDisplayName()));
            player.sendMessage(main.getConfig().getString("prefix")
            .replace("&", "§") + " " + main.getConfig().getString("mensagens.cb-ver")
                    .replace("&", "§")
                    .replace("%pontos%", pontos.toString()));
            return true;
        }

        return true;
    }
}
