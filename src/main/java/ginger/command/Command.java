package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException;
    protected abstract void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException;
    public boolean isExit() { return false; }
}