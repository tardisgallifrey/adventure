package com.tardisgallifrey.adventure.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Parser{

        static HashMap<String, WT> vocab = new HashMap<>();

        static void initVocab( ){
                vocab.put( "drop", WT.VERB );
                vocab.put( "north", WT.NOUN );
                vocab.put( "check", WT.VERB ); 
                vocab.put( "go", WT.VERB );
                vocab.put( "south", WT.NOUN );
                vocab.put( "east", WT.NOUN );
                vocab.put( "west", WT.NOUN );
                vocab.put( "look", WT.VERB );
                vocab.put( "give", WT.VERB );
                vocab.put( "take", WT.VERB );
                vocab.put( "move", WT.VERB );
                vocab.put( "save", WT.VERB );
                vocab.put( "load", WT.VERB );
                vocab.put( "game", WT.NOUN );
                vocab.put( "inventory", WT.NOUN );
                vocab.put( "up", WT.VERB );
                vocab.put( "down", WT.VERB ); 
                vocab.put( "sword", WT.NOUN );
                vocab.put( "bone", WT.NOUN );
                vocab.put( "button", WT.NOUN );
                vocab.put( "carrot", WT.NOUN );
                vocab.put( "chest", WT.NOUN );
                vocab.put( "the", WT.ARTICLE );
                vocab.put( "a", WT.ARTICLE );
                vocab.put( "an", WT.ARTICLE );
                vocab.put( "in", WT.PREPOSITION );
                vocab.put( "into", WT.PREPOSITION );
                vocab.put( "on", WT.PREPOSITION );
                vocab.put( "onto", WT.PREPOSITION );
                vocab.put( "small", WT.ADJECTIVE );
                vocab.put( "large", WT.ADJECTIVE );
                vocab.put( "big", WT.ADJECTIVE );
                vocab.put( "little", WT.ADJECTIVE ); 
                vocab.put( "open", WT.VERB );
                vocab.put( "close", WT.VERB ); 
                vocab.put( "here", WT.NOUN );
                vocab.put( "there", WT.NOUN );
                vocab.put( "shut", WT.VERB ); 
                vocab.put( "ring", WT.NOUN );
                vocab.put( "wombat", WT.NOUN );
                vocab.put( "bow", WT.NOUN );
                vocab.put( "quiver", WT.NOUN );
                vocab.put( "armor", WT.NOUN );
                vocab.put( "ruby", WT.NOUN );
                vocab.put( "helm", WT.NOUN );
                vocab.put( "longsword", WT.NOUN );
                vocab.put( "diamond", WT.NOUN ); 
                vocab.put( "hint", WT.NOUN ); 
                vocab.put( "put", WT.VERB );
                vocab.put( "in", WT.PREPOSITION );
                vocab.put( "into", WT.PREPOSITION );
                vocab.put( "from", WT.PREPOSITION );
                vocab.put( "out of", WT.PREPOSITION );
                vocab.put( "key", WT.NOUN );
                vocab.put( "shirt", WT.NOUN ); 
                vocab.put( "jacket", WT.NOUN ); 


        }


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

       public static CmdObj parseCommand( List<String> wordlist ) {
                
               WT wordtype;
                String verb = "";
                String noun1 = "";
                String noun2 = "";
                String preposition = "";     // future
                String adjective = "";       // future
                
                initVocab();

               for( String k : wordlist ){
                       // System.out.println(k);  // for debugging only
                       if( vocab.containsKey( k ) ){
                               wordtype = vocab.get( k );
                               // System.out.println(wordtype);   // for debugging only

                               switch( wordtype ){
                                        case WT.VERB -> { verb = k; }
                                        case WT.NOUN -> { if( noun1 != null && noun1.isBlank( )  ){
                                                                noun1 = k;
                                                           } else {
                                                                noun2 = k; 
                                                           }
                                                        }
                                        case WT.ARTICLE -> { /* articles are skipped */ }
                                        case WT.PREPOSITION -> { preposition = k; }
                                        case WT.ADJECTIVE -> { adjective = k; }
                                        case WT.CONJUNCTION -> { }
                                        case WT.UNKNOWN -> { vocab.put( k, WT.UNKNOWN ); }
                                        case WT.ERROR -> { }
                               }

                       } else {
                               if( verb.equals( "" ) ){ 
                                verb = k + " is not a known word.";
                               }
                       }
 
                }

                return new CmdObj( verb, noun1, preposition, adjective, noun2 ); 
        }



}
