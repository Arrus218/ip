package ginger.task;

/**
 * Represents a basic task without any specific date or time constraints.
 * <p>
 * A {@code Todo} object is the simplest form of a task, consisting only of
 * a description and a completion status.
 * </p>
 */
public class Todo extends Task {
    /**
     * Constructs a new {@code Todo} task with the specified description.
     * The task is initialized as not done by default.
     *
     * @param description The name or details of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} task with an explicit completion status.
     * <p>
     * This constructor is primarily used by the {@code Storage} component
     * when loading existing tasks from the data file.
     * </p>
     *
     * @param description The name or details of the todo task.
     * @param isDone      The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String getTaskIcon() {
        return "[T]";
    }

    public String getTaskType() {
        return "Todo";
    }

    /**
     * Formats the todo task into a data string for file storage.
     * <p>
     * The format used is {@code Todo|Description|isDone}.
     * </p>
     *
     * @return A pipe-separated string representing the todo task.
     */
    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString();
    }

    /**
     * Returns a string representation of the todo task for UI display.
     *
     * @return A string combining the todo icon ([T]) and the base task representation.
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString();
    }
}
