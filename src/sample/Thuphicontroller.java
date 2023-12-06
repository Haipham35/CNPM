package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.dao.DataAccessObject;
import sample.dao.KhoanPhiDAO;
import sample.model.KhoanPhi;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
//chỉnh về table view rồi ra đơn hàng

public class Thuphicontroller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;




    @FXML
    private TableView<KhoanPhi> tableView;
    @FXML
    private TableColumn<KhoanPhi, Integer> idPhi;
    @FXML
    private TableColumn<KhoanPhi, Integer> mucphi;
    @FXML
    private TableColumn<KhoanPhi, String> tieudephi;
    @FXML
    private TableColumn<KhoanPhi, LocalDateTime> ngaytao;
    @FXML
    private TableColumn<KhoanPhi, LocalDateTime> ngayketthuc;
    @FXML
    private TableColumn<KhoanPhi, String> kieuphi;
    @FXML
    private TableColumn<KhoanPhi, String> noidungphi;



    String query = null;
    Connection connection =null;
    PreparedStatement preparedStatement =null;
    ResultSet resultSet =null;
    KhoanPhi khoanPhi =null;
    ObservableList<KhoanPhi> danhsachphi = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        mychoicebox = new ChoiceBox<String>(data);

        connection = SqlConnection.connect();


        DataAccessObject<KhoanPhi, Integer> khoanPhiIntegerDataAccessObject = new KhoanPhiDAO(connection);
        List<KhoanPhi> getkhoanphilist = null;
        try {
            getkhoanphilist = khoanPhiIntegerDataAccessObject.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhsachphi.addAll(getkhoanphilist);
        idPhi.setCellValueFactory(new PropertyValueFactory<>("idPhi"));
        mucphi.setCellValueFactory(new PropertyValueFactory<>("mucphi"));
        tieudephi.setCellValueFactory(new PropertyValueFactory<>("tieudephi"));
        ngaytao.setCellValueFactory(new PropertyValueFactory<>("ngaytao"));
        ngayketthuc.setCellValueFactory(new PropertyValueFactory<>("ngayketthuc"));
        noidungphi.setCellValueFactory(new PropertyValueFactory<>("noidungphi"));
        kieuphi.setCellValueFactory(new PropertyValueFactory<>("kieuphi"));
        tableView.setItems(danhsachphi);
//        loadDate();



    }
    @FXML
    private void refreshTable() throws SQLException {
        try {
            danhsachphi.clear();
            query = "SELECT * FROM khoanphi ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                danhsachphi.add(new KhoanPhi());
            }

        }catch (SQLException e){}


    }

//    private void loadDate() {
//        connection = SqlConnection.connect();
//
//        idphi.setCellValueFactory(new PropertyValueFactory<>("idphi"));
//        muctien.setCellValueFactory(new PropertyValueFactory<>("mucphi"));
////        tieude.setCellValueFactory(new PropertyValueFactory<>("tieudephi"));
//    }


    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scence1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToScence4(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("Scence4.fxml"));
        Parent khoanphiViewParent = loader.load();
        scene = new Scene(khoanphiViewParent);
        thuphidetailController controller = loader.getController();
        KhoanPhi selected = tableView.getSelectionModel().getSelectedItem();
        controller.setKhoanPhi(selected);
        stage.setScene(scene);
        stage.show();
    }

}
