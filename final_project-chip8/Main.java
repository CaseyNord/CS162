import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.Timeline;


public class Main extends Application
{
    /*
    private void initialize()
    {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), new KeyValue (node.translateXProperty(), 25)));
        timeline.play();
    }
    */


    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage theStage)
    {
        theStage.setTitle("Hello World!");
        theStage.show();
    }
}