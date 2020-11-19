import java.io.*;
import java.util.Scanner;
public class CovidProject
{

    private int size=0;
    private String[] countryArray;
    private String[] dateArray;
    private String[] states;
    private String[] confirmedCases;
    private String[] fatalities;
    private final String finalDate="5/15/2020";
    
   
   
   
   
   public CovidProject() throws IOException
   {
      
         File file = new File("train-covid.txt");
         Scanner inputFile=new Scanner(file);
         //get array size
         while(inputFile.hasNextLine())
         {
            String str=inputFile.nextLine();
            size++;
      
         }
        
      //initialzie arrays   
      inputFile=new Scanner(file);
      countryArray=new String[size];
      dateArray=new String[size];
      confirmedCases= new String[size];
      fatalities= new String[size];
      states=new String[size];
      
      int index=0;
     
      //add data from file to the arrays
      while(inputFile.hasNextLine())
      {
         String line=inputFile.nextLine();
         String[] tokens=line.split(",");
         states[index]=tokens[1].trim();
         countryArray[index]=tokens[2].trim();
         dateArray[index]=tokens[3].trim();
         confirmedCases[index]=tokens[4].trim();
         fatalities[index]=tokens[5].trim();
         index++;
     
       
            
      }
         
      }
      
      //gets the total cases in a country
      public String getCasesCountry(String country)
      {
         String cases="";
         int total=0;
         
         
         for(int i=0;i<size;i++)
         {
            
            
            if(dateArray[i].equals(finalDate)&&countryArray[i].equals(country))
               total+=Integer.parseInt(confirmedCases[i]);
            
         
         }
        
            return Integer.toString(total);   

      }
      
    //   public String getCasesCountry(String country,String month)
//       {
//             get the total amount of cases during a month in a country
//          
//       
//       }
      //gets the total cases in world
      public String getCasesWorld()
      {
         int cases=0;
         for(int i=0;i<size;i++)
         {
            if(dateArray[i].equals(finalDate))
               cases+=Integer.parseInt(confirmedCases[i]);
         
         }
         
         return Integer.toString(cases);
      
      }
      
      // public String getCasesWorld(String month)
//       {
//             get the total amount of cases during a  month worldwide
//       
//       }
  
         //gets total cases in a state in the US
         public String getCasesState(String inputState)
         {
            String cases="";
            for(int i=0;i<size;i++)
            {
               
               if(states[i].equals(inputState)&&dateArray.equals(finalDate))
                  cases=confirmedCases[i];
                
            
            }
            return cases;
         
         
         }
         //gets total cases in a state in the US during a month
         public String getCasesState(String inputState,String month)
         {
            String cases="";
            String[] dateTokens=month.split("/");
            for(int i=0;i<size;i++)
            {
                for(int j=0;j<states.length;j++)
               {
               if(states[j].equals(inputState)&&//need to modify this if statement to only get the last date from the month since data is cumulative
               dateTokens.equals(month))
                  cases=confirmedCases[i];
               } 
           
            }
               return cases;

  
         }
         
          
        
}  
      
      
      
   














