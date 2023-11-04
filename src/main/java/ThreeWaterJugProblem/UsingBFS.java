package ThreeWaterJugProblem;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
        
public class UsingBFS {
    public static int MAX_JUG1, MAX_JUG2, MAX_JUG3, GOAL;
    
    public static Queue<Vertex> queue = new LinkedList<>();
    
    public static Set<Vertex> visited = new HashSet<Vertex>(){
        public boolean contains(Object obj) {
        	
            Vertex vertex = (Vertex) obj;
            for (Vertex v : this) {
                if ((vertex.equals(v)) && (vertex.getPath().equals(v.getPath()))) {
                    return true;
                }
            }
        
            return false;
        }
    };
    
    public static void main(String[] args) {
        MAX_JUG1 = 8;
        MAX_JUG2 = 5;
        MAX_JUG3= 3;
        GOAL = 4;
        int count = 1;
        
        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2,MAX_JUG3);

	Vertex initialVertex = new Vertex(new State(0,0, 0));
        queue.add(initialVertex);      
        visited.add(initialVertex);
        
	while(!queue.isEmpty()){             
            Vertex currentVertex = queue.poll();                                              
            currentVertex.addToPath();
            
            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL || currentVertex.getState().getJug3() == GOAL){
                System.out.print(count + " ");
            	currentVertex.printPath();   
                count++;
                currentVertex.getPath().forEach((s) -> {                    
                    visited.remove(s);                                                         
                });
                
                continue;
            }            
            
            ArrayList<Vertex> newVertices = new ArrayList<>();
            
            newVertices.add(currentVertex.full_jug1());
            newVertices.add(currentVertex.full_jug2());
            newVertices.add(currentVertex.full_jug3());
            
            newVertices.add(currentVertex.empty_jug1()); 
            newVertices.add(currentVertex.empty_jug2());
            newVertices.add(currentVertex.empty_jug3());

            newVertices.add(currentVertex.pour_jug1_jug2());
            newVertices.add(currentVertex.pour_jug1_jug3());

            newVertices.add(currentVertex.pour_jug2_jug1());
            newVertices.add(currentVertex.pour_jug2_jug3());

            newVertices.add(currentVertex.pour_jug3_jug1());
            newVertices.add(currentVertex.pour_jug3_jug2());
            
            for (Vertex newVertex : newVertices){                                                                
                if(!currentVertex.getPath().contains(newVertex)){
                    newVertex.setPath(currentVertex.getPath());
                    
                    if (!visited.contains(newVertex))
                        queue.add(newVertex);                   
                    visited.add(newVertex);                                        
                }                                             
            }                       
	}
	
    }  
}
