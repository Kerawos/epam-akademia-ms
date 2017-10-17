package pl.mareksowa.models;

import pl.mareksowa.views.SimpleConsole;

public class ShellCommand {

    private SimpleConsole simpleConsole = new SimpleConsole();

    protected void exit(){
        simpleConsole.print("Bye");
        System.exit(0);
    }

    protected void unknown(){
        simpleConsole.print("unknown command...");
    }


}
