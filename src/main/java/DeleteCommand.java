public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = tasks.getTask(index);
        tasks.deleteTask(t);
        ui.showDeletedTask(t, tasks.size());
    }
}
