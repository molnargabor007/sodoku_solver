import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/*
Sample input easy1:

6 0 8 0 0 0 9 0 4
2 0 0 0 1 4 0 5 0
0 0 7 9 8 3 0 0 0
0 2 0 5 0 0 0 0 9
0 3 9 4 0 8 5 1 0
8 0 0 0 0 9 0 7 0
0 0 0 3 9 2 7 0 0
0 5 0 7 4 0 0 0 8
9 0 4 0 0 0 3 0 6

Expected Result sample 1:
6 1 8 2 7 5 9 3 4
2 9 3 6 1 4 8 5 7
5 4 7 9 8 3 2 6 1
4 2 1 5 3 7 6 8 9
7 3 9 4 6 8 5 1 2
8 6 5 1 2 9 4 7 3
1 8 6 3 9 2 7 4 5
3 5 2 7 4 6 1 9 8
9 7 4 8 5 1 3 2 6

Sample input 2 medium:

8 0 0 9 3 0 0 0 2
0 0 9 0 0 0 0 4 0
7 0 2 1 0 0 9 6 0
2 0 0 0 0 0 0 9 0
0 6 0 0 0 0 0 7 0
0 7 0 0 0 6 0 0 5
0 2 7 0 0 8 4 0 6
0 3 0 0 0 0 5 0 0
5 0 0 0 6 2 0 0 8

Expected Result sample 2:
8 4 6 9 3 7 1 5 2
3 1 9 6 2 5 8 4 7
7 5 2 1 8 4 9 6 3
2 8 5 7 1 3 6 9 4
4 6 3 8 5 9 2 7 1
9 7 1 2 4 6 3 8 5
1 2 7 5 9 8 4 3 6
6 3 8 4 7 1 5 2 9
5 9 4 3 6 2 7 1 8

Sample input 3 hard:
not works yet:
3 8 0 0 0 0 0 0 0
0 0 0 4 0 0 7 8 5
0 0 9 0 2 0 3 0 0
0 6 0 0 9 0 0 0 0
8 0 0 3 0 2 0 0 9
0 0 0 0 4 0 0 7 0
0 0 1 0 7 0 5 0 0
4 9 5 0 0 6 0 0 0
0 0 0 0 0 0 0 9 2

more samples here:
http://lipas.uwasa.fi/~timan/sudoku/

*/
public class SodokuSolver2 {


    static void FillArray(int[][][] Array3D) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = scan.nextInt();
                Array3D[i][j][0] = n;
                for (int k = 1; k < 10; k++) {
                    if (n == 0) {
                        Array3D[i][j][k] = k;
                    } else {
                        Array3D[i][j][k] = 0;
                    }
                }
            }
        }

    }

    static void PrintResultArray(int[][][] Array3D) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(Array3D[i][j][0] + " ");

            }
            System.out.println();
        }

    }

    static void Print3DArray(int[][][] Array3D) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 10; k++) {
                    System.out.print(Array3D[i][j][k]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    static void RowAndColumnCheck(int[][][] Array3D) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //check in rows excludes possibilities
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 9; r++) {
                            if (Array3D[i][j][k] == Array3D[i][r][0]) {
                                Array3D[i][j][k] = 0; //set the possibilities to 0 after exclude a number
                                break; // break after exclude a number, no need to continue
                            }

                        }

                    }
                }
                //check in columns excludes possibilities
                if (Array3D[j][i][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 9; r++) {
                            if (Array3D[j][i][k] == Array3D[r][i][0]) {
                                Array3D[j][i][k] = 0; //set the possibilities to 0 after exclude a number
                                break; // break after exclude a number, no need to continue
                            }
                        }

                    }

                }

            }
        }
    }


    static void FillUpInRowsAndColums(int[][][] Array3D) {
        int rcount = 0;
        int poz = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //check in rows fill up if only one option
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        rcount = 0;
                        for (int r = 0; r < 9; r++) {
                            if (Array3D[i][r][k] != 0) {
                                rcount++; //count numbers in row
                                poz = r; // save positions in the row
                            }
                        }
                        //if the number appears only ones fill up the sodoku matrix in the saved position
                        if (rcount == 1) {
                            Array3D[i][poz][0] = Array3D[i][poz][k];
                        }

                    }

                }

            }
        }

        rcount = 0;
        poz = 0;
        //Column Fill Up
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Array3D[j][i][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        rcount = 0;
                        for (int r = 0; r < 9; r++) {
                            if (Array3D[r][i][k] != 0) {
                                rcount++; //count numbers in Columns
                                poz = r; // save positions in the Columns
                            }
                        }
                        //if the number appears only ones fill up the sudoku matrix in the saved position
                        if (rcount == 1) {
                            Array3D[poz][i][0] = Array3D[poz][i][k];
                        }

                    }

                }

            }

        }

        FillUpWithZerosWhereSolved(Array3D);
        RowAndColumnCheck(Array3D);
        BoxCheckAll(Array3D);
    }

