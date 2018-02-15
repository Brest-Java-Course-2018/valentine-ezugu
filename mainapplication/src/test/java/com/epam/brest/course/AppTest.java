package com.epam.brest.course;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(JUnit4.class)
public class AppTest {
    private PrintStream originalOut;

    @Before
    public void Before() {
        originalOut = System.out;
    }

    @Test
    public void mainMethodTest() {
        // to have a way to undo the binding with your `ByteArrayOutputStream`
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        App.main(null);

        // assertion
        Assert.assertEquals("Hello World!", bos.toString().trim());
    }

     // undo the binding in System
    public void after(){
        System.setOut(originalOut);
    }




}