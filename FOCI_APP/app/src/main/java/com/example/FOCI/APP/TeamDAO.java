package com.example.FOCI.APP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) a Team osztályhoz.
 * Ez az osztály felelős a Team objektumok és az adatbázis közötti kommunikációért.
 */
public class TeamDAO {

    /**
     * Elment egy Team objektumot az adatbázis 'teams' táblájába.
     * @param team A menteni kívánt Team objektum.
     */
    public static void saveTeam(Team team) {
        
        // Az SQL parancs '?' placeholder-ekkel. Ez biztonságosabb (SQL Injection ellen véd).
        String sql = "INSERT INTO teams(id, name) VALUES(?, ?)";

        // A try-with-resources automatikusan lezárja a kapcsolatot (conn) 
        // és a PreparedStatement-et (pstmt) is.
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Beállítjuk a '?' helyére az értékeket a Team objektumból
            pstmt.setInt(1, team.getId());
            pstmt.setString(2, team.getName());

            // Lefuttatjuk a parancsot
            pstmt.executeUpdate();
            
            System.out.println("Csapat sikeresen elmentve: " + team.getName());

        } catch (SQLException e) {
            // Kezeljük, ha a csapat már létezik (UNIQUE constraint)
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.err.println("Figyelmeztetés: A csapat már létezik az adatbázisban (ID: " + team.getId() + " vagy Név: " + team.getName() + ")");
            } else {
                System.err.println("Hiba a csapat mentése közben (" + team.getName() + "):");
                e.printStackTrace();
            }
        }
    }
}

