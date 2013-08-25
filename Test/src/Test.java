import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		int[] values = new int[]{ 1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17,-2147483647,-2147483647 };
//		List<Integer> list = new ArrayList<Integer>();
//		for(int i = 0 ; i< values.length; i ++){
//			list.add(values[i]);
//		}
//		
//		UniqueIteratorImpl<Integer> myIterator = new UniqueIteratorImpl<Integer>(list);
//		while(myIterator.hasNext()){
//			Number number = myIterator.next();
//			System.out.println(number);
//		}
		
		Test test = new Test();
		double s =  test.square_root(0, 1e-6);
		System.out.println(s);
		
	}
	
	
	public int[] test(int[]values ){
		
		if(values == null || values.length == 0){
		     return new int[0];
			 }
		
		int[] tempArray = new int[values.length];
		
		

		int uniqueCounter = 0;
        int actualSize = 0; 
		int maxNegativeIntValue = -2147483648;
		
		for (int i = 0; i < values.length; i++) {

			boolean unique = true;

			for (int j = 0; j < uniqueCounter && unique; j++) {
				if (values[i] == tempArray[j]) {
					unique = false;
				}
			}
			if (unique) {
				tempArray[uniqueCounter++] = values[i];
				actualSize++;
			} else {
				tempArray[uniqueCounter++] = maxNegativeIntValue;
			}
		}
		
		int[] finalArray = new int[actualSize];
		
	    int indexCounter = 0;
		for(int m =0; m < tempArray.length ; m ++){
			int val = tempArray[m];
			
			if(val != maxNegativeIntValue){
				finalArray[indexCounter++] = val;
			}
		}

		return finalArray;
	}
	
	/**
	 * Method to remove duplicated value in an int array
	 * @param values an int array which can contain both positive and negative integer
	 * @return An empty array if values is NULL or Empty. Otherwise, an int array which contains only unique integer value 
	 */
	public int[] removeDuplicates(int[] values) {
		if(values == null || values.length == 0){
			return new int[0];
		}
		int tempArray[] = new int[values.length];
		int j = 0;
		int actualSize = 0 ;
		for (int i : values) {
			if (!isDuplicated(tempArray, i)){
				tempArray[j++] = i;
				actualSize++;
			}	
		}
		
		int[] finalArray = new int[actualSize];
		j = 0;
		for(int m =0; m < actualSize ; m ++){
			finalArray[j++] =  tempArray[m];
		}
		return finalArray;
	}

	private static boolean isDuplicated(int[] array, int value) {
		for (int i : array) {
			if (i == value){
				return true;
			}	
		}
		return false;
	}
	
	public double square_root(double a, double epsilon){
	
		double number = a;            
        while (Math.abs(number - a/number) > epsilon*number) {
            number = (a/number + number) / 2.0;
        }

        return number;
	}
}
