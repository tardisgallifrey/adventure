# A Java text Adventure Game -- V0.8

I am working through how to build text-games in Java.  I am using Huw Collingbourne's book, "The Little Book of Adventure Game Programming in Java" as my guide.  This is Huw's program from the book, as I build it.  I am making some minor changes as I go on to fit how I think it should work.

Right now, the player can move from room to room and back.  I added a `HashMap<Direction, Room>` to the `Room` class so that movement can occur in a direction and know which room is on the other side.  This allows movement towards a goal and back from the goal, or even sidequest moves.

Completed Treasures and the Bag of Holding ( inventory ).

Completed simple save and load game feature.

Completed simple hint feature.

Completed take, move, features.

Finished book, major architecture change ongoing.

##  Update for August 2026 

The game now has a map and the player can move from room to room and back.  In each room, the player will get a description of items in the room.  None are hidden.  The player can pick up and drop any items they can see.  Next, will be an inventory listing command for the player's Bag Of Holding.  Then onward to saving and loading games.

I have cleaned up the game process with reduction in code and allow better/flexible command wording.

Added player bagOfHolding showInventory feature.

At this point, the game will show room features (items), move from room to room, take items, drop items, and show player inventory.

The game now has a save and load feature, which only saves the current game, not multiple games.

To get a hint, type 'h' or 'hint' at the prompt.  

## Update for September 2026

I reached the end of *Collingbourne's* book.  I have had to make a major architecture decision.  I agree somewhat with his concepts, but not the actions.  He seemed to be writing as everything was about to become a ThingHolder class, which had not been the case up to this point.  Player and Room classes do not act like containers.  They may contain containers, which is part of the issue.  

In order to come to a conclusion and direction which I could handle, I decided to finish the book, put it away and go forward.  Every class that holds something has a ThingList.  Players have a ThingList ( inventory ).  Rooms have a Thinglist ( things ).  All other containers that are ThingHolder class also contain ThingLists ( chests, sacks, bowls, whatever ).  Additionally, Players are Things, Rooms are Things, and containers are Things ( ThingHolder extends Thing ).  Yet, ThingHolder has properties that don't go up the class hierarchy, namely open/close; *the ability for its ThingList to be hidden from view*.

So, I am going to move torwards using the ThingList as the base container class and devise ways to sort out what I am looking into for items.  

The author also is moving towards a vocabulary method of determining user commands from user input.  I have begun this and will extend it.  Rather than a list of two words returned from the parser, we will now return a CmdObject record which can contain multiple words.  Every process can pass along the object and methods can use what they need and avoid the rest.  This opens up the use of preposiitons ( *look in*, *put in* ) and adjectives ( *wood chest* versus *silver chest* ).

Finally, we will move toward the Player class being responsible for player actions ( take, drop, look, view, etc. ).  Motion will likely remain in Game for now as it is responsible for map position of the character.

### Update for October 2026

I have finished the work to take things from a container or put things into a container.  I have also determined versioning up to V1.0.  I am at V0.8.  I need to add one more item to the game and that is the puzzle that will determine *end of game*.  When that is complete, the game will be at V0.9.  V1.0 will be achieved when I develop a real narrative story and add that into the Game class initialization.  At that point, I will stop and work on some other projects before adding new features.  Testing will continue for a bit, however.

I am working on setting up CI with Codeberg so that I can produce an *msi* and *app-image* output for the game.  Microsoft has struck again and it is extremely difficult to download the jar file to Windows.

### TLDR, I want to play the game

You may download just the `jar` file to play if you don't wish to use the rest of the game.  The file is `adventure.jar` in the root folder above.  It will play on Linux or Windows with `java -jar adventure.jar`.  There are only four rooms and a limited number of items at the moment.  You can open the chest, remove items, or put items in the chest.  Beware of forgetting to tell where to put items *in* or *into*.  Please add an issue if you find a problem.  I welcome any bug fixes.  Sorry, feature adds right now will be ignored until I reach V1.0. 

### Some game commands

* look
* take
* open
* hint
* go
* save
* load
* check

Combine the commands ( verbs ) with nouns that you can see or know ( south, north, key, sword ) and see where you can go.

