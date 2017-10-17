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
    }

    protected void exit(){
        console.print("Bye");
        System.exit(0);
    }

    protected void unknown(){
        console.print("unknown command...");
    }


}
