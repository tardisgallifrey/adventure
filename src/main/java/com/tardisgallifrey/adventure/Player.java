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

        public String showInventory(){
                StringBuilder s = new StringBuilder();
                if( this.bagOfHolding.size() > 0 ){
                        s.append("\n  Your bag of Holding contains: \n");
                        for( Thing thing : this.bagOfHolding ){
                                s.append("\n\t\t" + thing.describe());
                        }

                } else {
                        s.append("Your bag is empty.  Find some stuff.");
                }
                return s.toString();
        }





}
