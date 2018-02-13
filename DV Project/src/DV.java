//Author:Fawziah Alkharnda
//DV Program

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.*;
import org.jfree.chart.axis.ValueAxis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
//import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.io.*;
import java.util.ArrayList;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;


public class DV extends JFrame implements DocumentListener {
    private static long seriesCount = 0;
    private static final long serialVersionUID = 1L;

    //Declaration for the textArea
    JPanel sliderPanel;
    JPanel jp = new JPanel();
    JTextArea analyticsText;
    XYSeriesCollection data;
    JFreeChart chart;
    XYPlot plot;

    public void start() {

    }

    //setting the window width & height
    final private int width = 1200;
    final private int height = 800;

    //constructor

    public DV() {
        super("DV Program");
        this.setName("DV Program");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

        //Create the menu
        createMenuBar(this);

        //Create the main panel for the program and set its constraints using gridbaglayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create the three main scroll panes that will be used in the program
        JScrollPane graphPane, anglesPane, analyticsPane;

        //Create the grid and add it to the corresponding scroll pane

        //Code for creating graph
        data = new XYSeriesCollection();
        chart = ChartFactory.createXYLineChart("", "", "", data);
        plot = (XYPlot) chart.getPlot();
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
                new Paint[] {Color.RED},
                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        plot.getRangeAxis().setVisible(false);
        plot.getDomainAxis().setVisible(false);
        chart.removeLegend();
        plot.setRangeGridlinesVisible(false);
        chart.setBorderVisible(false);
        ChartPanel graphPanel = new ChartPanel(chart);
        graphPanel.setPreferredSize(new Dimension(1000, 630));
        //Code for creating graph end

        graphPane = new JScrollPane(graphPanel);
        graphPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        graphPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        constraints.weightx = 0.7;
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JPanel graphDomainPanel = new JPanel();
        graphDomainPanel.setLayout(new BoxLayout(graphDomainPanel, BoxLayout.Y_AXIS));
        graphDomainPanel.setPreferredSize(new Dimension(1000, 650));
        graphDomainPanel.add(graphPane);
        
        //Create slider to handle range and grouping
        JPanel domainPanel = new JPanel();
        JSlider domainSlider = new JSlider();
        domainSlider.setPreferredSize(new Dimension(1000, 20));
        domainSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        domainPanel.add(domainSlider);
        
        graphDomainPanel.add(domainPanel);

        mainPanel.add(graphDomainPanel, constraints);

        //Create the angles list and add it to the corresponding scroll pane
        sliderPanel = new JPanel(new GridLayout(1,0));
        sliderPanel.setPreferredSize(new Dimension(160,650));
        anglesPane = new JScrollPane(sliderPanel);
        anglesPane.setPreferredSize(new Dimension(200, 650));
        anglesPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        constraints.weightx = 0.3;
        constraints.gridx = 1;
        constraints.gridy = 0;

        mainPanel.add(anglesPane,constraints);


        //Create the analytics text and add it to the corresponding scroll pane
        analyticsText = new JTextArea(10, 108);
        jp.add(analyticsText);
        analyticsPane = new JScrollPane(jp);
        analyticsPane.setPreferredSize(new Dimension(1200, 150));
        analyticsPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        mainPanel.add(analyticsPane, constraints);

        add(mainPanel)
;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub

    }

    private static void updateFieldAngles(JPanel sliderPanel) {
        sliderPanel.removeAll();
        sliderPanel.setLayout(new GridLayout(Main.userInputData.get(0).fields.length, 0));
        int fieldNumber = Main.userInputData.get(0).fields.length;
        for (int j = 0; j < fieldNumber; j++) {
            String fieldName = Main.userInputData.get(0).getFieldName(j);
            int fieldAngle = 45;
            SliderWithIntegration.createPanel(fieldName, fieldAngle, j, sliderPanel);
        }
    }

    private void createMenuBar(JFrame frame) {
        //Adding menu bar to the app
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();
        JMenuItem menuItem = new JMenuItem();
        frame.setJMenuBar(menuBar);
        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "");
        menuBar.add(menu);

        //a group of JMenuItems
        JMenuItem menuItem0 = new JMenuItem("Create New Project",
                KeyEvent.VK_T);
        menuItem0.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem0.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem0);

        //Create code for file opener
        final JFileChooser fc = new JFileChooser();

        menuItem0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open the file chooser dialog and get the users file location
      
               int returnVal = fc.showOpenDialog(DV.this);

                //Check if the return value is a valid path
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    //get the file that the user selected
                    File inputFile = fc.getSelectedFile();
                    //Create a new parser and parse the file
                    DVParser inputParser = new DVParser();
                    Main.userInputData.add(inputParser.parseData(inputFile));
                    //Update the slider panel to fit all the sliders
                    sliderPanel.setPreferredSize(new Dimension(160,(100 * Main.userInputData.get(0).fieldAngles.length)));
                    //Draw the graph
                    drawGraphs();
                }
                //Update the field angles, then redraw UI
                updateFieldAngles(sliderPanel);
                repaint();
                revalidate();
            }
        });

        JMenuItem menuItem1 = new JMenuItem("Open Saved Project",
                KeyEvent.VK_T);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem1);

        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open the file chooser dialog and get the users file location
                int returnVal = fc.showOpenDialog(DV.this);
            }
        });

        JMenuItem menuItem2 = new JMenuItem("Save Project",
                KeyEvent.VK_T);
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem2.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem2);

        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open the file chooser dialog and get the users file location
                int returnVal = fc.showOpenDialog(DV.this);
            }
        });

        JMenuItem menuItem3 = new JMenuItem("Save Project As",
                KeyEvent.VK_T);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem3.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem3);

        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open the file chooser dialog and get the users file location
                int returnVal = fc.showOpenDialog(DV.this);
            }
        });

        JMenuItem menuItem4 = new JMenuItem("Import Data",
                KeyEvent.VK_T);
        menuItem4.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem4.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem4);

        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Open the file chooser dialog and get the users file location
                int returnVal = fc.showOpenDialog(DV.this);
            }
        });
        //Build second menu in the menu bar.
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "");
        menuBar.add(menu);


        menuItem = new JMenuItem("Additional Options",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem);

        //Build third menu in the menu bar.
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "");
        menuBar.add(menu);

        menuItem = new JMenuItem("User Manual",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "");
        menu.add(menuItem);
    }

    void drawGraphs() {
        data.removeAllSeries();
        ValueAxis domainView = plot.getDomainAxis();
        domainView.setRange(0, Main.userInputData.get(0).fieldLength);
        ValueAxis rangeView = plot.getRangeAxis();
        rangeView.setRange(0, Main.userInputData.get(0).fieldLength);
        for(int i = 0; i < Main.userInputData.size(); i++)
        {
            ArrayList<XYSeries> graphObjects = new ArrayList<XYSeries>();
            Main.userInputData.get(i).generatePoints();
            for(int j = 0; j < Main.userInputData.get(i).members.length; j++)
            {
                graphObjects.add(new XYSeries(j, false, true));
                graphObjects.get(j).add(0, 0);
                for(int k = 0; k < Main.userInputData.get(i).fields.length; k++)
                {
                    double x = Main.userInputData.get(i).members[j].points[k][0];
                    double y = Main.userInputData.get(i).members[j].points[k][1];
                    graphObjects.get(j).add(x, y);
                }

            }
            for(int j = 0; j < graphObjects.size(); j++)
            {
                data.addSeries(graphObjects.get(j));
            }
        }

    }
}
