package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine implements Serializable {

    public VendingMachine(){
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\user\\IdeaProjects\\WendingMachine\\src\\com\\company\\database");
            fill();
            if(inputStream.available()==0) {
                setVendingMachineToDatabase(this);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<ArrayList<Product>>> vendingMachine = new HashMap<>();


    public ArrayList<Product> getProducts(Command command){
        VendingMachine currentVendingMachine = getVendingMachineFromDatabase();

        ArrayList<Product> productsToReturn = new ArrayList<Product>();
        ArrayList<ArrayList<Product>> currentProductRow = currentVendingMachine.vendingMachine.get(command.rowNumber);
        ArrayList<Product> currentProductColumn = currentProductRow.get(command.columnNumber-1);
        if(currentProductColumn.size()<command.quantity || currentProductColumn.size()==0){
            if(currentProductColumn.size()==0){
                Product currentProduct = vendingMachine.get(command.rowNumber).get(command.columnNumber-1).get(0);
                fillColumn(currentProductColumn,currentProduct);
            }
            else {
                fillColumn(currentProductColumn, currentProductColumn.get(0));
            }
        }
        for(int i = 0;i<command.quantity;i++){
            productsToReturn.add(currentProductColumn.get(currentProductColumn.size()-1));
            currentProductColumn.remove(currentProductColumn.size()-1);
        }
        System.out.println("You have bought "+productsToReturn.size()+" times ");
        for(int i = 0;i<productsToReturn.size();i++){
            System.out.print(productsToReturn.get(0).getClass().getTypeName());
        }
        setVendingMachineToDatabase(currentVendingMachine);
        return productsToReturn;
    }
    public void setVendingMachineToDatabase(VendingMachine vendingMachine){
        try {
            File file = new File("C:\\Users\\user\\IdeaProjects\\WendingMachine\\src\\com\\company\\database.txt");
            OutputStream outputStream = new FileOutputStream("C:\\Users\\user\\IdeaProjects\\WendingMachine\\src\\com\\company\\database.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(vendingMachine);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VendingMachine getVendingMachineFromDatabase(){
        VendingMachine currentVendingMachine = null;
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\user\\IdeaProjects\\WendingMachine\\src\\com\\company\\database.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            currentVendingMachine = (VendingMachine) objectInputStream.readObject();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currentVendingMachine;
    }


    private  void fill(){
        ArrayList<ArrayList<Product>> drinksRow = new ArrayList<>();
        ArrayList<Product> cocaColaColumn = new ArrayList<>();
        fillColumn(cocaColaColumn,new Boom());
        ArrayList<Product> fantaColumn = new ArrayList<>();
        fillColumn(fantaColumn,new Fanta());
        ArrayList<Product> spriteColumn = new ArrayList<>();
        fillColumn(spriteColumn,new Sprite());

        drinksRow.add(cocaColaColumn);
        drinksRow.add(fantaColumn);
        drinksRow.add(spriteColumn);
        vendingMachine.put("A",drinksRow);

        ArrayList<ArrayList<Product>> sigaretRow = new ArrayList<>();
        ArrayList<Product> sigaretColumn = new ArrayList<>();
        fillColumn(sigaretColumn,new Winston());
        ArrayList<Product> sigaretColumn2 = new ArrayList<>();
        fillColumn(sigaretColumn2,new Parlament());
        ArrayList<Product> sigaretColumn3 = new ArrayList<Product>();
        fillColumn(sigaretColumn3,new Mallboro());

        sigaretRow.add(sigaretColumn);
        sigaretRow.add(sigaretColumn2);
        sigaretRow.add(sigaretColumn3);
        vendingMachine.put("B",sigaretRow);

        ArrayList<Product> chocolateColumn = new ArrayList<>();
        fillColumn(chocolateColumn,new Twix());
        ArrayList<Product> chocolateColumn2 = new ArrayList<>();
        fillColumn(chocolateColumn2,new Bounty());
        ArrayList<Product> chocolateColumn3 = new ArrayList<>();
        fillColumn(chocolateColumn3,new Mars());

        ArrayList<ArrayList<Product>> chocoltaeRow = new ArrayList<>();
        chocoltaeRow.add(chocolateColumn);
        chocoltaeRow.add(chocolateColumn2);
        chocoltaeRow.add(chocolateColumn3);
        vendingMachine.put("C",chocoltaeRow);
    }

    private void fillColumn(ArrayList<Product> list,Product product){
        for(int i = list.size();i<20;i++){
            list.add(product);
        }
    }
}

