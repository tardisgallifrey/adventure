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
                Thing found = newPlayer.findThing( "carrot", newPlayer.getBag( ) );  
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
 
 

                

}
