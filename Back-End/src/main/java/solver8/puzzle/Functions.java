package solver8.puzzle;

import java.util.ArrayList;

public class Functions {
    // Method to get the pass to the goal
    public static ArrayList<String> pathToGoal(Node Goal){
        ArrayList<String> path = new ArrayList<>();
        path.add(0,Goal.getState());
        while (Goal != null){
            if(Goal.getParent() == null)
                break;
            Goal = Goal.getParent();
            path.add(0,Goal.getState());
        }
        return path;
    }


}
