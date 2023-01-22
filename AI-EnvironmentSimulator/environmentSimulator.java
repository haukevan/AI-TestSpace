import java.util.HashMap; // import the HashMap class

class MyClass {
    public static void main(String args[]) {
        char current_location = args[0].charAt(0);
        
        //state: true -> clean, falst -> not clean
        boolean status_A = Boolean.parseBoolean(args[1]);
        boolean status_B = Boolean.parseBoolean(args[2]);
        boolean status_C = Boolean.parseBoolean(args[3]);
        boolean status_D = Boolean.parseBoolean(args[4]);

        System.out.println("Current Location: " + current_location + "\n" + 
                        "Square-A Status: " + status_A + "\n" +
                        "Square-B Status: " + status_B + "\n" +
                        "Square-C Status: " + status_C + "\n" +
                        "Square-D Status: " + status_D + "\n");

        //determine direct neighbours to current location

        //function to get next location
        char next_location = nextLocation(current_location, status_A, status_B, status_C, status_D);

        System.out.println("Action - Next Location = " + next_location);

    }

    static char nextLocation(char current, boolean a, boolean b, boolean c, boolean d) {
        //create numeric list of 0, 1, 2, 3 for A, B, D, C locations
        HashMap<Character, Integer> letterMap = new HashMap<Character, Integer>();
        letterMap.put('A', 1);
        letterMap.put('B', 2);
        letterMap.put('D', 3);
        letterMap.put('C', 4);

        HashMap<Integer, Character> numberMap = new HashMap<Integer, Character>();
        numberMap.put(1, 'A');
        numberMap.put(2, 'B');
        numberMap.put(3, 'D');
        numberMap.put(4, 'C');

        //allocate true or false (cleaned) values to locations 0, 1, 2, 3.
        HashMap<Integer, Boolean> cleanMap = new HashMap<Integer, Boolean>();
        cleanMap.put(1, a);
        cleanMap.put(2, b);
        cleanMap.put(3, d);
        cleanMap.put(4, c);

        //System.out.println(cleanMap.get(1));

        //check if all squares are clean, then return current
        if (a && b && c && d) { return current; }

        //check if current location is not clean, then clean it and return current
        if (cleanMap.get(letterMap.get(current)) == false ) {
            return current;
        }

        //get next and prev locations
        int next = addNext(letterMap.get(current));
        int prev = subNext(letterMap.get(current));

        //if next spot is dirty go there, else if prev spot is dirty go there
        if (cleanMap.get(next) == false) {
            return numberMap.get(next);
        } else if (cleanMap.get(prev) == false) {
            return numberMap.get(prev);
        }

        //if both neighbour locations are clean, then a diagonal is dirty, move to neighbour location
        if (cleanMap.get(next) == true && cleanMap.get(prev) == true) {
            return numberMap.get(next);
        }

        //failed case check
        return 'z';
    }

    static int addNext(int prev) {
        if (prev == 4) {
            return 1;
        } else {
            return prev + 1;
        }
    }

    static int subNext(int prev) {
        if (prev == 1) {
            return 4;
        } else {
            return prev - 1;
        }
    }
}