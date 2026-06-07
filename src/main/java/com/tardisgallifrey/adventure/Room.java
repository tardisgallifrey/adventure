package com.tardisgallifrey.adventure;


import com.tardisgallifrey.adventure.utils.Direction;

public class Room extends Thing {

        Direction north;
        Direction south;
        Direction east;
        Direction west;

        public Room(String name, String description, Direction north, Direction south, Direction east, Direction west){
                super(name, description);
                this.north = north;
                this.south = south;
                this.east = east;
                this.west = west;

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


}
