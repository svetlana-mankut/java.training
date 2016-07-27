package ua.qa.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by polkota on 27.07.2016.
 */
public class Collections {
    public static void main(String[] args){
        /*String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Pyton";
        langs[3] = "PHP";
        */
        String[] langs = {"Java","C#", "Pyton", "PHP"};
        /*for (int i = 0; i < langs.length; i++){
            System.out.println("I want learn" + i); */

        List languages = Arrays.asList("Java","C#", "Pyton", "PHP");

        for (Object l : languages){
            System.out.println("I want learn " + l);
        }
    }
}
