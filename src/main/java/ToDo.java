public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String getTaskIcon() {
        return "[T]";
    }

    public String getTaskType() {
        return "ToDo";
    }

    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString();
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString();
    }
}
