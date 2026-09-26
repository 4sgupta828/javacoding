// Phase 7 EXERCISE — a team scoreboard. 📋
// Keep a roster of team names in an ArrayList, and each team's score in a
// HashMap. Then print the scoreboard and find who's in the lead.
//
// Run:   java Scoreboard.java
// Check your work against exercises/solutions/Scoreboard.java when done.

import java.util.ArrayList;
import java.util.HashMap;

public class Scoreboard {
    public static void main(String[] args) {

        // The roster: an ArrayList of team names.
        ArrayList<String> teams = new ArrayList<>();
        // TODO 1: add three team names to 'teams' with teams.add("...")
        //         e.g. "Gears", "Bolts", "Sparks"

        // The scores: a HashMap from team name -> score.
        HashMap<String, Integer> scores = new HashMap<>();
        // TODO 2: put a score for each team with scores.put("Gears", 42), etc.
        //         Use the SAME names you added to the roster.

        // TODO 3: print each team and its score, using a for-each loop over 'teams'.
        //         Inside the loop, get the score with scores.get(team).
        //         Print something like:   Gears: 42

        // TODO 4: find the leader (the team with the highest score) and print it.
        //         Hint: start by assuming the first team leads (teams.get(0)),
        //         then loop and update 'leader' whenever you find a bigger score.

        // STRETCH: add a new team mid-game (add to BOTH the list and the map),
        //          then re-print. Notice the ArrayList grew without you resizing anything.
    }
}
