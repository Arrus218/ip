package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import task.Task;
import task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        ui.showGoodbye();
    }

    @Override
    public void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
