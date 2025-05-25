package contraller.orders;

import DB.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ordersFormContraller {
@FXML
    public TextField txtOrderDate;
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

            new Alert(Alert.AlertType.CONFIRMATION,"ADDED COMPLETE");
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }




}
