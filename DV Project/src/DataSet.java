import java.util.Arrays;

public class DataSet{                            
        String name;                                            //name of the DataSet
        String[] fields;                                        //An array containing the name of each data field
        double[] fieldAngles;                                   //an array containing the angle that each data field should be rendered at
        DataObject[] members;                                   //an array containing each DataObject in the DataSet
        int fieldLength;                                        //the number of fields in the DataSet
        
        
        public DataSet(String name, String[] fieldNames, DataObject[] objects){ //creates a new DataSet using the given input
            this.name = name;
            fields = fieldNames;
            members = objects;
            fieldLength = fields.length;
            fieldAngles = new double[fieldLength];
            for(int i = 0; i < fieldLength; i++)
            {
                fieldAngles[i] = 45;
            }
        }

        public void generatePoints()
        {
            for(int i = 0; i < members.length; i++)
            {
                members[i].updatePoints(fieldAngles);
            }
        }
        
        
        public String getFieldName(int position){
            return fields[position];
        }
        
        public DataObject getMember(int position){
            return members[position];
        }
        
        public int length(){
            return fieldLength; 
        }
        
        public double getFieldAngle(int position){
            return fieldAngles[position];
        }
        
        public void updateFieldAngles(double[] userFieldAngles){ 
            fieldAngles = userFieldAngles;
        }
    
    }