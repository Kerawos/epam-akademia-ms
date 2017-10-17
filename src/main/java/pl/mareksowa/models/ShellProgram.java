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
    private String input;
    private List<String> listOfDir;
    private String prompt;
    private boolean isPromptDirectory;
    private int treeCounter;

    private String directory;
    private File currentDirectory;
    private int statCdSuccess, statCdFail, statTreeSuccess, statTreeFail, statDirSuccess, statDirFail;
    private int statPromptSuccess, statPromptFail, statStatisticsSuccess, statStatisticsFail;

    //main program
    public void startShellProgram(){

        //initialization
        console = new SimpleConsole();
        controller = new UserController();
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
                cd(input);
            } else if (input.length() > 8 && input.substring(0, 7).equals("prompt ")) {
                prompt(input);
            } else {
                switch (input) {
                    case "dir": {
                        dir();
                        break;
                    }
                    case "tree": {
                        tree(getCurrentDirectory());
                        break;
                    }
                    case "statistics": {
                        console.print(statistics());
                        break;
                    }
                    case "exit": {
                        exit();
                    }
                    default:
                        unknown();
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


    //commands
    protected void dir(){
        setCurrentDirectory(new File(getDirectory()));
        console.print("Content of " + getDirectory());
        //creating files array
        File[] filesList = getCurrentDirectory().listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                console.print("DIR      " + f.getName());
            if(f.isFile()){
                console.print("FILE     " + f.getName());
            }
        }
        setStatDirSuccess(getStatDirSuccess()+1);
    }

    protected void cd(String input){
        if (input.substring(3,input.length()).equals("..")){
            if (getDirectory().contains("\\")){
                setDirectory(getDirectory().substring(
                        0, getDirectory().lastIndexOf("\\")));
                setStatCdSuccess(getStatDirSuccess()+1);
            } else {
                setStatCdFail(getStatDirFail()+1);
            }
        } else {
            if (checkIfDirIsCorrected(input.substring(3, input.length()))) {
                setDirectory(getDirectory() +
                        "\\" + input.substring(3, input.length()));
                setStatCdSuccess(getStatDirSuccess()+1);
            } else {
                setStatCdFail(getStatDirFail()+1);
            }
        }
    }

    protected void prompt(String input){
        if (input.substring(7, input.length()).equals("reset")){
            promptReset();
        } else if(input.substring(7, input.length()).equals("$cwd")){
            //change boolean -> update prompt
            setIsPromptDirectory(true);
        } else {
            setIsPromptDirectory(false);
            setPrompt(input.substring(7, input.length()));
        }
        setStatPromptSuccess(getStatPromptSuccess()+1);
    }

    protected void tree(File currentFolder){
        //todo tree
        treeCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        showDirectoryTree(currentFolder, treeCounter, stringBuilder);
        console.print(stringBuilder.toString());
    }

    private void showDirectoryTree(File folder, int treeCounter,StringBuilder stringBuilder){
        stringBuilder.append(getCounterString(treeCounter));
        stringBuilder.append(folder.getName());
        stringBuilder.append("\n");
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                showDirectoryTree(file, treeCounter + 1, stringBuilder);
            }
        }
    }

    //back "-"
    private String getCounterString(int treeCounter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < treeCounter; i++) {
            stringBuilder.append("-");
        }
        return stringBuilder.toString();
    }

    protected String statistics(){
        StringBuilder stringBuilder = new StringBuilder();
        setStatStatisticsSuccess(getStatPromptSuccess()+1);
        stringBuilder.append("cd:"+getStatCdSuccess()+":"+getStatCdFail()+ "\n");
        stringBuilder.append("tree:"+getStatTreeSuccess()+":"+getStatTreeFail()+ "\n");
        stringBuilder.append("dir:"+getStatDirSuccess()+":"+getStatDirFail()+ "\n");
        stringBuilder.append("prompt:"+getStatPromptSuccess()+":"+getStatPromptFail()+ "\n");
        stringBuilder.append("statistics:"+getStatStatisticsSuccess()+":"+getStatStatisticsFail()+ "\n");
        return stringBuilder.toString();
    }

    protected void exit(){
        console.print("Bye");
        System.exit(0);
    }

    protected void unknown(){
        console.print("unknown command...");
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
