package com.company;

public class DynamicListArray {
    private Product[] products = new Product[20];

    public DynamicListArray(Product product){
        for (int i = 0; i < 20; i++) {
            products[i]=product;
        }
    }
    public Product[] pop(int count){
        Product[] returnedProducts = new Product[count];
        int j =0;
        for (int i = products.length-1; i >= products.length-count ; i--) {
            returnedProducts[j] =products[i];
            j++;
        }
        Product[] newObject = new Product[products.length-count];
        for (int i = 0; i < products.length-count; i++) {
            newObject[i]=products[i];
        }
        products=newObject;
        return returnedProducts;
    }
    public void pull(int count,Product product){
        for (int i = count; i < 20; i++) {
            products[i]=product;
        }
    }
    public Product[] getProducts(){
        return products;
    }
}
