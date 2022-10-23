package solver8.puzzle;

import java.util.*;

public class Astar {
    int Maxdepth = 0; // Max depth of all states.
    int expandedCount = 0; // Number of expanded nodes.
    int pathCost = 0; // Depth on the goal state.
    HashSet<String> expanded = new HashSet<String>();
    ArrayList<String> statePath = new ArrayList<String>();
    String time;

    //
    public boolean solve(String intialState, String goalState, boolean euclidean) {
        //creating Calendar instance
        Calendar cal1 = Calendar.getInstance();
        //Returns current time in millis
        long start = cal1.getTimeInMillis();
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
        Node currentState = new Node(intialState, 0, null);
        currentState.setCost(0);
        frontier.add(currentState);
        ArrayList<Node> neighbors;
        Node inFrontier;
        while (!frontier.isEmpty()) {
            // remove least cost element in frontier
            currentState = frontier.poll();
            expanded.add(currentState.getState());
            // Updating maximum depth.
            updateMaxDepth(currentState.getDepth());
            // if current state equals goal state
            if (currentState.getState().equals(goalState)) {
                Calendar cal2 = Calendar.getInstance();
                long end = cal2.getTimeInMillis();
                time=Long.toString(end-start);
                this.pathCost = currentState.getDepth();
                System.out.println("Solved");
                System.out.println("Cost of Path = " + currentState.getDepth());
                System.out.println("Max Depth = " + GetMaxDepth());
                System.out.println("Expanded Nodes = " + expanded.size());
                System.out.println("Path to Goal :");
                this.statePath = Functions.pathToGoal(currentState);
                for (String s : this.statePath) {
                    System.out.println(s);
                }


                return true;
            }
            // get the current node neighbors
            neighbors = currentState.getNeighbors();
            // loop on all neighbors
            for (Node n : neighbors) {
                // Set Cost for the neighbors
                if (euclidean) {
                    n.setCost(EuclideanCost(n.getState(), goalState));
                } else {
                    n.setCost(ManhattanCost(n.getState(), goalState));
                }
                // Node n in the frontier"If Exist", else null.
                inFrontier = isInFrontier(frontier, n);
                // if not in frontier and not in expanded
                if (inFrontier == null && !expanded.contains(n.getState())) {
                    // add it to frontier
                    frontier.add(n);
                } else if (!expanded.contains(n.getState())) {
                    // if the new node less than its previous declaration.
                    if (n.getCost() < inFrontier.getCost()) {
                        frontier.remove(inFrontier);
                        frontier.add(n);// set parent, set depth
                        // remove equal costs
                        // add equal costs
                    }
                }

            }

        }
        System.out.println("Failure");
        System.out.println("number of nodes expanded : " + expanded.size() + " ,Max Pass Depth : " +GetMaxDepth());
        return false;// Return Error Message.

    }

    public Node isInFrontier(PriorityQueue<Node> frontier, Node node) {

        for (Node n : frontier) {
            if (n.getState().equals(node.getState()))
                return n;
        }
        return null;
    }

    // Method to return the maximum depth.
    public int GetMaxDepth() {
        return this.Maxdepth;
    }
    public int getSolcost() {
        return this.pathCost;
    }
    public int getNumExplored(){
        return this.expanded.size();
    }

    public ArrayList<String> getSolPath() {
        return this.statePath;
    }

    // Method to calculate the maximum depth.
    public void updateMaxDepth(int depth) {
        this.Maxdepth = this.Maxdepth < depth ? depth : Maxdepth;
    }

    // method calculate Cost with Manhattan distance
    public int ManhattanCost(String currState, String goalState) {
        int Index;
        int manhattan = 0;
        for (int i = 0; i < 9; i++) {
            // return number at index i in current state.
            Index = Integer.parseInt("" + currState.charAt(i));// 7
            manhattan += Math.abs((i % 3) - (Index % 3)) + Math.abs((i / 3) - (Index / 3));
        }

        return manhattan;
    }

    // method calculate Cost with Euclidean distance
    public double EuclideanCost(String currState, String goalState) {
        int Index;
        double Euclidean = 0;
        for (int i = 0; i < 9; i++) {
            // return number at index i in current state.
            Index = Integer.parseInt("" + currState.charAt(i));// 7
            Euclidean += Math.sqrt(Math.pow((i % 3) - (Index % 3), 2) + Math.pow((i / 3) - (Index / 3), 2));
        }

        return Euclidean;
    }

    public String getTime(){
        return time;
    }

    // class to compare the cost of two nodes to set in priority queue.
    class NodeComparator implements Comparator<Node> {
        // Overriding compare()method of Comparator
        public int compare(Node n1, Node n2) {
            if (n1.getCost() > n2.getCost())
                return 1;
            else if (n1.getCost() < n2.getCost())
                return -1;
            return 0;
        }
    }
}