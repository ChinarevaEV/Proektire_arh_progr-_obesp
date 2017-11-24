/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import buisness_logic.Film;
import com.google.gson.Gson;
import database.DBMemberRepository;
import database.MemberRepository;
import java.util.ArrayList;

/**
 *
 * @author Hellen_User
 */
public class FilmFacade {
    MemberRepository rep = new DBMemberRepository();
    
    public static String getAllEquipmentInJson() {
        DBMemberRepository repository = new DBMemberRepository();
        ArrayList<Film> list = repository.getFilms();
        ArrayList<String> strList = new ArrayList<>();
        for (Film fil : list) {
            strList.add(fil.getName());            
        }
        String json = new Gson().toJson(strList);
        return json;
    }
}
