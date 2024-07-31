package com.tester.pkgtest.leetcodetests;

public class FindCenterofStarGraph {

    //one liner ternary operator implementation

    public int findCenter(int[][] e) {
        //check if the first edge of the first node matches the first edge on the second node
        //or check to see if the second edge matches between the nodes as one will.
        //Then you will know which one to pass back.

        /*
        this is because the problem states that the center will be shared among all other edges so you
        only need to find the matching edge for the first two to know the rest will be the same.
         */
        return e[0][0] == e[1][0] || e[0][0] == e[1][1] ? e[0][0] : e[0][1];
    }
}
