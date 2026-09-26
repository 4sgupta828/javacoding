// Phase 7 EXERCISE — worked solution: a team scoreboard.
// Run:   java Scoreboard.java

import java.util.ArrayList;
import java.util.HashMap;

public class Scoreboard {
    public static void main(String[] args) {

        // The roster.
        ArrayList<String> teams = new ArrayList<>();
        teams.add("Gears");
        teams.add("Bolts");
        teams.add("Sparks");

        // The scores, keyed by team name.
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Gears", 42);
        scores.put("Bolts", 31);
        scores.put("Sparks", 55);

        // Print the scoreboard.
        System.out.println("=== Scoreboard ===");
        for (String team : teams) {
            int score = scores.get(team);        // Integer auto-unboxes to int
            System.out.println(team + ": " + score);
        }

        // Find the leader: assume the first team leads, then look for anyone bigger.
        String leader = teams.get(0);
        for (String team : teams) {
            if (scores.get(team) > scores.get(leader)) {
                leader = team;
            }
        }
        System.out.println("Leader: " + leader + " (" + scores.get(leader) + ")");

        // ---- STRETCH: add a team mid-game ----
        teams.add("Volts");
        scores.put("Volts", 60);
        System.out.println("=== After adding Volts ===");
        for (String team : teams) {
            System.out.println(team + ": " + scores.get(team));
        }
        // The list grew from 3 to 4 with no resizing — that's the ArrayList advantage.
    }
}
