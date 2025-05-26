package contraller.orders;

import DB.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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


public class ordersFormContraller implements Initializable {
@FXML
    public TextField txtOrderDate;
    public TableView<Order> tableorder2;
    public TableColumn colId;
    public TableColumn colcusname;
    public TableColumn colItem;
    public TableColumn colQuentity;
    public TableColumn colDate;
    public TableColumn colprice;
    @FXML
    private TextField txtCusID;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtItem;



    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuentity;

    orderService orderService = new orderContraller();

    @FXML
    void btnAddOrdersOnAction(ActionEvent event) {

        Order order = new Order(Integer.parseInt(txtID.getText()) ,
                txtCusID.getText(),txtItem.getText(),
                Integer.parseInt(txtQuentity.getText()),
                LocalDate.parse(txtOrderDate.getText()),
                Double.parseDouble(txtQuentity.getText()));


        if (orderService.addOrder(order)){
            dataReload();
            new Alert(Alert.AlertType.CONFIRMATION,"ADDED COMPLETE");
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (orderService.deleteOrder(Integer.valueOf(txtID.getText()))){
            new Alert(Alert.AlertType.CONFIRMATION,"DELETED");
            dataReload();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Order order= orderService.searchOrder(Integer.parseInt(txtID.getText()));
        txtID.setText(""+order.getId());
        txtCusID.setText(order.getCustomerName());
        txtItem.setText(order.getItemCode());
        txtQuentity.setText(""+order.getQuentity()) ;
        txtOrderDate.setText(String.valueOf(order.getDate()) );
        txtPrice.setText(""+order.getPrice());

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Order order = new Order(Integer.parseInt(txtID.getText()) ,
                txtCusID.getText(),txtItem.getText(),
                Integer.parseInt(txtQuentity.getText()),
                LocalDate.parse(txtOrderDate.getText()),
                Double.parseDouble(txtQuentity.getText()));

        if (orderService.updateOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"updated success");
            dataReload();
        }

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
        tableorder2.setItems(orderList);
    }



    public void btnreload1(ActionEvent actionEvent) {
        dataReload();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcusname.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQuentity.setCellValueFactory(new PropertyValueFactory<>("quentity"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tableorder2.getSelectionModel().selectedItemProperty().addListener((observableValue, oldval, newval) -> {
            
            if (newval!=null){
                inserTOtextfieldData(newval);
            }
            
        });
        dataReload();
    }

    private void inserTOtextfieldData(Order newval) {
        txtID.setText((""+newval.getId()));
        txtCusID.setText(newval.getCustomerName());
        txtItem.setText(newval.getItemCode());
        txtQuentity.setText(""+newval.getQuentity());
        txtOrderDate.setText(String.valueOf(newval.getDate()));
        txtPrice.setText(""+newval.getPrice());
    }
}
