package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return ui.showHelp();
    }

    @Override
    protected String onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return null;
    }
}
