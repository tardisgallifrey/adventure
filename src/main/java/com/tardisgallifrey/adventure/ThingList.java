package com.tardisgallifrey.adventure;

import java.util.ArrayList;

public class ThingList extends ArrayList<Thing> {

        public Thing thisObj(String aName){

                if( aName == null ) {
                        return null;
                }

                 String target = aName.trim().toLowerCase();
  
                 //This code is from a chat with Gemini
                 //I'm not this good with streams
                 return this.stream()
                         // 1. Filter out any null names
                         .filter(t -> t.getName() != null)
                         // 2. Filter for matching name
                         .filter(t -> t.getName().trim().toLowerCase().equals(target))
                         // 3. Grab the first match found
                         .findFirst()
                         // 4. If found,  return Thing, if empty, return null
                         .orElse(null);


        }

}
