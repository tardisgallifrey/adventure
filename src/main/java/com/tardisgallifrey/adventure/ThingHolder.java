package com.tardisgallifrey.adventure;

import com.tardisgallifrey.adventure.utils.ThingandThingHolder;

public class ThingHolder extends Thing {

        private ThingList things = new ThingList();
        StringBuilder thingStr = new StringBuilder( ); 

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

        public String describeThings( ){
                thingStr.append( "" );
                doDescribeThings(this);
                return thingStr.toString();
        }

        private void doDescribeThings( ThingHolder th ){
                ThingList tlist = th.getThingList();

                for( Thing t : tlist ) { 
                        thingStr.append( "\n" );
                        if( t instanceof ContainerThing ){
                                ContainerThing container = ( ContainerThing ) t;
                                if( container.isOpen() && container.getThingList().size( ) > 0 ){
                                        thingStr.append( "The " + container.getName( ) + " contains: \n");
                                        doDescribeThings(container);
                                }
                        }
                }
                        
        }

        public ThingandThingHolder findThing( String objectName ){
                ThingandThingHolder t_and_th = null;
                t_and_th = findThinginAnyList( this, objectName );
                return t_and_th;
        }

        private ThingandThingHolder findThinginAnyList( ThingHolder th, String objectName ){
                boolean found = false;
                ThingandThingHolder t_and_th = null;

                for( Thing t : th.getThingList( ) ){
                        if( t.getName( ).equals( objectName ) ){
                                t_and_th = new ThingandThingHolder( t, th );
                                found = true;
                        }
                        if( !found ){
                                 if( th instanceof ContainerThing ){
                                         ContainerThing container = ( ContainerThing )th;
                                         if( container.isOpen( ) ){
                                                 findThinginAnyList( container, objectName );
                                         }
                                 }
                        }
                }
                return t_and_th;
        }

}
