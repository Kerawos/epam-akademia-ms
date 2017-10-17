package pl.mareksowa.models;

import pl.mareksowa.controllers.UserController;
import pl.mareksowa.views.SimpleConsole;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShellProgram {

    /*
    task:
    get rid of statics here
     */

    //declarations
    private SimpleConsole console;
    private UserController controller;
    private ShellCommand command;
    private String input;
    private List<String> listOfDir;
    private String prompt = "$";
    private boolean isPromptDirectory;

    private String directory;
    private File currentDirectory;
    private int statCdSuccess, statCdFail, statTreeSuccess, statTreeFail, statDirSuccess, statDirFail;
    private int statPromptSuccess, statPromptFail, statStatisticsSuccess, statStatisticsFail;

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
        System.out.println("reset uruchomiony " + getPrompt());
        setPrompt("asdfsfsdfasdfa");
        System.out.println("reset uruchomiony " + getPrompt());
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


    public boolean isIsPromptDirectory() {
        return isPromptDirectory;
    }

    public void setIsPromptDirectory(boolean promptDirectory) {
        isPromptDirectory = promptDirectory;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public int getStatCdSuccess() {
        return statCdSuccess;
    }

    public void setStatCdSuccess(int statCdSuccess) {
        this.statCdSuccess = statCdSuccess;
    }

    public int getStatCdFail() {
        return statCdFail;
    }

    public void setStatCdFail(int statCdFail) {
        this.statCdFail = statCdFail;
    }

    public int getStatTreeSuccess() {
        return statTreeSuccess;
    }

    public void setStatTreeSuccess(int statTreeSuccess) {
        this.statTreeSuccess = statTreeSuccess;
    }

    public int getStatTreeFail() {
        return statTreeFail;
    }

    public void setStatTreeFail(int statTreeFail) {
        this.statTreeFail = statTreeFail;
    }

    public int getStatDirSuccess() {
        return statDirSuccess;
    }

    public void setStatDirSuccess(int statDirSuccess) {
        this.statDirSuccess = statDirSuccess;
    }

    public int getStatDirFail() {
        return statDirFail;
    }

    public void setStatDirFail(int statDirFail) {
        this.statDirFail = statDirFail;
    }

    public int getStatPromptSuccess() {
        return statPromptSuccess;
    }

    public void setStatPromptSuccess(int statPromptSuccess) {
        this.statPromptSuccess = statPromptSuccess;
    }

    public int getStatPromptFail() {
        return statPromptFail;
    }

    public void setStatPromptFail(int statPromptFail) {
        this.statPromptFail = statPromptFail;
    }

    public int getStatStatisticsSuccess() {
        return statStatisticsSuccess;
    }

    public void setStatStatisticsSuccess(int statStatisticsSuccess) {
        this.statStatisticsSuccess = statStatisticsSuccess;
    }

    public int getStatStatisticsFail() {
        return statStatisticsFail;
    }

    public void setStatStatisticsFail(int statStatisticsFail) {
        this.statStatisticsFail = statStatisticsFail;
    }
}
