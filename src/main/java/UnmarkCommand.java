public class UnmarkCommand extends IndexCommand {
    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        super.execute(tasks, ui, storage);
        Task t = tasks.getTask(super.index);
        t.setUndone();
        ui.showUnmarkSuccess(t);
        super.onExecute(t, tasks, ui, storage);
    }
}
