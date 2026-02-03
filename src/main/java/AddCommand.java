public abstract class AddCommand extends Command {
    protected String description;

    public AddCommand(String description) {
        this.description = description;
    }

    protected void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
    }
}
