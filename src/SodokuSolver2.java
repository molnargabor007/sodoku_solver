import java.util.*;

/*
Sample input:

works:
6 0 8 0 0 0 9 0 4
2 0 0 0 1 4 0 5 0
0 0 7 9 8 3 0 0 0
0 2 0 5 0 0 0 0 9
0 3 9 4 0 8 5 1 0
8 0 0 0 0 9 0 7 0
0 0 0 3 9 2 7 0 0
0 5 0 7 4 0 0 0 8
9 0 4 0 0 0 3 0 6


not works:
8 0 0 9 3 0 0 0 2
0 0 9 0 0 0 0 4 0
7 0 2 1 0 0 9 6 0
2 0 0 0 0 0 0 9 0
0 6 0 0 0 0 0 7 0
0 7 0 0 0 6 0 0 5
0 2 7 0 0 8 4 0 6
0 3 0 0 0 0 5 0 0
5 0 0 0 6 2 0 0 8

extra line

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

    static void RowCheck(int[][][] Array3D) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 9; r++) {
                            if (Array3D[i][j][k] == Array3D[i][r][0]) {
                                Array3D[i][j][k] = 0;
                            }
                        }

                    }

                }
            }
        }

    }

    static void ColumnCheck(int[][][] Array3D) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Array3D[j][i][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 9; r++) {
                            if (Array3D[j][i][k] == Array3D[r][i][0]) {
                                Array3D[j][i][k] = 0;
                            }
                        }

                    }

                }
            }
        }

    }

    static void BoxCheck(int[][][] Array3D) {
        //top left box

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 3; r++) {
                            for (int o = 0; o < 3; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }

        //top middle box

        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 3; r++) {
                            for (int o = 3; o < 6; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }

        //top right box
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 9; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 0; r < 3; r++) {
                            for (int o = 6; o < 9; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }
        //middle left box
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 3; r < 6; r++) {
                            for (int o = 0; o < 3; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }

        // middle middle box
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 3; r < 6; r++) {
                            for (int o = 3; o < 6; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }
        //middle left box
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 3; r < 6; r++) {
                            for (int o = 6; o < 9; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }

        //bottom lef box
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 6; r < 9; r++) {
                            for (int o = 0; o < 3; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }

        //bottom middle box
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 6; r < 9; r++) {
                            for (int o = 3; o < 6; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }

        //bottom left box
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int r = 6; r < 9; r++) {
                            for (int o = 6; o < 9; o++) {
                                if (Array3D[i][j][k] == Array3D[r][o][0]) {
                                    Array3D[i][j][k] = 0;
                                }
                            }

                        }
                    }
                }
            }
        }


    }


    static void IfOneOptionFillUp(int[][][] Array3D) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int ksum = 0;
                int counter = 0;
                if (Array3D[i][j][0] == 0) {
                    for (int k = 1; k < 10; k++) {
                        if (Array3D[i][j][k] > 0) {
                            counter++;
                        }
                        ksum = ksum + Array3D[i][j][k];
                    }
                    if (counter == 1) {
                        Array3D[i][j][0] = ksum;

                    }
                }
            }
        }

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
        System.out.println("Please enter a Sodoku to solve:");
        FillArray(SodokuArray);

        while(SumOf3DArray(SodokuArray) != 405) {
            BoxCheck(SodokuArray);
            RowCheck(SodokuArray);
            ColumnCheck(SodokuArray);
            IfOneOptionFillUp(SodokuArray);
        }

//            System.out.println(SumOf3DArray(SodokuArray));

//        Print3DArray(SodokuArray);
        System.out.println();
        System.out.println("Your Sodoku solved:");
        System.out.println();
        PrintResultArray(SodokuArray);


    }


}