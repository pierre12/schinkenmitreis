# Mine Sweeper
![alt text](https://media.tenor.com/RA1wR8-1K-AAAAAM/corre-run.gif)

UI
- A grid of cells (16x16, 30x30)
  - A cell can have multiple status:
    - covered
    - uncovered mine
    - uncovered cell
    - uncovered cell with hint of number of surrounding mines
    - markedAsMine
    - unknown
- A user can interact with the grid by clicking on a cell
    - left click: if cell is covered the cell status changes to uncovered; else nothing happens
    - right click: covered cell changes to status markedAsMine, twice cell with status markedAsMine changes to unknown, thrice cell with status unknown changes to covered
- Displays the remaining covered mines
- Shows a timer for the current duration of the current game
- Implement UI with Apache Pivot

Logic
- Creates a grid of cells with a set number of mines randomly distributed depending on the size of the grid
  -  (16x16 = 20 Mines, 30x30= 40 Mines)
- Uncovering a minefield leads to a loss
- First click onto the grid has to be safe
- if an uncovered cell is clicked all connected cells are uncovered until the border consists of uncovered cells with hint.
- every game should be completely solvable without a luck factor. -> could be very hard to implement
- if the grid only consists of covered mines the game is won

- 100% test coverage is expected