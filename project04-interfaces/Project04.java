import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Project04 extends Application
{
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(new temperaturePane(), 800, 950);

        primaryStage.setTitle("Temperature");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}


