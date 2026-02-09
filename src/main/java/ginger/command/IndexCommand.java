package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

public abstract class IndexCommand extends Command {
    protected int index;

    public IndexCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new GingerException("Meow! That task is not in my list!");
        }
    }

    @Override
    protected void onExecute(Task t, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        storage.save(tasks);
    }
}
