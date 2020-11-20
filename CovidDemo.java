
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import java.io.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;






public class CovidDemo extends Application{
    
  
 
   private VBox vbox,checkBoxes;
   private HBox dropDownMenus,USCheckBoxList,finalContainer;
   private Button button;
   private ComboBox<String> monthSelection,countrySelection,stateSelection;
   private Scene scene1,scene2;
   private Label label1,result;
   private CovidProject obj;
   private CheckBox countryCheckBox,monthCheckBox,USCheckBox,worldWideCheckBox,stateCheckBox;
   private TextField countryText,stateText;
   private GridPane gridPane,checkBoxList;
      

   public static void main(String[] args) throws IOException{
      launch(args);
   }
   public void start(Stage stage)throws IOException{
      obj=new CovidProject();
      
      /*World Covid Interface Components
      _____________________________________________________________________________________________
      _____________________________________________________________________________________________
      */
      
      
      //button for triggering event 
      button=new Button("Submit");
      button.setMaxHeight(20);

            
      //checkBox for country
      countryCheckBox=new CheckBox("Country");
      
      //checkbox for month checkbox
      monthCheckBox=new CheckBox("Month");
      
      //worldwide checkbox
      worldWideCheckBox=new CheckBox("WorldWide");
      
      //grid pane for checkbox layout
      checkBoxList=new GridPane();
      checkBoxList.getChildren().addAll(countryCheckBox,monthCheckBox,worldWideCheckBox);
      
      checkBoxList.setConstraints(countryCheckBox,1,0);
      checkBoxList.setConstraints(monthCheckBox,1,1);
      checkBoxList.setConstraints(worldWideCheckBox,0,0);
      
      checkBoxList.setHgap(10);
      checkBoxList.setVgap(10);
      
      //vboxes
      label1=new Label("Select Filter(s)");
      checkBoxes=new VBox(10,label1,checkBoxList);
      checkBoxes.setPadding(new Insets(0));
      checkBoxes.setAlignment(Pos.CENTER_LEFT);
      
      //month choice dropdown menu
      monthSelection = new ComboBox<>();
      monthSelection.getItems().addAll("All Months","January","February","March","April","May");
      monthSelection.setValue("Select a Month");
      monthSelection.setPrefWidth(130);
      
      //vbox that that textfields will be added to based on an event of checking a box
      vbox=new VBox(10);
      
      
      
      // text fields
      countryText=new TextField("Enter Country");
      countryText.setPrefWidth(130);
      countryText.setMaxWidth(130);
      stateText=new TextField("Enter State");
   
      //label that will be used to show the case numbers
      result=new Label("");
      

      //gridPane to organize all componets
      gridPane=new GridPane();
      gridPane.getChildren().addAll(result,button,checkBoxes,vbox);
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setPadding(new Insets(10));
      
      gridPane.setConstraints(result,1,1);
      gridPane.setConstraints(button,1,0);
      gridPane.setConstraints(vbox,0,1);
      gridPane.setConstraints(checkBoxes,0,0);
      
      gridPane.setVgap(20);
      gridPane.setHgap(40);
      
      //scene
      stage.setTitle("Covid Tracker");      
      scene1=new Scene( gridPane,400,250);
      stage.setScene(scene1);
      stage.show();
      
      /* US Interface components
      _______________________________________________________________________
      _______________________________________________________________________
      */
      
      //state dropdown
      stateSelection=new ComboBox<>();
      stateSelection.getItems().addAll
      (
      "All States","Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida",
      "Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","LouisianaMaine","Maryland","Massachusetts",
      "Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico",
      "New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
      "South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"
      );
      
      stateSelection.setPrefWidth(100);
      stateSelection.setMaxWidth(100);
      stateSelection.setPrefHeight(20);
      stateSelection.setMaxHeight(20);
      stateSelection.setValue("Select a State");
      
      //state checkbox
      stateCheckBox=new CheckBox("states");
      
      
      //us checkbox
      
      USCheckBox=new CheckBox("United States");
      USCheckBox.setSelected(false);
      USCheckBox.setAlignment(Pos.CENTER);
      
      checkBoxList.getChildren().add(USCheckBox);
      checkBoxList.setConstraints(USCheckBox,0,1);
      
      //hbox for us interface checkboxes
      HBox USCheckBoxes=new HBox(10,stateCheckBox);
      USCheckBoxes.setAlignment(Pos.CENTER);
      USCheckBoxes.setPadding(new Insets(10));
      
      //back button
      Button backButton=new Button("Go Back");
      backButton.setPadding(new Insets(10));
      backButton.setMaxHeight(20);
      
      Label USResult=new Label("Default");
      
      //vbox
      
      VBox USInterfaceLeft=new VBox(10,backButton,new Label("Select Filter(s)"),USCheckBoxes);
      USInterfaceLeft.setAlignment(Pos.CENTER);
      USInterfaceLeft.setPadding(new Insets(10));
      
      VBox USInterfaceRight=new VBox(1,USResult);
      USInterfaceRight.setAlignment(Pos.CENTER);
      USInterfaceRight.setPadding(new Insets(10));
      
      //gridpane to organize
      HBox USInterface=new HBox(10,USInterfaceLeft,USInterfaceRight);
      USInterface.setAlignment(Pos.CENTER);
      USInterface.setPadding(new Insets(10));
      
      

      
      
      
      //scene
      
      scene2=new Scene(USInterface,300,200);
      
      

   
   
     /*events
      ____________________________________________________________________________________________ 
     */    
      
      
      String userTextUS=countryText.getText().toUpperCase();
      String monthName=monthSelection.getValue();
      
      //button event used to submit user input and use the input to retieve the data needed to retrieve the total cases          
      button.setOnAction(event ->
      {
         
         //need to set if statements and call the correct method
         if(monthName.equals("Select a Month"))
         {
         if(countryText.getText().equals("Enter Country"))
            result.setText("Total Cases WorldWide: \n"+obj.getCasesWorld());
            
       
          }
         
         else result.setText("Default");
      });

      
      //month checkbox event
      monthCheckBox.setOnAction(event ->{
         if(!monthCheckBox.isSelected())
            vbox.getChildren().remove(monthSelection);
         if(monthCheckBox.isSelected())
            vbox.getChildren().add(monthSelection);
       });
       
       
       //event for country checkbox
      countryCheckBox.setOnAction(event -> {
      if(!countryCheckBox.isSelected())
         vbox.getChildren().remove(countryText);
      else
         vbox.getChildren().add(countryText);
      });  
      
      //event for worldwide checkbox
      worldWideCheckBox.setOnAction(event ->
      {
         if(worldWideCheckBox.isSelected())
            if(USCheckBox.isSelected())
               USCheckBox.setSelected(false);
            if(countryCheckBox.isSelected())
               countryCheckBox.setSelected(false);
               vbox.getChildren().remove(countryText);
               
      });  
      
      //event for us checkbox
      USCheckBox.setOnAction(event ->
      {
         if(USCheckBox.isSelected())
         {
         
            USCheckBoxes.getChildren().add(monthCheckBox);
            USInterfaceRight.getChildren().add(1,button);
            button.setPrefHeight(10);
            button.setPrefHeight(10);
            
            button.setPadding(new Insets(20));
            stage.setTitle("Covid Tracker-US Interface");
            stage.setScene(scene2);
        }     
      });      
            
                
            
       

      
   }
}   
      
      
    


   
  

 
