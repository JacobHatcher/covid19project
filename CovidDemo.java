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
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;






public class CovidDemo extends Application{
    
  
 
   private VBox checkBoxes,nodesContainer;
   private Button button;
   private ComboBox<String> monthSelection,countrySelection;
   private Scene scene;
   private Label label1;
   private CovidProject obj;
   private CheckBox countryCheckBox,monthCheckBox;
   private TextField countryText;
   private TextArea result;
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
      CheckBox worldCheckBox=new CheckBox("Worldwide");
      monthCheckBox=new CheckBox("Month");
            
      //labels
      
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
      
      result=new TextArea();
      result.setPrefWidth(227);
      result.setPrefHeight(75);
      result.setEditable(false);

      //gridpanes
      checkBoxList=new GridPane();
      checkBoxList.getChildren().addAll(countryCheckBox,monthCheckBox,usCheckBox,worldCheckBox);
      checkBoxList.setConstraints(worldCheckBox,0,0);
      checkBoxList.setConstraints(countryCheckBox,0,1);
      checkBoxList.setConstraints(usCheckBox,1,0);
      checkBoxList.setConstraints(monthCheckBox,1,1);
        
      
      //vboxes
      checkBoxes=new VBox(10,label1,checkBoxList);
      checkBoxes.setPadding(new Insets(0));
      checkBoxes.setAlignment(Pos.CENTER_LEFT);     
      nodesContainer=new VBox(10);
      
      //gridPane to organize all componets
      gridPane=new GridPane();
      gridPane.getChildren().addAll(button,checkBoxes,nodesContainer);
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setPadding(new Insets(10));
      
      gridPane.setConstraints(result,1,1);
      gridPane.setConstraints(button,0,2);
      gridPane.setConstraints(nodesContainer,0,1);
      gridPane.setConstraints(checkBoxes,0,0);
      
      gridPane.setVgap(20);
      gridPane.setHgap(10);
      
      
      //scene
      stage.setTitle("Covid Case Tracker from January 22-May 15");      
      scene=new Scene( gridPane,400,345);
      stage.setScene(scene);
      stage.show();
   
   
     /*events below
      ____________________________________________________________________________________________ 
      ____________________________________________________________________________________________     
     */    
      
      //button event 
      
      obj=new CovidProject(); 
             
      button.setOnAction(event ->
      {
         String monthName=monthSelection.getValue();
         String countryName=countryText.getText();
         String stateName=stateSelection.getValue();
      
        int monthNum=0;
        if(monthName.equals("January"))
           monthNum=1;
        else if(monthName.equals("February"))
           monthNum=2;               
        else if(monthName.equals("March"))
           monthNum=3;
        else if(monthName.equals("April"))
           monthNum=4;
        else if(monthName.equals("May"))
           monthNum=5;
        
        if(!gridPane.getChildren().contains(result)){
            gridPane.getChildren().add(result);
            gridPane.setConstraints(result,0,3);
         }      
         //if only month set result to getCasesWorld(monthNum)
         if(monthCheckBox.isSelected()&&!countryCheckBox.isSelected()&&!usCheckBox.isSelected()){
            if(monthName.equals("Select a Month"))
               result.setText("Please Select a Month");
            else if(monthName.equals("All Months"))
               result.setText("\tTotal Cases Worldwide\n\n\t\t "+obj.getCasesWorld());  
            else
               result.setText("New Cases Worldwide during "+monthName+"\n\n\t\t\t"+obj.getCasesWorld(monthNum));   
            }   
         //if only worldwide selected set result to getCasesWorld()
         //if world and month selected set result to getCasesWorld(monthNum)
         else if(worldCheckBox.isSelected()){
            if(!monthCheckBox.isSelected())
               result.setText("\tTotal Cases Worldwide\n\n\t\t\t "+obj.getCasesWorld());
            else{
               if(monthName.equals("All Months"))
                  result.setText("\tTotal Cases Worldwide\n\n\t\t\t "+obj.getCasesWorld());
               else 
                  result.setText("New Cases Worldwide during "+monthName+"\n\n\t\t\t"+obj.getCasesWorld(monthNum));  
            }   
         }      
         else if(countryCheckBox.isSelected()){     
         //if only country selected set result to getCasesCountry(country)
            if(!monthCheckBox.isSelected()||monthName.equals("Select a Month"))
              result.setText("\tTotal Cases in "+countryName+"\n\n\t\t\t"+obj.getCasesCountry(countryName));  
            //if country and month selected set result to getCasesCountry(countryName, monthNum) 
            else{
               if(monthName.equals("Select a Month"))
                  result.setText("Please Select a valid month");
               else if(monthName.equals("All Months"))
                  result.setText("New Cases in "+countryName+"\n\n\t\t\t"+obj.getCasesCountry(countryName));
               else
                  result.setText("New Cases in "+countryName+" during "+monthName+"\n\n\t\t\t"+obj.getCasesCountry(countryName,monthNum));  
                     
            }
       }       
         //if statements for all us options and call correct method             
         else if(usCheckBox.isSelected()){
            if(stateName.equals("Select a State"))
                  result.setText("Please Select a State");
            else if(monthCheckBox.isSelected()){
               
               if(!stateName.equals("All States"))
                  result.setText("New Cases in "+stateName+" during "+monthName+"\n\n\t\t\t"+obj.getCasesState(stateName,monthNum));
               else
                  result.setText("New Cases in US during"+monthName+"\n\n\t\t\t"+obj.getCasesCountry("us",monthNum));   
            }
            else{
               if(!stateName.equals("All States"))
                  result.setText("\tNew Cases in "+stateName+"\n\n\t\t\t"+obj.getCasesState(stateName));
               else
                  result.setText("\tTotal Cases in US \n\n\t\t\t"+obj.getCasesCountry("us"));
             }        
         }
         
         
         gridPane.setHgap(0);
         label1.setText("choose search filter(s)"); 
         nodesContainer.setPadding(new Insets(0));   
      
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
         if(!countryCheckBox.isSelected())
            nodesContainer.getChildren().remove(countryText);
         else{
            worldCheckBox.setSelected(false);             
            usCheckBox.setSelected(false); 
            nodesContainer.getChildren().add(countryText);  
         } 
         result.clear();        
                         
                  
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
            else
               nodesContainer.getChildren().remove(stateSelection);      
         }
         result.clear(); 
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
         
         result.clear(); 
            
     
     
     
     
     });  
      
   }
}   