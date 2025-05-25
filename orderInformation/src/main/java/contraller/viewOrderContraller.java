package contraller;

import DB.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import order.Order;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.LocalDate.*;

public class viewOrderContraller implements Initializable {

//    public TextField txtCusID;
//    public TextField txtItem;
//    public TextField txtQuentity1;
//    public TextField txtID;
//    public TextField txtOrderDate;
//    public TextField txtPrice;
    public TextField txtOrderDateview;
    public TextField txtPriceview;
    public TextField txtIDView;
    public TextField txtItemview;
    public TextField txtCusIDView;
    public TextField txtQuentity1view;
    @FXML
    private TableView<Order> TableOrders;

    @FXML
    private TableColumn<?, ?> txtCusName;

    @FXML
    private TableColumn<?, ?> txtDate;

    @FXML
    private TableColumn<?, ?> txtItemCode;

    @FXML
    private TableColumn<?, ?> txtOrderId;

    @FXML
    private TableColumn<?, ?> txtQuentity;

    @FXML
    private TableColumn<?, ?> txtprice;


    public void btnReloadOnAction(ActionEvent actionEvent) {
        dataReload();
    }

    private void dataReload(){
        ObservableList<Order> orderList = FXCollections.observableArrayList();

        try {
            Connection connection = dbConnection.getInstance().getConnection();
            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = prepared.executeQuery();

            while (resultSet.next()){
                Order order = new Order(resultSet.getInt("order_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("item_code"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("order_date").toLocalDate(),
                        resultSet.getDouble("total_price")
                );
                orderList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
TableOrders.setItems(orderList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        txtOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtCusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        txtItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        txtQuentity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        txtDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        txtprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableOrders.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{

            if (newVal!=null){
                InsertToTextField(newVal);
            }
                 } );

        dataReload();
    }

    private void InsertToTextField(Order newVal) {
        txtIDView.setText((""+newVal.getId()));
        txtCusIDView.setText(newVal.getCustomerName());
        txtItemview.setText(newVal.getItemCode());
        txtQuentity1view.setText(""+newVal.getQuentity());
        txtOrderDateview.setText(String.valueOf(newVal.getDate()));
        txtPriceview.setText(""+newVal.getPrice());




    }
}
