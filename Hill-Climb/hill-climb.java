/*
 * Author: Evan Hauk
 * Date: Feb 15, 2023
 * 
 * 
 * 
 * Purpose: Test the Hill-Climb Algorythm
 * 
 */

import java.util.*;

 class MyClass {
     //write program to implement search
     List <Character> hill_search(Character [] sO, Character [] sG) {

        Boolean solved = false;

        List<Character> sO_list = new ArrayList<Character>();
        sO_list.addAll(Arrays.asList(sO));

        List<Character> sG_list = new ArrayList<Character>();
        sG_list.addAll(Arrays.asList(sG));

        int [] h = {0, 0, 0, 0, 0, 0, 0, 0};

        while (!solved) {

            //check for goal state and exit loop if so
            if (sO_list.equals(sG_list)) {
                solved = true;
                continue;
            }

            //get initial h
            for (int i = 0; i < sO_list.size(); i++) {
                //calculat m index at each
                if (sO_list.get(i).equals(sG_list.get(i))) {
                    h[i] = 1;
                } else {
                    h[i] = -1;
                }
            }
            //else start hill climbing
            for (int i = 0; i < sO_list.size(); i++) {
                //calculat m index at each
                if (sO_list.get(i).equals(sG_list.get(i))) {
                    h[i] = 1;
                } else {
                    h[i] = -1;
                }

                //if m(i) is -1, swap with index i+1
                if ()

                //


            }
            
        }


        return (sO_list);
     }


 //----------------------------MAIN---------------------------------------
    public static void main(String args[]) {

        Character [] sO = {'A', 'L', 'O', 'P', 'S', 'O', 'K', 'M'};
        Character [] sG = {'K', 'A', 'M', 'L', 'O', 'O', 'P', 'S'};

        MyClass path = new MyClass();

        //get arrayList of Characters from function
        List <Character> result = path.hill_search(sO, sG);

        //print out ArrayList
        System.out.print(result);

    }

}
        