public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskIcon() {
        return "[D]";
    }

    public String getTaskType() {
        return "Deadline";
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString() + " (by: " + this.by + ")";
    }
}
