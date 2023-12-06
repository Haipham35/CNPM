package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.KhoanPhi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class thuphidetailController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label idphiLabel;
    @FXML
    Label mucphiLabel;
    @FXML
    Label kieuphiLabel;
    @FXML
    Label ngaybatdauLabel;
    @FXML
    Label ngayketthucLabel;
    @FXML
    TextField tieudephi;

    @FXML
    TextArea noidungphi;
    @FXML
    CheckBox mycheckbox;
    @FXML
    Label ktracheckbox;
    public void setKhoanPhi(KhoanPhi khoanPhi){


        idphiLabel.setText(String.valueOf(khoanPhi.getIdPhi()));
        mucphiLabel.setText(String.valueOf(khoanPhi.getMucphi()));
        tieudephi.setText(khoanPhi.getTieudephi());

        noidungphi.setText(khoanPhi.getNoidungphi());
        ngaybatdauLabel.setText(String.valueOf(khoanPhi.getNgaytao()));
        ngayketthucLabel.setText(String.valueOf(khoanPhi.getNgayketthuc()));





    }
    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scence3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScene5(ActionEvent event) throws IOException {
        if (mycheckbox.isSelected()) {
            root = FXMLLoader.load(getClass().getResource("Scrence5.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            ktracheckbox.setText("Bạn chưa đồng ý nộp phí");
        }

    }



}
