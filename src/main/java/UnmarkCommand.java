public class UnmarkCommand extends IndexCommand {

    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = tasks.getTask(super.index);
        t.setUndone();
        super.onExecute(t, tasks, ui, storage);
    }
}
