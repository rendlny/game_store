/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Conno
 */
public class Order {
    private int order_id;
    private int user_id;
    private double total_amount_due;
    private int total_quanity;
    private int address_id;
    private String order_date;
    
    public Order(){
        order_id = -1;
        user_id = -1;
        total_amount_due = -1;
        total_quanity = -1;
        address_id = -1;
        order_date = null;
    }

    public Order(int order_id, int user_id, double total_amount_due, 
                int total_quanity, int address_id, String order_date) 
    {
        this.order_id = order_id;
        this.user_id = user_id;
        this.total_amount_due = total_amount_due;
        this.total_quanity = total_quanity;
        this.address_id = address_id;
        this.order_date = order_date;
    }
    
    

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal_amount_due() {
        return total_amount_due;
    }

    public void setTotal_amount_due(double total_amount_due) {
        this.total_amount_due = total_amount_due;
    }

    public int getTotal_quanity() {
        return total_quanity;
    }

    public void setTotal_quanity(int total_quanity) {
        this.total_quanity = total_quanity;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.order_id;
        hash = 37 * hash + this.user_id;
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
        final Order other = (Order) obj;
        if (this.order_id != other.order_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", user_id=" + user_id + ", total_amount_due=" + total_amount_due + ", total_quanity=" + total_quanity + ", address_id=" + address_id + ", order_date=" + order_date + '}';
    }
}
