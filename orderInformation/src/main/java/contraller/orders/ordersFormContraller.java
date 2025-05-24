package contraller.orders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import order.Order;

public class ordersFormContraller {

    @FXML
    private TextField txtCusID;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtOrder;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuentity;

    orderService orderService = new orderContraller();

    @FXML
    void btnAddOrdersOnAction(ActionEvent event) {

        Order order = new Order(txtID.getText(),txtCusID.getText(),txtItem.getText(),txtQuentity.getText(),txtOrder.getText());


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
