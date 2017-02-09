package DES;
import java.util.*;

public class Main {

    //Save Final keys
    private static ArrayList <String> FKey = new ArrayList<>();
    //Save Final Ci
    private static ArrayList <String> FC = new ArrayList<>();
    //Save Final Di
    private static ArrayList <String> FD = new ArrayList<>();
    //Save Final Ri
    private static ArrayList <String> FR = new ArrayList<>();
    //Save Final Li
    private static ArrayList <String> FL = new ArrayList<>();

    //Table IP
    private static int[][] IP = {{58, 50, 42 ,34, 26, 18, 10, 2}, {60, 52, 44, 36, 28, 20, 12, 4},
            {62, 54, 46, 38, 30, 22, 14, 6}, {64, 56, 48, 40, 32, 24, 16, 8},{57, 49, 41, 33, 25, 17, 9, 1},
            {59, 51,43, 35, 27, 19, 11, 3}, {61, 53, 45, 37, 29, 21, 13, 5},{63, 55, 47, 39, 31, 23, 15, 7}};;
    //Table IP-1
    private static int[][] IP1 = {{40, 8, 48, 16, 56, 24, 64, 32}, {39, 7, 47, 15, 55, 23, 63, 31},
            {38, 6, 46, 14, 54, 22, 62, 30}, {37, 5, 45, 13, 53, 21, 61, 29}, {36, 4, 44, 12, 52, 20, 60, 28},
            {35, 3, 43, 11, 51, 19, 59, 27}, {34, 2, 42, 10, 50, 18, 58, 26}, {33, 1, 41, 9, 49, 17, 57, 25}};
    //Table PC-1
    private static int[][] PC1 = {{57, 49, 41, 33, 25, 17, 9},{1, 58, 50, 42, 34, 26, 18},
            {10, 2, 59, 51, 43, 35, 27},{19, 11, 3, 60, 52, 44, 36},{63, 55, 47, 39, 31, 23, 15},
            {7, 62, 54, 46, 38, 30, 22},{14, 6, 61, 53, 45, 37, 29},{21, 13, 5, 28, 20, 12, 4}};
    //Table PC-2
    private static int[][] PC2 = {{14, 17, 11, 24, 1, 5},{3, 28, 15, 6, 21, 10},
            {23, 19, 12, 4, 26, 8},{16, 7, 27, 20, 13, 2},{41, 52, 31, 37, 47, 55},{30, 40, 51, 45, 33, 48},
            {44, 49, 39, 56, 34, 53},{46, 42, 50, 36, 29, 32}};
    //Table E
    private static int[][] E = {{32, 1, 2, 3, 4, 5},{4, 5, 6, 7, 8, 9},{8, 9, 10, 11, 12, 13},
            {12, 13, 14, 15, 16, 17},{16, 17, 18, 19, 20, 21},{20, 21, 22, 23, 24, 25},
            {24, 25, 26, 27, 28, 29},{28, 29, 30, 31, 32, 1}};
    //Table P
    private static int[][] P = {{16, 7, 20, 21},{29, 12, 28, 17},{1, 15, 23, 26},{5, 18, 31, 10},
            {2, 8, 24, 14},{32, 27, 3, 9},{19, 13, 30, 6},{22, 11, 4 ,25}};
    //S-box S1
    private static int[][] S1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
    //S-box S2
    private static int[][] S2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
    //S-box S3
    private static int[][] S3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
    //S-box S4
    private static int[][] S4 = {{7, 13, 14 ,3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
    //S-box S5
    private static int[][] S5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
    //S-box S6
    private static int[][] S6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 3}};
    //S-box S7
    private static int[][] S7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
    //S-box S8
    private static int[][] S8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please input the plaintext:");
        String PlainText =scan.nextLine();
        System.out.println("Please input the key:");
        String key = scan.nextLine();
        System.out.println("Please tell me the number of round you want to run: ");
        int numOfRound = scan.nextInt();

        //Schedule subkeys
        KeySchedule(key, numOfRound);

        //Firstly, permutate plaintext(X).
        String X0 = permutation(PlainText, IP);
        //Divide X0 into L0 and R0, the left half and right half.
        String L0 = X0.substring(0,32);
        FL.add(L0);
        String R0 = X0.substring(32);
        FR.add(R0);

        //Run numOfRound round
        for(int i = 1; i <=numOfRound;i++) {
            //Li = R(i-1)
            FL.add(FR.get(i-1));
            String XorR = "";
            String tmpS = RoundFunction(FR.get(i-1), FKey.get(i));
            for(int tmp=0;tmp<32;tmp++) {

                if(FL.get(i-1).charAt(tmp) == tmpS.charAt(tmp)) {
                    XorR += "0";
                } else {
                    XorR += "1";
                }
            }

            //Ri = L(i-1) XOR f(R(i-1), Ki)
            FR.add(XorR);
        }

