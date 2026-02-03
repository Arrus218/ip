public class MarkCommand extends IndexCommand {
    public MarkCommand(int index) {
        super(index);
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = tasks.getTask(super.index);
        t.setDone();
        super.onExecute(t, tasks, ui, storage);
    }
}
