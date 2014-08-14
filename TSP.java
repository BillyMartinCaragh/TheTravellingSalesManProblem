import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Write a description of class RouteVerifier here.
 * 
 * @author Billy Martin 12406742
 * @version 1.0 09/05/2014
 */
public class TravellingSalesManProblem
{   

    public static double[]latitudeArray; //Array for the latitude
    public static double[]longtitudeArray; //Array for the longtitude
    public static double [][] DistanceArray = new double[100][100]; //Matrix to store the Distances
    public static boolean [] townsVisited = new boolean[100]; //Boolean array to prevent duplicates
    public static double [] TrackTaken = new double[100];
    public static int [] MainArray = new int[100]; //Array to store the path by the towns values 0.12.5 etc.
    public static double totalDistanceFromEachTown = 0; //storing the final distance

   

    public static void main(String args[]) throws IOException
    {
        
      
        FileIO latitude = new FileIO();  
        String[] latitudeA = latitude.load("latitude.txt"); //Loading in the latitude
        double[]latitudeArray=new double[latitudeA.length];
        
        FileIO longtitude = new FileIO(); 
        String[] longtitudeA = longtitude.load("longtitude.txt");//Loading in the longtitude
        double[]longtitudeArray=new double[longtitudeA.length];
        
    
     
        for(int i=0; i<latitudeA.length; i++)
        {
           
            latitudeArray[i] = Double.parseDouble(latitudeA[i]);
            longtitudeArray[i] = Double.parseDouble(longtitudeA[i]);//CONVERTING STRINGS TO DOUBLES, FILLING THE ARRAYS
          
           
        }
        
        
        
        for(int i=0; i<latitudeA.length; i++) //FILLING THE MATRIX
        {
            
            for(int j=0; j<latitudeA.length; j++)
            {
               DistanceArray[i][j]=DistanceCalc(latitudeArray[i],longtitudeArray[i],latitudeArray[j],longtitudeArray[j]); //Filling up the matrix with distances
            }
            
        }
          
     
     
         int townNumber = 27; //Starts at town 1
         double SmallestDistance= 120000.0; //This number is set large on purpose
         
        
         townsVisited[townNumber] = true; //Set it to visited
         int current = townNumber; //Set current town you're on to FirstTown
         MainArray[0]=townNumber;//Hardcoding
         int townN=1; //Keeping each town number in order, 0. town++ next number 0.52. and so on
  
 
        for(int z=1; z<100; z++)//run for each row/col
        {
           //No towns Visited yet
           SmallestDistance= 120000.0; //Reset it each time
           
            for(int x=0; x<100; x++)
            {
                
                if(townsVisited[x]==false) //if a towns not visited yet
                {
                   
                    if(DistanceArray[current][x]<SmallestDistance) //Finding smallest not equal to 0 The random jitter is in there at 15 km (+Math.random()*15)
                    {
                    
                        SmallestDistance =  DistanceArray[current][x]; //Setting the shortest distance
                        townNumber=x; //Tracking the town
                        
                      
                    }
                    
                   
                }
           
            }
            
            townsVisited[townNumber]=true; //Town was visited so mark it to true
            MainArray[townN++] = townNumber; //Giving each town its own slot in the array
            current = townNumber; //Changing the town each town to the next town in the array
            totalDistanceFromEachTown += SmallestDistance; //Adding up the distance each time
          
            
            
        }
    
         totalDistanceFromEachTown = totalDistanceFromEachTown + DistanceArray[MainArray[0]][MainArray[MainArray.length-1]]; //Calculating the total Distance going fr
        
    
        for(int i=0; i<MainArray.length;i++) //Prints out path...
        {
            System.out.print(MainArray[i]+1+ "."); //1.53.46.35.67.2.72.71.62.18.77.65.6.43.59.31.29.32.20.41.75.61.54.66.22.48.21.79.8.76.39.69.55.5.30.12.68.13.37.64.33.4.60.23.14.63.28.16.49.10.57.11.51.27.19.50.9.7.44.70.36.80.40.3.78.74.34.52.38.58.26.47.15.24.56.45.42.17.73.25
        }
        
        
        System.out.println("\nThe Distance I recieved is: "+totalDistanceFromEachTown); //Print out the results
   
        
    
 }


   
   public static double DistanceCalc(double L1, double L01, double L2, double L02)//Reading in the coordinates of the text files
   {
       
        //CODE FROM LAB8 SOLUTION
        double latitude1=L1; //Read in the first latitude
        double longtitude1=L01; //Read in the first Longtitude
        double latitude2=L2;//Read in the second latitude
        double longtitude2=L02;//Read in the secong longtitude
        double radiusOfTheEarth = 6371; //Earths Radius hardcoded
        
        double differenceLa = Math.toRadians(latitude2 - latitude1); //Difference of the latitudes
        double differenceLo = Math.toRadians(longtitude2 - longtitude1); //Difference of the lontitudes
        
        double calc = Math.sin(differenceLa / 2) * Math.sin(differenceLa / 2) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.sin(differenceLo / 2) * Math.sin(differenceLo / 2); //Doing the calculations for the haversine
        double calc2 = 2 * Math.atan2(Math.sqrt(calc), Math.sqrt(1 - calc));//Continue Calculations
        double distance = radiusOfTheEarth * calc2;
        
        return distance; //Return the distance
    }
    
   
 

} 

    
       
      
