package sample.util;

import javafx.scene.control.Button;

public final class ActionButton {
    private ActionButton() {
    }
    
    public static void showButtonSubmit(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-blue");
    }
    
    public static void hideButtonSubmit(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-blue-hidden");
    }
    
    public static void showButtonClear(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-red");
    }
    
    public static void hideButtonClear(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-red-hidden");
    }
}
