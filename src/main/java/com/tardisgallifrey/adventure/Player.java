package com.tardisgallifrey.adventure;



public class Player extends Thing {

        private Room location;
        private ThingList bagOfHolding;

        public Player(String name, String description, Room aRoom, ThingList tl){
                super(name, description);
                this.location = aRoom;
                this.bagOfHolding = tl;
        }

        public Room getLocation(){
                return location;
        }

        public void setLocation(Room aRoom){
                this.location = aRoom;
        }

        public ThingList getBag(){
                return this.bagOfHolding;
        }

        public void addThing(Thing thing){
                bagOfHolding.add(thing);
        }



}
