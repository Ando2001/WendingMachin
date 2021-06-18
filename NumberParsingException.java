package com.company;

public class NumberParsingException extends NumberFormatException{
    @Override
    public String getMessage(){
        return "The Second member of CommandRow is not number";
    }
}
