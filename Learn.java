public class Learn {
    // this method check and print the path to the end of mat if all the letter to the end is ch
    public static String charPath(char[][] mat, char ch) {
        return charPath(mat, 0, 0, "", ch);
    }

    private static String charPath(char[][] mat, int i, int j, String str, char ch) {
        int row = mat.length;
        int col = mat[0].length;

        if (i >= row || i < 0 || j >= col || j < 0 || mat[i][j] != ch)
            return null;

        // Check if we reached the bottom-right corner
        if (i == row - 1 && j == col - 1)
            return str;

        if (mat[i][j] == ch)
            str += " --> ";
        // Mark the current cell to avoid revisiting
        char original = mat[i][j];
        mat[i][j] = '*';

        // Explore all directions
        String up = charPath(mat, i - 1, j, str + "up", ch);
        String down = charPath(mat, i + 1, j, str + "down ", ch);
        String left = charPath(mat, i, j - 1, str + "left ", ch);
        String right = charPath(mat, i, j + 1, str + "right ", ch);

        // Restore the original value
        mat[i][j] = original;

        // Check if any direction found a path
        if (up != null) return up;
        if (down != null) return down;
        if (left != null) return left;
        if (right != null) return right;

        // If none of the directions found a path
        return null;
    }

    //2 print the sum of the max path
    public static int greatsRouts(int[][] mat) {
        return greatsRouts(mat, 0, 0);
    }

    private static int greatsRouts(int[][] mat, int i, int j) {
        int row = mat.length;
        int col = mat[0].length;

        if (i >= row || i < 0 || j >= col || j < 0 || mat[i][j] < 0)
            return Integer.MIN_VALUE;

        if (i == row - 1 && j == col - 1)
            return mat[i][j];

        int temp = mat[i][j];
        mat[i][j] = -temp;

        int up = temp + greatsRouts(mat, i - temp, j);
        int down = temp + greatsRouts(mat, i + temp, j);
        int left = temp + greatsRouts(mat, i, j - temp);
        int right = temp + greatsRouts(mat, i, j + temp);

        mat[i][j] = temp;
        return Math.max(Math.max(up, down), Math.max(left, right));
    }

    public static int greatsRouts2(int[][] mat) {
        int[][] counter = new int[mat.length][mat[0].length];
        return greatsRouts2(mat, 0, 0, counter,"");
    }

    private static int greatsRouts2(int[][] mat, int i, int j, int[][] counter,String str) {
        int row = mat.length;
        int col = mat[0].length;

        if (i >= row || i < 0 || j >= col || j < 0 || counter[i][j] >= mat[i][j]) // STOP
            return Integer.MIN_VALUE;

        if (i == row - 1 && j == col - 1){ // END OF MAT
            str += "(" + i + ", " + j + ")";
            System.out.println(str );
            return mat[i][j];}

        counter[i][j]++;
        int up = mat[i][j] + greatsRouts2(mat, i - 1, j, counter,str +"(" + i + ", " + j + ")" + " --> ");
        int down = mat[i][j] + greatsRouts2(mat, i + 1, j, counter,str + "(" + i + ", " + j + ")" + " --> ");
        int left = mat[i][j] + greatsRouts2(mat, i, j - 1, counter,str+ "(" + i + ", " + j + ")" + " --> ");
        int right = mat[i][j] + greatsRouts2(mat, i, j + 1, counter,str +"(" + i + ", " + j + ")" + " --> ");

        return Math.max(Math.max(up, down), Math.max(left, right));


    }

    public static void findSubarray(int []arr , int target){

        int ptr1=0,ptr2=0;
        int sum=arr[ptr1];
        boolean run = true;
        while (ptr1 <= arr.length -1 && ptr2 <= arr.length -1 && run){

            if (sum == target){
                System.out.println("Subarray found between indexes" + ptr1 +" and " + ptr2 + " with sum " + target);
                run = false;
            } else if (sum<target) {
                ptr2++;
                if (ptr2 < arr.length)
                    sum += arr[ptr2];

            }else{
                sum -= arr[ptr1];
                ptr1++;
            }
        }
        System.out.println("No subarray found with sum " + target);
    }
    // 2022b -1
    public static int calc(int num , int result , int maxOp){
        return calc(num ,result,maxOp,num,"" + num,0,1);
    }
    private static int calc(int num, int result , int maxOp,int sum , String str,int curOp,int lastOp){
        if (sum == result && maxOp >=  0){
            str += "= " + sum;
            System.out.println(str);
            return 1;
        }

       if (curOp + lastOp == 0)
           return 0;

        int options =0;
        if (maxOp > 0 ){
            options = calc(num,result,maxOp -1 ,sum + num,str + "+" + num,1,curOp) +
                    calc(num,result,maxOp -1 ,sum / num,str + "/" + num,-2,curOp) +
                    calc(num,result,maxOp -1 ,sum * num,str + "*" + num,2,curOp) +
                    calc(num,result,maxOp -1 ,sum - num,str + "-" + num,-1,curOp) ;

            }
         return options;
    }

    // 2022b -2
    public static int kAlmostSearch(int []a,int num){
        int low =0 ,high=a.length-1;
        int mid;
        while (low<=high){
            mid = (low + high) /2;
            int temp = mid;

            while(mid<high && a[mid]==0)
                mid++;

            if(a[mid]==num)
                return mid;

            if(a[mid] > num)
                high = mid -1;

            else if (a[mid] < num){
                low=mid-1;
            }

        }
        return -1;
    }

    // 2022b 91 -1
    public static int cheapRt(int[]stations,int step1,int step2,int limit){
        return cheapRt(stations,0,step1,step2,limit," ",0);
    }
    private static int cheapRt(int[]stations,int i ,int step1,int step2,int limit,String str,int sum){

        if (i >= stations.length) // STOP out of arr
            return 0;
        if (i == stations.length -1 ){  // we go to the distention
            str += i;
            sum += stations[i];
            System.out.println(str + " = " + sum);
            return stations[i]; // return the final value to get the sum and check the min
        }
        int options =0;
        if (limit > 0) {
            options = stations[i] + cheapRt(stations, i + step2, step1, step2, limit - 1, str + i + " ", sum + stations[i]);
        }
        int options2 = stations[i] + cheapRt(stations,i + step1,step1,step2,limit,str + i + " ",sum + stations[i]);
        return Math.min(options,options2);
    }

    // 2022b 91 -1
    public static int findTriplet(int[]arr){
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int j =0;j <= arr.length ; j++ ){
            if(max1>arr[j]){
                max3=max2;
                max2=max1;
                max1=arr[j];
            } else if (max2>arr[j]) {
                max3=max2;
                max2=arr[j];

            }else
                max3 =Math.max(max3,arr[j]);
            if (min2<arr[j]){
                min2=min1;
                min1=arr[j];
            }else
                min2 = Math.min(min2,arr[j]);
        }
        return Math.max(max1*max2*max3,max1*min2*min1);
    }
    //2022b 86 -1
    public static int maxPath(int [][]mat){
        return maxPath(mat,0,0);
    }
    private static int maxPath(int [][]mat,int i,int j){

        if (i >= mat.length || j >= mat[0].length) // STOP outside the matrix
            return Integer.MIN_VALUE;

        if (i == mat.length -1 && j == mat[0].length -1) // we go to the distention
            return mat[i][j];

        int step1 = mat[i][j] /10;
        int step2 = mat[i][j] %10;
        // consider the two options to go
        int option1 = maxPath(mat, i + step1, j + step2);
        int option2 = maxPath(mat,i +step2,j+step1);
        //the max path (sum of the values)
        return mat[i][j] + Math.max(option1,option2);
    }
    // 2020 87 -1
    public  static int totalWays(int[][]mat,int k){
        return totalWays(mat,k,0,0,0,1,"");
    }
    private static int totalWays(int[][]mat,int k,int i,int j,int currentStep,int lastStep,String str){
        if (i >= mat.length || j >= mat[0].length) // STOP outside the matrix
            return 0;

        if (currentStep + lastStep == 0) // check turn
            k--;

        if (i == mat.length -1 && j == mat[0].length -1 ){ // we go to the distention
            if (k == 0){
                System.out.println(str + i + ", " + j);
                return 1;
            }else
                return 0;
        }

        return totalWays(mat, k, i + 1, j, 1, currentStep,str + i + "," + j + " --> ") +
                totalWays(mat, k, i, j + 1, -1, currentStep,str + i + ","+  j + " --> ");

    }
    // 2020 87 -1
    public static void printTriplets(int []a,int num){
        int sum;
        for (int i=a.length-1;i>1;i--){
            int cur = a[i]; // the number that we check other number with him
            int high = i-1; // the second highest num in the arr
            int low= 0;

            while (low != high && cur <= num){
                sum = cur * a[high] * a[low]; // the 3 number
                if (sum==num){
                    System.out.println(cur + " " + a[high] + " " + a[low]);
                    low ++; // keep check if we have more 3 number
                } else if (sum < num) {
                    low++;

                }else
                    high--;

            }
        }

    }
    public static int howManySorted(int n,int max){
        return howManySorted(n,max,1,0,0,"{ ");
    }
    private static int howManySorted(int n,int max,int start,int last,int len,String str){
        if (len>n || start> max)
            return 0;

        if (start< last)
            return 0;

        if (start<=max && len==n){
            System.out.println(str);
            return 1;
        }
        int temp = start +1;
        return howManySorted(n, max, start,start ,len + 1, str + start + ",") +
                howManySorted(n, max, temp,start, len +1 , str + temp + ",");


    }
    //2020b 84 -1
    public static boolean findWord(char [][]mat,String word){
        return findWord(mat,word,0,0);
    }
    private static boolean findWord(char [][]mat,String word,int i,int j){
        if (i == mat.length)
            return false;
        if (j >= mat[0].length)
            return findWord(mat, word, i +1, 0);

        if (word.charAt(0) == mat[i][j])
            return completeWord(mat,word,i,j,0);
        return findWord(mat, word, i, j+1);
    }
    private static boolean completeWord(char [][]mat,String word,int i,int j,int index){
        int row = mat.length;
        int col = mat[0].length;
        if (i >= row || i < 0 || j >= col || j < 0 || index >= word.length()|| mat[i][j] == '#')  // STOP
            return false;
        if(word.charAt(index)==mat[i][j]){
            index++;
            if(index==word.length())
                return true;}
        else
            return false;
        char temp = mat[i][j];
        mat[i][j] = '#';
        boolean up = completeWord(mat, word, i -1, j,index);
        boolean down = completeWord(mat, word, i+1, j,index);
        boolean right = completeWord(mat, word, i, j+1,index);
        boolean left = completeWord(mat, word, i, j-1,index);
        mat[i][j] = temp;

        if(up||down||right||left)
            return true;
        return false;
    }
    public static void ptintPathWeights(int [][]mat){
        ptintPathWeights(mat,0,0,"",0);
    }
    private static void ptintPathWeights(int[][]mat,int i,int j,String str,int sum){
        if (i>=mat.length || i < 0
                || j>= mat[0].length || j<0
                || mat[i][j] == -1)
            return;
        if (i == mat.length -1 && j == mat[0].length -1){
            sum += mat[i][j];
            str += mat[i][j] + "=" + sum;
            System.out.println(str);
            return ;
        }
        int temp =mat[i][j];
        mat[i][j] = -1;
        ptintPathWeights(mat,-1,j,str+temp + "+",sum+temp);
        ptintPathWeights(mat,i+1,j,str+temp + "+",sum+temp);
        ptintPathWeights(mat,i,j+1,str+temp + "+",sum+temp);
        ptintPathWeights(mat,i,j-1,str+temp + "+",sum+temp);
        mat[i][j]=temp;

    }
    public static int findSumKnight(int[][]mat){
        return findSumKnight(mat,0,0,mat[0][0],"",0);
    }
    private static int findSumKnight(int[][]mat,int i ,int j,int last,String str,int sum){

        if (i>=mat.length || i < 0
                || j>= mat[0].length || j<0
                || mat[i][j] == -1)
            return 0;

        if(Math.abs(last - mat[i][j])>1)
            return 0;

        if (i == mat.length -1 && j == mat[0].length -1){
            System.out.println(str + mat[i][j]);
            return sum + mat[i][j];
        }
        int temp = mat[i][j];
        mat[i][j] = -1;

        int up1 =findSumKnight(mat,i-2,j-1,temp,str + temp + " + ",sum +temp);
        int up2 =findSumKnight(mat,i-2,j+1,temp,str + temp + " + ",sum +temp);
        int down1 = findSumKnight(mat,i+2,j-1,temp,str + temp + " + ",sum +temp);
        int down2 = findSumKnight(mat,i+2,j+1,temp,str + temp + " + ",sum +temp);
        int left1 = findSumKnight(mat,i+1,j-2,temp,str + temp + " + ",sum +temp);
        int left2 = findSumKnight(mat,i-1,j-2,temp,str + temp + " + ",sum +temp);
        int right1 = findSumKnight(mat,i-1,j+2,temp,str + temp + " + ",sum +temp);
        int right2 = findSumKnight(mat,i+1,j+2,temp,str + temp + " + ",sum +temp);
        mat[i][j] = temp;

        up1 = Math.max(up1,up2);
        down1 = Math.max(down1,down2);
        left1 = Math.max(left1,left2);
        right1 = Math.max(right1,right2);

        return Math.max(Math.max(right1,left1),Math.max(up1,down1));

    }
    public static int edit (String source,String target){
        if (source.isEmpty())
            return target.length();

        if (target.isEmpty())
            return source.length();

        if (source.charAt(0)==target.charAt(0))
            return edit(source.substring(1),target.substring(1));

        return Math.min(edit(source.substring(1),target) +1 //
                ,edit(source,target.substring(1))+1);
    }

    public static int findSingle(int[]arr){
        int low=0,high=arr.length -1;
        int mid=0;
        while (low<=high){
            mid = (low+high) / 2;
            if (arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1]) // we find the single number
                return arr[mid];

            if(arr[mid]==arr[mid-1])
                if((low+mid-1)%2 == 0)
                    low = mid +1;
                else
                    high = mid -2;
            else if (arr[mid]==arr[mid+1])
                if((high+mid-1)%2 == 0)
                    high = mid -1;
                else
                    low = mid -2;
        }
    return -1;
    }
    public static int findNumber(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int expected = nums[0] - mid;
            if (nums[mid] != expected) {
                if (mid > 0 && nums[mid - 1] - nums[mid] > 1) {
                    return nums[mid - 1] - 1;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return Integer.MIN_VALUE;
    }
    //2023a 85 -2
    // the methode return true if sum of number equals to k
    public static boolean superInc(int[]arr,int k){
        int low=0,high=arr.length-1;
        int sum;
        while (low<=high){
            sum = arr[high] + arr[low];
            if (sum==k)
                return true;
            else if (sum>k) {
                high = high -1;

            }else
                low = low +1;

        }
        return false;
    }
    //2022a 89 - 1
    public static boolean isjump(String str1,String str2,int step){
        if(str1.charAt(0) != str2.charAt(0))
            return false;
        if(str1.length() < str2.length())
            return false;
        return isjump(str1,str2,step,step,1);
    }
    private static boolean isjump(String str1,String str2,int step,int index1,int index2){
        if(index2 == str2.length())
            return true;

        if(index1 > str1.length()-1)
            return false;

        if (str1.charAt(index1)==str2.charAt(index2))
            return isjump(str1,str2,step,index1+step,index2 +1);
        return false;
    }
    public static int printExpr(int[]arr,int num){
        return printExpr(arr,num,0,0,"");
    }

    private static int printExpr(int[]arr,int num,int i,int sum,String str){
        // consider the last number
        if (num==sum){
            System.out.println(str);
            return 1;
        }

        if(i>=arr.length) // Stop - go out the array
            return 0;

        // consider all the options
        return printExpr(arr,num,i+1,sum+arr[i],str+ "+" + arr[i]) //add number
                + printExpr(arr,num,i+1,sum-arr[i],str+ "-" + arr[i]) // add negative number
                + printExpr(arr,num,i+1,sum,str); // not take number
    }
    //find how many number have in order with k - zeros
    public static int longestSequence(int[] a,int k){
        int max = 0;
        int counter=0;
        for (int i=0;i<a.length;i++)
            if(a[i]==1)
                counter++;
            else
                if(k==0) {
                    max = Math.max(max, counter);
                    counter = 0;
                } else
                    k--;
        return max;
    }
    public static int minDiff (int []arr){
        return minDiff(arr,0,0,0);
    }
    private static int minDiff (int []arr,int i ,int sum1,int sum2){

        if(i>arr.length) //STOP - outside the array
            return Integer.MAX_VALUE;

        if(i==arr.length) // finish
            return Math.abs(sum1-sum2);
        return Math.min(minDiff(arr,i+1,sum1+arr[i],sum2),minDiff(arr,i+1,sum1,sum2+arr[i]));
    }
    public static int makeSum(int[]length,int k,int num){
        return makeSum(length,k,num,0,0,"");
    }
    private static int makeSum(int[]length,int k,int num,int sum,int i,String str){
        if (length.length <= i || k <=0)
            return 0;
        if(num == sum ){
            System.out.println(str);
            return 1;
        }

        return
                 makeSum(length,k-1,num,sum+length[i],i,str+ length[i] + " ") // take number and stay
                + makeSum(length,k,num,sum,i+1,str); // go to the next number
    }
    static void minimumSubK(int[] arr, int k){
        int counter =0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        int startIndex=0,endIndex=0;

        for(int i=0;i<arr.length;i++)
            if (counter==k){
                if(min > sum){

                    min = sum;
                    startIndex = start;
                    endIndex = i-1;
                }
                counter = k-1;
                sum -= arr[start];
                start++;

            }else{
                sum+=arr[i];
                counter++;
            }

        System.out.println("Min sum sub-array is " + startIndex + "," + endIndex + " = " + min);
    }
    // 2019 84 -1
    public static boolean sumPower3(int num){
        return sumPower3(num,0,0);
    }

    private static boolean sumPower3(int num, double i, double sum) {
        if(sum == num)
            return true;
        if (sum>num || i>num)
            return false;
        return sumPower3(num,i+1,sum + Math.pow(3,i)) || sumPower3(num,i+1,sum);
    }
    public static int howManyPath(int [][]mat){
        return howManyPath(mat,0,0);
    }
    private static int howManyPath(int [][]mat,int i,int j){
        // STOP - outside the matrix
        if (i>=mat.length || i <0
            || j>=mat[0].length ||  j<0
                || mat[i][j]<0)
            return 0;

        if (i == mat.length -1 && j == mat[0].length -1)
            return 1;

        int temp = mat[i][j];
        mat[i][j] = -1;

        int up = howManyPath(mat,i-temp,j);
        int down = howManyPath(mat,i+temp,j);
        int right = howManyPath(mat,i,j+temp);
        int left = howManyPath(mat,i,j-temp);

        mat[i][j] = temp;
        return up + down + left +right;

    }
    // 2018a 91 -1
    public static int cheapestRoute(int[] stations){
        return cheapestRoute(stations,0,0);
    }
    private static int cheapestRoute(int[] stations,int i,int sum){
        if (i >= stations.length)
            return Integer.MAX_VALUE;

        if(i == stations.length -1)
            return sum + stations[i];

        return Math.min(cheapestRoute(stations,i+1,sum+stations[i]),cheapestRoute(stations,i+2,sum+stations[i]));
    }
    public static int loongestSlope(int[][]mat,int num){
        return loongestSlope(mat,num,0,0,0,0);
    }

    private static int loongestSlope(int[][] mat, int num, int i, int j,int sum,int last) {
        if (i>=mat.length || i <0
                || j>=mat[0].length ||  j<0
                || mat[i][j]<0)
            return 0;

        if(mat[i][j] - last == num)
            sum++;
        else
            return sum;

        int temp = mat[i][j];
        mat[i][j] = -1;

        int up = loongestSlope(mat, num, i-1, j, sum, temp);
        int down = loongestSlope(mat, num, i+1, j, sum, temp);
        int left = loongestSlope(mat, num, i, j-1, sum, temp);
        int right = loongestSlope(mat, num, i, j+1, sum, temp);

        mat[i][j] = temp;
        return Math.max(Math.max(up,down),Math.max(left,right));
    }
    public static int oneFiveSeven(int n){
        return oneFiveSeven(n,0);
    }
    // 2017
    private static int oneFiveSeven(int n, int sum) {

        if (sum > n)
            return 99999999;
        if (n== sum)
            return 0;
        int one = 1 + oneFiveSeven(n, sum+1);
        int five = 1 + oneFiveSeven(n, sum+5);
        int seven = 1 + oneFiveSeven(n, sum+7);
        return Math.min(Math.min(one,five),seven);
    }
    //2017a 87 -1
    public static boolean covers(int[][]mat,int[]arr,int k ){
        return covers(mat, arr, k,0,0,0);
    }

    private static boolean covers(int[][] mat, int[] arr, int k, int i, int j, int count) {

        if (k<0 || i>= mat.length || count >=arr.length)
            return false;

        if (count == arr.length-1 && k > 0)
            return true;

        if(j >= mat.length)
            return covers(mat, arr, k-1, i+1, 0, count);

        if (mat[i][j]==arr[count])
            count++;

        return covers(mat, arr, k, i, j+1, count) ;

    }
    public static int findSmallestSubarrayLen(int[] a, int target) {

        // Write your Java code here
        int sum =0,start =0,end=0,min =a.length +1;
        String small = "";
        while(end < a.length  ){
            sum+=a[end];
            if(sum>target && end>start){
                if(end-start+1 < min) {
                    min = end - start + 1;
                    small = "Subarray found [" + start + "-" + end + " ]";
                    sum -= a[start];
                    start++;
                }else {
                    sum -= a[start];
                    start++;
                }
            }else{
                end++;
            }

        }
        if(min ==a.length +1){
            return 0;
        }else{
            System.out.println(small);}
        return min;
    }


}
class LearnTester {
    public static void main(String[] args) {

        char[][] matrix = {
                {'A', 'A', 'A', 'D'},
                {'A', 'A', 'A', 'G'},
                {'H', 'I', 'A', 'K'},
                {'L', 'M', 'A', 'A'}
        };

        System.out.println("Path for character 'A':");
        String result = Learn.charPath(matrix, 'A');
        System.out.println(result);
        System.out.println();

        System.out.println(" 2 -- TESTING THE greatsRouts METHOD");
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 1, 1}
        };

        int[][] matrix2 = {
                {2, 1, 1},
                {1, -5, 2},
                {2, 8, 1}
        };

        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, -6},
                {7, 8, 9}
        };

        int result1 = Learn.greatsRouts(matrix1);
        int result2 = Learn.greatsRouts(matrix2);
        int result3 = Learn.greatsRouts(matrix3);

        System.out.println("Matrix 1: Sum of the max path = " + result1);
        System.out.println("Matrix 2: Sum of the max path = " + result2);
        System.out.println("Matrix 3: Sum of the max path = " + result3);


        System.out.println(" 3 -- TESTING THE greatsRouts2 METHOD");
        int[][] matrix4 = {
                {2, 3},
                {4, 1}
        };
        int result4 = Learn.greatsRouts2(matrix4);


        System.out.println("Matrix 4: Sum of the max path = " + result4);
        System.out.println(" 4 -- TESTING THE findSubarray METHOD");

        int[] array1 = {1, 2, 3, 4, 5};
        int target1 = 9;
        System.out.println("Array 1: " + java.util.Arrays.toString(array1));
        System.out.println("Target 1: " + target1);
        Learn.findSubarray(array1, target1);

        int[] array2 = {1, 2, 3, 7, 5};
        int target2 = 12;
        System.out.println("Array 2: " + java.util.Arrays.toString(array2));
        System.out.println("Target 2: " + target2);
        Learn.findSubarray(array2, target2);

        int[] array3 = {1, 2, 3, 4, 5};
        int target3 = 11;
        System.out.println("Array 3: " + java.util.Arrays.toString(array3));
        System.out.println("Target 3: " + target3);
        Learn.findSubarray(array3, target3);

        int[] array4 = {1, 2, 3, 4, 5};
        int target4 = 7;
        System.out.println("Array 4: " + java.util.Arrays.toString(array4));
        System.out.println("Target 4: " + target4);
        Learn.findSubarray(array4, target4);

        System.out.println("TESTING calc METHOD ");
        int result5 = Learn.calc(3,36,5);
        System.out.println(result5);
