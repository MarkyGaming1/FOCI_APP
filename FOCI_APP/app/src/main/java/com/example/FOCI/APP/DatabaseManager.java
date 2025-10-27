package com.example.FOCI.APP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Felelős az adatbázis kapcsolat kezeléséért és a séma inicializálásáért.
 * @author bocsk
 */
public class DatabaseManager {

    // Az adatbázis fájl neve. 
    private static final String DATABASE_URL = "jdbc:sqlite:foci_database.db";

    /**
     * Létrehozza az adatbázis táblákat (a sémát), ha azok még nem léteznek.
     */
    public static void initializeDatabase() {
        
        String sqlCreateTeams = "CREATE TABLE IF NOT EXISTS teams ("
                              + " id INTEGER PRIMARY KEY,"
                              + " name TEXT NOT NULL UNIQUE"
                              + ");";

        String sqlCreatePlayers = "CREATE TABLE IF NOT EXISTS players ("
                                + " player_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + " nev TEXT NOT NULL,"
                                + " mezszam INTEGER,"
                                + " pozicio TEXT,"
                                + " golok INTEGER DEFAULT 0,"
                                + " assists INTEGER DEFAULT 0," // Ez hiányzott a korábbi verziódból
                                + " yellowCards INTEGER DEFAULT 0," // Ez is hiányzott
                                + " piaci_ertek_m_euro REAL DEFAULT 0.0,"
                                + " team_id INTEGER," 
                                + " FOREIGN KEY (team_id) REFERENCES teams (id),"
                                + " UNIQUE (team_id, mezszam)" 
                                + ");";

        // 3. Csatlakozás és a parancsok futtatása
        // Figyelem: Itt a getConnection()-t hívjuk, hogy biztosan működjön!
        try (Connection conn = getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            // Táblák létrehozása
            stmt.execute(sqlCreateTeams);
            System.out.println("A 'teams' tábla sikeresen létrehozva (vagy már létezett).");
            
            stmt.execute(sqlCreatePlayers);
            System.out.println("A 'players' tábla sikeresen létrehozva (vagy már létezett).");

        } catch (SQLException e) {
            // Hiba esetén kiírjuk a problémát
            System.err.println("Hiba az adatbázis inicializálása közben:");
            e.printStackTrace();
        }
    }

    /**
     * ---- EZ A HIÁNYZÓ METÓDUS ----
     * Létrehoz és visszaad egy új kapcsolatot az adatbázishoz.
     * A DAO osztályok (TeamDAO, PlayerDAO) ezt fogják használni.
     * * @return Connection objektum
     * @throws SQLException ha a csatlakozás sikertelen
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
}

