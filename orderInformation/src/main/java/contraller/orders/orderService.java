package contraller.orders;

import order.Order;

public interface orderService {
    boolean addOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(Integer id);

    Order searchOrder(Integer id);
}
