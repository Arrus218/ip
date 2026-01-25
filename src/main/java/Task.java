public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return "[X] ";
        } else {
            return "[ ] "; // mark done task with X, undone empty
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
