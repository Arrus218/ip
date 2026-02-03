public class TodoCommand extends AddCommand {

    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Todo(this.description);
        super.onExecute(t, tasks, ui, storage);
    }
}
