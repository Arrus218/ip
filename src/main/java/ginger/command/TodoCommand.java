package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import task.Task;
import task.TaskList;
import task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Todo(super.description);
        super.onExecute(t, tasks, ui, storage);
    }
}
