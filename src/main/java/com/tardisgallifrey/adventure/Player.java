package com.tardisgallifrey.adventure;



public class Player extends Thing {

        private Room location;

        public Player(String name, String description, Room aRoom){
                super(name, description);
                this.location = aRoom;
        }

        public Room getLocation(){
                return location;
        }

        public void setLocation(Room aRoom){
                this.location = aRoom;
        }



}
