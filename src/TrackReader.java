
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TrackReader {

    public static char[][] readFile(String fileName) {

        ArrayList<String> file = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(file::add);
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
            System.exit(-1);
        }

        String[] dimensions = file.remove(0).split(",");
        int numRows = Integer.parseInt(dimensions[0]);
        int numCols = Integer.parseInt(dimensions[1]);

        char[][] track = new char[numRows][numCols];

        for (int i = 0; i < file.size(); i++) {
            track[i] = file.get(i).toCharArray();
        }
        return track;
    }
}
