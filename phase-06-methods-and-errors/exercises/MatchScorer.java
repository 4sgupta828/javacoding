// Phase 6 CAPSTONE EXERCISE — the FTC Match Scorer. 🏆
// Compute a match score from the things a robot did, using METHODS.
// Each scoring rule gets its own small method with parameters and a return value.
//
// This uses a SIMPLIFIED, made-up scoring model (real FTC games change every year):
//   AUTONOMOUS period:
//     - each sample scored          = 4 points
//     - parked at the end of auto   = 3 points (a boolean: true/false)
//   TELE-OP period:
//     - each sample scored          = 2 points
//     - each specimen scored        = 5 points
//   ENDGAME:
//     - ascent level "high"         = 15 points
//     - ascent level "low"          = 3  points
//     - anything else               = 0  points
//
// Run:   java MatchScorer.java
// Check your work against exercises/solutions/MatchScorer.java when done.

public class MatchScorer {

    // TODO 1: write autoScore(int samples, boolean parked) that returns
    //         samples * 4, plus 3 more if parked is true.
    //         Hint: return samples * 4 + (parked ? 3 : 0);

    // TODO 2: write teleOpScore(int samples, int specimens) that returns
    //         samples * 2 + specimens * 5.

    // TODO 3: write endgameScore(String ascent) that returns
    //         15 if ascent equals "high", 3 if it equals "low", else 0.
    //         Remember: compare Strings with .equals(), NOT ==  (Phase 3!).

    // TODO 4: write totalScore(int auto, int teleop, int endgame) that
    //         returns the three added together.

    public static void main(String[] args) {

        // TODO 5: call your methods to score this match, then print each part
        //         and the total. Example values to try:
        //           auto:    3 samples, parked = true
        //           tele-op: 5 samples, 2 specimens
        //           endgame: "high"
        //
        // Expected with those numbers:
        //   Auto: 15   TeleOp: 20   Endgame: 15   TOTAL: 50

    }
}
