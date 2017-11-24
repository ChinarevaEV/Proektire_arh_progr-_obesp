/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicTest;

import buisness_logic.Cashier;
import buisness_logic.Film;
import buisness_logic.FilmStatus;
import buisness_logic.KinoKompany;
import buisness_logic.Manager;
import database.ArrayMemberRepository;
import database.DBMemberRepository;
import database.MemberRepository;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hellen_User
 */
public class FilmBuyJUnitTest {

    MemberRepository rep;

    public FilmBuyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //rep = new ArrayMemberRepository();
        rep = new DBMemberRepository();
        rep.clearAll();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    
    //  Бизнес процесс покупка фильма у кинокомпании
    @Test
    public void hello() {
        String name ="Film1";
        String info ="Info about F1";
        int timef = 90;
        KinoKompany kinkomp = new KinoKompany("Kinkomp", "log1", name);
        Manager manager = new Manager("Manager", "log2", name);
        Cashier cashier = new Cashier("Cashier", "log3", name);
        rep.addPerson(cashier);
        rep.addPerson(manager);
        rep.addPerson(kinkomp);
        assertTrue(kinkomp.addFilm(200, name, info, timef));
        assertEquals(name, rep.getFilms().get(0).getName());
        Film film = rep.getFilms().get(0);
        cashier.addCash(500);
        assertFalse(manager.NewSeans(LocalDateTime.now(), film));
        assertTrue(manager.FIlmToBuy(film));
        assertTrue(kinkomp.FIlmAcsessBuy(film));
        assertTrue(manager.FIlmToCasshierpay(film));
        assertTrue(cashier.FIlmPay(film));
        assertEquals(300,cashier.getCash());
        assertEquals(200,kinkomp.getCash());
        assertEquals(FilmStatus.Payed,film.getStatus());
        rep.updateAll();
        
    }

   
}
