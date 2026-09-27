package com.tardisgallifrey.adventure;

import java.io.Serializable;

public class Thing implements Serializable{

        private String name;
        private String description;
        private boolean takable;
        private boolean movable;

        public Thing(String name, String description, boolean takable, boolean movable){
                this.name = name;
                this.description = description;
                this.takable = takable;
                this.movable = movable;

        }

        public String getName(){
                return this.name;
        }

        public String getDescription(){
                return this.description;
        }

        public boolean getTakable(  ){
                return this.takable;
        }

        public boolean getMovable(  ){
                return this. movable;
        }

        public String describe(){
                return " Here is a " + this.getName() + ": It is" +  this.getDescription();
        }

}
