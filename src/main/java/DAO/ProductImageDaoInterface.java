/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductImage;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public interface ProductImageDaoInterface {
    public boolean addImageToProduct(ProductImage img);
    public boolean deleteProductImage(int image_id);
    public ArrayList<ProductImage> getProductImageById(int product_id);
    public ProductImage getImageById(int image_id);
    public boolean checkImageName(String imageName);
}
