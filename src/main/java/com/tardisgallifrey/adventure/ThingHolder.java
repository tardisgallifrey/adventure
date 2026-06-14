package com.tardisgallifrey.adventure;


public class ThingHolder extends Thing {

        private ThingList things = new ThingList();

        public ThingHolder(String name, String description, ThingList tl){
                super(name, description);
                this.things = tl;
        }

        public ThingList getThingList(){
                return things;
        }

        public void setThingList(ThingList things){
                this.things = things;
        }

}
