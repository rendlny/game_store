package DAO;
//lauren

import DTO.Address;
import DTO.User;
import java.util.ArrayList;

public class AddressDaoProxy implements AddressDaoInterface {

    private AddressDao addressDao;

    public AddressDaoProxy() {
        addressDao = new AddressDao("gamestore");
    }

    public ArrayList<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    public int addAddress(User activeUser, Address address) {
        if (activeUser.getIs_admin() == 0 || activeUser.getIs_admin() == 1) {
            return addressDao.addAddress(activeUser, address);
        } else {
            return -2;
        }
    }

    @Override
    public int deleteAddressByAddressId(User activeUser, int addressId, int userId) {
        if (activeUser.getUserId() == userId || activeUser.getIs_admin() == 1) {
            return addressDao.deleteAddressByAddressId(activeUser, addressId, userId);
      }else{
        return -2;
        }
    }

    @Override
    public ArrayList<Address> getAddressByUserId(int userId) {
         return addressDao.getAddressByUserId(userId);
    }

    @Override
    public boolean updateAddress(User activeUser, int userId, String add1, String add2, String city, String county, String country) {
        if (activeUser.getUserId() == userId || activeUser.getIs_admin() == 1) {
            return addressDao.updateAddress(activeUser, userId,add1, add2, city, county, country);
        } else {
            return false;
        }
    }
}
