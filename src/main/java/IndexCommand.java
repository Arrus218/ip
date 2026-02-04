public abstract class IndexCommand extends Command {
    protected int index;

    public IndexCommand(int index) {
        this.index = index;
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new GingerException("Meow! That task is not in my list!");
        }
    }

    protected void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) {

    }
}
