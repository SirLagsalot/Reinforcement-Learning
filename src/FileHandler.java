
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

    public static void exportPolicy(int[][] policy, String name) {
        try {
            System.setOut(new PrintStream(new FileOutputStream("policies/" + name + ".txt")));

            for (int i = 0; i < policy.length; i++) {
                for (int j = 0; j < policy[i].length; j++) {
                    System.out.print(policy[i][j]);
                }
                System.out.println("");
            }
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int[][] readPolicy(String name){
        int[][] policy;
         try {
            FileReader in = new FileReader(name);
            ArrayList<int[]> lines = new ArrayList();
            BufferedReader reader = new BufferedReader(in);
            String next;
            while((next = reader.readLine()) != null){
                int[] temp = new int[9];
                char[] tempS = next.toCharArray();
                for(int i = 0; i < temp.length; i++){
                    temp[i] = tempS[i];
                }
                lines.add(temp);
            }
            
            policy = new int[lines.size()][];
            for(int i = 0; i < policy.length; i++){
                policy[i] = lines.get(i);
            }
            
            return policy;
            
        } catch (IOException | NumberFormatException e) {
            System.out.println("Exception caught: " + e);
        }
        return new int[1][];
    }
}
