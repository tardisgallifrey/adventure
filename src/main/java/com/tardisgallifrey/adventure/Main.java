package com.tardisgallifrey.adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

        public static void main(String[] args) throws IOException {

                BufferedReader in;
                String input;
                String output;

                Game game = new Game();

                in = new BufferedReader(new InputStreamReader(System.in));

                clearScreen();

                System.out.println("Welcome to the Adventure");

                do{
                        System.out.print(" >  ");
                        input = in.readLine();
                        if("q".equals(input.toLowerCase())) {
                                System.out.println("Thanks for playing. Bye");
                        } else {
                                // System.out.println("You entered: '" + input + "'");
                                output = game.runCommand(input);
                                System.out.println(output);
                        }
                }while(!"q".equals(input.toLowerCase()));
                

        }

        public static void clearScreen(){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
        }
}
