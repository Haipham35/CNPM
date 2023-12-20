package sample.util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Objects;

public class AlertUtils {
    public static void showAlert(String title, String content) {
        // create icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-96.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo thông báo
        Alert alert = createAlert(title, content, image);
        
        // addOK
        addOK(alert);
        
        // Hiển thị
        alert.showAndWait();
    }
    
    public static void showAlert(String title, String content, Runnable action) {
        // create icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-96.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo thông báo và hiển thị
        showAlert(title, content, image, action);
    }
    
    public static void showAlertError(String title, String content) {
        // create icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-red-100.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo thông báo
        Alert alert = createAlert(title, content, image);
        
        // addOK
        addOK(alert);
        
        // Hiển thị
        alert.showAndWait();
    }
    
    public static void showAlertError(String title, String content, Runnable action) {
        // create icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-red-100.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo thông báo và hiển thị
        showAlert(title, content, image, action);
    }
    
    public static void showAlert(String title, String content, Image image) {
        // Tạo thông báo có icon
        Alert alert = createAlert(title, content, image);
        
        // addOK
        addOK(alert);
        
        // Hiển thị
        alert.showAndWait();
    }
    
    public static void showAlert(String title, String content, Image image, Runnable action) {
        // Tạo thông báo có icon
        Alert alert = createAlert(title, content, image);
        
        // addOK and run
        addOK(alert, action);
    }
    
    public static Alert createAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        
        // Xóa mọi phần tử không cần thiết
        alert.getDialogPane().getButtonTypes().clear();
        
        // Tăng kích thước của cửa sổ để vừa với nội dung
        Node graphic = alert.getDialogPane().getGraphic();
        if (graphic instanceof Region) {
            ((Region) graphic).setMaxHeight(Double.MAX_VALUE);
            ((Region) graphic).setMaxWidth(Double.MAX_VALUE);
        }
        
        return alert;
    }
    
    public static Alert createAlert(String title, String content, Image image) {
        // Tạo thông báo
        Alert alert = createAlert(title, content);
        
        // Thêm icon cho Alert
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(image);
        
        return alert;
    }
    
    private static void addOK(Alert alert) {
        // Thêm nút "OK"
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
    }
    
    private static void addOK(Alert alert, Runnable action) {
        // Thêm nút "OK"
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        
        // Xử lý sự kiện khi nút "OK" được nhấn
        alert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                action.run();
            }
        });
    }
}
