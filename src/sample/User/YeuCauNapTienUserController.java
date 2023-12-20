package sample.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.SqlConnection;
import sample.dao.TaiKhoanNhanKhauDAO;
import sample.model.NapTien;
import sample.dao.NapTienDao;
import sample.model.NhanKhau;
import sample.dao.NhanKhauDAO;
import sample.model.TaiKhoanNhanKhau;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.ResourceBundle;

public class YeuCauNapTienUserController implements Initializable{
    @FXML
    private TextField onNhapTien;
    @FXML
    private TextField ghiChu;
    @FXML
    private Label ktra;
    public  static String CCCD ="001075000006";
    Connection connection = SqlConnection.connect();

    public void naptien(ActionEvent event) throws IOException, SQLException {
    if(onNhapTien.getText().isEmpty()){
        ktra.setText("ban chua nhap sao tien");

    } else if (onNhapTien.getText().equals("0")) {
        ktra.setText("So tien >0");
    }else{
        NapTienDao naptiendao = new NapTienDao(connection);
        naptiendao.save(CCCD, Integer.parseInt(onNhapTien.getText()), ghiChu.getText());




    }



    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NhanKhauDAO nhanKhauDao = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        try {
            Optional<NhanKhau> resultNK = nhanKhauDao.get(CCCD);
            if(resultNK.isPresent()) {
                NhanKhau nhankhau = resultNK.get();
                System.out.println(nhankhau.getThongTinNhanKhau().getIdHoKhau());
                System.out.println(countHoKhau(nhankhau.getThongTinNhanKhau().getIdHoKhau()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static int countHoKhau(int idHK) throws SQLException {
        int number = 0 ;
        Connection connection1 = SqlConnection.connect();
        PreparedStatement statement = connection1.prepareStatement("select count(*) as numberNK from nhankhau where idHoKhau = ?");
        statement.setInt(1, idHK);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            number = resultSet.getInt("numberNK");
        }
        return number;
    }

    public static int countHoKhau(String soCccd) throws SQLException {
        Connection connection1 = SqlConnection.connect();
        int number = 0 ;
        NhanKhauDAO nhanKhauDao = new NhanKhauDAO(connection1, NhanKhauDAO.TableType.NHANKHAU);
        try {
            Optional<NhanKhau> resultNK = nhanKhauDao.get(CCCD);
            if(resultNK.isPresent()) {
                NhanKhau nhankhau = resultNK.get();
                PreparedStatement statement = connection1.prepareStatement("select count(*) as numberNK from nhankhau where idHoKhau = ?");
                statement.setInt(1, nhankhau.getThongTinNhanKhau().getIdHoKhau());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    number = resultSet.getInt("numberNK");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return number;
    }


}
