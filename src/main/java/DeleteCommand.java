public class DeleteCommand extends IndexCommand {

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = tasks.getTask(index);
        tasks.deleteTask(t);
        ui.showDeletedTask(t, tasks.size());
    }
}
