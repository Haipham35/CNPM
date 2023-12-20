package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Node7 extends VBox {

    @FXML
    private Label idHoKhau;

    @FXML
    private Label idPhi;

    @FXML
    private Label soTien;

    public Node7(int idHoKhau, int idPhi, int soTien) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Scence7.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.idHoKhau.setText(String.valueOf(idHoKhau));
        this.idPhi.setText(String.valueOf(idPhi));
        this.soTien.setText(String.valueOf(soTien));
    }
}
