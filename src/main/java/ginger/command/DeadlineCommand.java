package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Deadline;
import ginger.task.Task;
import ginger.task.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Deadline(super.description, this.by);
        super.onExecute(t, tasks, ui, storage);
    }
}
