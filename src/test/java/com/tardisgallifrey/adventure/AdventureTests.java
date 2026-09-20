package com.tardisgallifrey.adventure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.tardisgallifrey.adventure.utils.*;

public class AdventureTests{

 
        @Test
        void testThingIsNotNull( ) {
                Thing thing = new Thing( "rock", " well worn rock.", true, true );

                assertEquals( "rock", thing.getName( )  );
        }

        @Test
        void testFindThing( ){
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );
                
                Player newPlayer = new Player("Bob", "a pleasant sort", null, inventory);
                Thing found = newPlayer.getBag( ).thisObj( "carrot" );  
                assertEquals( "carrot", found.getName( )); 
                assertNotEquals( "rock", found.getName( )); 

        }

        @Test
        void testIsThingInInventory( ){
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );
                
                Player newPlayer = new Player("Bob", "a pleasant sort", null, inventory);

                assertTrue( newPlayer.isThinginInventory("carrot") );
                assertFalse( newPlayer.isThinginInventory("rock") );
        }

        @Test
        void testIsThingInRoom( ){
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );
                
                Room aRoom = new Room("Hall", "a long hall", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, inventory);
                Player newPlayer = new Player("Bob", "a pleasant sort", aRoom, null);

                assertTrue( newPlayer.isThinginRoom("carrot") );
                assertFalse( newPlayer.isThinginRoom( "rock" ) );
        }

        @Test
        void testIsThingHere( ){
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );

                ThingList room = new ThingList( );
                room.add( new Thing( "sword", "a sharp sword", true, true ) );
                room.add( new Treasure( "diamond", "shiny", 1000 ) );

                
                Room aRoom = new Room("Hall", "a long hall", 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT,
                                room );

                Player newPlayer = new Player("Bob", "a pleasant sort", aRoom, inventory);

                assertTrue( newPlayer.isThingHere( "carrot" ) );
                assertFalse( newPlayer.isThingHere( "rock" ) );
                assertTrue( newPlayer.isThingHere( "diamond" ) );
                assertFalse( newPlayer.isThingHere( "wombat" ) ); 

        } 
 

        @Test
        void testTakeObjectFromThingList( ){
                CmdObj command = new CmdObj("", "sword", "", "", "");
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );

                ThingList chest_inventory = new ThingList(  );
                chest_inventory.add( new Thing( "key", " it opens something", true, true) );
                chest_inventory.add( new Thing( "jacket", " a warm leather jacket", true, true ) ); 
                ContainerThing chestWood = new ContainerThing( "chest", " a wooden chest", chest_inventory, false, false, true, false);
                
                ThingList room = new ThingList( );
                room.add( new Thing( "sword", "a sharp sword", true, true ) );
                room.add( new Treasure( "diamond", "shiny", 1000 ) );
                room.add( chestWood );  

                
                Room aRoom = new Room("Hall", "a long hall", 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT,
                                room );

                Player newPlayer = new Player("Bob", "a pleasant sort", aRoom, inventory);
                newPlayer.takeObj( command, room ); 
                command = new CmdObj("", "chest", "", "", "");
                newPlayer.openObject( command ); 

                assertTrue( newPlayer.isThingHere( "key" ) );
                assertTrue( newPlayer.isThinginInventory( "sword" ));
                assertFalse( newPlayer.isThinginRoom( "sword" ) ); 

                 
        }

        @Test
        void testDropObjectintoRoomList( ){
                CmdObj command = new CmdObj("", "shirt", "", "", "");
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );

                ThingList room = new ThingList( );
                room.add( new Thing( "sword", "a sharp sword", true, true ) );
                room.add( new Treasure( "diamond", "shiny", 1000 ) );

                
                Room aRoom = new Room("Hall", "a long hall", 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT,
                                room );

                Player newPlayer = new Player("Bob", "a pleasant sort", aRoom, inventory);
                newPlayer.dropObj(command, inventory); 

                assertTrue( newPlayer.isThingHere( "shirt" ) );
                assertTrue( newPlayer.isThinginRoom( "shirt" ) ); 
                assertFalse( newPlayer.isThinginInventory( "shirt" ));
        }

        @Test
        void testOpenContainer( ){
                CmdObj command = new CmdObj("", "chest", "", "", "");
                ThingList inventory = new ThingList( );
                inventory.add( new Thing("carrot", "a tasty carrot", true, true) );
                inventory.add( new Thing( "shirt", "a nice satin shirt", true, true) );
                inventory.add( new Treasure( "ruby", "a large red ruby", 400) );

                ThingList chest_inventory = new ThingList(  );
                chest_inventory.add( new Thing( "key", " it opens something", true, true) );
                chest_inventory.add( new Thing( "jacket", " a warm leather jacket", true, true ) ); 
                ContainerThing chestWood = new ContainerThing( "chest", " a wooden chest", chest_inventory, false, false, true, false);

                ThingList room = new ThingList( );
                room.add( new Thing( "sword", "a sharp sword", true, true ) );
                room.add( new Treasure( "diamond", "shiny", 1000 ) );
                room.add( chestWood ); 

                
                Room aRoom = new Room("Hall", "a long hall", 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT, 
                                Direction.NOEXIT,
                                room );

                Player newPlayer = new Player("Bob", "a pleasant sort", aRoom, inventory);

                assertFalse( newPlayer.isThingHere( "key" ) ); 
                newPlayer.openObject(command);
                assertTrue( newPlayer.isThingHere( "key" ) ); 


        }

}
