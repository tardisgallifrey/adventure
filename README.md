[![Adventure Release - Windows status](https://github.com/tardisgallifrey/adventure/actions/workflows/release.yaml/badge.svg)](https://github.com/tardisgallifrey/adventure/actions/workflows/release.yaml)

[![Adventure Release - Linux DEB](https://github.com/tardisgallifrey/adventure/actions/workflows/releasedeb.yaml/badge.svg)](https://github.com/tardisgallifrey/adventure/actions/workflows/releasedeb.yaml)

[![Adventure Release - Linux RPM](https://github.com/tardisgallifrey/adventure/actions/workflows/releaserpm.yaml/badge.svg)](https://github.com/tardisgallifrey/adventure/actions/workflows/releaserpm.yaml)

# A Java text Adventure Game -- V0.8

I am working through how to build text-games in Java.  I am using Huw Collingbourne's book, "The Little Book of Adventure Game Programming in Java" as my guide.  This is Huw's program from the book, as I build it.  I am making some minor changes as I go on to fit how I think it should work.

Right now, the player can move from room to room and back.  I added a `HashMap<Direction, Room>` to the `Room` class so that movement can occur in a direction and know which room is on the other side.  This allows movement towards a goal and back from the goal, or even sidequest moves.

Completed Treasures and the Bag of Holding ( inventory ).

Completed simple save and load game feature.

Completed simple hint feature.

Completed take, move, features.

Finished book, major architecture change ongoing.

For **Installation and Game Play** help see the **TLDR** entry below.

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

I spent several recent weekends working on just the packaging, versioning, and releasing of the game.  I had originally begun this work on [Codeberg]( https://codeberg.org/tardisgallifrey ), but have become convinced that either I have no idea how WoodPecker CI works or that WoodPecker CI does not work.  So, I am back here on Github and things are now working.  

I have decided upon how to complete the game engine before actual game construction.  I will use what the author set up for chests, sacks, and other containers.  It is a `ContainerThing` class.  I plan to extend this class into a new class that will have a limited number of slots, and some methodology to ensure that only certain identified `Thing`'s will go in order into the container.  In the `Game` class, completion of this special container class with all of its required items will determing game completion.

### TLDR, I want to play the game

##### To Install on Windows as a Zip file

Click on the **Releases** link for the latest release.  There will be a link there in Releases to download `windows-adventure.zip`.  It will download into your Downloads folder.  Extract the folder in the zip and place it wherever you like in your Windows file system.  Open the `adventure` folder and double clik `run-game.bat`.  It should launch a `cmd` window and begin the game.

##### To Install on Linux with a .deb file

Download the `.deb` package and run `sudo apt-get install adventure-X.X.amd64.deb`.  You should be able to search your menus for `adventure` and start the game.

##### To Install on Linux with a .rpm file

Download the `.rpm` package and run `sudo dnf install adventure-X.X.x86_64.rpm`.  You should be able to search your menus for `adventure` and start the game.

##### Run the adventure.jar 

You may also download the `jar` file directly to your Linux or Windows file system and run `java -jar adventure.jar`.  You do need Java 21 installed at minimum.

##### Game Play

The game prompt is waiting for you to enter commands.  Most commands will require a verb and a noun.  Things such as `look around`, `go north`, `take object`, or `drop object` will work.  You may also `check inventory` to see what you have.  However, when you encounter chests, sacks, or similar, you will need to `open object` or sometimes `close object`.  Finally, if you want to take something out of a container, you will need a verb, object, preposition, and the object of the preposition.  So,  you might have to say, `take sword out of container` or `put something into container`; maybe even `take something from container`.  The only single word commands right now are `save` and `load`; which will save the game and load a saved game. 

There are only four rooms and a limited number of items at the moment.  You can open the chest, remove items, or put items in the chest.  Beware of forgetting to tell where to put items *in* or *into*.  Please add an issue if you find a problem.  I welcome any bug fixes.  Sorry, feature adds right now will be ignored until I reach V1.0. 

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

