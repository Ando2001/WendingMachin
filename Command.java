package com.company;

public class Command {
    public String rowNumber;
    public int columnNumber;
    public int quantity;

    public static Command createCommand(String commandRow){
        Command command = new Command();
        String[] commandArray = commandRow.split(":");
        if(!"ABC".contains(String.valueOf(commandArray[0]))){
            throw new InvalidRowType();
        }
        else{
            command.rowNumber = commandArray[0];
        }
        if(Integer.parseInt(commandArray[1])<0 || Integer.parseInt(commandArray[1])>3){
            throw new InvalidCoulmnType();
        }
        else{
            command.columnNumber = Integer.parseInt(commandArray[1]);
        }
        if(Integer.parseInt(commandArray[2])<0){
            throw new InvalidQuantityType();
        }
        else{
            command.quantity = Integer.parseInt(commandArray[2]);
        }

        return command;
    }

}
