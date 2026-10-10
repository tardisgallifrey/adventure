package com.tardisgallifrey.adventure;


public class ContainerAltar extends ContainerThing {

        // Change this to alter how many objects
        // can be in the ContainerAltar
        private final int maxSize = 10;
        private final ThingList winningList;

        public ContainerAltar(  String name, String aDescription, ThingList emptyList, ThingList winList, int requiredSize ){

                super(  name, aDescription, emptyList, false, false, true, false );

                if( !emptyList.isEmpty() ){
                        throw new IllegalArgumentException(
                                        "Game Engine Error: The provided ThingList must be empty."
                                        );
                }

                if( winList.size( ) < 2 || winList.size( ) > maxSize ){
                        throw new IllegalArgumentException( 
                                        "Game Engine Error: The provided winList is too small or too large."
                                        );
                }else{

                        this.winningList = winList;
                }
 

        }

        public ThingList getWinList( ){
                return this.winningList;
        }



}
