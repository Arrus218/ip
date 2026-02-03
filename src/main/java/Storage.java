import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(path.getParent());
        Files.write(path, tasks.stream().map(Task::toFileString).toList());
    }

    public ArrayList<Task> load() throws IOException {
        Path path = Paths.get("./data/Ginger.txt");
        ArrayList<Task> tasks = new ArrayList<>(100);

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            try {
                Task task = Task.fromFileString(line);
                tasks.add(task);
            } catch (GingerException e) {
                System.err.println(e.getMessage());
            }
        }

        return tasks;
    }
}
