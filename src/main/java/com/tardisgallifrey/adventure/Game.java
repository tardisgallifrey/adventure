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
                                        "w"
                                        ));
        List<String> objects = new ArrayList<>(Arrays.asList("sword",
                                        "ring",
                                        "snake"
                                        ));


        public Game(){
                map = new ArrayList<>();
                ThingList dungeonList = new ThingList();

                dungeonList.add("ring",
                                " a ring of great power.", 
                                500);
                dungeonList.add("wombat",
                                " a cuddly wombat. It is squeaking gently in the corner.",
                                700);

                // Build map of world
                map.add(new Room("Forest", " a leafy woodland",
                                        Direction.NOEXIT, 
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.WEST,
                                        new ThingList()));
                map.add(new Room("Troll Room", " a dank room that smells of troll",
                                        Direction.NOEXIT,
                                        Direction.SOUTH,
                                        Direction.EAST,
                                        Direction.NOEXIT,
                                        new ThingList()));
                map.add(new Room("Cave", " a dismal cave with walls covered in luminous moss",
                                        Direction.NORTH,
                                        Direction.NOEXIT,
                                        Direction.EAST,
                                        Direction.NOEXIT,
                                        new ThingList()));
                map.add(new Room("Dungeon", " a nasty, dark cell",
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


                player = new Player("Dave", 
                                " a loveable sort", 
                                map.get(0), 
                                new ThingList());

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
                                System.out.println(verb + " is not a know verb");
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
                System.out.println("You are in the "+player.getLocation().getName());

       }

       private void processCommand(String verb, String noun){
                

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
