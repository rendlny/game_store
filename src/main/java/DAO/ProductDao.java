/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Product;
import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public class ProductDao extends Dao implements ProductDaoInterface {

    public ProductDao(String database) {
        super(database);
    }

    /**
     * This method is used to add a product to the product table
     *
     * @param activeUser 
     * @param p a product object containing user input passed from add product
     * form
     * @return boolean, true if the product added successfully, false if not
     */
    @Override
    public boolean addProduct(User activeUser, Product p) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String query = "INSERT INTO products(product_name, "
                    + "product_desc, product_price, vat_percentage, stock) "
                    + "VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, p.getProduct_name());
            ps.setString(2, p.getProduct_desc());
            ps.setDouble(3, p.getProduct_price());
            ps.setDouble(4, p.getVat_percentage());
            ps.setInt(5, p.getStock());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                added = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the addProduct() method: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addProduct() method: " + e.getMessage());
            }
        }

        return added;
    }

    /**
     * This method deletes a product using its product id
     *
     * @param product_id is the id of a specific product
     * @return boolean, true if the product was deleted , false if not
     */
    @Override
    public boolean deleteProduct(User activeUser, int product_id) {
        boolean deleted = false;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "DELETE FROM products WHERE product_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, product_id);
            int affected_rows = ps.executeUpdate();

            if (affected_rows > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteProduct()"
                    + " method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the deleteProduct() method: " + e.getMessage());
            }
        }

        return deleted;
    }

    /**
     * this method displays all products to the user
     *
     * @return arraylist of all products in the product table
     */
    @Override
    public ArrayList<Product> listAllProducts() {

        ArrayList<Product> allProducts = new ArrayList();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM products";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price"),
                        rs.getDouble("vat_percentage"),
                        rs.getInt("stock")
                );
                allProducts.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the listAllProducts() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the listAllProducts() method: " + e.getMessage());
            }
        }

        return allProducts;
    }

    /**
     * This method is used to find a specific product
     *
     * @param searchTerm- the product thats being searched for
     * @return arraylist of products that match the search term
     */
    @Override
    public ArrayList<Product> searchProducts(String searchTerm) {
        ArrayList<Product> searchResults = new ArrayList();
        Product p = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            String query = "SELECT * FROM products WHERE product_name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, searchTerm);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price"),
                        rs.getDouble("vat_percentage"),
                        rs.getInt("stock")
                );
                searchResults.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the searchProducts() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchProducts() method: " + e.getMessage());
            }
        }

        return searchResults;
    }

    /**
     * This method finds products that contain search criteria entered by the
     * user
     *
     * @param searchTerm - search criteria entered by the user
     * @return arraylist of results that match the search criteria
     */
    @Override
    public ArrayList<Product> searchProductsLike(String searchTerm) {
        ArrayList<Product> searchResults = new ArrayList();
        Product p = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            String query = "SELECT * FROM products WHERE product_name LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + searchTerm + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price"),
                        rs.getDouble("vat_percentage"),
                        rs.getInt("stock")
                );
                searchResults.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the searchProductsLike() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchProductsLike() method: " + e.getMessage());
            }
        }

        return searchResults;
    }

    /**
     * This method is used to return a product object that matches a product id
     *
     * @param product_id id of the product you want to return
     * @return product object that matches the id
     */
    @Override
    public Product getProductById(int product_id) {
        Product p = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM products WHERE product_id = ?";

            ps = con.prepareStatement(query);

            ps.setInt(1, product_id);

            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price"),
                        rs.getDouble("vat_percentage"),
                        rs.getInt("stock"),
                        rs.getInt("steam_app_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getProductById()"
                    + " method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of"
                        + " the getProductById() method: " + e.getMessage());
            }
        }

        return p;
    }

    /**
     * This method returns the last 8 products to be added to the shop
     *
     * @return the last 8 products added to the shop
     */
    @Override
    public ArrayList<Product> recentProducts() {
        ArrayList<Product> results = new ArrayList();
        Product p = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            String query = "SELECT * FROM products "
                    + "ORDER BY product_id DESC "
                    + "LIMIT 8";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_desc"),
                        rs.getDouble("product_price"),
                        rs.getDouble("vat_percentage"),
                        rs.getInt("stock")
                );
                results.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the searchProductsLike() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the searchProductsLike() method: " + e.getMessage());
            }
        }

        return results;
    }

    /**
     * This method is used to update a product
     *
     * @param p - Product object containing user input from update product form
     * @return boolean, true if the product was updated successfully,0 if not
     */
    @Override
    public boolean updateProduct(User activeUser, Product p) {
        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        int affectedRows = 0;

        try {
            con = getConnection();

            String query = "UPDATE products SET product_name = ?, "
                    + "product_desc = ?, product_price = ?, vat_percentage = ?,"
                    + " stock = ?  WHERE product_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, p.getProduct_name());
            ps.setString(2, p.getProduct_desc());
            ps.setDouble(3, p.getProduct_price());
            ps.setDouble(4, p.getVat_percentage());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getProduct_id());
            affectedRows = ps.executeUpdate();

            if (affectedRows != 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateProduct() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateProduct() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method updates the quantity of a specific product
     *
     * @param product_id the id of the product to be updated
     * @param newQty the new quantity of the product
     * @return boolean, true if the product was updated, false if not
     */
    public boolean updateProductQuantity(int product_id, int newQty) {
        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        int affectedRows = 0;

        try {
            con = getConnection();

            String query = "UPDATE products SET stock = ? WHERE product_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, newQty);
            ps.setInt(2, product_id);

            affectedRows = ps.executeUpdate();

            if (affectedRows != 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateProduct() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateProduct() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean addProductWithSteamId(User activeUser, Product p) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String query = "INSERT INTO products(product_name, "
                    + "product_desc, product_price, vat_percentage, stock, steam_app_id) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, p.getProduct_name());
            ps.setString(2, p.getProduct_desc());
            ps.setDouble(3, p.getProduct_price());
            ps.setDouble(4, p.getVat_percentage());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getSteam_app_id());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                added = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the addProductWithSteamId() method: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addProductWithSteamId() method: " + e.getMessage());
            }
        }

        return added;
    }
}
