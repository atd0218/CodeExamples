package com.tester.pkgtest.leetcodetests;

/**
 *
 */

public class shorestDistanceToTargetStringCircularArray {

    public int closetTarget(String[] words, String target, int startIndex) {

        //set to max int value to begin with.
        int minDistance = Integer.MAX_VALUE;
        //store words.length to avoid typing it
        int n = words.length;

        // Iterate through the circular array to find occurrences of the target string
        for (int i = 0; i < n; i++) {
            if (!words[i].equals(target))
                continue;

            // Calculate the shortest distance to reach the target string from the current position
            /*
            min distance of (The absolute difference between the current index and the length of the circular array,
            The absolute difference between the current index and the length of the circular array minus the index of the target string.
            (This accounts for the circular nature of the array.))
             */
            int distance = Math.min(Math.abs(i - startIndex), n - Math.abs(i - startIndex));

            // Update the minimum distance
            minDistance = Math.min(minDistance, distance);
        }

        //check at the end if the minDistance was never changed indicating that the target was not found
        //If that is the case I manually set the value to -1 as that is what the assignment requests
        if(minDistance == Integer.MAX_VALUE) {
            minDistance = -1;
        }

        return minDistance;
    }
}
