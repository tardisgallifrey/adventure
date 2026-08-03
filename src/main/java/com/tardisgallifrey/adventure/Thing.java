package com.tardisgallifrey.adventure;

import java.io.Serializable;

public class Thing implements Serializable{

        private String name;
        private String description;

        public Thing(String name, String description){
                this.name = name;
                this.description = description;

        }

        public String getName(){
                return this.name;
        }

        public String getDescription(){
                return this.description;
        }

        public String describe(){
                return " Here is a " + this.getName() + ": It is" +  this.getDescription();
        }

}