        //Y = use IP1 to permute R(numOfRound)L(numOfRound)
        String cipherText = permutation(FR.get(numOfRound)+FL.get(numOfRound), IP1);
        System.out.println("The cipher text is:");
        System.out.println(cipherText);

    }

    public static void KeySchedule(String Kinit, int numOfRound) {

        String K0 = permutation(Kinit, PC1);
        FKey.add(K0);
        String C0 = K0.substring(0,28);
        FC.add(C0);
        String D0 = K0.substring(28);
        FD.add(D0);

        //If index = 1,2,9,16, then Ci = C(i-1) shift left one position, otherwise, Ci = C(i-1) shift left 2 positions
        for(int j=1;j<=numOfRound;j++) {
            if(j == 1 || j== 2 || j==9 || j==16) {
                FC.add(FC.get(j-1).substring(1)+String.valueOf(FC.get(j-1).charAt(0)));
                FD.add(FD.get(j-1).substring(1)+String.valueOf(FD.get(j-1).charAt(0)));
            } else {
                FC.add(FC.get(j-1).substring(2)+String.valueOf(FC.get(j-1).substring(0,2)));
                FD.add(FD.get(j-1).substring(2)+String.valueOf(FD.get(j-1).substring(0,2)));
            }

        }

        //Ki = CiDi
        for(int k=1;k<=numOfRound;k++) {
            FKey.add(permutation(FC.get(k)+FD.get(k), PC2));
        }

    }

    //use to permutate
    public static String permutation(String korig, int[][] table) {
        String perRes = "";
        int i = 8;
        int j = 0;

        if(table == IP || table == IP1) {
            j=8;
        } else if (table == PC1) {
            j=7;
        } else if (table == PC2 || table == E) {
            j=6;
        } else if (table == P) {
            j=4;
        } else {
            j=8;
        }


        for(int l =0;l< i;l++) {
            for(int m = 0; m<j;m++) {
                int pos = table[l][m] - 1;
                perRes += String.valueOf(korig.charAt(pos));
            }
        }

        return perRes;

    }

    //F function
    public static String RoundFunction(String Ri, String Ki) {
        String fres ="";
        //first, permutate Ri
        String ERi = permutation(Ri, E);
        String C = "";
        String B = "";
        //Then use E XOR Ki to get B
        for (int tmp = 0; tmp < 48;tmp++) {
            if(ERi.charAt(tmp) == Ki.charAt(tmp)) {
                B += "0";
            } else {
                B += "1";
            }
        }
        ArrayList <String> Bn = new ArrayList<>(8);

        //divide B to 8 groups with 6 bits for each group
        for(int n=0; n<48;n=n+6) {
            Bn.add(B.substring(n, n+6));
        }

        //Bn=b1b2b3b4b5b6, b1b6 is row number, b2b3b4b5 is colum number, use them to locate a number in Sn, then change the number into 4 binary to get Cn
        for(int a = 0; a<8;a++) {
            String row = String.valueOf(Bn.get(a).charAt(0))+String.valueOf(Bn.get(a).charAt(5));
            String column = Bn.get(a).substring(1,5);
            String Ci = Substitution(Bn.get(a), a+1, row, column);
            //C = c1c2c3c4c5c6c7c8
            C += Ci;
        }

        //permutate C with P to get f(Ri, Ki)
        fres = permutation(C, P);
        return fres;

    }

    //Use to get Ci
    public static String Substitution(String s, int index, String row, String column) {
        int rowI = convertDec(row);
        int columnI = convertDec(column);
        int res = 0;
        String resS = "";

        //select different Sn
        switch(index) {
            case 1:
                res = S1[rowI][columnI];
                resS = convertBin(res);
                break;
            case 2:
                res = S2[rowI][columnI];
                resS = convertBin(res);
                break;
            case 3:
                res = S3[rowI][columnI];
                resS = convertBin(res);
                break;
            case 4:
                res = S4[rowI][columnI];
                resS = convertBin(res);
                break;
            case 5:
                res = S5[rowI][columnI];
                resS = Integer.toBinaryString(res);
                break;
            case 6:
                res = S6[rowI][columnI];
                resS = Integer.toBinaryString(res);
                break;
            case 7:
                res = S7[rowI][columnI];
                resS = Integer.toBinaryString(res);
                break;
            case 8:
                res = S8[rowI][columnI];
                resS = Integer.toBinaryString(res);
                break;
            default:
                break;
        }

        switch(resS.length()) {
            case 1:
                resS = "000"+resS;
                break;
            case 2:
                resS = "00"+resS;
                break;
            case 3:
                resS = "0"+resS;
                break;
            default:
                break;
        }

        return resS;
    }

    //change dec to bin
    public static String convertBin (int sum) {
        StringBuffer binary = new StringBuffer();
        if (sum == 0 || sum == 1) {
            binary.insert(0, sum % 2);
        }
        while (sum != 0 && sum != 1) {
            binary.insert(0, sum % 2);
            sum = sum / 2;
            if (sum == 0 || sum == 1) {
                binary.insert(0, sum % 2);
            }
        }
        return binary.toString();
    }

    //change bin to dec
    public static int convertDec (String sumS) {
        int sum = 0;
        for (int s = 0; s < sumS.length(); s++) {

            sum += Math.pow(2, (sumS.length()-1-s)) * Integer.parseInt(String.valueOf(sumS.charAt(s)));
        }

        return sum;
    }
}
