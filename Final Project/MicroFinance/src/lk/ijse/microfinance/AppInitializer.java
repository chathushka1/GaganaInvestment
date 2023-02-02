package lk.ijse.microfinance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/microfinance/view/LoginForm.fxml"))));
        primaryStage.setTitle("GAGANA INVESTMENT");
        primaryStage.show();
    }
}
