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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        TaskList foundTasks = new TaskList(tasks.find(this.keyword));
        return ui.showFoundTasks(foundTasks);
    }

    @Override
    protected String onExecute(Task t, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return null;
    }
}
