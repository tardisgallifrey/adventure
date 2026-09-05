package com.tardisgallifrey.adventure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdventureTests{

 
        @Test
        void testThingIsNotNull( ) {
                Thing thing = new Thing( "rock", " well worn rock.", true, true );

                assertEquals( "rock", thing.getName( )  );
        }

}
