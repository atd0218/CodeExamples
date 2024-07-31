package com.tester.pkgtest.leetcodetests;

public class findIfPathExistsGraph {

    //DEPTH FIRST SEARCH
//    boolean found = false;
//    public boolean validPath(int n, int[][] edges, int start, int end) {
//        if(start == end) return  true;
//
//        //create a hash map that has an integer key and a List of integers as a value.
//        Map<Integer,List<Integer>> graph = new HashMap();
//
//        //initialise a visited array the size of n passed in.
//        boolean[] visited = new boolean[n];
//
//        //loop through n number of times and add the current index and a new arraylist in each position
//        //of the hashmap
//        for(int i = 0 ; i < n ; i++) graph.put(i, new ArrayList());
//
//        //construct graph, add bidirectional vertex
//        for(int[] edge : edges){
//            graph.get(edge[0]).add(edge[1]);
//            graph.get(edge[1]).add(edge[0]);
//        }
//        //start dfs from start point
//        dfs(graph,visited,start,end);
//        return found;
//    }
//
//    private void dfs(Map<Integer,List<Integer>> graph,boolean[] visited, int start, int end){
//        if(visited[start] || found) return;
//        visited[start] = true;
//        //look through each record of the hashmap until you find the end or destination
//        for(int nei : graph.get(start)){
//            //if the neighbor you are looking at equals the end then the path exists.
//            if(nei == end){
//                found = true;
//                break;
//            }
//            //otherwise recursively call dfs again and look at the next neighbor
//            if(!visited[nei])
//                dfs(graph, visited, nei, end);
//        }
//    }

    //BREADTH FIRST SEARCH MUCH SLOWER IN THIS CASE
//    public boolean validPath(int n, int[][] edges, int source, int destination) {
//        List<List<Integer>> graph = buildGraph(n, edges);
//        boolean[] visited = new boolean[n];
//        Queue<Integer> queue = new LinkedList<>();
//
//        queue.offer(source);
//
//        while(!queue.isEmpty()) {
//            int current = queue.poll();
//
//            if (current == destination) return true;
//
//            visited[current] = true;
//
//            for(int neighbor: graph.get(current)) {
//                if (!visited[neighbor]) queue.offer(neighbor);
//            }
//        }
//
//        return false;
//    }
//
//    private List<List<Integer>> buildGraph(int n, int[][] edges) {
//        List<List<Integer>> graph = new ArrayList<>();
//
//        for(int i=0;i<n;i++) {
//            graph.add(new ArrayList<>());
//        }
//
//        for(int[] edge: edges) {
//            graph.get(edge[0]).add(edge[1]);
//            graph.get(edge[1]).add(edge[0]);
//        }
//
//        return graph;
//    }

    //BRUTE FORCE ITERATION VERSION

    public boolean validPath(int n, int[][] edges, int start, int end) {

        //create a boolean array to store if we have used a node
        boolean[] used = new boolean[n];

        //initally state that the start node has been used or visited
        used[start] = true;

        //set an arbitrary variable to true
        boolean newUsedFound = true;

        //while end has not been added to the used array
        //and the newUsedFound is true then run the loop
        while (!used[end] && newUsedFound) {

            //set newUsedFound to false
            newUsedFound = false;

            //loop through edges array backwards marking the node as used/visited each time
            //if the used array contains true for the end position then there is a path to it.
            for (int i = edges.length - 1; i >= 0; i--) {

                //
                if (used[edges[i][0]]) {
                    if (!used[edges[i][1]])  newUsedFound = used[edges[i][1]] = true;
                } else if (used[edges[i][1]]) {
                    newUsedFound = used[edges[i][0]] = true;
                }
            }
        }
        return used[end];
    }
}
