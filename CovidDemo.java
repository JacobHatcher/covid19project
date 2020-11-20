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
    
  
 
   private VBox checkBoxes,nodesContainer;
   private Button button;
   private ComboBox<String> monthSelection,countrySelection;
   private Scene scene;
   private Label label1,result;
   private CovidProject obj;
   private CheckBox countryCheckBox,monthCheckBox;
   private TextField countryText;
   private GridPane gridPane,checkBoxList;
      

   public static void main(String[] args) throws IOException{
      launch(args);
   }
   public void start(Stage stage)throws IOException{
      
      
      //buttons 
      button=new Button("Submit");
            
      //checkBoxes
      countryCheckBox=new CheckBox("Country");
      CheckBox usCheckBox=new CheckBox("United States");
      CheckBox worldCheckBox=new CheckBox("WorldWide");
      monthCheckBox=new CheckBox("Month");
            
      //labels
      result=new Label("");
      label1=new Label("choose search filter(s)");
      
      
      
      //comboboxes
      monthSelection = new ComboBox<>();
      monthSelection.getItems().addAll("All Months","January","February","March","April","May");
      monthSelection.setValue("Select a Month");
      monthSelection.setPrefWidth(130);
      
      ComboBox<String> stateSelection=new ComboBox<>();
      stateSelection.getItems().addAll
      (
      "All States","Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida",
      "Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","LouisianaMaine","Maryland","Massachusetts",
      "Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico",
      "New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
      "South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"
      );
      
      stateSelection.setPrefWidth(130);
      stateSelection.setMaxWidth(130);
      stateSelection.setPrefHeight(20);
      stateSelection.setMaxHeight(20);
      stateSelection.setValue("Select a State");


      // text fields
      countryText=new TextField("Enter Country");
      countryText.setPrefWidth(130);
      countryText.setMaxWidth(130);

      //gridpanes
      checkBoxList=new GridPane();
      checkBoxList.getChildren().addAll(countryCheckBox,monthCheckBox,usCheckBox,worldCheckBox);
      checkBoxList.setConstraints(worldCheckBox,0,0);
      checkBoxList.setConstraints(countryCheckBox,0,1);
      checkBoxList.setConstraints(usCheckBox,1,0);
      checkBoxList.setConstraints(monthCheckBox,1,1);
      checkBoxList.setPadding(new Insets(10));
      checkBoxList.setAlignment(Pos.CENTER);
      checkBoxList.setHgap(2);
      checkBoxList.setVgap(2);
      
      //vboxes
      checkBoxes=new VBox(10,label1,checkBoxList);
      checkBoxes.setPadding(new Insets(0));
      checkBoxes.setAlignment(Pos.CENTER_LEFT);     
      nodesContainer=new VBox(10);
      
      //gridPane to organize all componets
      gridPane=new GridPane();
      gridPane.getChildren().addAll(result,button,checkBoxes,nodesContainer);
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setPadding(new Insets(10));
      
      gridPane.setConstraints(result,1,1);
      gridPane.setConstraints(button,1,0);
      gridPane.setConstraints(nodesContainer,0,1);
      gridPane.setConstraints(checkBoxes,0,0);
      
      gridPane.setVgap(20);
      gridPane.setHgap(40);
      
      
      
      

      
      
      //scene
      stage.setTitle("Covid Tracker");      
      scene=new Scene( gridPane,350,250);
      stage.setScene(scene);
      stage.show();
   
   
     /*events below
      ____________________________________________________________________________________________ 
     */    
      
      //button event 
      
      obj=new CovidProject();  
             
      button.setOnAction(event ->
      {
         //if only month set result to getCasesWorld(monthNum)
         //if only worldwide selected set result to getCasesWorld()
            //if world and month selected set result to getCasesWorld(monthNum)
         //if only country selected set result to getCasesCountry(country)
            //if country and month selected set result to getCasesCountry(country, monthNum) 
         //if statements for all us options and call correct method             
             
      });

      
      //month checkbox event
      monthCheckBox.setOnAction(event ->{
      if(!monthCheckBox.isSelected())
        nodesContainer.getChildren().remove(monthSelection);
      else
        nodesContainer.getChildren().add(monthSelection);
       });
       
       
       //event for country checkbox
      countryCheckBox.setOnAction(event -> 
      {
         if(countryCheckBox.isSelected())
         {
            nodesContainer.getChildren().remove(stateSelection);
            worldCheckBox.setSelected(false);             
            usCheckBox.setSelected(false);      
            if(!nodesContainer.getChildren().contains(countryText))
               nodesContainer.getChildren().add(countryText);
         }           
   });   

      //event for us checkbox
      usCheckBox.setOnAction(event ->
      {
         if(usCheckBox.isSelected()){
            nodesContainer.getChildren().remove(countryText);
            countryCheckBox.setSelected(false);             
            worldCheckBox.setSelected(false);
            if(!nodesContainer.getChildren().contains(stateSelection))
               nodesContainer.getChildren().add(stateSelection);       
         }
     });    
     
     
     //event for worldwide checkbox
     worldCheckBox.setOnAction(event ->
     {
         if(worldCheckBox.isSelected()){
            nodesContainer.getChildren().remove(countryText);
            nodesContainer.getChildren().remove(stateSelection);
            countryCheckBox.setSelected(false);             
            usCheckBox.setSelected(false);
         }
         
         
            
     
     
     
     
     });  
      
   }
}   