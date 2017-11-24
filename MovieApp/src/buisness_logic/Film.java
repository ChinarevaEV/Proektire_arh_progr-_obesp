/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness_logic;

import database.DBMemberRepository;
import database.MemberRepository;
import java.util.ArrayList;

/**
 *
 * @author Hellen_User
 */
public class Film {

    private int filmProkatCost;
    private String name;
    private String info;
    private int id;
    private KinoKompany kinkomp;
    private FilmStatus status;
    private int time;
    protected MemberRepository rep = new DBMemberRepository();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film(KinoKompany kinkomp,int filmProkatCost, String name, String info,int time) {
        this.kinkomp = kinkomp;
        this.filmProkatCost = filmProkatCost;
        this.name = name;
        this.info = info;
        this.status = FilmStatus.New;
        this.time = time;
    }

    public KinoKompany getKinkomp() {
        return kinkomp;
    }

    public void setKinkomp(KinoKompany kinkomp) {
        this.kinkomp = kinkomp;
    }

        
    
    public FilmStatus getStatus() {
        return status;
    }
    public void update() {
        rep.update(this);
    }
    public void setStatus(FilmStatus status) {
        this.status = status;
        this.update();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFilmProkatCost() {
        return filmProkatCost;
    }

    public void setFilmProkatCost(int filmProkatCost) {
        this.filmProkatCost = filmProkatCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
