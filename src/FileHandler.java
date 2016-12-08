
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void exportPolicy(double[][] policy, String fileName) {

        List<String> lines = new ArrayList<>();
        for (double[] p : policy) {
            String line = "";
            for (int j = 0; j < policy[0].length; j++) {
                line += p[j];
                if (j < policy[0].length - 1) {
                    line += ",";
                }
            }
            lines.add(line);
        }
        try {
            Files.write(Paths.get("policies/" + fileName + ".txt"), lines);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static double[][] importPolicy(String fileName) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("policies/" + fileName + ".txt"));
            double[][] policy = new double[lines.size()][9];
            for (int i = 0; i < lines.size(); i++) {
                String[] elements = lines.get(i).split(",");
                double[] values = new double[elements.length];
                for (int j = 0; j < elements.length; j++) {
                    values[j] = Double.parseDouble(elements[j]);
                }
                policy[i] = values;
            }
            return policy;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return new double[0][0];
    }
}
