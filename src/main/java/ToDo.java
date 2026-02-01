public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
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
