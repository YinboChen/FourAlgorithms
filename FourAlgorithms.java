/*I certify that I wrote the code I am submitting. 
 * I did not copy whole or parts of it from another 
 * student or have another person write the code for 
 * me. Any code I am reusing in my program is clearly 
 * marked as such with its source clearly identified
 *  in comments.
 *  Student_Name: Yinbo Chen
 *  Student_ID: yzc0129
 *  COMP-3270 Introduction to Algorithms
 *  Purpose: to compute the maximum sum contiguous sub-vector by using four different algorithms
 *  
 */
import java.io.File;//import the file class
import java.io.FileNotFoundException;// to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;//import the scanner class to read .txt file

public class FourAlgorithms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FourAlgorithms it = new FourAlgorithms();
		
		//1.This part is for the questions 1 to 5.
		int[] mytest = it.readFileString();
		System.out.println("algorithm-1: <"+it.algorithm_1(mytest)+">");
		System.out.println("algorithm-2: <"+it.algorithm_2(mytest)+">");
		System.out.println("algorithm-3: <"+it.maxSum(mytest,0, mytest.length - 1)+">");
		System.out.println("algorithm-4: <"+it.algorithm_4(it.readFileString())+">");
		
		//2.This part is for the question 6.		
		int[][] callcreate = it.createMatrix();
		
		//3.This part is for the question 7.
		//generate the 19x8 matrix. rows= 19, columns= 8 to calculate Elapsed Time.
		long[][] time_matrix = new long[19][8];
		
		//iteration_ag? is the N times variable for enlarging the resolution of each algorithm.
		//note for algorithm_1: no more than 100000...
		int iteration_ag1 = 100000;
		int iteration_ag2 = 1000000;
		int iteration_ag3 = 1000000;
		int iteration_ag4 = 10000000;
		
		//For testing algorithm_1 iteration_ag1 times to compute Elapsed Time
		for(int i = 0; i < callcreate.length; i++) {
			
			long start_time = System.nanoTime();
			for(int n =0; n < iteration_ag1; n++) {
				it.algorithm_1(callcreate[i]);
			}
			long end_time = System.nanoTime();
			long elapsed_time = end_time - start_time;
			time_matrix[i][0]= elapsed_time / iteration_ag1;
			//System.out.println(time_matrix[i][0]);
		}
		System.out.println("Completed the algorithm_1");
		
		//For testing algorithm_2 iteration_ag2 times to compute Elapsed Time
		for(int i = 0; i < callcreate.length; i++) {
			long start_time = System.nanoTime();
			for(int n =0; n < iteration_ag2; n++) {
				it.algorithm_2(callcreate[i]);
			}
			long end_time = System.nanoTime();
			long elapsed_time = end_time - start_time;
			time_matrix[i][1]= elapsed_time / iteration_ag2;
			//System.out.println(time_matrix[i][1]);
		}
		System.out.println("Completed the algorithm_2");
		
		//For testing algorithm_3 iteration_ag3 times to compute Elapsed Time
		for(int i = 0; i < callcreate.length; i++) {
					
			long start_time = System.nanoTime();
			for(int n =0; n < iteration_ag3; n++) {
				it.maxSum(callcreate[i],0, callcreate[i].length - 1);
			}
			long end_time = System.nanoTime();
			long elapsed_time = end_time - start_time;
			time_matrix[i][2]= elapsed_time / iteration_ag3;
			//System.out.println(time_matrix[i][2]);
		}
		System.out.println("Completed the algorithm_3");
		
		//For testing algorithm_4 iteration_ag4 times to compute Elapsed Time	
		for(int i = 0; i < callcreate.length; i++) {
				
			long start_time = System.nanoTime();
			for(int n =0; n < iteration_ag4; n++) {
				it.algorithm_4(callcreate[i]);
			}
			long end_time = System.nanoTime();
			long elapsed_time = end_time - start_time;		
			time_matrix[i][3]= elapsed_time / iteration_ag4;
			//System.out.println(time_matrix[i][3]);
			//System.out.println("start: "+ start_time + "; end: "+ end_time);
			}
		System.out.println("Completed the algorithm_4");
		
		//generate the theoretical value of each algorithm and write into the matrix 5-8 columns whose index from 4 -7;		
		for(int i = 0; i < callcreate.length; i++) {
			int j = (i + 2)*5;
			//scale value divided by 30, T1(n) =7n3/4 + 31n2/4 + 54n/4 +4
			long groundtrue1 = (long)Math.ceil((7*j*j*j/4 + 31*j*j/4 + 54*j/4 + 4)/30);
			//scale value divided by 20, T2(n) =11n2/2 + 17n/2 + 4
			long groundtrue2 = (long)Math.ceil((11*j*j/2 + 17*j/2 +4) /20);
			//scale value divided by 5, T3(n) =11n* log_2⁡n + 12
			long groundtrue3 = (long)Math.ceil((11 * j * Math.log(j) / Math.log(2)+12)/5 );
			//scale value divided by 10, T4(n)=13n+5
			long groundtrue4 = (long)Math.ceil((13*j+ 5)/10);
			//writing each algorithm's theoretical value into time_matrix 5 to 8 columns. 
			time_matrix[i][4] = groundtrue1;
			time_matrix[i][5] = groundtrue2;
			time_matrix[i][6] = groundtrue3;
			time_matrix[i][7] = groundtrue4;
		}
		
		//4.this part is for questions 8 and 9.
		//write into a file with name "yinbochen_phw_output.txt"
		it.outputFileTxt(time_matrix, "yinbochen_phw_output.txt");
		
		//testing block
