package ginger.task;

import java.util.ArrayList;
import java.util.List;

import ginger.GingerException;

/**
 * Manages the collection of tasks in the Ginger application.
 * <p>
 * This class provides an abstraction over an {@code ArrayList} to handle
 * operations such as adding, deleting, and retrieving tasks. It also
 * facilitates the conversion of tasks into a format suitable for storage.
 * </p>
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} populated with an existing list of tasks.
     * <p>
     * This constructor creates a shallow copy of the provided list,
     * commonly used when loading data from the {@code Storage} component.
     * </p>
     *
     * @param tasks A {@code List} of tasks to initialize the list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the collection.
     *
     * @param t The {@code Task} to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes a specific task from the collection.
     *
     * @param t The {@code Task} to be removed.
     * @throws GingerException If an error occurs during the removal process.
     */
    public void deleteTask(Task t) throws GingerException {
        this.tasks.remove(t);
    }

    /**
     * Retrieves a task based on its position in the list.
     * <p>
     * Note: This method does not perform internal bounds checking beyond
     * the standard {@code ArrayList} behavior.
     * </p>
     *
     * @param index The zero-based index of the task.
     * @return The {@code Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is less than 0 or
     * greater than or equal to {@code size()}.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Converts all tasks in the list into their file-persistent string representations.
     * <p>
     * This method utilizes Java Streams to map each {@code Task} to its
     * storage-compatible format.
     * </p>
     *
     * @return A {@code List} of strings, where each string represents one task.
     */
    public List<String> toSaveFormat() {
        return this.tasks.stream().map(Task::toFileString).toList();
    }

    public List<Task> find(String keyword) {
        return this.tasks.stream().filter(x -> x.getDescription().contains(keyword)).toList();
    }
}
