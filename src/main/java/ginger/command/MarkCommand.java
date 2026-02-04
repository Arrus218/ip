package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import task.Task;
import task.TaskList;

public class MarkCommand extends IndexCommand {
    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        super.execute(tasks, ui, storage);
        Task t = tasks.getTask(super.index);
        t.setDone();
        ui.showMarkSuccess(t);
        super.onExecute(t, tasks, ui, storage);
    }
}
