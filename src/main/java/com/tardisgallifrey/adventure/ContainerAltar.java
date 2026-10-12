package com.tardisgallifrey.adventure;


public class ContainerAltar extends ContainerThing {

        // Change this to alter how many objects
        // can be in the ContainerAltar
        private final int maxSize = 10;
        private int requiredSize = 0;
        private final ThingList winningList;

        public ContainerAltar(  String name, String aDescription, ThingList emptyList, ThingList winList, int requiredSize ){


                super(  name, aDescription, emptyList, false, false, true, false );

                // 1. ensure list size of initial ThingList is zero
                if( !emptyList.isEmpty() ){
                        throw new IllegalArgumentException(
                                        "Game Engine Error: The provided ThingList must be empty."
                                        );
                }

                // 2. ensure list size of winning list is less than maxSize
                if( winList.size( ) < 2 || winList.size( ) > requiredSize ){
                        throw new IllegalArgumentException( 
                                        "Game Engine Error: The provided winList is too small or too large."
                                        );
                
                }else if( requiredSize < 2 || requiredSize > maxSize ) {
                        throw new IllegalArgumentException(
                                        "Game Engine Error: The requiredSize of List is too large."
                                        );
                }else{

                        this.winningList = winList;
                        this.requiredSize = requiredSize;
                }

                
        }

        public ThingList getWinList( ){
                return this.winningList;
        }

        public boolean addAltarItem( Thing thing ){
                if( this.getThingList( ).size( ) >= this.requiredSize ){
                        return false;
                } else {
                        this.getThingList( ).add( thing );
                        return true;
                }
        }

        
}
