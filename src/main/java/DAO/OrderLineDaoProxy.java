package DAO;
//Lauren

import DTO.OrderLine;
import DTO.User;
import DTO.Order;
import java.util.ArrayList;

public class OrderLineDaoProxy implements OrderLineInterface {

  private OrderLineDao orderLineDao;

  public OrderLineDaoProxy(){
    orderLineDao = new OrderLineDao("gamestore");
  }

    @Override
    public boolean createOrderLine(User activeUser, OrderLine ol, Order order) {
      if((ol.getOrder_id() == order.getOrder_id() && activeUser.getUserId() == order.getUser_id()) || activeUser.getIs_admin() == 1){
        return createOrderLine(activeUser, ol, order);
      }else{
        return false;
      }
    }

    @Override
    public boolean removeOrderLine(User activeUser, OrderLine ol, Order order) {
      if((ol.getOrder_id() == order.getOrder_id() && activeUser.getUserId() == order.getUser_id()) || activeUser.getIs_admin() == 1){
        return removeOrderLine(activeUser, ol, order);
      }else{
        return false;
      }
    }

    @Override
    public ArrayList<OrderLine> getOrderLines(User activeUser, Order order) {
      if(order.getUser_id() == activeUser.getUserId() || activeUser.getIs_admin() == 1){
        return getOrderLines(activeUser, order);
      }else{
        return null;
      }
    }

}
