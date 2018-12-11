/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugarov.xml.finder.control;

import com.mugarov.xml.finder.control.Initiator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author black
 */
public class InitiatorTest {
    
    public InitiatorTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * Test of update method, of class Initiator.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Initiator instance = new Initiator(new String[]{"../testWarningLists", "../testSources"});;
        //instance.update();
    }

    
    
}
