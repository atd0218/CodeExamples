package com.tester.pkgtest.algorithmPractice;

public class Sorting {

    /**
     *
     *
     * Quicksort: Quicksort is generally considered one of the fastest sorting algorithms with an average and best-case
     * time complexity of O(n log n). However, in the worst-case scenario, it can degrade to O(n^2) if the pivot
     * selection is poor and the input data is already sorted or nearly sorted. Nevertheless, its average-case time
     * complexity makes it one of the best choices for general-purpose sorting.
     *
     * Mergesort: Mergesort also has a time complexity of O(n log n) in all cases, including the worst-case scenario.
     * It is stable and performs well on larger datasets due to its consistent performance. Although it may require
     * additional memory for the merge step, its efficiency and stability make it a popular choice for various applications.
     *
     * Heapsort: Heapsort has a time complexity of O(n log n) in all cases, making it efficient for large datasets.
     * It is an in-place sorting algorithm, meaning it doesn't require additional memory like Mergesort. However,
     * it is less commonly used in practice compared to Quicksort and Mergesort due to its lower average performance
     * and lack of stability.
     */


    /**
     *
     * Linear search, also known as sequential search, is a simple searching algorithm that sequentially checks each
     * element of a collection until a match is found or the entire collection has been searched. In Java, you can
     * implement linear search using a loop that iterates through the elements of an array or a list, comparing each
     * element with the target value.Linear search has a time complexity of O(n), where n is the number of elements in
     * the collection. It is suitable for small datasets or unsorted collections but becomes inefficient for large
     * datasets compared to binary search.
     *
     *
     * Binary search is an efficient searching algorithm used to find a target value in a sorted collection
     * (e.g., array or list) by repeatedly dividing the search interval in half.To perform binary search in Java, you
     * typically use a while loop or recursive function to divide the search space and compare the target value with
     * the middle element of the current interval.Binary search has a time complexity of O(log n), where n is the number
     * of elements in the collection. It is significantly faster than linear search for large datasets because it
     * reduces the search space exponentially with each iteration.Note that binary search requires the collection to be
     * sorted beforehand.
     *
     * Depth-First Search is a graph traversal algorithm used to explore a graph or tree by starting at a root node
     * and traversing as far as possible along each branch before backtracking.In Java, you can implement DFS using
     * recursion or a stack data structure to keep track of nodes to visit. The algorithm typically involves visiting
     * a node, recursively visiting its adjacent nodes (depth-first), and backtracking when all adjacent nodes have
     * been visited.DFS is commonly used in graph algorithms, such as finding connected components, detecting cycles,
     * and traversing trees. It is not limited to graphs and can be applied to any data structure that exhibits
     * hierarchical relationships.
     */


}
