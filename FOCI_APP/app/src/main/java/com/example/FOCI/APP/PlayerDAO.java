package com.example.FOCI.APP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) a Player osztályhoz.
 * Ez az osztály felelős a Player objektumok és az adatbázis közötti kommunikációért.
 */
public class PlayerDAO {

    /**
     * Elment egy Player objektumot az adatbázis 'players' táblájába.
     * @param player A menteni kívánt Player objektum.
     */
    public static void savePlayer(Player player) {
        
        // SQL parancs a 'players' táblához.
        // FIGYELEM: A 'player_id'-t nem adjuk meg, mert az AUTOINCREMENT (automatikusan generálódik).
        String sql = "INSERT INTO players(nev, mezszam, pozicio, golok, assists, yellowCards, piaci_ertek_m_euro, team_id) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Beállítjuk a '?' helyére az értékeket a Player objektumból
            // A sorrend FONTOS!
            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getJerseyNumber());
            pstmt.setString(3, player.getPosition());
            pstmt.setInt(4, player.getGoals());
            pstmt.setInt(5, player.getAssists());
            pstmt.setInt(6, player.getYellowCards());
            pstmt.setDouble(7, player.getMarketValue_m_euro());
            pstmt.setInt(8, player.getTeamId()); // Ez köti össze a csapattal

            // Lefuttatjuk a parancsot
            pstmt.executeUpdate();
            
            System.out.println("Játékos sikeresen elmentve: " + player.getName());

        } catch (SQLException e) {
            System.err.println("Hiba a játékos mentése közben (" + player.getName() + "):");
            
            // Különösen figyeljünk a UNIQUE(team_id, mezszam) hibára
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                // Mivel a Player.java-ban nincs player_id (mivel az AUTOINCREMENT), a teamId-t írjuk ki
                System.err.println("Hiba: Ez a mezszám (" + player.getJerseyNumber() + 
                                   ") már foglalt ebben a csapatban (Team ID: " + player.getTeamId() + ")");
            } else {
                // Minden más SQL hiba esetén
                e.printStackTrace();
            }
        }
    }
}

