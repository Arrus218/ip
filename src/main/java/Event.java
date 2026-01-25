public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTaskIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
