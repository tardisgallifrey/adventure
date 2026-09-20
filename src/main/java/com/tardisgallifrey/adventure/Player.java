package com.tardisgallifrey.adventure;

import com.tardisgallifrey.adventure.utils.CmdObj;

public class Player extends Thing {

        private Room location;
        private ThingList bagOfHolding;

        public Player(String name, String description, Room aRoom, ThingList tl){
                super(name, description, false, false);
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

        public boolean isThinginInventory( String objectName ){
                Thing t = this.bagOfHolding.thisObj( objectName ) ; 
                if( t != null && t.getName( ).equals( objectName ) ){
                        return true;
                }
                return false;
        }

        public boolean isThinginRoom( String objectName ){
                Thing t =  this.getLocation( ).getThings( ).thisObj( objectName );
                if( t != null && t.getName( ).equals( objectName ) ){
                        return true;
                }
                return false;
        }

        public boolean isThingHere( String objectName ){
                Thing t = null;

                if( isThinginInventory(objectName) ){
                        return true;
                }

                if( isThinginRoom( objectName ) ){
                        return true;
                }

                for( Thing thing : this.getLocation( ).getThings( ) ){

                        if( thing instanceof ContainerThing ){
                                ContainerThing container = ( ContainerThing ) thing;
                                if( container.isOpen() ){
                                        t = container.getThingList( ).thisObj(objectName);
                                        break;
                                }
                        }
                }

                if( t != null && t.getName().equals( objectName )  ){
                        return true;
                }

                return false;
        }

       public String openObject( CmdObj command ){
               String retStr = "";
               Thing t = this.getLocation().getThings().thisObj( command.noun1( ) );

               if( t instanceof ContainerThing ){
                        ContainerThing container = ( ContainerThing ) t;
                        if( container.isOpenable( )  ){
                                container.open( );
                                retStr = "You opened the " + container.getName( ) + ".\n";
                                System.out.println( container.showInventory() );
                        } else {
                                retStr = "You didn't open the " + container.getName( ) + ".\n";
                        }
               } else {
                       retStr = t.getName( ) + " is not able to be opened.\n";
               }

                return retStr;
       }


        public String takeObj( CmdObj command, ThingList tl ){
               String retStr = "";

               if( command.noun1( ).equals("") ){
                       return retStr = "nameless object"; // if no object specified
               }

               if( tl == null ) {
                       retStr = "Nothing to take " + command.noun1( ) + " from.\n";
                       return retStr;   //  Must break early if tl is null
               }


               if( tl != null && this.isThingHere( command.noun1( ) ) ){
                        Thing found = tl.thisObj( command.noun1( ) ); 
                        if( found != null && found.getTakable( )  ){ 
                                transferObj( found, tl, this.bagOfHolding );
                                retStr = command.noun1( ) + " taken.\n";
                        } else {
                                retStr = command.noun1( ) + " cannot be taken.\nOr, you didn't say what to take object from.\n";
                        }
               } else {
                       retStr = command.noun1( ) + " is not found here.\n";
               }
        
               return retStr;
 
        }

        public String dropObj( CmdObj command, ThingList tl ){
               String retStr = "";

               if( command.noun1( ).equals("") ){
                       retStr = "nameless object"; // if no object specified
               }

               if( tl == null ) {
                       retStr = "There is no " + command.noun1( )  + " here.";
                       return retStr;   //  Must break early if t is null
               }


               if( tl != null && this.isThingHere( command.noun1( ) ) ){
                        Thing found = tl.thisObj( command.noun1( ) ); 
                        transferObj( found, tl, this.location.getThings( ) ); 
                        retStr = command.noun1( ) + " dropped.\n";
               }
        
               return retStr;
 
        }

        public String putObjInto( CmdObj command, ThingList tl ){
               String retStr = "";

               if( command.noun1( ).equals("") ){
                       return retStr = "nameless object"; // if no object specified
               }

               if( tl == null ) {
                       retStr = "Nothing to put " + command.noun1( ) + " inside.\n";
                       return retStr;   //  Must break early if tl is null
               }


               if( tl != null && this.isThingHere( command.noun1( ) ) ){
                        Thing thing = this.bagOfHolding.thisObj( command.noun1( ) ); 
                        transferObj( thing , this.bagOfHolding, tl );
                        retStr = command.noun1( ) + " put into " + command.noun2( ) +"\n";
                } else {
                        retStr = command.noun1( ) + " cannot be put there.\nOr, you didn't say what to put object into.\n";
                }
        
                return retStr;
 

       }

       private void transferObj( Thing t, ThingList  fromList, ThingList  toList ) {

               fromList.remove(t);
               toList.add(t);

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
