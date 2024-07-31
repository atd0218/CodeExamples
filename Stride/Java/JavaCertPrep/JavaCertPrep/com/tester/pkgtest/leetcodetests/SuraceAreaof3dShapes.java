package com.tester.pkgtest.leetcodetests;

/**
 * You can have several different 3d shapes all pushed next to each other.
 *
 * Keep in mind surface area is the length * width * height so to take account for any heights that are shorter than the
 * current sphere then you will want to subtract any height calculations off the surface area to account for that
 */
public class SuraceAreaof3dShapes {
        public int surfaceArea(int[][] grid) {
            //initiate a variable n to store grid.length
            int n = grid.length;
            //set initial result variable to zero
            int surfaceArea = 0;

            //loop through the outer loop until I equals the grid length = ROWS
            for (int i = 0; i < n; i++) {
                //loop through the inner loop until J equals the grid length = COLUMNS
                for (int j = 0; j < n; j++) {

                    //height is set to the current position of grid[i][j]
                    int height = grid[i][j];


                    //while the height variable is greater than 0
                    //terminating condition is when the height condition is 0
                    if (height > 0) {

                        //set surfaceArea to 4 times height plus 2
                        //2 represents bottom and top faces and 4 represents
                        //all 4 side faces
                        surfaceArea += 2 + (4 * height);

                        /*
                        It then subtracts the areas of adjacent sides that are in contact with other blocks.
                        It subtracts the smaller of the heights of the adjacent blocks from the current block's height,
                        multiplied by 2, for both the left and upper adjacent blocks. This is done to account for the
                        fact that adjacent blocks will overlap with the current block, reducing the exposed surface area.
                         */
                        surfaceArea -= (i > 0) ? Math.min(height, grid[i - 1][j]) * 2 : 0;
                        surfaceArea -= (j > 0) ? Math.min(height, grid[i][j - 1]) * 2 : 0;
                    }
                }
            }
            return surfaceArea;
        }
}
