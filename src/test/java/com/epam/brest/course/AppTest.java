package com.epam.brest.course;

import junit.framework.Assert;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;

@RunWith(JUnit4.class)
public class AppTest extends TestCase {

    /*
    System.out.println( "Hello World!" );
    is invoked, behind the scene, it writes
    in the ByteArrayOutputStream referenced
    by bos as out is a PrintWriter that decorates
    this ByteArrayOutputStream
     */

    //java lang exception is never thrown so we dont need
    @Test
    public void mainMethodTest() {

        // to have a way to undo the binding with your `ByteArrayOutputStream`
        PrintStream originalOut = System.out;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        System.setOut(new PrintStream(bos));

        // action
        App.main(null);

        // assertion
        Assert.assertEquals("Hello World!", bos.toString().trim());
        // undo the binding in System
        System.setOut(originalOut);
    }

}
