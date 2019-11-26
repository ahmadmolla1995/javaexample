package Taxi;

public class DistanceCoefficientMatrix {
    private static int[][] matrix = {{1, 2, 2, 4, 3},{2, 1, 4, 2, 3},{3, 5, 1, 3, 2},{4, 3, 3, 1, 2},{3, 3, 2, 2, 1}};

    public static int getCoefficient(int first, int second) {
        return matrix[first][second];
    }
}
