package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import task.Task;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        ui.showAllTasks(tasks);
    }

    @Override
    protected void onExecute(Task t, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return;
    }
}
