
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static void exportPolicy(int[] policy, String fileName) {    //of format StateID, action \n

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < policy.length; i++) {
            lines.add("StateID: " + i + "\tAction: " + policy[i]);
        }
        try {
            Files.write(Paths.get("policies/" + fileName + ".txt"), lines);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static int[] importPolicy(String fileName) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("policies/" + fileName + ".txt"));
            int[] policy = new int[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String[] elements = lines.get(i).split("Action: ");
                policy[i] = Integer.parseInt(elements[1]);
            }
            return policy;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return new int[0];
    }
}
