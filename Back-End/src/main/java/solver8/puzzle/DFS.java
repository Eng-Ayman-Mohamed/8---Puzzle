package solver8.puzzle;

import java.util.*;

public class DFS {
    private HashSet<String> Explored = new HashSet<String>();
    int Maxdepth = 0; // Max depth of all states.
    private int solcost = 0;
    private ArrayList<String> solPath = new ArrayList<String>();
    String time;

    public boolean solve(String Initial_state, String Goal_State) {
        //creating Calendar instance
        Calendar cal1 = Calendar.getInstance();
        //Returns current time in millis
        long start = cal1.getTimeInMillis();
        Stack<Node> frontier = new Stack<>();
        Node State = new Node(Initial_state, 0, null);
        frontier.push(State);
        Node inFrontier;
        while (!frontier.empty()) {
            State = frontier.pop();
            Explored.add(State.getState());
            updateMaxDepth(State.getDepth());
            if (State.getState().equals(Goal_State)) {
                Calendar cal2 = Calendar.getInstance();
                long end = cal2.getTimeInMillis();
                time=Long.toString(end-start);
                this.solcost = State.getDepth();
                System.out.println("Solved");
                System.out.println("Cost of Path = " + State.getDepth());
                System.out.println("Max Depth = " +GetMaxDepth());
                System.out.println("Expanded Nodes = " + Explored.size());
                System.out.println("Path to Goal :");
                this.solPath = Functions.pathToGoal(State);
                for (String s : solPath) {
                    System.out.println(s);
                }

                return true;
            }
            if (State.getDepth() < 5000) {
                for (Node neighbor : State.getDFSNeighbors()) {
                    inFrontier =isInFrontier(frontier, neighbor);
                    if (inFrontier == null && !Explored.contains(neighbor.getState())) {
                        frontier.push(neighbor);
                    }
                }
            }

        }
        System.out.println("Failure");
        System.out.println("number of nodes expanded : " + Explored.size() + " ,Max Pass Depth : " +GetMaxDepth());

        return false;
    }

    public String getTime(){
        return time;
    }
    public Node isInFrontier(Stack<Node> frontier, Node node) {

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
        return this.solcost;
    }
    public int getNumExplored(){
        return this.Explored.size();
    }


    public ArrayList<String> getSolPath() {
        return this.solPath;
    }

    // Method to calculate the maximum depth.
    public void updateMaxDepth(int depth) {
        this.Maxdepth = this.Maxdepth < depth ? depth : Maxdepth;
    }
}
