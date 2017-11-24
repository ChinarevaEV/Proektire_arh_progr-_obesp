/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness_logic;

/**
 *
 * @author Hellen_User
 */
public class KinoKompany extends Person {
    
    public KinoKompany(String name, String login, String pass) {
        this.name = name;
        this.login = login;
        this.pasw = pass;
        this.type = PersonType.KinoKompany;
        
    } 

    public KinoKompany() {
    }
    
    public boolean addFilm(int filmProkatCost, String name, String info, int time){
        Film fil = new Film(this,filmProkatCost, name, info, time);
        return rep.addFilm(fil);
        
    }
    
    public boolean FIlmAcsessBuy(Film film){
        
        if(film.getStatus()!=FilmStatus.WantBuy)
            return false;
        
        film.setStatus(FilmStatus.WaitPay);
        return true;
    }
    
}
