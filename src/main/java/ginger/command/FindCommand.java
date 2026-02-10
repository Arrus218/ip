package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        TaskList foundTasks = new TaskList(tasks.find(this.keyword));
        ui.showFoundTasks(foundTasks);
    }

    @Override
    protected void onExecute(Task t, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return;
    }
}
