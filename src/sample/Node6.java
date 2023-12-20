package sample;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import sample.dao.DataAccessObject;
import sample.dao.DongPhiDAO;
import sample.dao.KhoanPhiDAO;
import sample.model.DongPhi;
import sample.model.KhoanPhi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Node6 extends VBox {


    @FXML
    private Label kieuPhi;

    @FXML
    private Label mucPhi;

    @FXML
    private Label tieuDe;

    private VBox VBoxdanhsachnoptien;
    private KhoanPhi khoanPhi;

    public Node6(KhoanPhi khoanPhi, VBox VBoxdanhsachnoptien) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Scence6.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.kieuPhi.setText(khoanPhi.getKieuphi());
        this.mucPhi.setText(String.valueOf(khoanPhi.getMucphi()));
        this.tieuDe.setText(khoanPhi.getTieudephi());
        this.VBoxdanhsachnoptien = VBoxdanhsachnoptien;
    }

    @FXML
    private void clickEvent() {
        System.out.println("Clicked");
        Connection connection = SqlConnection.connect();
        DataAccessObject<DongPhi, Integer> accessObject = new DongPhiDAO(connection);

//        try {
//            List<DongPhi> dongPhiList = accessObject.get(this.khoanPhi.getIdPhi()).ifPresent();
//            this.VBoxdanhsachnoptien.getChildren().clear();
//            for (DongPhi dongPhi : dongPhiList) {
//                Node7 node = new Node7(dongPhi.getIdHoKhau(), dongPhi.getIdPhi(), dongPhi.getSoTien());
//                this.VBoxdanhsachnoptien.getChildren().add(node);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
