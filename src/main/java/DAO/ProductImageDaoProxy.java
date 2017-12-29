/*
 * This project was created by Connor Pakenham 29-Dec-2017
 * All Rights Reserved * 
 */
package DAO;

import DTO.ProductImage;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public class ProductImageDaoProxy implements ProductImageDaoInterface {

    private ProductImageDao productImageDao;
    
    public ProductImageDaoProxy() {
        productImageDao = new ProductImageDao("gamestore");
    }
    
    @Override
    public boolean addImageToProduct(ProductImage img) {
        return productImageDao.addImageToProduct(img);
    }

    @Override
    public boolean deleteProductImage(int image_id) {
        return productImageDao.deleteProductImage(image_id);
    }

    @Override
    public ArrayList<ProductImage> getProductImageById(int product_id) {
        return productImageDao.getProductImageById(product_id);
    }

    @Override
    public ProductImage getImageById(int image_id) {
        return productImageDao.getImageById(image_id);
    }

    @Override
    public boolean checkImageName(String imageName) {
        return productImageDao.checkImageName(imageName);
    }
    
}
