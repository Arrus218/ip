public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (this.getStatus()) {
            return "[X] ";
        } else {
            return "[ ] "; // mark done task with X, undone empty
        }
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public abstract String getTaskType();

    public abstract String getTaskIcon();

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