//this method fill up in boxes where is only one option left

    static void FillUpInBoxesParam(int rowStart, int columnStart, int[][][] Array3D) {
        int rcount = 0;
        int pozi = 0;
        int pozj = 0;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = columnStart; j < columnStart + 3; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        rcount = 0;
                        for (int r = rowStart; r < rowStart + 3; r++) {
                            for (int o = columnStart; o < columnStart + 3; o++) {
                                if (Array3D[r][o][k] != 0) {
                                    rcount++;
                                    pozi = r;
                                    pozj = o;
                                }
                            }

                        }
                        if (rcount == 1) {
                            Array3D[pozi][pozj][0] = Array3D[pozi][pozj][k];
                        }

                    }
                }

            }
        }
        FillUpWithZerosWhereSolved(Array3D);
        RowAndColumnCheck(Array3D);
        BoxCheckAll(Array3D);

    }

    //Run FillUpInBoxesParam method for all 9 boxes

    static void BoxFillAll(int[][][] Array3D) {
        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {
                FillUpInBoxesParam(i, j, Array3D);
            }
        }
    }


// Search in the box specified in the parameter, excludes possibilities

    static void BoxCheckPar(int rowStart, int columnStart, int[][][] Array3D) {
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = columnStart; j < columnStart + 3; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = rowStart; r < rowStart + 3; r++) {
                            for (int o = columnStart; o < columnStart + 3; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0; //set the possibilities to 0 after exclude a number
                                    break; // break after exclude a number, no need to continue
                                }
                            }

                        }
                    }
                }
            }
        }
    }

//Run BoxCheck() method for all 9 boxes

    static void BoxCheckAll(int[][][] Array3D) {
        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {
                BoxCheckPar(i, j, Array3D);
            }
        }
    }


    static void FillUpWithZerosWhereSolved(int[][][] Array3D) {
        //fill up with 0 where its solved
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Array3D[i][j][0] != 0) {
                    for (int k = 1; k < 10; k++) {
                        Array3D[i][j][k] = 0;
                    }
                }

            }
        }


    }

//this part needs to be fixed
//
//    static void IfOneOptionFillUp(int[][][] Array3D) {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                int ksum = 0;
//                int counter = 0;
//                if (Array3D[i][j][0] == 0) {
//                    for (int k = 1; k < 10; k++) {
//                        if (Array3D[i][j][k] > 0) {
//                            counter++;
//                        }
//                        ksum = ksum + Array3D[i][j][k];
//                    }
//
//                }
//                if (counter == 1) {
//                    Array3D[i][j][0] = ksum;
//
//                }
//
//            }
//            FillUpWithZerosWhereSolved(Array3D);
//
//        }
//    }


    static int SumOf3DArray(int[][][] Array3D) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sum += Array3D[i][j][0];
            }
        }

        return sum;
    }


    public static void main(String[] args) {

        int[][][] SodokuArray = new int[9][9][10];
        System.out.println("Please enter a Sudoku to solve:");
        FillArray(SodokuArray);
        int loopSum = 0;
        int ResultSum = 0;
        int loop = 1;


        do {
            FillUpInRowsAndColums(SodokuArray);
            BoxFillAll(SodokuArray);
//   not working   IfOneOptionFillUp(SodokuArray);
            if (loopSum == SumOf3DArray(SodokuArray)) {
                System.out.println();
                System.out.println("Sorry I can't solve it right now :(");
                System.out.println("This much what I have solved now:");
                System.out.println();
                PrintResultArray(SodokuArray);
                break;
            } else {
                ResultSum = SumOf3DArray(SodokuArray);
                System.out.println(loop + ". loop");
                Print3DArray(SodokuArray);
                System.out.println("--------------------------------------------------------------------------------------------------");

            }
            loop++;
            loopSum = SumOf3DArray(SodokuArray);
            System.out.println("SumSodoku:" + SumOf3DArray(SodokuArray));
        } while (SumOf3DArray(SodokuArray) != 405);


        if (ResultSum == 405) {
            System.out.println();
            System.out.println("Your Sudoku was solved:");
            System.out.println();
            PrintResultArray(SodokuArray);
        }


    }


}