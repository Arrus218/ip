package ginger.task;

/**
 * Represents a task that occurs within a specific time frame.
 * <p>
 * An {@code Event} object consists of a description, a starting time,
 * and an ending time. Unlike a {@code Deadline}, it represents a
 * duration rather than a single point in time.
 * </p>
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new {@code Event} task with a description and time range.
     *
     * @param description The name of the event.
     * @param from        The start time/date string.
     * @param to          The end time/date string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an {@code Event} task with an explicit completion status.
     * <p>
     * Primarily used for reconstructing tasks from a data file.
     * </p>
     *
     * @param description The name of the event.
     * @param isDone      The completion status of the task.
     * @param from        The start time/date string.
     * @param to          The end time/date string.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    public String getTaskIcon() {
        return "[E]";
    }

    public String getTaskType() {
        return "Event";
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    /**
     * Formats the event into a data string for storage.
     * <p>
     * The format used is {@code Event|Status|Description|From|To}.
     * </p>
     *
     * @return A pipe-separated string formatted for the storage file.
     */
    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString() + "|" + this.from + "|" + this.to;
    }

    /**
     * Returns a string representation of the event for display in the UI.
     *
     * @return A user-friendly string (e.g., "[E][ ] project meeting (from: Mon 2pm to: 4pm)").
     */
    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
