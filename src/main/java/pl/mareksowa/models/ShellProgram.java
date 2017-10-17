package pl.mareksowa.models;

import pl.mareksowa.controllers.UserController;
import pl.mareksowa.views.SimpleConsole;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShellProgram {

    //declarations
    private SimpleConsole console;
    private UserController controller;
    private ShellCommand command;
    private String input;
    private List<String> listOfDir;
    private static boolean isPromptDirectory;



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

            //check cd command
            if (input.substring(0, 3).equals("cd ")){
                command.cd(input);
            } else {
                switch (input){

                    case "dir":{
                        command.dir();
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

    //getting our directory list
    private List<String> getDir(File currentDirectory){
        File[] filesList = currentDirectory.listFiles();
        //listOfDir.clear();
        listOfDir = new ArrayList<>();
        for(File f : filesList){
            if(f.isDirectory())
                listOfDir.add(f.getName());
        }
        return listOfDir;
    }

    //checking our directory
    protected boolean checkIfDirIsCorrected(String userCdInput){
        listOfDir = getDir(getCurrentDirectory());
        for (String dir : listOfDir) {
            if (dir.equals(userCdInput)){
                return true;
            }
        }
        return false;
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
    public static boolean isIsPromptDirectory() {
        return isPromptDirectory;
    }

    public static void setIsPromptDirectory(boolean isPromptDirectory) {
        ShellProgram.isPromptDirectory = isPromptDirectory;
    }



}
