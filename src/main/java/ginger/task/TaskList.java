package ginger.task;

import java.util.ArrayList;
import java.util.List;

import ginger.GingerException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
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

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public List<String> toSaveFormat() {
        return this.tasks.stream().map(Task::toFileString).toList();
    }

    public List<Task> find(String keyword) {
        return this.tasks.stream().filter(x -> x.getDescription().contains(keyword)).toList();
    }
}
