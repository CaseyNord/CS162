import javafx.application.Application;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.beans.value.*;
import javafx.scene.layout.Pane; // USE PANE TO BE ABLE TO MOVE COMPONENTS FREELY

import javafx.stage.Stage;

public class HelloWorld extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Hello World!");
        Font font = new Font(18);

        // Labels
        Label prompt1 = new Label("Enter temperature in Celsius: ");
        prompt1.setFont(font);
        prompt1.setLayoutX(125);
        prompt1.setLayoutY(300);

        Label label1 = new Label("Fahrenheit: ");
        label1.setFont(font);
        label1.setLayoutX(200);
        label1.setLayoutY(350);

        Label output1 = new Label("---");
        output1.setFont(font);
        output1.setLayoutX(325);
        output1.setLayoutY(350);

        // Textbox top center
        TextField txt1 = new TextField();
        txt1.setFont(font);
        txt1.setPrefWidth(50);
        txt1.setLayoutX(370);
        txt1.setLayoutY(295);

        // Bottom left button
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        ButtonClicked bClick = new ButtonClicked();
        btn.setOnAction(bClick);
        btn.setLayoutX(25);
        btn.setLayoutY(700);

        // Checkbox bottom right
        CheckBox chbx1 = new CheckBox();
        chbx1.setText("Celsius when checked");
        chbx1.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    prompt1.setText("Enter temperature in Fahrenheit: ");
                    prompt1.setLayoutX(100);
                    label1.setText("Celsius: ");
                }
                else
                {
                    prompt1.setText("Enter temperature in Celsius: ");
                    prompt1.setLayoutX(125);
                    label1.setText("Fahrenheit: ");
                }
            }
        });
        chbx1.setLayoutX(425);
        chbx1.setLayoutY(700);

        Pane root = new Pane();
        root.getChildren().add(btn);
        root.getChildren().add(chbx1);
        root.getChildren().add(txt1);
        root.getChildren().add(prompt1);
        root.getChildren().add(label1);
        root.getChildren().add(output1);
        primaryStage.setScene(new Scene(root, 600, 750));
        primaryStage.show();
    }

    public void processReturn(ActionEvent event)
    {

    }

    public class ButtonClicked implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            System.out.println("Hello World!");
        }
    }
}