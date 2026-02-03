import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    private void addTask(Task t) {
        this.tasks.add(t);
    }

    private void deleteTask(int index) throws GingerException {
        this.tasks.remove(index);
    }

    private int size() {
        return this.tasks.size();
    }
}
