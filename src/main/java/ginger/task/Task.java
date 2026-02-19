package ginger.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ginger.GingerException;

/**
 * Represents the abstract base for all task types in the Ginger application.
 * <p>
 * This class encapsulates common properties such as description and completion status.
 * It also provides a static factory method to reconstruct tasks from persistent storage.
 * </p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * By default, the task is initialized as not done.
     *
     * @param description The name/details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Reconstructs a {@code Task} object from a formatted string line.
     * <p>
     * This method parses lines from the data file using a pipe-separated format.
     * Expected format: {@code Type|Description|isDone|AdditionalParams...}
     * </p>
     *
     * @param line A single line from the storage file.
     * @return The specific {@code Task} subclass instance (Todo, Deadline, or Event).
     * @throws GingerException If the line is corrupted, has missing parameters,
     *                         or contains an unknown task type.
     */
    public static Task fromFileString(String line) throws GingerException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new GingerException("Corrupted line (missing parameters): " + line);
        }
        String type = parts[0];
        String desc = parts[1];
        boolean isDone = Boolean.parseBoolean(parts[2]);

        switch (type) {
        case "Todo":
            return new Todo(desc, isDone);
        case "Deadline":
            // parts[3] is /by
            return new Deadline(desc, isDone, LocalDate.parse(parts[3],
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        case "Event":
            // parts[3] and [4] are '/from' and '/to'
            return new Event(desc, isDone,
                    LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    LocalDate.parse(parts[4], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        default:
            throw new GingerException("Unknown task type in file: " + type);
        }
    }

    /**
     * Returns the visual status icon for the task.
     *
     * @return "[X] " if the task is completed; "[ ] " otherwise.
     */
    public String getStatusIcon() {
        if (this.getStatus()) {
            return "[X] ";
        } else {
            return "[ ] "; // mark done task with X, undone empty
        }
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public abstract String getTaskType();

    public abstract String getTaskIcon();

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Formats the task's base properties into a string for file storage.
     * <p>
     * Subclasses should call this method via {@code super.toFileString()}
     * to maintain a consistent format for shared fields.
     * </p>
     *
     * @return A pipe-separated string of the description and completion status.
     */
    public String toFileString() {
        return this.getDescription() + "|" + this.getStatus();
    }

    /**
     * Returns a string representation of the task for display in the UI.
     * <p>
     * Subclasses should call this method via {@code super.toString()}
     * to maintain a consistent format for shared fields.
     * </p>
     *
     * @return A string combining the status icon and description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
