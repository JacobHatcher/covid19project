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
import java.util.Locale;
import javafx.collections.FXCollections;
import java.io.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;





public class CovidDemo extends Application{
    
  
    
   private VBox labels,results;
   private HBox dropDownMenus;
   private Button button;
   private ComboBox<String> monthSelection,countrySelection;
   private Scene scene;
   private Label label1,label2,label3,label4,result1,result2,result3,result4;
   

    
    
    

   public static void main(String[] args) throws Exception{
      launch(args);
   }
   public void start(Stage stage)throws IOException{
     
      //button 
      button=new Button("Submit");
      button.setOnAction(new ButtonClickHandler());
      
      //month choice dropdown menu
      monthSelection = new ComboBox<>();
      monthSelection.getItems().addAll("January","February","March","April","May");
      monthSelection.setValue("Select a Month");
      
      //country choice dropdown menu
      countrySelection = new ComboBox<>();
      countrySelection.getItems().add("Select a Country");
      String[] locales = Locale.getISOCountries();
      
      for (String countrylist : locales)
      {
         Locale obj = new Locale("", countrylist);
         String[] city = {obj.getDisplayCountry()};
         countrySelection.setItems(FXCollections.observableArrayList(locales));
      }
      
      countrySelection.setValue("Select a Country");
      
      //labels for the "titles"
      label1=new Label("TOTAL CASES:");
      label2=new Label("AVERAGE CASES PER DAY:");
      label3=new Label("TOTAL DEATHS:");
      label4=new Label("AVERAGE DEATHS PER DAY:");
      
      //labels for the statistic results
      result1=new Label("Results");
      result2=new Label("Results");
      result3=new Label("Results");
      result4=new Label("Results");
      
      
      
      //hbox for drodown menus on GUI  
       dropDownMenus=new HBox(20,monthSelection,countrySelection);
       dropDownMenus.setAlignment(Pos.CENTER);

      //vbox for control prompts in GUI
       VBox controls=new VBox(10,dropDownMenus,button);
       controls.setAlignment(Pos.CENTER);
        
       //vbox for the titles  
       VBox labels=new VBox(10,label1,label2,label3,label4);
       labels.setAlignment(Pos.CENTER_LEFT);
       
       //vbox for the covid stat results  
       VBox results=new VBox(10, result1,result2,result3,result4);
       results.setAlignment(Pos.CENTER_RIGHT);
       
       //hbox to organize the label and results vboxes
       HBox labelPlusResults=new HBox(35,labels,results);
       labelPlusResults.setAlignment(Pos.CENTER);
       
       // vbox to be container that will organize all componets of GUI
       VBox container = new VBox (10,controls,labelPlusResults);
       container.setAlignment(Pos.TOP_CENTER);
       container.setPadding(new Insets(10));
      

      
      //scene
      stage.setTitle("Covid Cases");      
      scene=new Scene( container,300,200);
      stage.setScene(scene);
      stage.show();
   
   }
      /*
    * Event handler class for button
    */
   class ButtonClickHandler implements EventHandler<ActionEvent>{
      @Override
      public void handle(ActionEvent event)
      {
         //need to modify labels to make it show the statistics based on which month and/or country the user selects         
        result1.setText("total cases");
        result2.setText("avg cases");
        result3.setText("total deaths");
        result4.setText("avg deaths");
       
      }
   }
}