//		for(int i = 10; i <= 100; i+=5) {
//			//scale value divided by 30, T1(n) =7n3/4 + 31n2/4 + 54n/4 +4
//			double groundtrue1 = Math.ceil((7*i*i*i/4 + 31*i*i/4 + 54*i/4 + 4)/30);
//			System.out.println("groundtrue of algorithm 1:" + groundtrue1 );
//		}
//		for(int i = 10; i <= 100; i+=5) {
//			//scale value divided by 20, T2(n) =11n2/2 + 17n/2 + 4
//			double groundtrue2 = Math.ceil((11*i*i/2 + 17*i/2 +4)/20 );
//			System.out.println("groundtrue of algorithm 2:" + groundtrue2 );
//		}
//		for(int i = 10; i <= 100; i+=5) {
//			//scale value divided by 5, T3(n) =11n* log_2⁡n + 12
//			double groundtrue3 = Math.ceil(11 * i * Math.log(i) / Math.log(2) /5);
//			System.out.println("groundtrue of algorithm 3:"+ groundtrue3);
//		}
//		for(int i = 10; i <= 100; i+=5) {
//			//scale value divided by 10, T4(n)=13n+5
//			double groundtrue4 = Math.ceil((13*i+ 5)/10);
//			System.out.println("groundtrue of algorithm 4:" + groundtrue4);
//		}
//		//check point
//		for(int i = 0; i < callcreate.length; i++) {
//			System.out.println(time_matrix[i][2]);
//		}
//		//check the random generated values 
//		int count = 0;
//		for(int i = 0; i < callcreate[18].length; i++) {
//			count++;
//			System.out.println("layer01: "+ callcreate[18][i]+ "--"+count);
//		}
		
	}
	
	//this function is to export the 19x8 matrix to local txt file.
	public void outputFileTxt(long[][] matrix, String filename) {
		/*this part of code refers to the web link
		 * https://stackoverflow.com/questions/33578685/writing-multi-dimensional-array-into-text-file-file
		 * https://stackabuse.com/java-save-write-string-into-a-file/
		 */
		File output = new File(filename);
		try(FileWriter writefile = new FileWriter(output)){
		writefile.write("algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n), T4(n)");
		writefile.write(System.getProperty( "line.separator" ));
			for (int i = 0; i < matrix.length; i++){
					for(int j = 0; j < matrix[i].length; j++) {
						if(j == matrix[i].length-1) {
							writefile.write(Long.toString(matrix[i][j]));
							writefile.write(System.getProperty( "line.separator" ));
							
						}else {
							writefile.write(Long.toString(matrix[i][j]) + ",");
						}
						
					}
			}
		writefile.flush();
		writefile.close();
		System.out.println("Matrix is written into the local file "+ filename +".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Can't write a file.");
		}		
		
	}
	//read strings from local txt file then returns integer array
	public int[] readFileString(){
		/*read 10 integers from the local phw_input.txt file which are split by ","
		 *this part of code refers to the web link
		 *https://javarevisited.blogspot.com/2015/12/how-to-split-comma-separated-string-in-java-example.html
		**/
		int[] convertInt = null;
		try {
			File myFile = new File("phw_input.txt");
			Scanner readFile = new Scanner(myFile);
			while(readFile.hasNextLine()) {
				String containString = readFile.nextLine();
				String[] convertString = containString.trim().split("\\s*,\\s*");
				//use string.trim to remove unexpected whitespace then split by comma
				//store each element into a string[]then convert into integer array
				convertInt = new int[convertString.length];
				for(int i = 0; i< convertInt.length; i++) {
					convertInt[i] = Integer.parseInt(convertString[i]);
					//System.out.print(convertInt[i]);//check point
				}
								
			}
			readFile.close();
			//close readFile
			
		}catch(FileNotFoundException e){
			System.out.print("Can't find the file. Check file name as 'phw_input.txt'.");
			
		}
		return convertInt;
	}
	
	/*This createMatrix function generates 19 integer sequences of length 10,15,20,25...90,95,100 with
	 * randomly created -, 0, and + integers, and stores in 19 arrays of size 10, 15,...,95, 100.
	 * */
	public int[][] createMatrix(){
		/*This part code refers to these web pages.
		*https://www.journaldev.com/747/two-dimensional-array-java
		*https://www.baeldung.com/java-generating-random-numbers-in-range
		**/
		int[][] create = new int[19][];
		Random random = new Random();
		//set the range of random function from -500 to 500
		int minbound = -500;
		int maxbound = 500;

		for(int i = 0; i < 19;i++) {
			//j = 10,15,20...95,100
			int j = (i + 2)*5;
			create[i]= new int[j];
		}
		for(int k=0; k < create.length; k++) {
			for (int l = 0; l < create[k].length; l++) {
				//System.out.println(create[k].length); //check point
				create[k][l] = random.nextInt((maxbound - minbound)+1)+minbound;
				//System.out.println(k +"--"+ l); //check point
			}
		}		
		return create;
		
	}
	/*Algorithm 1 to 4 come from COMP-3720 ProgrammingHW working sheet.
	 *The original ones are Pseudo-codes on the page 3 on the work sheet.
	 * */
	 //Algorithm-1(X : array[P..Q] of integer) 
	public int algorithm_1(int[] x) {
		int maxSoFar = 0;
		int p = 0;
		int q = x.length -1;
		for(int l = p;l <= q; l++) {
			for(int u = l; u <= q; u++) {
				int sum = 0;
				for(int i = l; i <=u; i++) {
					sum = sum + x[i];	
				}
				maxSoFar = max(maxSoFar, sum);
			}
		}
		
		return maxSoFar;	
	}
	
	//Algorithm-2
	public int algorithm_2(int[] x) {
		int maxSoFar = 0;
		int p = 0;
		int q = x.length -1;
		for(int l = p; l <= q; l++) {
			int sum = 0;
			for(int u = l; u <= q; u++) {
				sum = sum + x[u];
				maxSoFar = max(maxSoFar, sum);
			}
		}
		return maxSoFar;
		
	}
	
	//Algorithm-3
	//recursive function MaxSum
	public int maxSum(int[] x, int l, int u) {
		int m = (l + u)/2;
		
		if (l > u)
			return 0;
		else if (l == u)
			return max (0, x[l]);
		//Find max crossing to left
		else {
			int sum_1 = 0;
			int maxToLeft = 0;
		
			for(int i = m; i >= l; i--) {
				sum_1 = sum_1 + x[i];
				maxToLeft = max(maxToLeft, sum_1);
			}
		//Find max crossing to right
			int sum_2 = 0;
			int maxToRight = 0;
			for(int i = m+1; i <= u; i++) {
				sum_2 = sum_2 + x[i];
				maxToRight = max(maxToRight, sum_2);
			}
			int maxCrossing = maxToLeft + maxToRight;
		
			int maxInA = maxSum(x,l,m);
			int maxInB = maxSum(x, m+1, u);
			return max(maxCrossing, maxInA, maxInB);
		}
		
	}
	//Algorithm-4
	public int algorithm_4(int[] x) {
		int p = 0;
		int q = x.length - 1;
		int maxSoFar = 0;
		int maxEndingHere = 0;
		for(int i = p; i <= q; i++) {
			maxEndingHere = max(0,maxEndingHere + x[i]);
			maxSoFar = max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
		
	}

	
	//Compare function with two and three input arguments assists with the function algorithm_1 to 4
	 public int max(int x, int y) {
		 if(x >= y) {
			 return x;
		 }else
			 return y;
	 }
	 
	 public int max(int x, int y, int z) {
		 if(x >= y && x >= z) {
			 return x;
		 }else if(y >= x && y >= z) {
			 return y;
		 }else
			 return z;
	 }

}
