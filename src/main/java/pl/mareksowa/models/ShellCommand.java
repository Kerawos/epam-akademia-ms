package pl.mareksowa.models;

import pl.mareksowa.views.SimpleConsole;

import java.io.File;

public class ShellCommand {

    private SimpleConsole console;
    private ShellProgram shellProgram;

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
        shellProgram.setStatDirSucces(shellProgram.getStatDirSucces()+1);
    }

    protected void cd(String input){
        if (input.substring(3,input.length()).equals("..")){
            if (shellProgram.getDirectory().contains("\\")){
                shellProgram.setDirectory(shellProgram.getDirectory().substring(
                        0, shellProgram.getDirectory().lastIndexOf("\\")));
                shellProgram.setStatCdSuccess(shellProgram.getStatDirSucces()+1);
            } else {
                shellProgram.setStatCdFail(shellProgram.getStatDirFail()+1);
            }
        } else {
            if (shellProgram.checkIfDirIsCorrected(input.substring(3, input.length()))) {
                shellProgram.setDirectory(shellProgram.getDirectory() +
                        "\\" + input.substring(3, input.length()));
                shellProgram.setStatCdSuccess(shellProgram.getStatDirSucces()+1);
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
            shellProgram.setPrompt(input.substring(7, input.length()));
        }
        shellProgram.setStatPromptSucces(shellProgram.getStatPromptSucces()+1);
    }

    protected void tree(){
        //todo tree
    }


    protected void statistics(){
        shellProgram.setStatStatisticsSucces(shellProgram.getStatPromptSucces()+1);
        console.print("cd:"+shellProgram.getStatCdSuccess()+":"+shellProgram.getStatCdFail());
        console.print("tree:"+shellProgram.getStatTreeSuccess()+":"+shellProgram.getStatTreeFail());
        console.print("dir:"+shellProgram.getStatDirSucces()+":"+shellProgram.getStatDirFail());
        console.print("prompt:"+shellProgram.getStatPromptSucces()+":"+shellProgram.getStatPromptFail());
        console.print("statistics:"+shellProgram.getStatStatisticsSucces()+":"+shellProgram.getStatStatisticsFail());
    }


    protected void exit(){
        console.print("Bye");
        System.exit(0);
    }

    protected void unknown(){
        console.print("unknown command...");
    }

}
