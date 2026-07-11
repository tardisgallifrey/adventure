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
                                        "save",
                                        "load",
                                        "take"
                                        ));
        
        List<String> objects = new ArrayList<>(Arrays.asList("sword",
                                        "ring",
                                        "snake",
                                        "around",
                                        "bow",
                                        "quiver",
                                        "armor",
                                        "helm",
                                        "wombat"
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
                        //wl.forEach( (astr) -> System.out.println(astr) );
                        parseCommand(wl);
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

        private void parseCommand(List<String> wordlist){
                String verb;
                String noun;
                
                if(wordlist.size() > 2){
                        System.out.println("Only 2 word commands allowed");
                } else if( wordlist.size() == 1){
                        verb = wordlist.get(0);
                        if(!commands.contains(verb)){
                                System.out.println(verb + " is not a known verb");
                        } else {
                                processMove(verb);
                        }

                } else {
                        verb = wordlist.get(0);
                        noun = wordlist.get(1);
                        if(!commands.contains(verb)){
                                System.out.println(verb + " is not a known verb");
                        } else {
                                processCommand(verb, noun);
                        }
                                         
                }
        

       }

       private void movePlayer(Player aPlayer, Direction dir){
                Room r = aPlayer.getLocation();

               switch(dir) {
                        case Direction.NORTH:
                                if(r.getNorth() == Direction.NORTH){
                                        if(r.exits.containsKey(Direction.NORTH)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        System.out.println("Not an exit.");
                                }
                                break;
                        case Direction.SOUTH:
                                if(r.getSouth() == Direction.SOUTH){
                                        if(r.exits.containsKey(Direction.SOUTH)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        System.out.println("Not an exit.");
                                }
                                break;
                        case Direction.EAST:
                                if(r.getEast() == Direction.EAST){
                                        if(r.exits.containsKey(Direction.EAST)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        System.out.println("Not an exit.");
                                }
                                break;
                        case Direction.WEST:
                                if(r.getWest() == Direction.WEST){
                                        if(r.exits.containsKey(Direction.WEST)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } else {
                                        System.out.println("Not an exit.");
                                }
                                break;
                        case Direction.NOEXIT:  // technically can't be reached, leaving for now.
                                System.out.println("That direction is not an exit");

                }
                // reaching a non exit will still describe the room.  That's OK.
                System.out.println(player.getLocation().describe());

       }

       private void processCommand(String verb, String noun){
               switch(verb){
                        case "look"->System.out.println(player.getLocation().describe());
                        case "take"->takeObject(noun);
                        case "drop"->dropObject(noun);
                        default->System.out.println("I didn't understand that request.");

               }

       }

       private void takeObject( String object ){
               String retStr = "";
               Thing t = player.getLocation().getThings().thisObj(object);

               if( object.equals("") ){
                       object = "nameless object"; // if no object specified
               }

               if( t == null ) {
                       retStr = "There is no " + object + " here.";
                } else {
                        retStr = "Picking up " + t.getName();
                }

               System.out.println(retStr);
       }

       private void dropObject( String object ){
               String retStr = "";

               Thing t = player.getBag().thisObj( object );

               if( t == null ) {
                       retStr = "You haven't got one of those.";
               } else {
                       retStr = "Dropping " + t.getName();
               }

               System.out.println(retStr);
       }

       private void processMove(String verb){

               switch(verb){
                       case "north":
                       case "n":
                               movePlayer(player, Direction.NORTH);
                               break;
                       case "south":
                       case "s":
                               movePlayer(player, Direction.SOUTH);
                               break;
                       case "east":
                       case "e":
                               movePlayer(player, Direction.EAST);
                               break;
                       case "west":
                       case "w":
                               movePlayer(player, Direction.WEST);
                               break;

               }

               

       }



}
