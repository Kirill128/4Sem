package eu.grsu.map.readers;

import eu.grsu.map.twits.Twit;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterators;

public class TwitReader {
    public static void readFromFile(String fileName){// Hardcode !!!!!!!!!!!!!!!!!
        List<Twit> twits=new LinkedList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while((line=reader.readLine())!=null){
                String [] tokens=line.split("[|]|\s|_|,");
                for (int j = 0; j < tokens.length; j++) {
                    System.out.printf(tokens[j]+'/');
                }
                System.out.printf("\n");
            }
        }
        catch(IOException io){
            System.out.println(io.getStackTrace());//TODO: add logs
        }
    }
}
