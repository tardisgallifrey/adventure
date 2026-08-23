package com.tardisgallifrey.adventure;

import java.io.Serializable;

public class ContainerThing extends ThingHolder implements Serializable {

        private boolean openable;
        private boolean isopen;

        //  For buckets, bowls, and things without lids or covers
        public ContainerThing( String name, String aDescription, ThingList tl ){
                super(name, aDescription, tl, true, true);

                openable = false;
                isopen = true;

        }

        // For chests, sacks and things with lids or closures
        public ContainerThing(  String name, String aDescription, ThingList tl, boolean canMove, boolean canTake, boolean canOpen, boolean OpenOrShut ){
                super(name, aDescription, tl, canTake, canMove);

                openable = canOpen;
                isopen = OpenOrShut;

        }

        public boolean isOpenable(  ){
                return this.openable;
        }

        public void setOpenable( boolean status ){
                this.openable = status;
        }

        public void open(  ){
                this.isopen = true;
        }

        
        public void close(  ){
                this.isopen = false;
        }


        public String showInventory(){
                StringBuilder s = new StringBuilder();
                if( this.getThingList().size(  )  > 0 ){
                        s.append("\n  In the " + this.getName(  ) + " you can see: \n");
                        for( Thing thing : this.getThingList() ){
                                s.append("\n\t\t" + thing.describe());
                        }

                } else {
                        s.append("\nThe " + this.getName(  ) + "is empty.\n" );
                }
                return s.toString();
        }

}
