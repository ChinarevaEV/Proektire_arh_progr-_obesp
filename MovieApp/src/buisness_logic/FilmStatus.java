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
public enum FilmStatus {
     New, WaitPay, Payed, WantBuy, WaitAccessCashier;

    public static String statusToStr(FilmStatus type) {
        switch (type) {
            case New:
                return "New";
            case WaitPay:
                return "WaitPay";
            case Payed:
                return "Payed";
            case WantBuy:
                return "WantBuy";
            case WaitAccessCashier:
                return "WaitAccessCashier";
            default:
                return null;
        }
    }

    
    
     public static FilmStatus strToStatus(String st) {
           switch (st) {
            case "New":
                return New;
            case "WaitPay":
                return WaitPay;
            case "Payed":
                return Payed;
            case "WantBuy":
                return WantBuy;
            case "WaitAccessCashier":
                return WaitAccessCashier;
            default:
                return null;
        }
     }
}
