package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

public class DeleteCommand extends IndexCommand {
    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        super.execute(tasks, ui, storage);
        Task t = tasks.getTask(index);
        tasks.deleteTask(t);
        ui.showDeletedTask(t, tasks.size());
    }
}
