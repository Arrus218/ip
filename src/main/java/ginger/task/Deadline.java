package ginger.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline date.
 * <p>
 * A {@code Deadline} object contains a description and a {@code LocalDate}
 * indicating when the task is due. It provides methods to format this date
 * for both UI display and file persistence.
 * </p>
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new {@code Deadline} task with a description and due date.
     *
     * @param description The name of the deadline task.
     * @param by          The {@code LocalDate} representing the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a {@code Deadline} task with an explicit completion status.
     * <p>
     * This constructor is typically used when loading tasks from storage.
     * </p>
     *
     * @param description The name of the deadline task.
     * @param isDone      The completion status of the task.
     * @param by          The {@code LocalDate} representing the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    public String getTaskIcon() {
        return "[D]";
    }

    public String getTaskType() {
        return "Deadline";
    }

    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Formats the task into a data string for storage in a file.
     * <p>
     * The format used is {@code Deadline|Status|Description|dd-MM-yyyy}.
     * </p>
     *
     * @return A pipe-separated string representing the task.
     */
    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString() + "|"
                + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Returns a string representation of the deadline for the UI.
     * <p>
     * Includes the task icon, status icon, description, and the formatted date.
     * </p>
     *
     * @return A user-friendly string (e.g., "[D][ ] finish report (by: 01-01-2026)").
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }
}
