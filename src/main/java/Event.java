public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

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

    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
