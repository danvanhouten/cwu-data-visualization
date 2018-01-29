import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.*;
import java.lang.Math.*;
import java.util.Scanner;

class DVParser
{
    //Default constructor
    public DVParser()
    {}
    
	//Parses a .csv file into a usable DataSet
	public DataSet parseData(File loc)
	{
		//Import .csv file into java
		String[][] rawInput = this.getStringFromCSV(loc);
		//Transform raw input into field names array for DataSet object
		String[] inputFieldNames = this.stringToFieldNames(rawInput);
		//Transform raw input into double array
		double[][] inputValues = this.stringToValues(rawInput);
		//Normalize values
		double[][] normalizedValues = this.normalize(inputValues);
		//Transform values in to DataObjects
		DataObject[] inputObjects = this.valueToDataObjects(normalizedValues);
		//Create the DataSet and return it
		DataSet output = this.createDataSet(inputFieldNames, inputObjects);
		return output;
	}

	//Takes string path to .csv file and returns Strign array of input values.
	private String[][] getStringFromCSV(File location)
	{
      
		//Create a buffered reader object, using try to make sure file is handled correctly
		try (Scanner reader = new Scanner(location))
		{
			//Create a String arraylist to store split values in
			List<String> unsplitInput = new ArrayList<String>();
			String line;

			//Loop through, reading each line and adding it to the String array
			do
			{
				//Read next line of .csv
				line = reader.nextLine();

				//Add string to arraylist
				unsplitInput.add(line);
			}while(reader.hasNextLine());

			//Convert String array list into 2d String array, then return it
			String[][] output = new String[unsplitInput.size()][0];

			for(int i = 0; i < unsplitInput.size(); i++)
			{
				output[i] = unsplitInput.get(i).split(",");
			}
         	return output;
		}
      catch(IOException e)
      {}
      return null;
	}

	private String[] stringToFieldNames(String[][] inputRaw)
	{
		//Create a new String array that the output will be stored in
		String[] output = new String[inputRaw[0].length];

		//Loop the through the first row of the string array, setting the field name values to the values in the first row
		for(int i = 0; i < inputRaw[0].length; i++)
		{
			output[i] = inputRaw[0][i];
		}

		return output;

	}
	
	private double[][] stringToValues(String[][] inputRaw)
	{
      //Establsih height and width as variables
      int height = inputRaw.length - 1;
      int width = inputRaw[0].length;
      //Create new array using the height and width values
      double[][] output = new double[height][width];
      //Starting at the second row, iterate through array of values, moving them all into the new array
      for(int i = 0; i < height; i++)
      {

         for(int j = 0; j < width; j++)
         {
            //Turn the Strings into doubles and store in new array
            output[i][j] = Double.parseDouble(inputRaw[i+1][j]);
         }
      }
      //Return output
      return output;
	}

	private double[][] normalize(double[][] values)
	{
      //Create a for loop iterating through each array
      for(int i = 0; i < values.length; i++)
      {
         double tempMax = values[i][0];
         double tempMin = values[i][0];
         
         //Find the max and min of the array
         for(int j = 0; j < values[i].length; j++)
         {
            if(values[i][j] > tempMax)
            {
               tempMax = values[i][j];
            }
            else if(values[i][j] < tempMin)
            {
               tempMin = values[i][j];
            }
            else{}
         }
         //Iterate over each value, normalizing them to between 1 and 0.
         for(int j = 0; j < values[i].length; j++)
         {
            values[i][j] = ((values[i][j]-tempMin)/((tempMax-tempMin)))+.5;
         }
      }
      return values;
	}

	private DataObject[] valueToDataObjects(double[][] values)
	{
        //Create an array of dtat objects to store output
        DataObject[] output = new DataObject[values.length];
        //use a for loop to turn each array into a data object.
        for(int i = 0; i < values.length; i++)
        {
            output[i] = new DataObject(values[i], i);
        }
        //Return output
        return output;
	}

	private DataSet createDataSet(String[] fieldNames, DataObject[] objects)
	{
        //return a newly created DataSet
        return new DataSet("temp", fieldNames, objects);
	}
}