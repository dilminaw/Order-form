package contraller.orders;

import DB.dbConnection;
import order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            prepared.setObject(5,order.getDate());
            prepared.setObject(6,order.getPrice());

            prepared.executeUpdate();
            return true;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateOrder(Order order) {
        String sql="UPDATE orders set customer_name=?,item_code=?,quantity=?,order_date=?,total_price=? WHERE order_id=?";
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement prepared = connection.prepareStatement(sql);
            prepared.setObject(1,order.getCustomerName());
            prepared.setObject(2,order.getItemCode());
            prepared.setObject(3,order.getQuentity());
            prepared.setObject(4,order.getDate());
            prepared.setObject(5,order.getPrice());
            prepared.setObject(6,order.getId());

         return    prepared.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteOrder(Integer id) {
        try {
            PreparedStatement prepared = dbConnection.getInstance().getConnection().prepareStatement("DELETE FROM orders WHERE order_id='" + id + "'");
         return     prepared.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order searchOrder(Integer id) {

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM orders WHERE order_id='" + id + "'");
            ResultSet resultSet = prepared.executeQuery();

            if (resultSet.next()){
                return new  Order(
                        resultSet.getInt("order_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("item_code"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("order_date").toLocalDate(),
                        resultSet.getDouble("total_price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
