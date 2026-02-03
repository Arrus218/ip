public class ListCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        ui.showAllTasks(tasks);
    }
}
