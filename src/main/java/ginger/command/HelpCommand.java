package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command that displays help information to the user.
 * This command provides guidance on how to use the various features of the application.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command by requesting the UI to display the help message.
     *
     * @param tasks The list of tasks (unused by this command).
     * @param ui The user interface object to handle output.
     * @param storage The storage object for saving/loading tasks (unused by this command).
     * @return A string containing the help information for the user.
     * @throws GingerException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return ui.showHelp();
    }

    /**
     * Internal execution logic for commands that operate on a specific task.
     * As HelpCommand is a general utility command, this method returns null.
     *
     * @param task The specific task to operate on.
     * @param tasks The current task list.
     * @param ui The user interface object.
     * @param storage The storage handler.
     * @return null, as HelpCommand does not target a specific task.
     * @throws GingerException If an error occurs during execution.
     */
    @Override
    protected String onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return null;
    }
}
