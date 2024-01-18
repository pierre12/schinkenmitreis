# Mine Sweeper
![alt text](https://media.tenor.com/RA1wR8-1K-AAAAAM/corre-run.gif)

UI
- A grid of fields (16x16, 30x30)
  - A field can have multiple status:
    - covered
    - uncovered mine
    - uncovered field
    - uncovered field with hint of number of surrounding mines
    - markedAsMine
    - unknown
- A user can interact with the grid by clicking on a field
    - left click: if field is covered the field status changes to uncovered; else nothing happens
    - right click: covered field changes to status markedAsMine, twice field with status markedAsMine changes to unknown, thrice field with status unknown changes to covered
- Displays the remaining covered mines
- Shows a timer for the current duration of the current game
- Implement UI with Apache Pivot

Logic
- Creates a grid of fields with a set number of mines randomly distributed depending on the size of the grid
  -  (16x16 = 20 Mines, 30x30= 40 Mines)
- Uncovering a mine field leads to a lose
- First click onto the grid has to be safe
- if a uncovered field is clicked all connected fields are uncovered until the border consists of uncovered fields with hint.
- every game should be completely solvable without a luck factor. -> could be very hard to implement
- if the grid only consists of covered mines the game is won

- 100% test coverage is expected