/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author tyler
 */
public class SliderWithIntegration {

    
    
    public static void createPanel(String fieldName, int fieldAngle, int i, JPanel frame){
         
        JPanel panel = new JPanel();

        JTextField textField;
         
        // textField stuff
        
        textField = new JTextField(2);

        textField.setFont(textField.getFont().deriveFont(12f));
        String fieldAngleString = Integer.toString(fieldAngle);
        textField.setText(fieldAngleString);
        
        // Add status label to show the status of the slider
        JLabel status = new JLabel(fieldName);
         

        // Set the slider

        JSlider slider = new JSlider(0, 90, fieldAngle);

        slider.setMinorTickSpacing(10);

        slider.setPaintTicks(true);

         

        // Set the labels to be painted on the slider

        slider.setPaintLabels(true);

         

        // Add positions label in the slider

        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
        
        status.setText(fieldName + " Angle: " + fieldAngle);          
        
        position.put(0, new JLabel("0"));
        
        position.put(30, new JLabel("30"));

        position.put(60, new JLabel("60"));
        
        position.put(90, new JLabel("90"));

        //position.put(120, new JLabel("120"));
        
        //position.put(150, new JLabel("150"));
        
        //position.put(180, new JLabel("180"));
        
        // Set the label to be drawn

        slider.setLabelTable(position);

         

        // Add change listener to the slider

        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                
                status.setText(fieldName + " Angle: " + ((JSlider)e.getSource()).getValue());
                   
                   
                   int fieldAngle = slider.getValue();
                   String fieldAngleString = Integer.toString(fieldAngle);
                    Main.userInputData.get(0).fieldAngles[i] = fieldAngle;
                    Main.dv.drawGraphs();
                   try{

                        textField.setText(fieldAngleString);

                    }catch(NumberFormatException ex){

                        System.err.println("Ilegal input");

            

                    }
                   
           }

        });
        
        
        
        
        
        
         DocumentListener documentListener = new DocumentListener() {
      public void changedUpdate(DocumentEvent documentEvent) {
        //printIt(documentEvent);
      }
      public void insertUpdate(DocumentEvent documentEvent) {
        //printIt(documentEvent);
      }
      public void removeUpdate(DocumentEvent documentEvent) {
        //printIt(documentEvent);
      }
      private void printIt(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();
        String typeString = null;
        if (type.equals(DocumentEvent.EventType.CHANGE)) {
          typeString = "Change";
        }  else if (type.equals(DocumentEvent.EventType.INSERT)) {
          typeString = "Insert";
        }  else if (type.equals(DocumentEvent.EventType.REMOVE)) {
          typeString = "Remove";
        }
        System.out.print("Type : " + typeString);
        Document source = documentEvent.getDocument();
        int length = source.getLength();
        System.out.println("Length: " + length);
        String textInTextField = textField.getText();
        System.out.println(textInTextField);
        
        
        try{

            int textInTextFieldInt = Integer.valueOf(textInTextField); 

            
            slider.setValue(textInTextFieldInt);
           

        }catch(NumberFormatException ex){

            System.err.println("Ilegal input");

            

        } 
     
        
        
         
      }
    };
    textField.getDocument().addDocumentListener(documentListener);
    
        
        
        
       /*textField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                textField.getText();
                System.out.println("sdsd");
            }
           
       });*/

        
        
        // Add the slider to the panel

        panel.add(slider);
        
        // Add textField to the panel
        panel.add(textField);

        // Set the window to be visible as the default to be false

        panel.add(status);
        frame.add(panel);

        frame.setMaximumSize(new Dimension(195, 300));
    }
    
   
    /**
     * @param args the command line arguments
     */
   /** public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Field Angles");

        frame.setSize(500, 1000);

        frame.setLayout(new GridLayout(3, 1));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //int numFields = DataSet.length();
        int numFields = 5;//////////////////////////////////////////////////////FOR TESTING/////////////////////////////////////////////////////////////////////////////////////
           
        for(int i = 0; i < numFields; i++){
            //String fieldName = DataSet.getFieldName(i);
            String fieldName = "sdasdfasf";////////////////////////////////////FOR TESTING///////////////////////////////////////////////////////////////////////////////////////////////////
            //int fieldAngle = DataSet.getFieldAngle(i);
            int fieldAngle = 55;///////////////////////////////////////////////FOR TESTING//////////////////////////////////////////////////////////////////////////////////////////////////////////////
            createPanel(fieldName, fieldAngle, i, frame);
         
         
        }
         
         
        
        frame.pack();

        frame.setVisible(true);
        
        

    }**/
    
    
    
}
