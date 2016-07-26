package ua.qa.sandbox;

/**
 * Created by polkota on 26.07.2016.
 */
public class Equality {
    public static void main (String[] args){
        String s1 = "polkota";
        String s2 = "pol" + "kota";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
