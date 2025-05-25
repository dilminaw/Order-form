package contraller.orders;

import DB.dbConnection;
import order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class orderContraller implements orderService{

    @Override
    public boolean addOrder(Order order) {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement prepared = connection.prepareStatement("insert into orders values(?,?,?,?,?,?)");
            prepared.setObject(1,order.getId());
            prepared.setObject(2,order.getCustomerName());
            prepared.setObject(3,order.getItemCode());
            prepared.setObject(4,order.getQuentity());
            prepared.setObject(5,order.getPrice());
            prepared.setObject(6,order.getDate());

            prepared.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
