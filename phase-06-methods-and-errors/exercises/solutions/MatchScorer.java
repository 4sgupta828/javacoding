// Phase 6 CAPSTONE — worked solution: the FTC Match Scorer.
// Run:   java MatchScorer.java

public class MatchScorer {

    // AUTONOMOUS: 4 points per sample, +3 if parked.
    static int autoScore(int samples, boolean parked) {
        return samples * 4 + (parked ? 3 : 0);
    }

    // TELE-OP: 2 points per sample, 5 points per specimen.
    static int teleOpScore(int samples, int specimens) {
        return samples * 2 + specimens * 5;
    }

    // ENDGAME: points depend on how high the robot climbed.
    static int endgameScore(String ascent) {
        if (ascent.equals("high")) {      // .equals for Strings, not ==
            return 15;
        } else if (ascent.equals("low")) {
            return 3;
        } else {
            return 0;
        }
    }

    // Add the three periods together.
    static int totalScore(int auto, int teleop, int endgame) {
        return auto + teleop + endgame;
    }

    public static void main(String[] args) {

        int auto    = autoScore(3, true);       // 3*4 + 3  = 15
        int teleop  = teleOpScore(5, 2);         // 5*2 + 2*5 = 20
        int endgame = endgameScore("high");      // 15
        int total   = totalScore(auto, teleop, endgame);   // 50

        // A tiny bit of logging, in the spirit of our Log class:
        System.out.println("[INFO] Auto:    " + auto);
        System.out.println("[INFO] TeleOp:  " + teleop);
        System.out.println("[INFO] Endgame: " + endgame);
        System.out.println("[INFO] TOTAL:   " + total);
    }
}
