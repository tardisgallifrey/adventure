package com.tardisgallifrey.adventure;

public class Thing {

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

}
