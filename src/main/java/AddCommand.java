public abstract class AddCommand extends Command {
    protected String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    protected void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks);
    }
}
