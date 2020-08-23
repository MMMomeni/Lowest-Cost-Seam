/*
 * Mohammad Momeni
 * 
 * a. If n>1 then for every choice of pixel at a given row, 
 * we have at least 2 choices of pixel in the next row to add to the seam 
 * (3 if we're not in column 1 or n).
 * Thus the total number of possibilities is bounded below by 2^m.
 * 
 */
package csc421HomeWork;

import stdlib.StdIn;

public class Seam {
	public static int lowestSeam(String filename){
		int row = 0;
		StdIn.fromFile(filename);
		String line = StdIn.readLine();
		String[] colrow = line.split("\\s+");
		int m = Integer.parseInt(colrow[0]);
		int n = Integer.parseInt(colrow[1]);
		int[][] matrix1 = new int[m][n];
		while (!StdIn.isEmpty()) {
			line = StdIn.readLine();
			String[] nums = line.split("\\s+");
			if (nums.length == 2) {
				continue;
			}
			for (int col = 0; col < nums.length; col++) {
				matrix1[row][col] = Integer.parseInt(nums[col]);
			}
			row++;
		}
		
		int[][] matrix2 = new int [m][n];
		
		for (int i = 0; i < n; i++) {
			matrix2[m-1][i] = matrix1[m-1][i];
		}
		
		for (int i = m-2; i >= 0; i--) {
			for(int j = 0; j <= n-1; j++) {
				if (j == 0) {
					matrix2[i][j] = matrix1[i][j] + Math.min(matrix2[i+1][j], matrix2[i+1][j+1]);
				}
				else if (j == n-1) {
					matrix2[i][j] = matrix1[i][j] + Math.min(matrix2[i+1][j], matrix2[i+1][j-1]);
				}
				else {
					matrix2[i][j] = matrix1[i][j] + Math.min((Math.min(matrix2[i+1][j], matrix2[i+1][j-1])), matrix2[i+1][j+1]);
				}
			}
		}
		int lowest = matrix2[0][0];
		for (int i = 1; i < n; i++) {
			if (matrix2[0][i] <= lowest) {
				lowest = matrix2[0][i];
			}
		}
				
		
		return lowest;
	}

	
	

	public static void main(String[] args) {
		int a = lowestSeam("data/disruptionValuesSmall.txt");
		int b = lowestSeam("data/disruptionValues.txt");
		System.out.printf("Lowest-cost seam of the first file: %d\n" ,a);
		System.out.printf("Lowest-cost seam of the second file: %d" ,b);
	
	}
}


