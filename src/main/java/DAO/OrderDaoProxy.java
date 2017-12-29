package DAO;
//Lauren

import DTO.Order;
import DTO.User;
import java.util.ArrayList;

public class OrderDaoProxy implements OrderDaoInterface {
    private OrderDao orderDao;

    public OrderDaoProxy(){
      orderDao = new OrderDao("gamestore");
    }

    @Override
    public boolean createOrder(User activeUser, Order newOrder) {
      if(activeUser.getIs_admin() == 1 || activeUser.getUserId() == newOrder.getUser_id()){
        return orderDao.createOrder(activeUser, newOrder);
      }else{
        return false;
      }
    }

    @Override
    public boolean cancelOrder(User activeUser, Order order) {
        OrderDao orderDao = new OrderDao("gamestore");
      if(activeUser.getIs_admin() == 1 || activeUser.getUserId() == order.getUser_id()){
        return orderDao.cancelOrder(activeUser, order);
      }else{
        return false;
      }
    }

    @Override
    public ArrayList<Order> getUserOrders(User activeUser, int user_id) {
      if(activeUser.getIs_admin() == 1 || activeUser.getUserId() == user_id){
        return orderDao.getUserOrders(activeUser, user_id);
      }else{
        return null;
      }
    }
}
