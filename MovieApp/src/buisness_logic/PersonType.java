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
public enum PersonType {

    Cashier, Client, Manager, KinoKompany;

    public static String typeToStr(PersonType type) {
        switch (type) {
            case Cashier:
                return "Cashier";
            case Client:
                return "Client";
            case Manager:
                return "Manager";
            case KinoKompany:
                return "KinoKompany";
            default:
                return null;
        }
    }

    
    
     public static PersonType strToType(String st) {
           switch (st) {
            case "Cashier":
                return Cashier;
            case "Client":
                return Client;
            case "Manager":
                return Manager;
            case "KinoKompany":
                return KinoKompany;
            default:
                return null;
        }
     }
}
