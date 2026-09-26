// Phase 7 — ArrayList (a list that grows) and HashMap (a lookup table).
// Run:   java ListsAndMaps.java

import java.util.ArrayList;
import java.util.HashMap;

public class ListsAndMaps {
    public static void main(String[] args) {

        // ---- ArrayList: like an array, but it GROWS and SHRINKS. ----
        // The <String> says "this list holds Strings".
        ArrayList<String> team = new ArrayList<>();
        team.add("Ada");         // add to the end
        team.add("Grace");
        team.add("Alan");

        System.out.println("Team size: " + team.size());   // 3 (size() is a method here)
        System.out.println("First:     " + team.get(0));    // Ada (get(index), not [0])

        // for-each works on lists too:
        System.out.println("Everyone:");
        for (String name : team) {
            System.out.println("  " + name);
        }

        team.remove("Alan");     // remove by value
        System.out.println("After removing Alan, size: " + team.size());   // 2

        // ---- HashMap: look something up by a KEY (like a dictionary). ----
        // <String, Integer> = keys are Strings, values are Integers.
        HashMap<String, Integer> points = new HashMap<>();
        points.put("Ada", 42);       // key "Ada" -> value 42
        points.put("Grace", 55);
        System.out.println("Ada's points: " + points.get("Ada"));   // 42

        // Ask a map before trusting it — a missing key returns null:
        System.out.println("Alan's points: " + points.get("Alan")); // null (not in the map)

        // ---- The aliasing trap again, this time with an ArrayList ----
        ArrayList<String> listOne = new ArrayList<>();
        listOne.add("first");
        ArrayList<String> listTwo = listOne;   // NOT a copy — same list, two names
        listTwo.add("second");                 // add through listTwo...
        System.out.println("listOne size: " + listOne.size());  // ...listOne shows 2 as well!
    }
}
