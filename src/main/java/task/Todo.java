package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

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

    @Override
    public String toFileString() {
        return this.getTaskType() + "|" + super.toFileString();
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + super.toString();
    }
}
