public abstract class Command {
    protected abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException;
    protected abstract void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException;
    protected boolean isExit() { return false; }
}