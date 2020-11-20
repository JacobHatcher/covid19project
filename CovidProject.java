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
    private String[] dateTokens;
    private final String[] endingDate={"1/31/2020","2/29/2020","3/31/2020","4/30/2020","5/15/2020"};
    
    
   
   
   
   
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
     
      //read data from file and store in the arrays
      while(inputFile.hasNextLine())
      {
         String line=inputFile.nextLine();
         String[] tokens=line.split(",");
         states[index]=tokens[1].trim();
         countryArray[index]=tokens[2].trim();
         dateArray[index]=tokens[3].trim();
         confirmedCases[index]=tokens[4].trim();
         //fatalities[index]=tokens[5].trim();
         index++;
     
       
            
      }
         
      }
      
      //gets the total cases in a country
      public String getCasesCountry(String country)
      {
         
         int total=0;
         
         
         for( int i=0;i<size;i++)
         {
            
            
            if(dateArray[i].equals(endingDate[5])&&countryArray[i].equalsIgnoreCase(country))
               total+=Integer.parseInt(confirmedCases[i]);
            
         
         }
        
            return Integer.toString(total);   

      }
      
       public String getCasesCountry(String country,int monthNum) throws ArrayIndexOutOfBoundsException
      {
             //get cases in coutry during a month
             int total=0;
             int lastMonthTotal=0;
             for(int i=0;i<size;i++){
             if(country.equalsIgnoreCase(countryArray[i])&&dateArray[i].equals(endingDate[monthNum-1]))
               total+=Integer.parseInt(confirmedCases[i]);
               
             
               
               
                
             }
             return Integer.toString(total);
     }
      //gets the total cases in world
      public String getCasesWorld()
      {
                  
         return getCasesWorld(5);
      
      }
      
        public String getCasesWorld(int monthNum)
        {
           int cases=0;
           for(int i=0;i<size;i++)
            {
               if(dateArray[i].equals(endingDate[monthNum-1]))
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
               
               if(states[i].equals(inputState)&&dateArray[i].equals(endingDate[endingDate.length-1]))
                  cases=confirmedCases[i];
                
            
            }
            return cases;
         
         
         }
         //gets total cases in a state in the US during a month
          public String getCasesState(String inputState,int monthNum)
          {
             //get cases in state during month
            String cases="";
            for(int i=0;i<size;i++)
            {
               
               if(states[i].equals(inputState)&&dateArray[i].equals(endingDate[monthNum-1]))
                  cases=confirmedCases[i];
                
            
            }
            return cases;
                  
          }
          
                   
         public int getMonthNum(String month)
         {
            int monthNum=0;
            if(month.equals("January"))
               monthNum=1;
            if(month.equals("February"))
               monthNum=2;               
            if(month.equals("March"))
               monthNum=3;
            if(month.equals("April"))
               monthNum=4;
            if(month.equals("May"))
               monthNum=5;
            return monthNum;
         
         }
                     
            
            
            
         
         
         
         
                  
}  
      
      
      
   














