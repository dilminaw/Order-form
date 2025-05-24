package contraller.orders;

import order.Order;

public class orderContraller implements orderService{

    @Override
    public boolean addOrder(Order order) {
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public boolean deleteOrder(Integer id) {
        return false;
    }

    @Override
    public Order searchOrder(Integer id) {
        return null;
    }
}
