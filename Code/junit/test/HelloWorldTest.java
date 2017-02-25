package be.leerstad.junit.test;

import be.leerstad.junit.HelloWorld;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    private HelloWorld helloWorld;

    @Before
    public void setUp() {

        helloWorld = new HelloWorld();
    }

    @Test
    public void testSayHelloWorld() {
        assertEquals("expected result HelloWorld", "Hello world", helloWorld.sayHelloWorld());
    }


}
