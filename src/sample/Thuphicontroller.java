package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.dao.DataAccessObject;
import sample.dao.KhoanPhiDAO;
import sample.model.KhoanPhi;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Thuphicontroller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<String> mychoicebox;

    private String[] data={null,"Phí Bắt buộc", "Đóng góp"};
    @FXML
    private TableView<KhoanPhi> tableView;
    @FXML
    private TableColumn<KhoanPhi, Integer> idphi;
    @FXML
    private TableColumn<KhoanPhi,Integer> muctien;

    private ObservableList<KhoanPhi> danhsachphi;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        mychoicebox= new ChoiceBox<>();
        mychoicebox.getItems().addAll(data);

        mychoicebox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s==null) ?"Box chưa được chọn":s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });
        Connection connection = SqlConnection.connect();
        DataAccessObject<KhoanPhi, Integer> khoanPhiIntegerDataAccessObject = new KhoanPhiDAO(connection, KhoanPhiDAO.TableType.KHOANPHI);
        List<KhoanPhi> getkhoanphilist = khoanPhiIntegerDataAccessObject.getAll();
        danhsachphi.addAll(getkhoanphilist);
        idphi.setCellValueFactory(new PropertyValueFactory<KhoanPhi,Integer>("id"));
        muctien.setCellValueFactory(new PropertyValueFactory<KhoanPhi,Integer>("sotien"));
        tableView.setItems(danhsachphi);

        }







    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scence1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScence4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scence4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
