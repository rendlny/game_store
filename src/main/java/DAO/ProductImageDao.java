/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Dao;
import DAO.ProductImageDaoInterface;
import DTO.ProductImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ren
 */
public class ProductImageDao extends Dao implements ProductImageDaoInterface {

    public ProductImageDao(String database) {
        super(database);
    }

    /**
     * This method adds the id and url of the product image to the product image
     * table
     *
     * @param img - is an object that contains the id of the product and the
     * image url of the product
     * @return boolean, true if the image was added, false if it was not
     */
    @Override
    public boolean addImageToProduct(ProductImage img) {
        boolean added = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO product_images( product_id, image_url) "
                    + "VALUES(?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, img.getProduct_id());
            ps.setString(2, img.getImage_url());

            int result = ps.executeUpdate();
            if (result == 1) {
                added = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the addImageToProduct() method: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addImageToProduct() method: " + e.getMessage());
            }
        }

        return added;
    }

    /**
     * This method deletes a product image from the table by its image id
     *
     * @param image_id - is a unique id find the specific product image
     * @return boolean, true if the image was deleted, false if not
     */
    @Override
    public boolean deleteProductImage(int image_id) {
        boolean deleted = false;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "DELETE FROM product_images WHERE image_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, image_id);
            int affected_rows = ps.executeUpdate();

            if (affected_rows == 1) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteProductImage()"
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
                        + " the deleteProductImage() method: " + e.getMessage());
            }
        }

        return deleted;
    }

    /**
     * This method uses the product id to get a list of images of that specific
     * product
     *
     * @param product_id - the the id of the product to get its images
     * @return arraylist images for the specific product
     */
    @Override
    public ArrayList<ProductImage> getProductImageById(int product_id) {
        ArrayList<ProductImage> productImages = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM product_images WHERE product_id = ?";

            ps = con.prepareStatement(query);

            ps.setInt(1, product_id);

            rs = ps.executeQuery();

            productImages = new ArrayList<>();

            while (rs.next()) {
                ProductImage img = new ProductImage(
                        rs.getInt("image_id"),
                        rs.getInt("product_id"),
                        rs.getString("image_url")
                );
                productImages.add(img);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getProductImageById()"
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
                        + " the getProductImageById() method: " + e.getMessage());
            }
        }
        return productImages;
    }

    /**
     * this method uses image id to select a single unique image for a prodcut
     *
     * @param image_id id of the unique image to be selected
     * @return an product image object that matches the specific image
     */
    @Override
    public ProductImage getImageById(int image_id) {
        ProductImage image = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM product_images WHERE image_id = ?";

            ps = con.prepareStatement(query);

            ps.setInt(1, image_id);

            rs = ps.executeQuery();

            while (rs.next()) {
                image = new ProductImage(
                        rs.getInt("image_id"),
                        rs.getInt("product_id"),
                        rs.getString("image_url")
                );
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getImageById()"
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
                        + " the getImageById() method: " + e.getMessage());
            }
        }
        return image;
    }

    @Override
    public boolean checkImageName(String imageName) {
        boolean found = false;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT image_url FROM product_images WHERE image_url = ?";

            ps = con.prepareStatement(query);

            ps.setString(1, imageName);

            rs = ps.executeQuery();

            if (rs.next()) {
                found = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the checkImageName()"
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
                        + " the checkImageName() method: " + e.getMessage());
            }
        }
        return found;
    }

}
