/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import buisness_logic.Film;
import buisness_logic.FilmStatus;
import buisness_logic.KinoKompany;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hellen_User
 */
public class FilmMapper {
    
    private static Map<Integer, Film> loadedMap = new HashMap<>();
    private static Connection connection;
    private static FilmMapper instance;

    private FilmMapper() throws IOException, SQLException {
        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public static FilmMapper getInstance() throws IOException, SQLException {
        if (instance == null) {
            instance = new FilmMapper();
        }
        return instance;
    }
    
    public boolean addFilm(Film film) throws SQLException {
        String query = "INSERT INTO film (cost,time,kinkompid,filmstatus,name,info) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, film.getFilmProkatCost());
        statement.setInt(2, film.getTime());
        statement.setInt(3, film.getKinkomp().getId());
        statement.setString(4, FilmStatus.statusToStr(film.getStatus()));
        statement.setString(5, film.getName());
        statement.setString(6, film.getInfo());

        statement.execute();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            film.setId(id);
        } else {
            return false;
            //ToDO exception
        }

        loadedMap.put(film.getId(), film);
        return true;
    }
    
    public ArrayList<Film> getFilms() throws SQLException, IOException {

        

        String query = "SELECT * FROM film ;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        ArrayList<Film> list = dbRecordsToFilm(rs);
        if (list.isEmpty()) {
            return null;
        }

        return list;
    }
    
    public Film getById(int id) throws SQLException, IOException {

        Film fil = loadedMap.get(id);
        if (fil != null) {
            return fil;
        }

        String query = "SELECT * FROM film WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        ArrayList<Film> list = dbRecordsToFilm(rs);
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
    
    private ArrayList<Film> dbRecordsToFilm(ResultSet rs) throws SQLException, IOException {

        ArrayList<Film> list = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");

            Film film = loadedMap.get(id);
            if (film != null) {
                list.add(film);
                continue;
            }

            int cost = rs.getInt("cost");
            int time = rs.getInt("time");
            int kinkompid = rs.getInt("kinkompid");
            String name = rs.getString("name");
            String info = rs.getString("info");
            String status = rs.getString("filmstatus");
            
            KinoKompany kinkomp = (KinoKompany) PersonMapper.getInstance().getById(kinkompid);
            
            
            film = new Film(kinkomp, cost, name, info, time);
            film.setId(id);
            film.setStatus(FilmStatus.strToStatus(status));

            loadedMap.put(film.getId(), film);
            list.add(film);
        }
        return list;
    }
    
    public int update() throws SQLException {
        for (Map.Entry<Integer, Film> entry : loadedMap.entrySet()) {
            this.update(entry.getValue());
        }
        return 0;
    }

    public int update(int id) throws SQLException {

        Film film = loadedMap.get(id);
        if (film == null) {
            return -1;
        }

        this.update(film);
        return 0;
    }
    
    
    private int update(Film film) throws SQLException {
        if (film == null) {
            return -1;
        }

        String query = "UPDATE film SET kinkompid = ?, "
                + "cost = ?, filmstatus = ?, name = ?, info = ?, time = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, film.getKinkomp().getId());
        statement.setInt(2, film.getFilmProkatCost());
        statement.setString(3, FilmStatus.statusToStr(film.getStatus()));
        statement.setString(4, film.getName());
        statement.setString(5, film.getInfo());
        statement.setInt(6, film.getTime());
        
        statement.setInt(7, film.getId());

        statement.executeUpdate();
        return 0;
    }
    
    
    
     public void sync() throws SQLException, IOException {

        for (Map.Entry<Integer, Film> entry : loadedMap.entrySet()) {
            Integer id = entry.getKey();
            sync(id);
        }
    }

    public void sync(int id) throws SQLException, IOException {
        Film film = loadedMap.get(id);
        Film actalFilm = getActualCopy(id);
        film.setStatus(actalFilm.getStatus());
    }

    private Film getActualCopy(int id) throws SQLException, IOException {
        

            String query = "SELECT * FROM film WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            ArrayList<Film> list = dbActualToFilm(rs);

            if (list.isEmpty()) {
                return null;
            }

            Film film = list.get(0);
            return film;
        
    }

    private ArrayList<Film> dbActualToFilm(ResultSet rs) throws SQLException, IOException {

        ArrayList<Film> list = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");

            Film film;

            int cost = rs.getInt("cost");
            int time = rs.getInt("time");
            int kinkompid = rs.getInt("kinkompid");
            String name = rs.getString("name");
            String info = rs.getString("info");
            String status = rs.getString("filmstatus");
            
            KinoKompany kinkomp = (KinoKompany) PersonMapper.getInstance().getById(kinkompid);
            
            
            film = new Film(kinkomp, cost, name, info, time);
            film.setId(id);
            film.setStatus(FilmStatus.strToStatus(status));


            list.add(film);
        }
        return list;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void clear() {
        loadedMap.clear();
    }

    @Override
    protected void finalize() {
        try {
            connection.close();
            super.finalize();

        } catch (Exception ex) {
            Logger.getLogger(PersonMapper.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Throwable ex) {
            Logger.getLogger(PersonMapper.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
