#Environment Simulator for Vaccuum Cleaning Robot
Java program to simulate the behaviour of a model-based agent for a vacuum cleaner environment based on the following conditions:

The vacuum cleaner can move to one of 4 squares: A, B, C, or D as shown in Table 1.

Table 1: vacuum cleaner environment
| col1 | col2 |
| ---- | ---- |
| A | B |
| C | D |

* The vacuum cleaner checks the status of all squares and takes action based on the following order:
* If all squares are clean, the vacuum cleaner stays in its current location.
* If the current location is not clean, the vacuum cleaner stays in its current location to clean it up.
* The vacuum cleaner can only move horizontally or vertically (cannot move diagonally).
* The vacuum cleaner moves only one square at a time.
* Horizontal moves have the highest priority over vertical moves.

The vacuum cleaner moves to another square only when it needs to be cleaned up. If a diagonal square needs to be cleaned up, the vacuum cleaner moves to its neighbour vertical square first.

The vacuum cleaner action is evaluated based on the current location and the status of all squares.

The program evaluates only one action (one move) at each run.

Example Program

| Current Location | A-status | B-status | C-status | D-status | Action (Stay in or Move to) |
| ---------------- | :------: | :------: | :------: | :------: | --------------------------: |
| A | Clean | Clean | Clean | Clean | A |
| B | Not Clean | Clean | Clean | Clean | A |
| B | Not Clean | Clean | Clean | Not Clean | A |
