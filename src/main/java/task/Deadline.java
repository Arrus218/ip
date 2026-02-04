package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

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

    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString() + "|" + this.by;
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }
}
