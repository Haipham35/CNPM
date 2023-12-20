package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.dao.DataAccessObject;
import sample.dao.KhoanPhiDAO;
import sample.model.KhoanPhi;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    private VBox VBoxList;

    @FXML
    private VBox VBoxdanhsachnoptien;

    @FXML
    private Text hienThiChiTiet;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection connection = SqlConnection.connect();
        DataAccessObject<KhoanPhi, Integer> accessObject = new KhoanPhiDAO(connection);

        try {
            List<KhoanPhi> khoanPhiList = accessObject.getAll();
            VBoxList.getChildren().clear();
            for (KhoanPhi khoanPhi : khoanPhiList) {
                Node6 node = new Node6(khoanPhi, VBoxdanhsachnoptien);
                VBoxList.getChildren().add(node);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
