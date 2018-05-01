// Name: Casey Nord
// Class: CS162 Spring 2018
// Class time: 10:00am
// Date: 04/30/2018
// Project #: Project 4
// Team members: Miguel Ruiz, Sophia Kaeufel
// Driver Name: Project04.java
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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TemperaturePane extends GridPane implements TextFieldInterface, CheckboxInterface, ButtonInterface
{
    private int temp1 = Model.getFahrenheit1();
    private int temp2 = Model.getFahrenheit2();

    private Image winter = new Image("winter.jpg");
    private Image spring = new Image("spring.jpg");
    private Image summer = new Image("summer.jpg");

    private Label result, textFieldPrompt;
    private TextField temperature;
    private ImageView season;

    public TemperaturePane()
    {
        //**************************************************************************************
		// Textfield made by Sophia
		// Textfield receives temperature from user, and displays a text based on the value.
		//**************************************************************************************
        Font font = new Font(18);

        textFieldPrompt = new Label("Enter temperature in Fahrenheit: ");
        textFieldPrompt.setFont(font);
        GridPane.setHalignment(textFieldPrompt, HPos.CENTER);

        result = new Label("---");
        result.setFont(font);
        GridPane.setHalignment(result, HPos.CENTER);

        temperature = new TextField();
       	temperature.setFont(font);
        temperature.setPrefWidth(50);
        temperature.setAlignment(Pos.CENTER);
        temperature.setOnAction(this::processReturn);

		//**************************************************************************************
		// Checkbox made by Casey
        // When checkbox is checked the user can change the temperature unit from fahrenheit to celsius.
        //**************************************************************************************
        CheckBox checkbox = new CheckBox();
        checkbox.setText("Celsius when checked");
        checkbox.setOnAction(this::processChanged);

        //*************************************************************************************
		// Button made by Miguel
        // loads an image of what the temperature might look like in the real world based on user input.
        //**************************************************************************************
		Button push = new Button("Life Example");
        push.setOnAction(this::processButtonPress);

        season = new ImageView();
        season.setFitWidth(300);
        season.setFitHeight(300);

        //*************************************************************************************
        // Layout of all GUIs
        //**************************************************************************************
        setAlignment(Pos.CENTER);
        setHgap(20);
        setVgap(10);
        setStyle("-fx-background-color: white");


        GridPane top = new GridPane();
        top.add(textFieldPrompt, 0, 0);
        top.add(temperature, 0, 1);
        top.add(push, 0, 3);
        top.add(checkbox, 0, 4);

        GridPane bottom = new GridPane();
        bottom.add(result, 0, 1);
        bottom.add(season, 0, 2);

        this.add(top, 0, 1);
        this.add(bottom, 0 , 2);
    }


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
    }
    
    public void processButtonPress(ActionEvent event)
    {
        int userTemp = Integer.parseInt(temperature.getText());		// aquires input from the user from textfield

        if(userTemp < temp1)
        {	
            season.setImage(winter);
        }
        else if(userTemp < temp2)
        {	
            season.setImage(spring);
        }
        else
        {	
            season.setImage(summer);
        }
    }
    
    public void processChanged(ActionEvent event)
    {
        if(((CheckBox)event.getSource()).isSelected())
        {
            textFieldPrompt.setText("Enter temperature in Celsius: ");
            temp1 = Model.getCelsius1();
            temp2 = Model.getCelsius2();
        }
        else
        {
            textFieldPrompt.setText("Enter temperature in Fahrenheit: ");
            temp1 = Model.getFahrenheit1();
            temp2 = Model.getFahrenheit2();
        }
    }
}

