package com.tardisgallifrey.adventure.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Parser{
        static List<String> commands = new ArrayList<>(Arrays.asList("take",
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
                                        "sv",
                                        "save",
                                        "ld",
                                        "load",
                                        "take",
                                        "t",
                                        "h",
                                        "hint",
                                        "o",
                                        "open",
                                        "u",
                                        "up",
                                        "down"
                                        ));
 


        public static List<String> wordList(String input){
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

        public static CmdObj parseCommand(List<String> wordlist){
                String verb = "";
                String noun = "";

                if( wordlist.size() > 0 ) {
                        verb = wordlist.get( 0 );
                        if( wordlist.size() > 1 ) {
                                noun = wordlist.get( 1 );
                        }
                }

                if( commands.contains( verb ) ) {
                } else {
                        verb += " is not a known verb.";
                }
                
                
                return new CmdObj( verb, noun ) ;
        

       }



}
