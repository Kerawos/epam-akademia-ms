package pl.mareksowa.models;

import pl.mareksowa.views.SimpleConsole;

import java.io.File;

public class ShellCommand {

    private SimpleConsole console;
    private ShellProgram shellProgram;
    private int treeCounter;

    public ShellCommand() {
        console = new SimpleConsole();
        shellProgram = new ShellProgram();
    }

    protected void dir(){
        shellProgram.setCurrentDirectory(new File(shellProgram.getDirectory()));
        console.print("Content of " + shellProgram.getDirectory());
        //creating files array
        File[] filesList = shellProgram.getCurrentDirectory().listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                console.print("DIR      " + f.getName());
            if(f.isFile()){
                console.print("FILE     " + f.getName());
            }
        }
        shellProgram.setStatDirSuccess(shellProgram.getStatDirSuccess()+1);
    }

    protected void cd(String input){
        if (input.substring(3,input.length()).equals("..")){
            if (shellProgram.getDirectory().contains("\\")){
                shellProgram.setDirectory(shellProgram.getDirectory().substring(
                        0, shellProgram.getDirectory().lastIndexOf("\\")));
                shellProgram.setStatCdSuccess(shellProgram.getStatDirSuccess()+1);
            } else {
                shellProgram.setStatCdFail(shellProgram.getStatDirFail()+1);
            }
        } else {
            if (shellProgram.checkIfDirIsCorrected(input.substring(3, input.length()))) {
                shellProgram.setDirectory(shellProgram.getDirectory() +
                        "\\" + input.substring(3, input.length()));
                shellProgram.setStatCdSuccess(shellProgram.getStatDirSuccess()+1);
            } else {
                shellProgram.setStatCdFail(shellProgram.getStatDirFail()+1);
            }
        }
    }

    protected void prompt(String input){
        if (input.substring(7, input.length()).equals("reset")){
            shellProgram.promptReset();
        } else if(input.substring(7, input.length()).equals("$cwd")){
            //change boolean -> update prompt
            shellProgram.setIsPromptDirectory(true);
        } else {
            shellProgram.setIsPromptDirectory(false);
            shellProgram.setPrompt(input.substring(7, input.length()));
        }
        shellProgram.setStatPromptSuccess(shellProgram.getStatPromptSuccess()+1);
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
        shellProgram.setStatStatisticsSuccess(shellProgram.getStatPromptSuccess()+1);
        stringBuilder.append("cd:"+shellProgram.getStatCdSuccess()+":"+shellProgram.getStatCdFail()+ "\n");
        stringBuilder.append("tree:"+shellProgram.getStatTreeSuccess()+":"+shellProgram.getStatTreeFail()+ "\n");
        stringBuilder.append("dir:"+shellProgram.getStatDirSuccess()+":"+shellProgram.getStatDirFail()+ "\n");
        stringBuilder.append("prompt:"+shellProgram.getStatPromptSuccess()+":"+shellProgram.getStatPromptFail()+ "\n");
        stringBuilder.append("statistics:"+shellProgram.getStatStatisticsSuccess()+":"+shellProgram.getStatStatisticsFail()+ "\n");
        return stringBuilder.toString();
    }

    protected void exit(){
        console.print("Bye");
        System.exit(0);
    }

    protected void unknown(){
        console.print("unknown command...");
    }

}
