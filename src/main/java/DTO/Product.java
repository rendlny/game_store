/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Observer.Observable;
import Observer.Observer;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public class Product implements Observable {

    private ArrayList<Observer> observers = new ArrayList();
    private int product_id;
    private String product_name;
    private String product_desc;
    private double product_price;
    private double vat_percentage;
    private int stock;
    private int steam_app_id;

    public Product() {
        product_id = -1;
        product_name = null;
        product_desc = null;
        product_price = -1;
        vat_percentage = -1;
        stock = -1;
        steam_app_id = 0;
    }

    public Product(int product_id, String product_name, String product_desc,
            double product_price, double vat_percentage, int stock) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.vat_percentage = vat_percentage;
        this.stock = stock;
        steam_app_id = 0;
    }

    public Product(int product_id, String product_name, String product_desc,
            double product_price, double vat_percentage, int stock, int steam_app_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.vat_percentage = vat_percentage;
        this.stock = stock;
        this.steam_app_id = steam_app_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public double getVat_percentage() {
        return vat_percentage;
    }

    public void setVat_percentage(double vat_percentage) {
        this.vat_percentage = vat_percentage;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSteam_app_id() {
        return steam_app_id;
    }

    public void setSteam_app_id(int steam_app_id) {
        this.steam_app_id = steam_app_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.product_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.product_id != other.product_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", product_name=" + product_name + ", product_desc=" + product_desc + ", product_price=" + product_price + ", vat_percentage=" + vat_percentage + ", stock=" + stock + ", steam_app_id=" + steam_app_id + '}';
    }

    @Override
    public synchronized boolean register(Observer o){
      if(o != null && !observers.contains(o)){
        observers.add(o);
        System.out.println("Adding Observer: " + o.toString() + " to observers for " + product_name + ".");
        return true;
      }
      return false;
    }

    @Override
    public synchronized boolean unregister(Observer o){
      if(o!= null && observers.remove(o)){
        System.out.println("Removed Observer: " + o.toString() + " from observers for " + product_name + ".");
        return true;
      }
      return false;
    }

    @Override
    public synchronized void notifyObservers(){
      observers.stream().forEach((o) ->
      {
        o.update();
      });
    }
}
