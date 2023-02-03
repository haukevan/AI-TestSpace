/*
 * Author: Evan Hauk
 * Student ID: T00023763
 * Date: Jan 31, 2023
 * 
 * COMP3711 - Assignment 2
 * A* search algorithm
 * 
 */

import java.util.*;

class MyClass {
    //write program to implement search
    String a_star_search(int start, int goal, int[][]cost, int[] h) {
        
        int current = Integer.MAX_VALUE;
        int lowF = Integer.MAX_VALUE;
        int sucessor_current_cost = Integer.MAX_VALUE;
        int neighbour = Integer.MAX_VALUE;

        List <Integer> openSet = new ArrayList <>();
        List <Integer> closedSet = new ArrayList <>();
        List<Integer> neighbours = new ArrayList<>();
        HashMap<Integer, Integer> cameFrom = new HashMap<Integer, Integer>();

        //fScore will be updated after each entry to openSet?
        int[] fScore = new int[10];
        Arrays.fill(fScore, Integer.MAX_VALUE);

        //gScore array will be used to update current gScores of components from start
        //gScore between individual components will be evaluated from the cost matrix
        int[] gScore = new int[10];
        Arrays.fill(gScore, Integer.MAX_VALUE);

        //set initial gScore
        gScore[start] = 0;

        //set initial node to h start node
        fScore[start] = h[start];

        //add 0 position to openSet
        openSet.add(0);

        //count number of loop cycles to find goal and elapsed time
        long start_time = System.nanoTime();;
        int loops = 0;

        while(!openSet.isEmpty()) {
            loops++;
            //find lowest fScore in openSet list and set current to position
            lowF = Integer.MAX_VALUE;
            for (int i = 0; i < fScore.length; i ++) {
                if (fScore[i] < lowF) {
                    lowF = fScore[i];
                    current = i;
                }
            }

            //check if current is goal
            if(current == goal) {
                long finish_time = System.nanoTime();;
                long timeElapsed = finish_time - start_time;
                return reconstructPath(cameFrom, current, timeElapsed, loops);
            }

            //remove current from open set add to closed set
            openSet.remove(Integer.valueOf(current));

            //add item to closed set
            closedSet.add(current);

            //reset fScores from items in closed set
            fScore[current] = Integer.MAX_VALUE;

            //find node neighbours or successors
            for (int i = 0; i < cost.length; i++) {            
                if (cost[i][current] > 0) {
                    //get all neighbour positions
                    neighbours.add(i);
                }
            }

            //loop through neighbours
            for (int i = 0; i < neighbours.size(); i++) {
                //get each neighbour position
                neighbour = neighbours.get(i);

                //check if node neighbour successor is in closed list
                //we do not need to evaluate it because it has already been visited
                if (closedSet.contains(neighbour)) {
                    //continue to next loop cycle
                    continue;
                }
                
                //set current successor cost to gScore current + weight from current to sucessor
                sucessor_current_cost = gScore[current] + cost[neighbour][current];
                
                if (!openSet.contains(neighbour)) {
                    //add neighbour
                    openSet.add(neighbour);
                } else if (gScore[neighbour] <= sucessor_current_cost) {
                    continue;
                }

                //add map to show where neighbour came from current position
                cameFrom.put(neighbour, current);

                //update cumulative gScore for position
                gScore[neighbour] = sucessor_current_cost;

                //update new fScore for position
                fScore[neighbour] = gScore[neighbour] + h[neighbour];
            }

            //remove previous neighbours before going to next node
            neighbours.clear();

        }

        return "No Path Found";

    }

    static String reconstructPath(HashMap<Integer, Integer> cameFrom, int current, long timeSpent, int iterations) {

        HashMap <Integer, String> alphaMap = new HashMap<>();
        alphaMap.put(0, "A");
        alphaMap.put(1, "B");
        alphaMap.put(2, "C");
        alphaMap.put(3, "D");
        alphaMap.put(4, "E");
        alphaMap.put(5, "H");
        alphaMap.put(6, "J");
        alphaMap.put(7, "G1");
        alphaMap.put(8, "G2");
        alphaMap.put(9, "G3");

        String resultString = "\nGoal found at: " + current + "\nExecution time: " + timeSpent +
                        " [ns]\nLoop Iterations: " + iterations + "\nPath: ";

        ArrayList <Integer> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {

            current = cameFrom.get(current);
            totalPath.add(0, current);

        }

        for (int i = 0; i < totalPath.size(); i ++) {
            if (i < totalPath.size() - 1) {
                resultString += alphaMap.get(totalPath.get(i)) + " -> ";
            } else {
                resultString += alphaMap.get(totalPath.get(i));
            }
        }

        return resultString;
    }



    public static void main(String args[]) {
        
        //cost matrix where [1][0] would be 5 (route from 0 to 1)
        int [][] cost_matrix = {{0, 0, 0, 6, 1, 0, 0, 0, 0, 0},
                        {5, 0, 2, 0, 0, 0, 0, 0, 0, 0},
                        {9, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 2, 0, 0, 0, 0, 0},
                        {6, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                        {0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
                        {0, 9, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 5, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 8, 7, 0, 0, 0}};

        //heuristic vectors
        int [] heuristic_vector = {5, 7, 3, 4, 6, 8, 5, 0, 0, 0};

        //identify goal states and save in new vector
        int [] goal_states = {7, 8, 9};
        int start_pos = 0;

        MyClass path = new MyClass();
        String result = path.a_star_search(start_pos, goal_states[0],cost_matrix, heuristic_vector);
        System.out.println(result);

        result = path.a_star_search(start_pos, goal_states[1],cost_matrix, heuristic_vector);
        System.out.println(result);

        result = path.a_star_search(start_pos, goal_states[2],cost_matrix, heuristic_vector);
        System.out.println(result);
    }
}