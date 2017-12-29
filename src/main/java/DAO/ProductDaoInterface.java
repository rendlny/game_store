/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Product;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public interface ProductDaoInterface {
    public boolean addProduct(User activeUser, Product p);
    public boolean addProductWithSteamId(User activeUser, Product p);
    public boolean deleteProduct(User activeUser, int product_id);
    public boolean updateProduct(User activeUser, Product p);
    public ArrayList<Product> listAllProducts();
    public ArrayList<Product> searchProducts(String searchTerm);
    public ArrayList<Product> searchProductsLike(String searchTerm);
    public Product getProductById(int product_id);
    public ArrayList<Product> recentProducts();
}
