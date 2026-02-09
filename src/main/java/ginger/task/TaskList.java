package task;

import ginger.GingerException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void deleteTask(Task t) throws GingerException {
        this.tasks.remove(t);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }
    
    public List<String> toSaveFormat() {
        return this.tasks.stream().map(Task::toFileString).toList();
    }
}
