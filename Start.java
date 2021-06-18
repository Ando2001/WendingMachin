package com.company;

import java.util.Scanner;

public class Start {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input order");
        String commandRow = scanner.nextLine();
        try{

            Command command = Command.createCommand(commandRow);
            VendingMachine vendingMachine = new VendingMachine();
            vendingMachine.getProducts(command);
        }
        catch (NumberFormatException ex){
            System.out.println("The command members besides first must be number");
        }
        catch (CreatCommandException ex){
            System.out.println(ex.getMessage());
        }
    }
}
