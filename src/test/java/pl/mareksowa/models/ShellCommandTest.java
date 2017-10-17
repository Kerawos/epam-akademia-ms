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
        //ShellProgram shellProgram = new ShellProgram();
        assertEquals("cd:0:0\n" +
                "tree:0:0\n" +
                "dir:0:0\n" +
                "prompt:0:0\n" +
                "statistics:1:0\n", command.statistics());
    }

    @Test
    public void testPromptReset(){
        ShellProgram shellProgram = new ShellProgram();
        shellProgram.setIsPromptDirectory(true);
        shellProgram.setPrompt("prompt test");
        shellProgram.promptReset();
        assertEquals(false, shellProgram.isIsPromptDirectory());
        assertEquals("$", shellProgram.getPrompt());
    }





}