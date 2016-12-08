
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

    public static void exportPolicy(double[][] policy, String fileName) {

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < policy.length; i++) {
            String line = "";
            for (int j = 0; j < policy[0].length; j++) {
                line += policy[i][j];
                if (j < policy[0].length - 1) {
                    line += ",";
                }
            }
            lines.add(line);
        }
        try {
            Files.write(Paths.get("policies/" + fileName + ".txt"), lines);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double[][] importPolicy(String fileName) {

        double[][] policy;
//        try {
//            FileReader in = new FileReader("policies/" + fileName + ".txt");
//            BufferedReader reader = new BufferedReader(in);
//            String line = reader.readLine();
//            String[] values = line.split(",");
//            policy = new int[values.length];
//            for (int i = 0; i < values.length; i++) {
//                policy[i] = Integer.parseInt(values[i]);
//            }
//            return policy;
//        } catch (IOException | NumberFormatException e) {
//            System.out.println("Exception caught: " + e);
//        }
//        return new int[1];
//    }
        return new double[0][0];//policy;
    }
}
