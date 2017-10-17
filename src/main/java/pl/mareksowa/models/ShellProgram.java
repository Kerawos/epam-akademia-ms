package pl.mareksowa.models;

import pl.mareksowa.controllers.UserController;
import pl.mareksowa.views.SimpleConsole;

import java.io.File;

public class ShellProgram {

    //declarations
    private SimpleConsole console;
    private UserController controller;
    private ShellCommand command;
    private String input;

    private static String directory;
    private static File currentDirectory;



    //main program
    public void startShellProgram(){

        //initialization
        console = new SimpleConsole();
        controller = new UserController();
        command = new ShellCommand();
        setDirectory(System.getProperty("user.dir"));
        setCurrentDirectory(new File(getDirectory()));

        //main loop
        while (true){
            console.print("$>");
            input = controller.getUserInput();
            console.print("User said: " + input);

            switch (input){

                case "dir":{
                    command.dir();
                    break;
                }

                case "cd":{
                    //todo
                    break;
                }

                case "prompt":{
                    //todo
                    break;
                }

                case "tree":{
                    //todo
                    break;
                }

                case "statistics":{
                    //todo
                    break;
                }

                case "exit":{
                    command.exit();
                }

                default:
                    command.unknown();
                    break;
            }
        }
    }







    public static String getDirectory() {
        return directory;
    }

    public static void setDirectory(String directory) {
        ShellProgram.directory = directory;
    }

    public static File getCurrentDirectory() {
        return currentDirectory;
    }

    public static void setCurrentDirectory(File currentDirectory) {
        ShellProgram.currentDirectory = currentDirectory;
    }


}
