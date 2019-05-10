package com.masood;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static int DISTANCE(int[] a,int[] b,int[] tuple ,int i){
        int distance =0,distance1 =0,distance2=0;
        distance1 = (a[i]-tuple[0])*(a[i]-tuple[0]);
        distance2 =(b[i]-tuple[1])*(b[i]-tuple[1]);
        distance = distance1+distance2;
        return distance;
    }
     static int RANK(int arr[], int t)
    {
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (arr[i] == t)
                return i+1;
            else
                i = i + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter no of inputs : ");
        int n = scanner.nextInt();
        int[] X1 = new int[n];
        int[] X2 = new int[n];
        int[] tuple = new int[n];
        int[] distance = new int[n];
        int k = 3;
        int[] sortedDistance =new int[n];
        int[] rank = new int[n];
        String[] Y = new String[n];
        String[] NN = new String[n];
        String[] category = new String[n];

        for (int i = 0; i <n ; i++) {
            System.out.print("Enter value of X1 : ");
            X1[i] = scanner.nextInt();
            System.out.print("Enter value of X2 : ");
            X2[i] = scanner.nextInt();
            System.out.print("Enter value of Y : ");
            Y[i] = scanner.next();
        }

        System.out.println("Enter Tuple to Classify using KNN ");
        for (int i = 0; i <2 ; i++) {
            tuple[i] = scanner.nextInt();
        }

        System.out.println("\nX1\tX2\tY");
        for (int i = 0; i <n ; i++) {
            System.out.println(X1[i] +"\t" + X2[i] +"\t" +Y[i]);
        }

        System.out.println("\nStep 1 : Finding 'k' ");
        System.out.println("\nAssume k = "+k);
        System.out.println("\nStep 2 : Computing Distance");

        for (int i = 0; i <n ; i++) {
            distance[i] = DISTANCE(X1,X2,tuple,i);
        }

        System.out.println("\nX1\tX2\tSq of Dist");
        for (int i = 0; i <n ; i++) {
            System.out.println(X1[i] +"\t" + X2[i] +"\t\t" +distance[i]);
        }

        System.out.println("\nStep 3 : Sort the distance and determine the k -  neighbours");

        System.arraycopy(distance, 0, sortedDistance, 0, distance.length);
        Arrays.sort(sortedDistance);

        for (int i = 0; i <n ; i++) {
            int m = sortedDistance[i];
            rank[i] = RANK(distance,m);
        }

        for (int i = 0; i <n ; i++) {
            if (rank[i]>3)
                NN[i] = "No";
            else
                NN[i] = "Yes";
        }

        System.out.println("\nX1\tX2\tSq of Dist\tRank\tIs 3-NN");
        for (int i = 0; i <n ; i++) {
            System.out.println(X1[i] +"\t" + X2[i] +"\t\t" +distance[i] +"\t\t" +rank[i] +"\t\t"+NN[i]);
        }

        System.out.println("\nStep 4 : Gather the Category 'Y'");
        for (int i = 0; i <n ; i++) {
            if (NN[i].equals("Yes"))
                category[i] = Y[i];
            else
                category[i] = "-";
        }

        System.out.println("\nX1\tX2\tSq of Dist\tRank\tIs 3-NN\t\tCategory");
        for (int i = 0; i <n ; i++) {
            System.out.println(X1[i] +"\t" + X2[i] +"\t\t" +distance[i] +"\t\t" +rank[i] +"\t\t"+NN[i]+"\t\t\t"+category[i]);
        }

        int good=0;
        int bad =0;
        for (int i = 0; i <n ; i++) {
            if (category[i].equals("Good"))
                good++;
            else if (category[i].equals("Bad"))
                bad++;
        }
        if (good>bad)
            System.out.println("\nTuple ("+tuple[0]+"," +tuple[1] +") categorized as 'Good' because of majority from the above table");
        else

            System.out.println("\nTuple ("+tuple[0]+"," +tuple[1] +") categorized as 'Bad' because of majority from the above table");
    }
}
