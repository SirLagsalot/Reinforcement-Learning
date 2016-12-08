
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UNA
 */
public class FileHandler {

    public static void exportPolicy(int[] policy, String name) {
        try {
            System.setOut(new PrintStream(new FileOutputStream("policies/" + name + ".txt")));

            for (int i = 0; i < policy.length; i++) {
                    System.out.print(policy[i]);
            }
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int[] readPolicy(String name){
        int[] policy;
         try {
            FileReader in = new FileReader(name);
            BufferedReader reader = new BufferedReader(in);
            char[] next = reader.readLine().toCharArray();
            policy = new int[next.length];
            for(int i = 0; i < next.length; i++){
                policy[i] = Integer.parseInt(Character.toString(next[i]));
            }
            
            return policy;
            
        } catch (IOException | NumberFormatException e) {
            System.out.println("Exception caught: " + e);
        }
        return new int[1];
    }
}
