public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        if (this.description.isBlank()) {
            throw new GingerException("Todo description not provided, meow!");
        }
        Task t = new Todo(this.description);
        tasks.addTask(t);
        ui.showAddedTask(t);
    }

    @Override
    public boolean isExit() {
        return super.isExit();
    }
}
