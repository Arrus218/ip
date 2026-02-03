public class DeadlineCommand extends AddCommand {
    private String by;

    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Deadline(this.description, this.by);
        super.onExecute(t, tasks, ui, storage);
    }
}
