package com.vinola.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vinola.db.Database;

import net.sacredlabyrinth.phaed.simpleclans.Clan;

public class PointService {
    public void criar(Clan clan, double valor){
        try{
            Connection conn = Database.conectar();
            String sql = "INSERT INTO clan_money (tag, nome, pontos) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clan.getTag());
            stmt.setString(2, clan.getName());
            stmt.setDouble(3, valor);
            stmt.executeUpdate();
        }catch(SQLException er){
            er.getMessage();
        }catch(IOException er){
            er.getMessage();
        }finally{
            try {
                Database.desconectar();
            } catch (SQLException er) {
                er.getMessage();
            }
        }
    }

    public void deletar(Clan clan){
        try{
            Connection conn = Database.conectar();
            String sql = "DELETE FROM clan_money WHERE tag = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clan.getTag());
            stmt.executeUpdate();
        }catch(SQLException er){
            er.getMessage();
        }catch(IOException er){
            er.getMessage();
        }finally{
            try {
                Database.desconectar();
            } catch (SQLException er) {
                er.getMessage();
            }
        }
    }

    public void retirar(Clan clan, double valor){
        try{
            Connection conn = Database.conectar();
            String sql = "SELECT * FROM clan_money WHERE tag = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clan.getTag());
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                double pontos = result.getDouble("pontos");
                if (pontos < 5) {
                    String sql2 = "UPDATE clan_money SET pontos = ? WHERE tag = ?";
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.setDouble(1, 0);
                    stmt2.setString(2, clan.getTag());
                    stmt2.executeUpdate();
                } else {
                    String sql2 = "UPDATE clan_money SET pontos = ? WHERE tag = ?";
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.setDouble(1, pontos - 5);
                    stmt2.setString(2, clan.getTag());
                    stmt2.executeUpdate();
                }
            }
        }catch(SQLException er){
            er.getMessage();
        }catch(IOException er){
            er.getMessage();
        }finally{
            try {
                Database.desconectar();
            } catch (SQLException er) {
                er.getMessage();
            }
        }
    }

    public void depositar(Clan clan, double valor){
        try{
            Connection conn = Database.conectar();
            String sql = "SELECT * FROM clan_money WHERE tag = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clan.getTag());
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                String sql2 = "UPDATE clan_money SET pontos = ? WHERE tag = ?";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setDouble(1, result.getDouble("pontos") + 10);
                stmt2.setString(2, clan.getTag());
                stmt2.executeUpdate();
            }
        }catch(SQLException er){
            er.getMessage();
        }catch(IOException er){
            er.getMessage();
        }finally{
            try {
                Database.desconectar();
            } catch (SQLException er) {
                er.getMessage();
            }
        }
    }

    public double ver(Clan clan){
        try{
            Connection conn = Database.conectar();
            String sql = "SELECT * FROM clan_money WHERE tag = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clan.getTag());
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                return result.getDouble("pontos");
            }
        }catch(SQLException er){
            er.getMessage();
        }catch(IOException er){
            er.getMessage();
        }finally{
            try {
                Database.desconectar();
            } catch (SQLException er) {
                er.getMessage();
            }
        }
        return 0;
    }
}
