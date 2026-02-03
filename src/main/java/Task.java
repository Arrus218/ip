public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task fromFileString(String line) throws GingerException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new GingerException("Corrupted line (missing parameters): " + line);
        }
        String type = parts[0];
        String desc = parts[1];
        boolean isDone = Boolean.parseBoolean(parts[2]);

        switch (type) {
            case "Todo":
                return new Todo(desc, isDone);
            case "Deadline":
                // parts[3] is /by
                return new Deadline(desc, isDone, parts[3]);
            case "Event":
                // parts[3] and [4] are '/from' and '/to'
                return new Event(desc, isDone, parts[3], parts[4]);
            default:
                throw new GingerException("Unknown task type in file: " + type);
        }
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

    private String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    public String toFileString() {
        return this.getDescription() + "|" + this.getStatus();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
