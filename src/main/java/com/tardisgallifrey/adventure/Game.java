package com.tardisgallifrey.adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import com.tardisgallifrey.adventure.utils.Direction;

public class Game {

        private ArrayList<Room> map;
        private Player player;
        List<String> commands = new ArrayList<>(Arrays.asList("take",
                                        "drop",
                                        "d",
                                        "north",
                                        "n",
                                        "south",
                                        "s",
                                        "east",
                                        "e",
                                        "west",
                                        "w",
                                        "look",
                                        "l",
                                        "inventory",
                                        "i",
                                        "save",
                                        "load",
                                        "take",
                                        "t"
                                        ));
        

        // The above needs a better method.  
        // It should walk through the room lists and add things 
        // to the object list.



        public Game(){
                map = new ArrayList<>();
                ThingList forestList = new ThingList();
                ThingList trollList = new ThingList();
                ThingList caveList = new ThingList();
                ThingList dungeonList = new ThingList();
                dungeonList.add(new Treasure("ring", " a ring of great power", 600));
                dungeonList.add(new Thing("wombat", " a cuddly, furry wombat in the corner squeaking to itself"));

                forestList.add(new Treasure("sword", " a decent short sword", 300));
                forestList.add(new Treasure("bow" , " a fine sturdy warrior bow", 300));
                forestList.add(new Treasure("quiver", " full of sharp pointy arrows", 100));

                trollList.add(new Treasure("armor", " a suit of worthy chain mail armor", 1100));
                trollList.add(new Treasure("ruby", " a fine bright red gem", 400));

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
                        List<String> wl = wordList(lowstr);
                        // wl.forEach( (astr) -> System.out.println(astr) );
                        s = parseCommand(wl);
                }
                return "Status: " + s;
        }

        private List<String> wordList(String input){
                String delimiters = " \t,.:;?!\"'";
                String token;

                List<String> stringList = new ArrayList<>();
                StringTokenizer tokenizer = new StringTokenizer(input, delimiters);
                 while(tokenizer.hasMoreTokens()){
                         token = tokenizer.nextToken();
                         stringList.add(token);
                 }
                 return stringList;
        }

        private String parseCommand(List<String> wordlist){
                String verb = "";
                String noun = "";
                String msg = "Something is not quite right.\n";

                if( wordlist.size() > 0 ) {
                        verb = wordlist.get( 0 );
                        if( wordlist.size() > 1 ) {
                                noun = wordlist.get( 1 );
                        }
                }

                if( commands.contains( verb ) ) {
                        msg = processCommand( verb, noun );
                } else {
                        msg = verb + " is not a known verb.";
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
                        case Direction.NOEXIT -> { return "That direction is not an exit"; }

                }
                return player.getLocation().describe();

       }

       private String processCommand(String verb, String noun){
               String msg = "Command failed\n";

               switch(verb){
                        case "l", "look"-> { msg = player.getLocation().describe(); }
                        case "t", "take"-> { msg = takeObject(noun); }
                        case "d", "drop"-> { msg = dropObject(noun); }    
                        case "i", "inventory" -> { msg = player.showInventory(); } 
                        case "n", "s", "e", "w" -> { msg = processMove( verb ); } 
                        case "north", "south", "east", "west" -> { msg = processMove( verb ); } 
                        default-> msg = "I didn't understand that request.";

               }
               return msg;

       }

       private String takeObject( String object ){
               String retStr = "";
               Thing t = player.getLocation().getThings().thisObj(object);

               if( object.equals("") ){
                       object = "nameless object"; // if no object specified
               }

               if( t == null ) {
                       retStr = "There is no " + object + " here.";
                } else {
                        transferObj( t, player.getLocation().getThings(), player.getBag() );
                        retStr = t.getName() + " taken\n";
                }
                return retStr;
       }

       private String dropObject( String object ){
               String retStr = "";

               Thing t = player.getBag().thisObj( object );

               if( t == null ) {
                       retStr = "You haven't got one of those.";
               } else {
                       transferObj( t, player.getBag(), player.getLocation().getThings() );
                       retStr = "Dropping " + t.getName();
               }

               return retStr;
       }

       private void transferObj( Thing t, ThingList  fromList, ThingList  toList ) {

               fromList.remove(t);
               toList.add(t);

       }



       private String processMove(String verb){
               String msg = "something failed\n";

               switch(verb){
                       case "n", "north" -> { msg = movePlayer(player, Direction.NORTH); }
                       case "s", "south" -> { msg = movePlayer(player, Direction.SOUTH); }
                       case "e", "east" -> { msg = movePlayer(player, Direction.EAST); }
                       case "w", "west" -> { msg = movePlayer(player, Direction.WEST); }

               }
               return msg;

               

       }



}
