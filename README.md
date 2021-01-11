# Snakes and Ladders

## CPSC 210 Personal Project

### Proposal 

This application will generate an interactive game of **Snakes and Ladders**. The user will be playing against the 
computer; whoever reaches the top of the board first will win. A dice will determine how many spaces a player may move 
per turn. If a player lands on the top of a snake, they will be forced to move *down* the board; their new position will 
be at the bottom of the snake. If a player lands on the bottom of a ladder, they get to move *up* the board and claim 
the position at the top of the ladder.

This application can be used by anyone who wants to play a game of **Snakes and Ladders** without having somebody to 
play against or owning the original board game. I am interested in creating this project because it was my favourite 
game as a kid, so I am excited to create my own virtual version.


### User Stories

- As a user, I want to add my player to the board
- As a user, I want to be able to add multiple players to the board
- As a user, I want to be able to slide down a snake
- As a user, I want to be able to climb up a ladder
- As a user, I want to be able to win the game
- As a user, I want to be able to roll the dice
- As a user, I want to be able to move on the board
- As a user, I want to be able to save the score
- As a user, I want to be able to load the scores from the file


### Phase 4: Task 2

I chose the option *test and design a package in your model class that is robust.* The player class has a constructor
with a robust design. The player constructor initializes a player by assigning it the name that was passed in as a
parameter, and assigns the player to the first position of the board. The user gets to choose the name that the player is 
assigned; if they do not input a name, the constructor will throw an InvalidNameException.


### Phase 4: Task 3

- The snake and ladder classes are very similar, to improve this I would introduce a type hierarchy.
- In the UI package, in multiple classes (SnakePanel, LadderPanel, PlayerPanel, SnakesAndLadders), there are methods 
which generate icons, these methods are very similar. They could be refactored to decrease coupling. To refactor, I
would extract a method, then pass the file name and desired dimensions in as parameters.
- In the SnakesAndLadders class, the methods which take turns for each player are very similar. I could extract methods
and pass the player in as a parameter to reduce coupling.
- In the SnakesAndLadders class, each player has a method that checks if they won and performs an action if said player
reached the top of the board (won the game). These methods are all pretty much the same; to reduce this coupling, I 
would extract a method and pass in the player as a parameter.
- In the PlayerPanel class, the methods adding each player to the board are very similar. I could fix this by extracting 
a method which takes in the player as a parameter.