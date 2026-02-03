public abstract class IndexCommand extends Command {
    protected int index;

    public IndexCommand(int index) {
        this.index = index;
    }

    protected void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) {

    }
}
