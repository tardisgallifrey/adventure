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
                map.add(new Room("Forest", " a leafy woodland",
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.WEST));
                map.add(new Room("Troll Room", " a dank room that smells of troll",
                                        Direction.NOEXIT,
                                        Direction.SOUTH,
                                        Direction.EAST,
                                        Direction.NOEXIT));
                map.add(new Room("Cave", " a dismal cave with walls covered in luminous moss",
                                        Direction.NORTH,
                                        Direction.NOEXIT,
                                        Direction.EAST,
                                        Direction.NOEXIT));
                map.add(new Room("Dungeon", " a nasty, dark cell",
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.NOEXIT,
                                        Direction.WEST));

                player = new Player("Dave", " a loveable sort", map.get(0));

        }



        public String runCommand(String inputstr){
                String s = "ok";
                String lowstr = inputstr.trim().toLowerCase();

                if(lowstr.equals("")){
                        s = "You must enter a command.";
                } else {
                        List<String> wl = wordList(lowstr);
                        wl.forEach( (astr) -> System.out.println(astr) );
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

       private boolean movePlayer(Player aPlayer, Direction dir){
                Room r = aPlayer.getLocation();
                boolean exit = false;

                System.out.println(r.getName());
                System.out.println(r.getNorth());
                System.out.println(r.getSouth());
                System.out.println(r.getEast());
                System.out.println(r.getWest());
                switch(dir) {
                        case NORTH:
                                if(r.getNorth() == Direction.NORTH){
                                        exit = true;
                                }
                                break;
                        case SOUTH:
                                if(r.getSouth() == Direction.SOUTH){
                                        exit = true;
                                }
                                break;
                        case EAST:
                                if(r.getEast() == Direction.EAST){
                                        exit = true;
                                }
                                break;
                        case WEST:
                                if(r.getWest() == Direction.WEST){
                                        exit = true;
                                }
                                break;
                        case NOEXIT:
                                System.out.println("That direction is not an exit");

                }
                return exit;

       }

       private void processCommand(String verb, String noun){
                

       }

       private void processMove(String verb){
               boolean hasExit = false;

               switch(verb){
                       case "north":
                       case "n":
                               hasExit = movePlayer(player, Direction.NORTH);
                               break;
                       case "south":
                       case "s":
                               hasExit = movePlayer(player, Direction.SOUTH);
                               break;
                       case "east":
                       case "e":
                               hasExit = movePlayer(player, Direction.EAST);
                       case "west":
                       case "w":
                               hasExit = movePlayer(player, Direction.WEST);
                       default:
                               System.out.println("I don't know that direction");
               }

               if(hasExit){
                       System.out.println("Yes, you can go that direction" + hasExit);
               } else {
                       System.out.println("No, you cannot go that direction" + hasExit);
               }

       }


}
