# A Java text Adventure Game

I am working through how to build text-games in Java.  I am using Huw Collingbourne's book, "The Little Book of Adventure Game Programming in Java" as my guide.  This is Huw's program from the book, as I build it.  I am making some minor changes as I go on to fit how I think it should work.

Right now, the player can move from room to room and back.  I added a `HashMap<Direction, Room>` to the `Room` class so that movement can occur in a direction and know which room is on the other side.  This allows movement towards a goal and back from the goal, or even sidequest moves.

Currently working on Treasures and a Bag of Holding.

##  Update for July

The game now has a map and the player can move from room to room and back.  In each room, the player will get a description of items in the room.  None are hidden.  The player can pick up and drop any items they can see.  Next, will be an inventory listing command for the player's Bag Of Holding.  Then onward to saving and loading games.

I have cleaned up the game process with reduction in code and allow better/flexible command wording.

Added player bagOfHolding showInventory feature.

At this point, the game will show room features (items), move from room to room, take items, drop items, and show player inventory.

Next, before moving on, I need to initiate a hint() method.  

The game now has a save and load feature, which only saves the current game, not multiple games.


