/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness_logic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hellen_User
 */
public class Cashier extends Person{
    //private String clientName;
  //  private Film flm;
  // int selectedFilm
    
    private Seans se;
    private ArrayList<String> seanses;
    
    private Mesto mes;
    private  HashMap plases;
    
    private Ticket tik;
    
    
     public Cashier (String name, String login, String pass) {
        this.name = name;
        this.login = login;
        this.pasw = pass;
        this.type = PersonType.Cashier;
        
    } 

    public Cashier() {
    }
     
    public boolean FIlmPay(Film film){
        
        if(film.getStatus()!=FilmStatus.WaitAccessCashier)
            return false;
        
        if(!this.writeOffCash(film.getFilmProkatCost()))
            return false;
        
        if(!film.getKinkomp().addCash(film.getFilmProkatCost()))
            return false;
        
        film.setStatus(FilmStatus.Payed);
        return true;
    } 
    
    public boolean BronPay(Bron bron){
        
        if(bron.getStat()!=BronStatus.WaitCashierPay)
            return false;
        
        if(!bron.getClient().writeOffCash((int)bron.getCost()))
            return false;
        
        if(!this.addCash((int)bron.getCost()))
            return false;
        
        bron.PayedBron();
        return true;
    } 
         
}
