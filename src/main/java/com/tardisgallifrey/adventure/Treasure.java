package com.tardisgallifrey.adventure;


public class Treasure extends Thing {

        private int value;

        public Treasure(String name, String description, int value){
                super(name, description);
                this.value = value;
        }

        public int getValue(){
                return this.value;
        }

        @Override
        public String describe(){
                return " Here is " + this.getName() + ": It is" +  this.getDescription() + ". It's value is: " + this.getValue();
        }


}
