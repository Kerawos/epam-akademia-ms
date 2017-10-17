package pl.mareksowa.models;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.mareksowa.controllers.UserController;

import static org.junit.Assert.*;

public class ShellCommandTest {

    private static ShellCommand command;

    @BeforeClass
    public static void start(){
        command = new ShellCommand();
    }

    @AfterClass
    public static void stop(){
        command = null;
    }

    @Test
    public void testStatistics(){
//        ShellProgram program = new ShellProgram();
//        assertEquals("cd:0:0\n" +
//                "tree:0:0\n" +
//                "dir:0:0\n" +
//                "prompt:0:0\n" +
//                "statistics:1:0", command.statistics());
    }




}