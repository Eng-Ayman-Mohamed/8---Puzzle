package solver8.puzzle;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@CrossOrigin(origins="http://127.0.0.1:5500/")

@RestController
public class Controler {
    boolean flag = false;
    // all return array list 1-maxdepth 2-number of explored nodes 3-solution cost 4->end path

    @GetMapping("/BFS")
    public ArrayList<String> RunBFS(@RequestParam String initialState){
        BFS bfs = new BFS();
        flag = bfs.solve(initialState,"012345678");
        ArrayList<String> result = new ArrayList<>();
        if(flag){
            result.add(Integer.toString(bfs.GetMaxDepth()));
            result.add(Integer.toString(bfs.getNumExplored()));
            result.add(Integer.toString(bfs.getSolcost()));
            result.add(bfs.getTime());
            result.addAll(bfs.getSolPath());
        }else{
            result.add("failed");
        }
        return result;
    }

    @GetMapping("/DFS")
    public ArrayList<String> RunDFS(@RequestParam String initialState){
        DFS dfs = new DFS();
        flag = dfs.solve(initialState,"012345678");
        ArrayList<String> result = new ArrayList<>();
        if(flag){
            result.add(Integer.toString(dfs.GetMaxDepth()));
            result.add(Integer.toString(dfs.getNumExplored()));
            result.add(Integer.toString(dfs.getSolcost()));
            result.add(dfs.getTime());
            result.addAll(dfs.getSolPath());
        }else{
            result.add("failed");
        }
        return result;
    }

    @GetMapping("/Euclidean")
    public ArrayList<String> AstarEuclidean(@RequestParam String initialState){
        Astar a = new Astar();
        flag=a.solve(initialState,"012345678",true);
        ArrayList<String> result = new ArrayList<>();
        if(flag){
            result.add(Integer.toString(a.GetMaxDepth()));
            result.add(Integer.toString(a.getNumExplored()));
            result.add(Integer.toString(a.getSolcost()));
            result.add(a.getTime());
            result.addAll(a.getSolPath());
        }else{
            result.add("failed");
        }
        return result;
    }
    @GetMapping("/Manhattan")
    public ArrayList<String> AstarManhattan(@RequestParam String initialState){
        Astar a = new Astar();
        flag=a.solve(initialState,"012345678",false);
        ArrayList<String> result = new ArrayList<>();
        if(flag){
            result.add(Integer.toString(a.GetMaxDepth()));
            result.add(Integer.toString(a.getNumExplored()));
            result.add(Integer.toString(a.getSolcost()));
            result.add(a.getTime());
            result.addAll(a.getSolPath());
        }else{
            result.add("failed");
        }
        return result;
    }
}
