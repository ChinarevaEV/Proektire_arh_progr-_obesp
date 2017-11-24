/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import buisness_logic.Bron;
import buisness_logic.BronStatus;
import buisness_logic.Client;
import buisness_logic.Film;
import buisness_logic.FilmStatus;
import buisness_logic.Mesto;
import buisness_logic.Person;
import buisness_logic.PersonType;
import buisness_logic.Seans;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Hellen_User
 */
public class ArrayMemberRepository implements MemberRepository {

    private static ArrayList<Person> personList = new ArrayList<>();
    private static ArrayList<Film> filmList = new ArrayList<>();
    private static ArrayList<Seans> seansList = new ArrayList<>();
    private static ArrayList<Mesto> mestoList = new ArrayList<>();
    private static ArrayList<Bron> bronList = new ArrayList<>();
    @Override
    public Boolean addPerson(Person per) {
        per.setId(personList.size());
        personList.add(per);
        return true;
    }

    @Override
    public Person getPerson(String login) {
        for (Person per : personList) {
            if (per.getLogin().equals(login)) {
                return per;
            }
        }
        return null;
    }

    @Override
    public Boolean addFilm(Film film) {

        film.setId(filmList.size());
        filmList.add(film);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public ArrayList<Film> getFilms() {
        return filmList;
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addSeans(Seans se) {
       se.setId(seansList.size());
       seansList.add(se);
        return true;
    }

    @Override
    public ArrayList<Seans> getSeanses() {
        return seansList;
    }

    @Override
    public Boolean addMesto(Mesto mes) {
        mes.setId(mestoList.size());
        mestoList.add(mes);
        return true;
    }

    @Override
    public ArrayList<Mesto> getMesto() {
        
        return mestoList;
    }

    @Override
    public Boolean addBron(Bron bron) {
        for (Bron br : getBronsbySeans(bron.getSeans())) {
            if (br.getMesto().equals(bron.getMesto())) {
                return false;
            }
        }
        bronList.add(bron);
        return true;
    }

    @Override
    public ArrayList<Bron> getBrons() {
        return bronList;  
    }

    @Override
    public ArrayList<Bron> getBronsbySeans(Seans se) {
        ArrayList<Bron> ret = new ArrayList<>();
        for (Bron br : bronList) {
            if (br.getSeans().equals(se)) {
                ret.add(br);
            }
        }
        return ret;    
    }

    @Override
    public Boolean update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean clearAll() {
        bronList.clear();
        return true;
    }

    @Override
    public void sync() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Person> getPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Person> getPersons(PersonType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person getPersonById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Film getFilmById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seans getSeansById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mesto getMestoById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bron getBronById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Film> getFilmsStatus(FilmStatus stat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Seans> getSeanses(LocalDate dt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Bron> getBrons(Client cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Bron> getBronsStatus(BronStatus stat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Bron> getBronsStatus(Client cl, BronStatus stat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mesto getMestoByRyadNum(int ryad, int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