//        System.out.println("TESTING kAlmostSearch METHOD ");
//        int[] array5 = {3,0,0,4,7,9,0,0,0,0,11,15,0,19,20,0,0,31,40,0};
//        int result6 = Learn.kAlmostSearch(array5,9);
//        System.out.println(result6);

        System.out.println("TESTING cheapRt METHOD ");
        int[] array6 = {2,4,8,3,10,1,12,3,2};
        int result7 = Learn.cheapRt(array6,3,2,4);
        System.out.println(result7);

        System.out.println("TESTING maxPath METHOD ");
        int[][] mat1 = {{12,22,23,54,11},
                        {43,35,21,20,30},
                        {34,23,43,22,30},
                        {25,31,2,20,34},
                        {10,22,10,11,10},
                        {40,13,3,1,23}};
        int result8 = Learn.maxPath(mat1);
        System.out.println(result8);

        System.out.println("TESTING totalWay METHOD ");
        int[][] mat2 = {{12,22,23},
                        {43,35,21},
                        {34,23,43},};
        int result9 = Learn.totalWays(mat2,1);
        System.out.println(result9);

        System.out.println("TESTING howManySorted METHOD ");
        int result10 = Learn.howManySorted(3,2);
        System.out.println(result10);

        System.out.println("TESTING findWord METHOD ");
        char [][] mat3 = {{'s','w','l','d'},
                         {'m','o','o','h','d'},
                        {'s','r','d','l','l'},
                        {'o','l','l','e','h'}};
        boolean result11= Learn.findWord(mat3,"moodll");
        System.out.println(result11);

        System.out.println("TESTING totalWay METHOD ");
        int[][] mat4 = {{1,2,3},
                {1,2,3},
                {1,2,3},};

        Learn.ptintPathWeights(mat4);

        System.out.println("TESTING findSumKnight METHOD ");
        int[][] mat5 = {{4,5,6,7,1},
                        {3,5,1,7,4},
                        {4,5,6,5,8},
                        {3,4,7,7,9},
                        {6,2,2,7,6}};

        int result12 = Learn.findSumKnight(mat5);
        System.out.println(result12);

        System.out.println("TESTING findSingle METHOD ");
        int []arr = {6,6,7,18,18,4,4,9,9};
        System.out.println("The single number is: " + Learn.findSingle(arr));

        System.out.println("TESTING findNumber METHOD ");
        int []arr2 = {12,8,7,6,5,4,3,2};
        System.out.println("The num number is: " + Learn.findNumber(arr2));

        System.out.println("TESTING superInc METHOD ");
        int []arr3 = {2,3,8,27};
        System.out.println("The num number is: " + Learn.superInc(arr3,30));

        System.out.println("TESTING isjump METHOD ");
        System.out.println(Learn.isjump("adbscd","abc",2));

        System.out.println("TESTING printExpr METHOD");
        int [] arr4 = {2,1,6,3};
        System.out.println(Learn.printExpr(arr4,3));

        System.out.println("TESTING longestSequence METHOD");
        int [] arr5 = {1,1,0,1,1,0,1,1,1,1,0,0};
        System.out.println(Learn.longestSequence(arr5,0));

        System.out.println("TESTING minDiff METHOD");
        int [] arr6 = {5,2,4};
        System.out.println(Learn.minDiff(arr6));

        System.out.println("TESTING makeSum METHOD");
        int [] arr7 = {2,5,10,20,50};
        System.out.println(Learn.makeSum(arr7,4,40));

        System.out.println("TESTING makeSum METHOD");
        int [] arr8 = {1,1,1,1,5,10,2};
        Learn.minimumSubK(arr8,3);

        System.out.println("TESTING makeSum METHOD");
        System.out.println(Learn.sumPower3(37));

        System.out.println("TESTING howManyPath METHOD");
        int[][] mat6 = {{1,3,1,6},
                {2,8,1,2},
                {6,2,7,5},
                {2,4,1,3}};

        System.out.println(Learn.howManyPath(mat6));


        System.out.println("TESTING loongestSlope METHOD");
        int[][] mat7 = {{1,3,1,6},
                {55,54,53,1},
                {6,2,52,5},
                {2,4,51,3}};

        System.out.println(Learn.loongestSlope(mat7,1));

        System.out.println("TESTING oneFiveSeven METHOD");
        System.out.println(Learn.oneFiveSeven(13));

        System.out.println("TESTING covers METHOD");
        int [] arr11 = {7,2,3};
        int[][]mat= {{1,5,7},
                     {3,2,9},
                     {1,2,3}};
        System.out.println(Learn.covers(mat,arr11,2));
        int [] arr12= {5,1,4,1,3,2,5,4,12,1,2};
         System.out.println(Learn.findSmallestSubarrayLen(arr12,11));

    }


}



