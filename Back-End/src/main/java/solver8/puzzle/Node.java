package solver8.puzzle;

import java.util.ArrayList;

public class Node {
    String state; // state
    Node parent; // parent node
    int depth; // depth of the node
    double cost; // cost to reach this node "Needed for A* only"

    // Node constructor.
    public Node(String state, int depth, Node parent) {
        this.state = state;
        this.depth = depth;
        this.parent = parent;
    }

    // Method return the Parent of the node
    public Node getParent() {
        return parent;
    }

    // Method return the depth of the node
    public int getDepth() {
        return depth;
    }

    // Method return the state of the node
    public String getState() {
        return state;
    }

    // Method return the cost of the node
    public double getCost() {
        return cost;
    }

    // Method set the cost of the node
    public void setCost(double Cost) {
        this.cost = Cost + this.depth;
    }

    // Method to get all neighbors of th node
    public ArrayList<Node> getDFSNeighbors() {
        int EmptyIndex = this.state.indexOf("0");
        int x = EmptyIndex % 3;
        int y = EmptyIndex / 3;

        ArrayList<Node> neighbors = new ArrayList<Node>();
        int indexToSwap;

        // Moving left is possible move
        if (x - 1 >= 0) {
            indexToSwap = (y * 3) + (x - 1);
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }
        // Moving down is possible move
        if (y + 1 < 3) {
            indexToSwap = ((y + 1) * 3) + x;
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }
        // Moving right is possible move
        if (x + 1 < 3) {
            indexToSwap = (y * 3) + (x + 1);
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }
        // Moving up is possible move
        if (y - 1 >= 0) {
            indexToSwap = ((y - 1) * 3) + x;
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }

        return neighbors;
    }
    // Method to get all neighbors of th node
    public ArrayList<Node> getNeighbors() {
        int EmptyIndex = this.state.indexOf("0");
        int x = EmptyIndex % 3;
        int y = EmptyIndex / 3;

        ArrayList<Node> neighbors = new ArrayList<Node>();
        int indexToSwap;
        // Moving up is possible move
        if (y - 1 >= 0) {
            indexToSwap = ((y - 1) * 3) + x;
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }
        // Moving right is possible move
        if (x + 1 < 3) {
            indexToSwap = (y * 3) + (x + 1);
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }
        // Moving down is possible move
        if (y + 1 < 3) {
            indexToSwap = ((y + 1) * 3) + x;
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }
        // Moving left is possible move
        if (x - 1 >= 0) {
            indexToSwap = (y * 3) + (x - 1);
            neighbors.add(NeighborToNode(indexToSwap, EmptyIndex));
        }

        return neighbors;
    }

    // function to get state after moving.
    public Node NeighborToNode(int indexToSwap, int EmptyIndex) {
        String newState = state;
        char ch[] = state.toCharArray();
        ch[EmptyIndex] = ch[indexToSwap];
        ch[indexToSwap] = '0';
        newState = String.valueOf(ch);

        Node neighbor = new Node(newState, depth + 1, this);
        return neighbor;
    }
}
