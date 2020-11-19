
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
    
  
 
   private VBox checkBoxes,vbox;
   private HBox dropDownMenus,checkBoxList,finalContainer;
   private Button button;
   private ComboBox<String> monthSelection,countrySelection;
   private Scene scene;
   private Label label1,result;
   private CovidProject obj;
   private CheckBox countryCheckBox,monthCheckBox;
   private TextField countryText,stateText;
   private GridPane gridPane;
      

   public static void main(String[] args) throws IOException{
      launch(args);
   }
   public void start(Stage stage)throws IOException{
      obj=new CovidProject();
      //button for triggering event 
      button=new Button("Submit");
            
      //checkBox for country
      countryCheckBox=new CheckBox("Country");
      countryCheckBox.setSelected(false);

      
      //checkbox for month checkbox
      monthCheckBox=new CheckBox("Month");
      monthCheckBox.setSelected(false);
            
      //hbox for checkbox layout
      checkBoxList=new HBox(10,countryCheckBox,monthCheckBox);
      checkBoxList.setPadding(new Insets(10));
      checkBoxList.setAlignment(Pos.CENTER);
      
      //vbox for checkbox and label1
      label1=new Label("Select how to search");
      checkBoxes=new VBox(10,label1,checkBoxList);
      checkBoxes.setPadding(new Insets(0));
      checkBoxes.setAlignment(Pos.CENTER_LEFT);
      
      //month choice dropdown menu
      monthSelection = new ComboBox<>();
      monthSelection.getItems().addAll("All Months","January","February","March","April","May");
      monthSelection.setValue("Select a Month");
      monthSelection.setPrefWidth(130);
      
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
      scene=new Scene( gridPane,350,250);
      stage.setScene(scene);
      stage.show();
   
   
     /*events below
      ____________________________________________________________________________________________ 
     */    
      
      //button event           
      button.setOnAction(event ->
      {
      if(countryText.getText().toUpperCase().equals("US")){
            if(vbox.getChildren().contains(stateText))
               vbox.getChildren().remove(stateText);
            else  
               vbox.getChildren().add(stateText);
       }
       //need to set if statements and call the correct method
       result.setText("total cases: "+obj.getCasesWorld());
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
      if(countryCheckBox.isSelected())
         vbox.getChildren().add(countryText);
      });   

      
   }
}   
      
      
    


   
  

 
