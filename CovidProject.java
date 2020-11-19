import java.io.*;
import java.util.Scanner;
public class CovidProject
{
    private Scanner inputFile;
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
         inputFile=new Scanner(file);
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
         
         
         for( int i=0;i<size;i++)
         {
            
            
            if(dateArray[i].equals(finalDate)&&countryArray[i].equals(country))
               total+=Integer.parseInt(confirmedCases[i]);
            
         
         }
        
            return Integer.toString(total);   

      }
      
      public String getCasesCountry(String country,int monthNum)
     {
            String cases="";
            int total=0;
            for(int i=0;i<size;i++)
            {
                if(country.equals("US")){
                  if(getLastDate(monthNum).equals(dateArray[i]))
                     total+=Integer.parseInt(confirmedCases[i]);
                }     
                if(countryArray[i].equals(country)&&getLastDate(monthNum).equals(dateArray[i]))
                  cases=confirmedCases[i];

            }
                if(country.equals("US"))
                  return Integer.toString(total);
                else
                  return cases;  
         
       
     }
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
      
       public String getCasesWorld(char monthNum)
       {
            int cases=0;
            for(int i=0;i<size;i++)
            {
                
                  if(getLastDate(monthNum).equals(dateArray[i]))
                     cases+=Integer.parseInt(confirmedCases[i]);
            }
                

                  
           
                return Integer.toString(cases);    
       }
   
         //gets total cases in a state in the US
         public String getCasesState(String inputState)
         {
            String cases="";
            for(int i=0;i<size;i++)
            {
               
               if(states[i].equals(inputState)&&dateArray[i].equals(finalDate))
                  cases=confirmedCases[i];
                
            
            }
            return cases;
         
         
         }
         //gets total cases in a state in the US during a month
         public String getCasesState(String inputState,char monthNum)
         {
            String cases="";
            for(int i=0;i<size;i++)
            {
                if(states[i].equals(inputState)&&getLastDate(monthNum).equals(dateArray[i]))
                  cases=confirmedCases[i];

            }
                return cases;

  
         }
         
         public String getLastDate(int monthNum)
         {
            
            String lastDate="";
            if(monthNum==1);
               lastDate="1/31/2020";
            if(monthNum==2);
               lastDate="2/29/2020";
            if(monthNum==3);
               lastDate="3/31/2020";
            if(monthNum==4);
               lastDate="4/30/2020";
            if(monthNum==5);
               lastDate=finalDate;
             
            return lastDate;
            
            
            
            
         
         
         }
         
                  
}  
      
      
      
   














