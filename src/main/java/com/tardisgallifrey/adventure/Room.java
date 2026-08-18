package com.tardisgallifrey.adventure;


import java.util.HashMap;

import com.tardisgallifrey.adventure.utils.Direction;

public class Room extends Thing {

        Direction north;
        Direction south;
        Direction east;
        Direction west;

        HashMap<Direction, Room> exits = new HashMap<>();

        ThingList roomThings;

        public Room(String name, String description, Direction north, Direction south, Direction east, Direction west, ThingList tl){
                super(name, description, false, false);
                this.north = north;
                this.south = south;
                this.east = east;
                this.west = west;
                this.roomThings = tl;

        }

        public Direction getNorth(){
                return this.north;
        }

        public Direction getSouth(){
                return this.south;
        }

        public Direction getEast(){
                return this.east;
        }

        public Direction getWest(){
                return this.west;
        }

        public void addExit(Direction dir, Room aRoom){
                exits.put(dir, aRoom);
        }

        public ThingList getThings(){
                return this.roomThings;
        }

        @Override
        public String describe(){
                StringBuilder s = new StringBuilder();
                if( roomThings.size() > 0 ){
                        s.append("You are in " + this.getName() + "\n\t" + "It is " + this.getDescription());
                        s.append("\n  This room contains: \n");
                        for( Thing thing : roomThings ){
                                s.append("\n\t\t" + thing.describe());
                        }

                } else {
                        s.append("You are in " + this.getName() + "\n\t" + "It is " + this.getDescription() + "\nThere is nothing here.");
                }
                return s.toString();
        }


}
