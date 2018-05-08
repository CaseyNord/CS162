import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Project05 extends Application
{
    final int MAX = 10;

    ArrayList<Integer> input0 = new ArrayList<>();
    int index = 0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {   
        Group root = new Group();

        GridPane grid = new GridPane();

        TextField[] texts0 = new TextField[MAX];
        TextField[] texts1 = new TextField[MAX];
        Label[] labels0 = new Label[MAX];
        Button[] buttons0 = new Button[MAX];
        Button[] buttons1 = new Button[MAX];
        HBox[] boxes = new HBox[MAX];

        for (int i = 0; i < MAX; i++)
        {
            TextField text0 = new TextField();
            texts0[i] = text0;

            TextField text1 = new TextField();
            texts1[i] = text1;

            Label label0 = new Label("---");
            labels0[i] = label0;

            Button button0 = new Button();
            button0.setText("Go!");
            // The line below just sets the the label to the same text as its corresponding textfield (for first part of assignment)
            //button0.setOnAction(evt -> label0.setText(text0.getText())); // This is a lambda method that overrides the event handler
            button0.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    // e.getSource()getText;  <--
                    input0.add(Integer.parseInt(text1.getText()));
                    Collections.sort(input0);
                    for (int i = 0; i < MAX; i++)
                    {
                        labels0[i].setText(String.valueOf(input0.get(i)));
                    }
                }
            });
            buttons0[i] = button0;

            Button button1 = new Button();
            button1.setText("Click me!");
            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    buttons1[index].setText(String.valueOf(input0.get(index)));
                    index++;
                }   
            });
            buttons1[i] = button1;

            HBox box = new HBox();
            box.getChildren().addAll(buttons0[i], texts0[i], labels0[i], texts1[i], buttons1[i]);
            boxes[i] = box;

            grid.add(boxes[i], 0, i);      
        }
        
        root.getChildren().add(grid);
        primaryStage.setTitle("Basic GUI");
        primaryStage.setScene(new Scene(root, 550, 400));
        primaryStage.show();
    }

    /*

    I started implementing this special button class to try to handle counting the index for the button
    that shows the lowest available number when it is clicked.  I played with it for a little bit and
    could not get it to work how I wanted so abandoned the idea and left the index declared as an int
    at the top of the page.  For the purposes of this specific project it doesn't seem to conflict with
    anything really, but any input would be appreciated!

    private class SpecialButton
    {
        int index = 0;

        public void processButtonPress(ActionEvent event)
        {
            buttons1[index].setText(String.valueOf(input0.get(index)));
        }
    }
    */

    public static void main(String[] args)
    { 
        launch(args);
    }

}