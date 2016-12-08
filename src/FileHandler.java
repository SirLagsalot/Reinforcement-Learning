
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    public static void exportPolicy(int[] policy, String fileName) {

        Path path = Paths.get("policies/" + fileName + ".txt");
        String output = "";
        for (int i = 0; i < policy.length; i++) {
            output += policy[i];
            if (i < policy.length - 1) {
                output += ",";
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(output);
        } catch (IOException ex) {
            System.out.println("Exception:" + ex);
        }
    }

    public static int[] importPolicy(String fileName) {

        int[] policy;
        try {
            FileReader in = new FileReader("policies/" + fileName + ".txt");
            BufferedReader reader = new BufferedReader(in);
            String line = reader.readLine();
            String[] values = line.split(",");
            policy = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                policy[i] = Integer.parseInt(values[i]);
            }
            return policy;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Exception caught: " + e);
        }
        return new int[1];
    }
}
