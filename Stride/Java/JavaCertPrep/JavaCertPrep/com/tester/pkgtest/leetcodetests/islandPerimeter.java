package com.tester.pkgtest.leetcodetests;

public class islandPerimeter {

    public int islandPerimeter(int[][] grid) {

        int sumOfLandCells = 0;

        //navigate through the array by iterating through it.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                //if the current cell has a value of 1 this indicates it is a land cell
                //we need to calculate the Perimeter for the land cell using the helper function
                if (grid[i][j] == 1) {
                    sumOfLandCells+= calculatePermiter(grid, i, j);

                }
            }
        }

        return sumOfLandCells;
    }

    /**
     *
     * @param grid - the grid array passed as input
     * @param row - the current index of i in the above for loop
     * @param col - the current index of j in the above for loop
     * @return the perimeter value for each land cell
     *
     * for each land cell this function will calculate the permiter of it.
     *
     * 1. initialize perimiter to 0
     * 2. create directions to look 1 to left, 1 above, 1 to right, 1 below to check all adjacent cells.
     * 3. in the for each loop we look at each new row and column adjacent to ours and see if it should
     * have the perimeter increased.
     */
    private int calculatePermiter(int[][] grid, int row, int col) {

        int perimeter = 0;

        // Check adjacent cells
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the adjacent cell is out of bounds or water
            /*
            newRow < 0: Checks if the adjacent cell is above the grid boundary.
            newRow >= grid.length: Checks if the adjacent cell is below the grid boundary.
            newCol < 0: Checks if the adjacent cell is to the left of the grid boundary.
            newCol >= grid[0].length: Checks if the adjacent cell is to the right of the grid boundary.
            grid[newRow][newCol] == 0: Checks if the adjacent cell contains water (cell value is 0).
             */
            if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length || grid[newRow][newCol] == 0) {
                perimeter++;
            }
        }

        return perimeter;
    }


    /**
     *  public int islandPerimeter(int[][] grid) {
     *       int output = 0;
     *         for (int i = 0; i < grid.length; i++) {
     *             for (int j = 0; j < grid[i].length; j++) {
     *                 if (grid[i][j] == 1) {
     *                     output += 4;
     *
     *                     output -= (j - 1 >= 0 && grid[i][j - 1] == 1) ? 1  : 0;
     *                     output -= (j + 1 != grid[i].length && grid[i][j + 1] == 1)  ? 1 : 0;
     *                     output -= (i - 1 >= 0 && grid[i - 1][j] == 1 ) ? 1 : 0;
     *                     output -= (i + 1 != grid.length && grid[i + 1][j] == 1) ? 1 : 0;
     *                 }
     *             }
     *         }
     *         return output;
     *     }
     *
     *
     * This method is a little faster but still O(n^2) time due to the double for loops.
     */
}
