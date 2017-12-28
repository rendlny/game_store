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
public class OrderLine {
 
    private int line_id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double sale_price;
    
    public OrderLine() {
        line_id = -1;
        order_id = -1;
        product_id = -1;
        quantity = -1;
        sale_price = -1;
    }

    public OrderLine(int line_id, int order_id, int product_id, int quantity, double sale_price) {
        this.line_id = line_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.sale_price = sale_price;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.order_id;
        hash = 29 * hash + this.product_id;
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
        final OrderLine other = (OrderLine) obj;
        if (this.order_id != other.order_id) {
            return false;
        }
        if (this.product_id != other.product_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order_line{" + "line_id=" + line_id + ", order_id=" + order_id + ", product_id=" + product_id + ", quantity=" + quantity + ", sale_price=" + sale_price + '}';
    }
}
