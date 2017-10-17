package pl.mareksowa.models;

import pl.mareksowa.controllers.UserController;
import pl.mareksowa.views.SimpleConsole;

public class ShellProgram {

    //declarations
    private SimpleConsole console;
    private UserController controller;
    private ShellCommand command;
    private String input;


    //main program
    public void startShellProgram(){

        //initialization
        console = new SimpleConsole();
        controller = new UserController();
        command = new ShellCommand();

        //main loop
        while (true){
            console.print("$>");
            input = controller.getUserInput();
            console.print("User said: " + input);

            switch (input){

                case "dir":{
                    //todo
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

}
