package eu.grsu.map.application;

import eu.grsu.map.readers.TwitReader;

public class Application {
    public static void main(String[] args) {
        TwitReader.readFromFile("/home/kirill/Downloads/high_school_tweets2014.txt");
    }
}
