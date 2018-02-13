public class AdditionalOptions
{
	/**
	*Create a void method that converts the percent values found in DomainMinPercent and DomainMaxPercent
	*into their corresponding x values.
	*
	*the graph is x units long, where x is the number of fields
	*
	*Store the generated x values in DomainMinValue and DomainMaxValue
	*
	*All variables to be changed are found in Main.java
	**/
	public static void updateDomains()
	{

	}

    /**
	*Create a void method that converts the percent values found in sortingLinesPercent[]
	*into their corresponding x values.
	*
	*the graph is x units long, where x is the number of fields
	*
	*Store the generated x values in SortingLinesValue
	*
	*The method should then iterate over each DataObject in each DataSet and assess each line endpoint. 
	*
	*It should then record the number of values in each set in each range in sortingDistribution[set][inRange].
	*
	*sortingDistribution should be resized to be #fields x #fields
	*
	*All variables to be changed are found in Main.java
	**/
	public static void updateSorting()
	{
		
	}

	/**
	*Create a void method that uses sortingDistribution[set][inRange] to generate a confusion matrix
	*
	*The confusion matrix should looks imilar to the confusion matrix in Boris' paper
	*
	*Write the confusion matrix to the input JTextArea
	**/
	public static void generateConfusionMatrix(JTextArea writeArea)
	{

	}
}