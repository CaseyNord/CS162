// Name: Casey Nord
// Class: CS162 Spring 2018
// Class time: 10:00am
// Date: 04/23/2018
// Project #: Project 3
// Team members: Miguel Ruiz, Sophia Kaeufel
// Driver Name: temperaturePane
// Program Description: Team project to create three interactive GUIs
// Test Oracle: Textfield: 	Input different values into the textfiel see if the output label changes.
//							30 fahrenheit or 12 celsius = put on a coat, its cold
//							55 fahrenheit or 18 celsius = not yet warm enough, but not too bad
//							70 fahrenheit or 27 celsius = eat some icecream
//				Checkbox:	See if the temperature unit changes from fahrenheit to celsius or vise versa if clicked.
//							You have to rehit the enter key after the checkbox is selected, to receive the output based on the changed unit
//				Button:		When you first enter the button you select to receive a background color based on the temperature.
//							After the button is clicked the first time, you only have to hit enter when inputting values into the textfield to have background color changed.
// 							fahrenheit of 34 = light blue
//							fahrenheit of 55 = green
//							fahrenheit of 70 = red
//**************************************************************************************************************************
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javax.lang.model.util.ElementScanner6;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.beans.value.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;

public class temperaturePane extends GridPane
{
    int temp1 = 59;
    int temp2 = 72;

    private Label prompt1, result;
    private TextField temperature;


    public temperaturePane()
    {
		// Textfield made by Sophia
		// Textfield receives temperature from user, and displays a text based on the value.
		//******************************************************************************************************
        Font font = new Font(18);

        Label prompt1 = new Label("Enter temperature in Fahrenheit: ");
        prompt1.setFont(font);
        GridPane.setHalignment(prompt1, HPos.RIGHT);

        result = new Label("---");
        result.setFont(font);
        GridPane.setHalignment(result, HPos.CENTER);

        temperature = new TextField();
       	temperature.setFont(font);
        temperature.setPrefWidth(50);
        temperature.setAlignment(Pos.CENTER);
        temperature.setOnAction(this::processReturn);



		//****************************************************************************************************************************************************
		// Checkbox made by Casey
		// When checkbox is checked the user can change the temperature unit from fahrenheit to celsius.
        CheckBox checkbox = new CheckBox();
        checkbox.setText("Celsius when checked");
        checkbox.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    prompt1.setText("Enter temperature in Fahrenheit: ");
                    prompt1.setText("Celsius: ");
                    temp1 = 15;
                    temp2 = 22;
                }
                else
                {
                    prompt1.setText("Enter temperature in Celsius: ");
                    prompt1.setText("Fahrenheit: ");
                    temp1 = 59;
                    temp2 = 72;
                }
            }
        });


        //*************************************************************************************
		// Button made by Miguel
		// loads an image of what the temperature might look like in the real world based on user input.

		Button push = new Button("Life Example");
        push.setOnAction(this::processButtonPress);

        //*************************************************************************************
		// Layout of all GUIs
        setAlignment(Pos.CENTER);
        setHgap(20);
        setVgap(10);
        setStyle("-fx-background-color: white");

        add(prompt1, 0, 0);
        add(temperature, 1, 0);
        add(result, 1, 1);
        add(checkbox, 4, 20);
        add(push, 0, 20);
    }



	//*************************************************************************************************
	// Action Event for textfield, outputs a string based on the temperature entered.
    public void processReturn(ActionEvent event)
    {
       int fahrenheitTemp = Integer.parseInt(temperature.getText());

	  	if (fahrenheitTemp < temp1)
	    {
	    	result.setText("put on a coat, its cold");
	        setStyle("-fx-background-color: lightskyblue");
	    }
	    else if(fahrenheitTemp < temp2)
	    {
	        result.setText("not yet summmer heat, but not too bad");
	        setStyle("-fx-background-color: beige");
	    }
	    else
	    {
	       result.setText("eat some icecream");
	       setStyle("-fx-background-color: firebrick");
        }

	}// end of processReturn
     public void processButtonPress(ActionEvent event)
        {
			Image winter= new Image("winter.jpg");	//loads winter image
			Image spring= new Image("spring.jpg");	//loads spring image
			Image summer= new Image("summer.jpg");	//loads summer image
			ImageView season = new ImageView();
			season.setFitWidth(300);				//sets width of imageView season
			season.setFitHeight(300);				//sets height of imageView season
			int userTemp = Integer.parseInt(temperature.getText());		// aquires input from the user from textfield
			if(userTemp < temp1)
			{	season.setImage(winter);			//prints to the screen image of winter
				add(season,1,10);
				}
			else if(userTemp < temp2)
			{	season.setImage(spring);			//prints to the screen image of spring
				add(season,1,10);
				}
			else
			{	season.setImage(summer);			//prints to the screen image of summer
				add(season,1,10);
				}
	}// end of ButtonPress

}

