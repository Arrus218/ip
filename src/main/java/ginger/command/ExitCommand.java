package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to terminate the Ginger application.
 * <p>
 * When executed, this command triggers the display of the farewell message
 * and signals the application's main loop to stop processing further inputs.
 * </p>
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit sequence by displaying the goodbye message to the user.
     *
     * @param tasks   The list of tasks (unused by this command).
     * @param ui      The user interface used to display the farewell message.
     * @param storage The storage system (unused by this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return ui.showGoodbye();
    }

    /**
     * Provides an empty implementation of the internal hook.
     * <p>
     * Since an exit command does not involve specific task operations,
     * this method performs no action.
     * </p>
     *
     * @param task    Unused.
     * @param tasks   Unused.
     * @param ui      Unused.
     * @param storage Unused.
     * @return
     */
    @Override
    public String onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        return null;
    }

    /**
     * Signals that this command is an exit request.
     *
     * @return {@code true}, indicating the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
