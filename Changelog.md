7/11/26
    -- changed ThingList, Treasure, Thing, and Game to begin getting ready for taking and dropping items.

7/12/26
    -- finished takeObj and dropObj.  Wrote transferObj.  Fixed architecture to ensure that all methods produce messages that return up the logic tree so that the Main method owns all message output.  

7/19/26
    -- added inventory feature to game and filled out showInventory() player method
    -- refactored runCommand(), processMove(), and processCommand() to allow for smoother command lookups and more flexible wording for player.

8/3/26
    -- added save and load features.
    -- added simple hint
8/23/26
    -- added ContainerThing for chests, sacks
    -- added open verb
8/29/26
    -- refactored from processCommand( verb, noun ) to processCommand( CmdObj command )
    -- added CmdObj object to pass verb noun ( and future words ) 
    


 
