/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness_logic;

import java.time.LocalDateTime;

/**
 *
 * @author Hellen_User
 */

public class Manager extends Person{
    
    private KinoKompany kinkomp;
    
    public Manager (String name, String login, String pass) {
        this.name = name;
        this.login = login;
        this.pasw = pass;
        this.type = PersonType.Manager;
        
    }   

    public Manager() {
    }
  
    public int AceptCorpBron(Bron br, float skidka){
        
        return br.acessBron(skidka);
    }   
    
    public boolean NewSeans(LocalDateTime time, Film seansfilm){
        
        Seans se = new Seans(time, seansfilm);
        
        if(seansfilm.getStatus()!=FilmStatus.Payed)
            return false;
        if(rep.getSeanses()!=null)
        for (Seans sefor : rep.getSeanses()) {
            if (se.getTime().isBefore(sefor.getTimeend())   && se.getTimeend().isAfter(sefor.getTime())) {
                
                return false;
                
            }
        }
        rep.addSeans(se);
        return true;
    }
    
    public boolean FIlmToBuy(Film film){
        
        if(film.getStatus()!=FilmStatus.New)
            return false;
        film.setStatus(FilmStatus.WantBuy);
        return true;
    }
    
    public boolean FIlmToCasshierpay(Film film){
        
        if(film.getStatus()!=FilmStatus.WaitPay)
            return false;
        
        film.setStatus(FilmStatus.WaitAccessCashier);
        return true;
    }
    
    
}
