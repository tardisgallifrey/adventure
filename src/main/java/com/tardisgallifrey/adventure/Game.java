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
                                        "look"
                                        ));
        
        List<String> objects = new ArrayList<>(Arrays.asList("sword",
                                        "ring",
                                        "snake",
                                        "around"
                                        ));


        public Game(){
                map = new ArrayList<>();
                ThingList forestList = new ThingList();
                ThingList trollList = new ThingList();
                ThingList caveList = new ThingList();
                ThingList dungeonList = new ThingList();
                dungeonList.add(new Treasure("a ring", " of great power", 600));
                dungeonList.add(new Treasure("a wombat", " a cuddly, furry wombat in the corner squeaking to itself", 700));

                forestList.add(new Treasure("a sword", " a decent short sword", 300));
                forestList.add(new Treasure("a bow" , " a fine sturdy warrior bow", 300));
                forestList.add(new Treasure("a quiver of arrows", " sharp pointy arrows", 100));

                trollList.add(new Treasure("a suit of armor", " worthy chain mail armor", 1100));
                trollList.add(new Treasure("a large ruby", " a fine bright red gem", 400));

                caveList.add(new Treasure("a helm", " a heavy lidded helm", 980));
                caveList.add(new Treasure("a long sword", " a long, gem studded, very sharp sword", 1200));
                caveList.add(new Treasure("a large diamond", " a very precious carved diamond", 10000));

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
                return s;
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
                                }
                                break;
                        case Direction.SOUTH:
                                if(r.getSouth() == Direction.SOUTH){
                                        if(r.exits.containsKey(Direction.SOUTH)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                }
                                break;
                        case Direction.EAST:
                                if(r.getEast() == Direction.EAST){
                                        if(r.exits.containsKey(Direction.EAST)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                } 
                                break;
                        case Direction.WEST:
                                if(r.getWest() == Direction.WEST){
                                        if(r.exits.containsKey(Direction.WEST)){
                                                player.setLocation(r.exits.get(dir));
                                        }
                                }
                                break;
                        case Direction.NOEXIT:
                                System.out.println("That direction is not an exit");

                }
                System.out.println(player.getLocation().describe());

       }

       private void processCommand(String verb, String noun){
               switch(verb){
                        case "look"->System.out.println(player.getLocation().describe());
                        default->System.out.println("I didn't understand that request.");

               }

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


       private void describe(Room aRoom){
               System.out.printf("%s\n", aRoom.roomThings.get(0).getName());

       
       }

}
