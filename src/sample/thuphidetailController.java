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
    @FXML
    Label mucphi;
    @FXML
    TextField sotien;

    public KhoanPhi kp;
    public void setKhoanPhi(KhoanPhi khoanPhi){

        kp = khoanPhi;
        if(khoanPhi.getMucphi() == 0) {
            sotien.setVisible(true);
            mucphiLabel.setVisible(false);
            mucphi.setText("Số tiền:");
        }else {
            sotien.setVisible(false);
            mucphiLabel.setVisible(true);
            mucphi.setText("Mức phi:");
        }
        idphiLabel.setText(String.valueOf(khoanPhi.getIdPhi()));
        mucphiLabel.setText(String.valueOf(khoanPhi.getMucphi()));
        tieudephi.setText(khoanPhi.getTieudephi());
        kieuphiLabel.setText(khoanPhi.getKieuphi());
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
        if (kp.getMucphi() == 0) {

            if (sotien.getText().isEmpty()) {
                ktracheckbox.setText("Chua nhap so tien");
                return;
            } else if (sotien.getText().equals("0")) {
                ktracheckbox.setText("So tien >0");
            }
            return;}
//            }else {
//                if() {ktra cos ky hieu ko
//
//
//                } else {
//                    Optional<TaiKhoanNhanKhau>
//                    if (tk.getsodu() < sotian.gettext()) {
//                        ktracheckbox.setText("sodutkkod0");
//                        return;
//                    }
//                }
//            }
//        }

          if (mycheckbox.isSelected()) {
                root = FXMLLoader.load(getClass().getResource("User/Scrence5.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
          } else {
                ktracheckbox.setText("Bạn chưa đồng ý nộp phí");
            }

        }

    }


