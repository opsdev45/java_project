package org.example;
/**
 * Maman 13
 *
 * @author Ofir Izchakov
 * @version 01/02/2024
 */
public class Ex13 {
    /**
     * maxMult3 checking the 3 number in array that have the max multiplication
     * In thr method there is one for loop that goes through the element in the array so the time complexity is O(n).
     * @param arr The array
     * @return The maximum multiplication between 3 numbers
     */
    public static int maxMult3 (int [] arr){

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        if (arr.length < 3) {
            return Integer.MIN_VALUE;
        }

        if ((arr.length == 3)) {
            return arr[0] * arr[1] * arr[2];
        }
        // find 3 max number and two min number in array
        for (int curr_number : arr) {
            if (curr_number > max1) {
                max3 = max2;
                max2 = max1;
                max1 = curr_number;
            } else if (curr_number > max2) {
                max3 = max2;
                max2 = curr_number;
            } else
                max3 = Math.max(curr_number, max3);
            if (curr_number < min1) {
                min2 = min1;
                min1 = curr_number;
            } else
                min2 = Math.min(curr_number, min2);

        }
// check the max multiplication between the 3 max number or the max number and the two min number
        return Math.max(max1*max2*max3,max1*min1*min2);
    }


    /**
     * maxSnake call the maxSnake and return the max path from the start location to the end location in the matrix
     * @param mat The matrix
     * @return The max path
     */
    public static int maxSnake (int [][] mat){
        return maxSnake(mat,0,0,mat[0][0]);
    }
    private static int maxSnake(int [][] mat,int i,int j ,int k){
        // Stop conditions checking if we go outside the matrix or repeat the same location in the matrix.
        if (i < 0 || i >= mat.length ||
                j < 0 || j >= mat[0].length ||
                mat[i][j]<0)
            return 0;
        // We go to the end location
        if (i == mat.length-1 && j ==mat[0].length-1)
            return 1;

        // Can go to the next location just if the current location and the next location is equals or bigger in 1
        if (Math.abs(mat[i][j] - k) > 1)
            return Integer.MIN_VALUE;


        // Change the current location value to negative
        k = mat[i][j];
        mat[i][j] = -k;

        int up = maxSnake(mat,i+1,j,k);
        int down = maxSnake(mat,i-1,j,k);
        int right = maxSnake(mat,i,j+1,k);
        int left = maxSnake(mat,i,j-1,k);
        // Returns a matrix to its original state
        mat[i][j]=k;
        int max = Math.max(Math.max(up,down),Math.max(right,left));

        //Check if exist a max path
        if (max > 0)
            return 1+ max;
        else
            return Integer.MIN_VALUE;

    }

    /**
     * findMedian get two arrays and return the median number in them
     * In thr method there is one loop that goes through the element in the two arrays in binary search so the time complexity is O(log n).
     * @param arr1 The first array
     * @param arr2 The second array
     * @return the median in the two arrays
     */
    public static int findMedian(int [] arr1,int [] arr2){
        int low1 = 0, low2 = 0;
        int high1 = arr1.length -1 , high2 = arr2.length -1;
        int mid1 = 0, mid2 = 0;
        int result1,result2;
        // the loop stop when there are two numbers left in each array
        while((high1-low1 > 1) && (high2-low2 > 1)){

            if ((high1-low1+1)%2==0 && (high2-low2+1)%2==0){
                mid1 = (low1 + high1) /2;
                mid2 = (low2 + high2) /2 + 1;
            }else {
                mid1 = (low1 + high1) /2;
                mid2 = (low2 + high2) /2;
            }
            // using binary search to reducing the search in each array
            if (arr1[mid1] > arr2[mid2]){
                low2 = mid2;
                high1=mid1;
            } else if (arr1[mid1] < arr2[mid2]) {
                low1=mid1;
                high2=mid2;
            }else
                return arr1[mid1];
        }
        //find the max and the min number in the two array for find the median.
        result1 = Math.max(arr1[low1],arr2[low2]);
        result2 = Math.min(arr1[high1],arr2[high2]);
        return (result1 + result2) / 2;
    }

    /**
     * minimalSt return the min combination between the two string
     * @param st1 The first string
     * @param st2 The second string
     * @return The min compination between the two string
     **/
    public static String minimalSt(String st1,String st2){
        if (st1.equals(st2))
            return st1;
        if (st1.length()==0)
            return st2;
        if (st2.length()==0)
            return st1;

        return minimalSt(st1,st2,"");
    }
    // take the two string and checking every time if the first char in them equals and build new string that include them.
    public static String minimalSt(String st1,String st2,String sum) {
        if(st2.length() == 0 && st1.length() == 0)
            return sum;
        if (st2.length() == 0){
            return sum + st1;}

        if (st1.length() == 0){
            return sum + st2;}
        // Check if the first char is the same so add to the sum str and go to the next char
        if (st1.charAt(0) == st2.charAt(0))
            return minimalSt(st1.substring(1), st2.substring(1),sum + st1.charAt(0));

        // Consider both possibilities
        String result1 =  minimalSt(st1.substring(1), st2,sum + st1.charAt(0));
        String result2 = minimalSt(st1, st2.substring(1),sum + st2.charAt(0));

        // Return the min str
        return result1.length() < result2.length() ? result1 : result2;
    }


}

