package com.tardisgallifrey.adventure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.tardisgallifrey.adventure.utils.Direction;
import com.tardisgallifrey.adventure.utils.CmdObj;
import com.tardisgallifrey.adventure.utils.Parser;

public class Game implements Serializable{

        CmdObj cmd; 
        private ArrayList<Room> map;
        private Player player;
        public Game(){

                ThingList chest_inventory = new ThingList(  );
                chest_inventory.add( new Thing( "key", " it opens something", true, true) );
                chest_inventory.add( new Thing( "jacket", " a warm leather jacket", true, true ) ); 
                ContainerThing chestWood = new ContainerThing( "chest", " a wooden chest", chest_inventory, false, false, true, false);
                map = new ArrayList<>();
                ThingList forestList = new ThingList();
                ThingList trollList = new ThingList();
                ThingList caveList = new ThingList();
                ThingList dungeonList = new ThingList();
                dungeonList.add(new Treasure("ring", " a ring of great power", 600));
                dungeonList.add(new Thing("wombat", " a cuddly, furry wombat in the corner squeaking to itself", false, true));

                forestList.add(new Treasure("sword", " a decent short sword", 300));
                forestList.add(new Treasure("bow" , " a fine sturdy warrior bow", 300));
                forestList.add(new Treasure("quiver", " full of sharp pointy arrows", 100));

                trollList.add(new Treasure("armor", " a suit of worthy chain mail armor", 1100));
                trollList.add(new Treasure("ruby", " a fine bright red gem", 400));
                trollList.add( chestWood ); 

                caveList.add(new Treasure("helm", " a heavy lidded helm", 980));
                caveList.add(new Treasure("longsword", " a long, gem studded, very sharp sword", 1200));
                caveList.add(new Treasure("diamond", " a very precious carved diamond", 10000));

                // Build map of world
                map.add(new Room("the Forest", "a leafy woodland",
                                        Direction.NOEXIT, 
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.WEST,
                                        forestList));
                map.add(new Room("a Troll's Room", "a dank room that smells of troll",
                                        Direction.NOEXIT,
                                        Direction.SOUTH,
                                        Direction.EAST,
                                        Direction.NOEXIT,
                                        trollList));
                map.add(new Room("a Cave", "a dismal cave with walls covered in luminous moss",
                                        Direction.NORTH,
                                        Direction.NOEXIT,
                                        Direction.EAST,
                                        Direction.NOEXIT,
                                        caveList));
                map.add(new Room("a Dungeon", "a nasty, dark cell",
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.WEST,
                                        dungeonList));


                // Build room exits by room in map
                map.get(0).addExit(Direction.WEST, map.get(1));
                map.get(1).addExit(Direction.EAST, map.get(0));
                map.get(1).addExit(Direction.SOUTH, map.get(2));
                map.get(2).addExit(Direction.NORTH, map.get(1));
                map.get(2).addExit(Direction.EAST, map.get(3));
                map.get(3).addExit(Direction.WEST, map.get(2));


                ThingList playerBag = new ThingList();
                player = new Player("Dave", 
                                " a loveable sort", 
                                map.get(0), 
                                playerBag);

        }



        public String runCommand(String inputstr){
                String s = "ok";
                String lowstr = inputstr.trim().toLowerCase();

                if(lowstr.equals("")){
                        s = "You must enter a command.";
                } else {
                        List<String> wl = Parser.wordList(lowstr);
                        // wl.forEach( (astr) -> System.out.println(astr) );
                        s = getCommand( wl ); 
                }
                return "Status: " + s;
        }

        private String getCommand( List<String> wordlist  ){
                String msg = "Something is not quite right.\n";

                cmd = Parser.parseCommand(wordlist);
                if( cmd != null ){
                        msg = processCommand( cmd );
                }

                return msg;
        }

       private String movePlayer(Player aPlayer, Direction dir){
                Room r = aPlayer.getLocation();

               switch(dir) {
                        case Direction.NORTH -> {
                                if(r.getNorth() == Direction.NORTH){
                                        if(r.exits.containsKey(Direction.NORTH)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        return "Not an exit.";
                                }
                        }
                        case Direction.SOUTH -> {
                                if(r.getSouth() == Direction.SOUTH){
                                        if(r.exits.containsKey(Direction.SOUTH)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        return "Not an exit.";
                                }
                        }
                        case Direction.EAST -> {
                                if(r.getEast() == Direction.EAST){
                                        if(r.exits.containsKey(Direction.EAST)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        return "Not an exit.";
                                }
                        }
                        case Direction.WEST -> {
                                if(r.getWest() == Direction.WEST){
                                        if(r.exits.containsKey(Direction.WEST)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        return "Not an exit.";
                                }
                        }
                        case Direction.UP -> { return "Not an exit"; }
                        case Direction.DOWN -> { return "Not an exit"; }
                        case Direction.NOEXIT -> { return "That direction is not an exit"; }

                }
                return player.getLocation().describe();

       }

       private String processCommand(CmdObj command){

               String msg = "Command failed\n";

               switch(command.verb( ) ){
                        case "look"-> { msg = player.getLocation().describe(); }
                        case "take"-> { msg = player.takeObj( command, player.getLocation( ).getThings( )); }
                        case "drop"-> { msg = player.dropObj( command, player.getBag( ) ); }    
                        case "check" -> { msg = player.showInventory(); } 
                        case "go" -> { msg = processMove( command.noun() ); } 
                        case "save" -> { msg = saveGame( );   }
                        case "load" -> { msg = loadGame(  );  } 
                        case "give" -> { msg = hint(  ); } 
                        case "open" -> { msg = player.openObject(command); }
                        default-> msg = "I didn't understand that request.";

               }
               return msg;

       }


       private String processMove(String noun){
               String msg = "something failed\n";

               switch(noun){
                       case "north" -> { msg = movePlayer(player, Direction.NORTH); }
                       case "south" -> { msg = movePlayer(player, Direction.SOUTH); }
                       case "east" -> { msg = movePlayer(player, Direction.EAST); }
                       case "west" -> { msg = movePlayer(player, Direction.WEST); }

               }
               return msg;

               

       }

       private String saveGame( ){
               String msg = "Saving Game";

               try {
                       FileOutputStream fos = new FileOutputStream("./Adv.sav");
                       ObjectOutputStream oos = new ObjectOutputStream(fos);
                       oos.writeObject( this );
                       oos.flush(  );
                       oos.close(  );
                       msg = "Game Saved";
               } catch (Exception e) {
                       msg = "Serialization error! Can't save data.\n"+
                               e.getClass(  ) + ": " + e.getMessage(  ) + "\n";  
               }

                return msg;
       }

       private String loadGame(  ){
               String msg = "Loading Game";


               try {
                       FileInputStream fis = new FileInputStream("./Adv.sav");
                       ObjectInputStream ois = new ObjectInputStream( fis );
                       Game loaded = ( Game ) ois.readObject(  );
                       this.map = loaded.map;
                       this.player = loaded.player;
                       //this.commands = loaded.commands;
                       ois.close(  );
                       msg = "\n---Game Loaded---\n";
               } catch (Exception e) {
                       msg = "Serialization error! Can't load data.\n"+
                               e.getClass(  ) + ": " + e.getMessage(  );  
               }

               return msg;
       }

       private String hint(  ){
               String msg = "Try using go and a direction\nOr, try to look around\n";

               return msg;
       }



}
