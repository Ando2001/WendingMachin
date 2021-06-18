package com.company;

public class InvalidRowType extends CreatCommandException{
    @Override
    public String getMessage(){
        return "Invalid Row Type,Input A/B/C";
    }
}
