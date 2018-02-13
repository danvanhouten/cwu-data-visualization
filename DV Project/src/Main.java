import java.util.ArrayList;

public class Main {

   //Declare variables
   public static ArrayList<DataSet> userInputData = new ArrayList();
   static DV dv;
   static boolean domainActive;
   static boolean groupingActive;
   static double domainMinPercent;
   static double domainMaxPercent;
   static double domainMinValue;
   static double domainMaxValue;
   static double[] sortingLinesPercent;
   static double[] sortingLinesValue;
   static double[][] sortingDistribution;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dv = new DV();
		   dv.start();
	}

}
