# A Java text Adventure Game

I am working through how to build text-games in Java.  I am using Huw Collingbourne's book, "The Little Book of Adventure Game Programming in Java" as my guide.  This is Huw's program from the book, as I build it.  I am making some minor changes as I go on to fit how I think it should work.

Right now, the player can move from room to room and back.  I added a `HashMap<Direction, Room>` to the `Room` class so that movement can occur in a direction and know which room is on the other side.  This allows movement towards a goal and back from the goal, or even sidequest moves.

Currently working on Treasures and a Bag of Holding.

