
public class RadixSort {
	/**
	 * This radix is the one which we modified to use bitwise
	 *
	 *
	 * @param A
	 * @param B
	 * @param r
	 * @param count
	 */
	  static void radix(Integer[] A, Integer[] B, int r, int[] count) {
		int max = A[0];
		for (int i = 1; i < A.length; i++) {
			if (max<A[i]) max = A[i];
		}


		// Count[i] stores number of records in bin[i]
		int i, j, rtok=0;
		while ((max>>rtok)>0){ // For k digits
			for (j=0; j<r; j++) count[j] = 0; // Initialize count
			// Count the number of records for each bin on this pass
			for (j=0; j<A.length; j++) count[(A[j]>>rtok)&1]++;
			// count[j] will be index in B for last slot of bin j.
			for (j=1; j<r; j++) count[j] += count[j-1];
			// Put records into bins, working from bottom of bin
			// Since bins fill from bottom, j counts downwards
			for (j=A.length-1; j>=0; j--)
				B[--count[(A[j]>>rtok)&1]] = A[j];
			for (j=0; j<A.length; j++) A[j] = B[j]; // Copy B back
			rtok++;
		}
	}

	/**
	 * This the radix implemented in the book.
	 * @param A
	 * @param B
	 * @param k
	 * @param r
	 * @param count
	 */
	  static void radix(Integer[] A, Integer[] B,
	                         int k, int r, int[] count) {
		// Count[i] stores number of records in bin[i]
		int i, j, rtok;
		for (i=0, rtok=1; i<k; i++, rtok*=r) { // For k digits
			for (j=0; j<r; j++) count[j] = 0; // Initialize count
			// Count the number of records for each bin on this pass
			for (j=0; j<A.length; j++) count[(A[j]/rtok)%r]++;
			// count[j] will be index in B for last slot of bin j.
			for (j=1; j<r; j++) count[j] = count[j-1] + count[j];
			// Put records into bins, working from bottom of bin
			// Since bins fill from bottom, j counts downwards
			for (j=A.length-1; j>=0; j--)
				B[--count[(A[j]/rtok)%r]] = A[j];
			for (j=0; j<A.length; j++) A[j] = B[j]; // Copy B back
		}
	}


	public static void main(String[] args){


		/**
		 * The Report provide other 5 array sample which were used to test new radix with the book version.
		 *
		 */
		Integer[] arrA = {452,2931,6575,111,8907,550,879};

		Integer[] arrB = new Integer[arrA.length];
		int k = 4;
		int r = 2;
		int bins =10;
		int[] count = new int[r];
		int[] count2 = new int[bins];
		printArr(arrA);

		//using the radixSortBinary
		long start = timenow();
		radix(arrA,arrB,r,count);
		//radix(arrA,arrB,k,bins,count2);
		long end = timenow();
		long elapsedTime = timeElapsed(end,start);
		System.out.println("ElapsedTime: "+elapsedTime+" nanoseconds");
		printArr(arrA);
	}

	/**
	 *  Used to measure time elapsed
	 * @return
	 */
	public static long timenow(){return System.nanoTime();}

	public static long timeElapsed(long a, long b){return (a-b);}

	/**
	 *
	 * Used to print the array
	 * @param arr
	 */
	static void printArr(Integer[] arr){
		for (int i: arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}
