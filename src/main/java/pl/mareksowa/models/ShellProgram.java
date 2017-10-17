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
    private static String prompt;
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
        setPrompt("$");
        setIsPromptDirectory(false);

        //main loop
        while (true) {
            updatePrompt();
            input = controller.getUserInput();
            console.print("User said: " + input);

            //check cd command
            if (input.length() > 4 && input.substring(0, 3).equals("cd ")) {
                command.cd(input);
            } else if (input.length() > 8 && input.substring(0, 7).equals("prompt ")) {
                command.prompt(input);
            } else {
                switch (input) {
                    case "dir": {
                        command.dir();
                        break;
                    }

                    case "tree": {
                        //todo
                        break;
                    }

                    case "statistics": {
                        //todo
                        break;
                    }

                    case "exit": {
                        command.exit();
                    }

                    default:
                        command.unknown();
                        break;
                }
            }
        }
    }

    protected void updatePrompt(){
        if (isIsPromptDirectory()){
            prompt = getDirectory();
        }
        console.print(getPrompt() + ">");
    }

    protected void promptReset(){
        setPrompt("$");
        setIsPromptDirectory(false);
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

    public static String getPrompt() {
        return prompt;
    }

    public static void setPrompt(String prompt) {
        ShellProgram.prompt = prompt;
    }

    public static boolean isIsPromptDirectory() {
        return isPromptDirectory;
    }

    public static void setIsPromptDirectory(boolean isPromptDirectory) {
        ShellProgram.isPromptDirectory = isPromptDirectory;
    }

}
