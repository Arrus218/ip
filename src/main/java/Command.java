public abstract class Command {
    public abstract void execute(TaskList tasks, Ui chatBot, Storage storage) throws GingerException;
    public boolean isExit() { return false; }
}