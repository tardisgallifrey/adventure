package com.tardisgallifrey.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Game {


        public String runCommand(String inputstr){
                String s = "ok";
                String lowstr = inputstr.trim().toLowerCase();

                if(lowstr.equals("")){
                        s = "You must enter a command.";
                } else {
                        List<String> wl = wordList(lowstr);
                        wl.forEach( (astr) -> System.out.println(astr) );
                        //parseCommand(wl);
                }
                return s;
        }

        public List<String> wordList(String input){
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
}
