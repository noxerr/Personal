/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ferran
 */
public class JugadorFerranTest {
    
    public JugadorFerranTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of moviment method, of class JugadorFerran.
     */
    @Test
    public void testMoviment() {
        System.out.println("moviment");
        Tauler t = null;
        int color = 0;
        JugadorFerran instance = new JugadorFerranImpl();
        int expResult = 0;
        int result = instance.moviment(t, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nom method, of class JugadorFerran.
     */
    @Test
    public void testNom() {
        System.out.println("nom");
        JugadorFerran instance = new JugadorFerranImpl();
        String expResult = "";
        String result = instance.nom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class JugadorFerranImpl implements JugadorFerran {

        public int moviment(Tauler t, int color) {
            return 0;
        }

        public String nom() {
            return "";
        }
    }
    
}
