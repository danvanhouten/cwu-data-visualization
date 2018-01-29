import java.util.Arrays;

import static java.lang.Math.cos;

public class DataObject{                                        //DataObject class with 2 variables, index and value
        int index;                                              //index: the objects position in the dataset
        double[] values;                                        //values: the array of normalized values for this DataObject
        double[][] points;
        
        public DataObject(double[] val, int index){             //Creating the DataObject with variables val and index
            values = val;                                       //assigns val to values
            this.index = index;                                        //assigns ind to index
            
        }
        
        public double getValue(int position){                   //getValue: returns value at a specified position 
            return values[position];
        }
        
        public String toString(String[] fields){                //toString: returns a string of all values, each seperated by a comma and space
            return (Arrays.toString(values));
        }

    public void updatePoints(double[] angles)
    {
        points = new double[values.length][2];
        int counter = 0;
        double[] temp = getCoords(values[counter], angles[counter]);
        points[counter][0] = temp[0];
        points[counter][1] = temp[1];
        if(values.length > counter)
        {
            updatePoints(temp[0], temp[1], (counter + 1), angles);
        }
        else
        {}
    }

    private void updatePoints(double prevX, double prevY, int counter, double[] angles)
    {
        double[] temp = getCoords(values[counter], angles[counter]);
        points[counter][0] = (temp[0] + prevX);
        points[counter][1] = (temp[1] + prevY);
        counter++;
        if(values.length > counter)
        {
            updatePoints(points[counter - 1][0], points[counter - 1][1], counter , angles);
        }
        else
        {}
    }

    private double[] getCoords(double length, double angle)
    {
        double[] output = new double[2];
        output[0] = (Math.cos(Math.toRadians(angle))) * length;
        output[1] = (Math.sin(Math.toRadians(angle))) * length;
        return output;
    }
}