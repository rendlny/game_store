/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import javax.servlet.http.Part;

/**
 *
 * @author Ren
 */
public class ProductImage {
    private int image_id;
    private int product_id;
    private String image_url;
    
    public ProductImage(){
        image_id = -1;
        product_id = -1;
        image_url = null;
    }
    
    public ProductImage(int image_id, int product_id, String image_url){
        this.image_id = image_id;
        this.product_id = product_id;
        this.image_url = image_url;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.image_id;
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
        final ProductImage other = (ProductImage) obj;
        if (this.image_id != other.image_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductImage{" + "image_id=" + image_id + ", product_id=" + product_id + ", image_url=" + image_url + '}';
    }
    
    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
