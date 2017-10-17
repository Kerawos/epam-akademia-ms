package pl.mareksowa.controllers;

import java.util.Scanner;

public class UserController {

    private Scanner scanner;

    public UserController() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput(){
        return scanner.nextLine();
    }
}
