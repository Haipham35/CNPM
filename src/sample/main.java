package sample;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.TableView;
        import javafx.stage.Stage;
        import javafx.scene.Parent;
        import javafx.scene.Scene;

        import java.time.LocalDateTime;
        import java.util.Objects;

public class main extends Application {

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Scence1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
