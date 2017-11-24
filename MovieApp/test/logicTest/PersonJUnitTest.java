/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicTest;

import buisness_logic.Client;
import database.ArrayMemberRepository;
import database.MemberRepository;
import java.lang.reflect.Member;
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
public class PersonJUnitTest {

    MemberRepository rep;

    public PersonJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        rep = new ArrayMemberRepository();
        rep.clearAll();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        String name = "Pat";
        String login ="Patty";
        String pass ="qwerty";
        
        Client cl = new Client(name,login,pass);
        assertTrue(rep.addPerson(cl)); 
        assertTrue(cl.autentification(login, pass)); 
        assertFalse(cl.autentification("111", pass));
        assertFalse(cl.autentification(login, "123")); 
    }
}
