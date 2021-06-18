package com.company;

public class InvalidCoulmnType extends  CreatCommandException {
    @Override
    public String getMessage() {
        return "The Second Member(Column) type is invalid,try 1-3";
    }
}
