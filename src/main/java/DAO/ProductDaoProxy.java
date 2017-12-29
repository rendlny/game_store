/*
 * This project was created by Connor Pakenham 29-Dec-2017
 * All Rights Reserved * 
 */
package DAO;

import DTO.Product;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public class ProductDaoProxy implements ProductDaoInterface {

    private ProductDao productDao;
    
    public ProductDaoProxy() {
        productDao = new ProductDao("gamestore");
    }
    
    
    @Override
    public boolean addProduct(User activeUser, Product p) {
        if (activeUser.getIs_admin() == 0 || activeUser.getIs_admin() == 1) {
            return productDao.addProduct(activeUser, p);
        } else {
            return false;
        }
    }

    @Override
    public boolean addProductWithSteamId(User activeUser, Product p) {
        if (activeUser.getIs_admin() == 0 || activeUser.getIs_admin() == 1) {
            return productDao.addProductWithSteamId(activeUser, p);
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(User activeUser, int product_id) {
        if (activeUser.getIs_admin() == 0 || activeUser.getIs_admin() == 1) {
            return productDao.deleteProduct(activeUser, product_id);
        } else {
            return false;
        }
    }

    @Override
    public boolean updateProduct(User activeUser, Product p) {
        if (activeUser.getIs_admin() == 0 || activeUser.getIs_admin() == 1) {
            return productDao.updateProduct(activeUser, p);
        } else {
            return false;
        }}

    @Override
    public ArrayList<Product> listAllProducts() {
        return productDao.listAllProducts();
    }

    @Override
    public ArrayList<Product> searchProducts(String searchTerm) {
        return productDao.searchProducts(searchTerm);
    }

    @Override
    public ArrayList<Product> searchProductsLike(String searchTerm) {
        return productDao.searchProductsLike(searchTerm);
    }

    @Override
    public Product getProductById(int product_id) {
        return productDao.getProductById(product_id);
    }

    @Override
    public ArrayList<Product> recentProducts() {
        return productDao.recentProducts();
    }
    
}
