package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to display all tasks currently in the task list.
 * <p>
 * This command retrieves the full list of tasks and requests the UI to
 * format and display them to the user. It does not modify any data.
 * </p>
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by triggering the UI to show all tasks.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be displayed.
     * @param ui      The {@code Ui} component responsible for printing the list.
     * @param storage The {@code Storage} component (unused as no saving is required).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return ui.showListTasks(tasks);
    }

    /**
     * Provides an empty implementation as this command does not target a specific task.
     * <p>
     * Since listing all tasks is a general operation, the {@code onExecute}
     * hook is not utilized.
     * </p>
     *
     * @param t       Unused.
     * @param tasks   Unused.
     * @param ui      Unused.
     * @param storage Unused.
     * @return
     */
    @Override
    protected String onExecute(Task t, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return null;
    }
}
