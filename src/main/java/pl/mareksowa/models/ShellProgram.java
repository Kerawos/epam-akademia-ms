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
    private static int statCdSuccess, statCdFail, statTreeSuccess, statTreeFail, statDirSuccess, statDirFail;
    private static int statPromptSuccess, statPromptFail, statStatisticsSuccess, statStatisticsFail;


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
                        command.tree(getCurrentDirectory());
                        //System.out.println("not implemented yet");
                        break;
                    }
                    case "statistics": {
                        console.print(command.statistics());
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
            setPrompt(getDirectory());
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

    //getters and setters
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

    public static int getStatCdSuccess() {
        return statCdSuccess;
    }

    public static void setStatCdSuccess(int statCdSuccess) {
        ShellProgram.statCdSuccess = statCdSuccess;
    }

    public static int getStatCdFail() {
        return statCdFail;
    }

    public static void setStatCdFail(int statCdFail) {
        ShellProgram.statCdFail = statCdFail;
    }

    public static int getStatTreeSuccess() {
        return statTreeSuccess;
    }

    public static void setStatTreeSuccess(int statTreeSuccess) {
        ShellProgram.statTreeSuccess = statTreeSuccess;
    }

    public static int getStatTreeFail() {
        return statTreeFail;
    }

    public static void setStatTreeFail(int statTreeFail) {
        ShellProgram.statTreeFail = statTreeFail;
    }

    public static int getStatDirSuccess() {
        return statDirSuccess;
    }

    public static void setStatDirSuccess(int statDirSucces) {
        ShellProgram.statDirSuccess = statDirSucces;
    }

    public static int getStatDirFail() {
        return statDirFail;
    }

    public static void setStatDirFail(int statDirFail) {
        ShellProgram.statDirFail = statDirFail;
    }

    public static int getStatPromptSuccess() {
        return statPromptSuccess;
    }

    public static void setStatPromptSuccess(int statPromptSucces) {
        ShellProgram.statPromptSuccess = statPromptSucces;
    }

    public static int getStatPromptFail() {
        return statPromptFail;
    }

    public static void setStatPromptFail(int statPromptFail) {
        ShellProgram.statPromptFail = statPromptFail;
    }

    public static int getStatStatisticsSuccess() {
        return statStatisticsSuccess;
    }

    public static void setStatStatisticsSuccess(int statStatisticsSucces) {
        ShellProgram.statStatisticsSuccess = statStatisticsSucces;
    }

    public static int getStatStatisticsFail() {
        return statStatisticsFail;
    }

    public static void setStatStatisticsFail(int statStatisticsFail) {
        ShellProgram.statStatisticsFail = statStatisticsFail;
    }
}
