package com.tardisgallifrey.adventure;


public class ThingHolder extends Thing {

        private ThingList things = new ThingList();

        public ThingHolder(String name, String description, ThingList tl, boolean takable, boolean movable){
                super(name, description, takable, movable);
                this.things = tl;
        }

        public ThingList getThingList(){
                return things;
        }

        public void setThingList(ThingList things){
                this.things = things;
        }

}
