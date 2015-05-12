# Connect-Five
Artificial intelligence for a connect-five board game.

Also known as [Gomoku](http://en.wikipedia.org/wiki/Gomoku) or [Renju](http://en.wikipedia.org/wiki/Renju). Two players alternate in placing their color on an empty square. The winner is the first player to get an unbroken row of five stones horizontally, vertically, or diagonally.

**Disallowed moves**  
There are certain moves that the opening player is not allowed to make:
* **Double three** – The opening player cannot place their color that builds two separate lines with three colors in unbroken rows (i.e. rows not blocked by the opposing player's color).
* **Double four** – The opening player cannot place their color that builds two separate lines with four colors in a row.
* **Overline** – six or more colors in a row.

**Winning**  
The opening player can win the game only by placing five colors in a row (vertically, horizontally or diagonally).
The opposing player can win by either:
* Getting five (or more) colors in a row
* Forcing the opening player to make a forbidden move (see above)

### Algorithms used
The human (opening player) plays against the computer (the opposing player). AI algorithms used to calculate the best value move are:
* [Minimax](http://en.wikipedia.org/wiki/Minimax#Minimax_algorithm_with_alternate_moves) - A recursive algorithm that  minimizes the possible loss for a worst case (maximum loss) scenario. This value is computed by means of a position evaluation function and it indicates how good it would be for a player to reach that position. The player then makes the move that maximizes the minimum value of the position resulting from the opponent's possible following moves.
* [Alpha-beta pruning](http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) - A search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree. It stops completely evaluating a move when at least one possibility has been found that proves the move to be worse than a previously examined move. It returns the same move as minimax would, but prunes away branches that cannot possibly influence the final decision.
* [Heuristic evalation function](http://en.wikipedia.org/wiki/Evaluation_function) - A function used to estimate the value of a position in the minimax algorithm. The function looks only at the current position and does not explore possible moves (therefore static).

### Prerequisites
* Java JDK 1.7 or higher  
For convenience, you should add the `/bin` directory to the `PATH` environment variable.

### Getting Started
Clone the repository and `cd` into the same directory.  
`$ git clone http://github.com/rchen8/Connect-Five.git`  
`$ cd Connect-Five`  
While in the same directory, compile **Game.java**  
`$ javac Game.java`  
To run the game  
`$ java Game`  

### Credits
Created by Richard Chen  
Email: richardchen39@gmail.com

Please feel free to contact me if you have any questions or suggestions for improvements.
