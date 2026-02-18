package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents an executable command in the Ginger application.
 * <p>
 * This abstract class defines the structure for all user actions. It follows the
 * Command Pattern, where each subclass implements specific logic to interact
 * with the task list, user interface, and storage system.
 * </p>
 */
public abstract class Command {
    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The {@code TaskList} containing the current tasks.
     * @param ui      The {@code Ui} for handling user interaction.
     * @param storage The {@code Storage} for saving or loading data.
     * @throws GingerException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws GingerException;

    /**
     * Defines the specific internal logic to be performed on a task.
     * <p>
     * This method is called by {@code execute} after initial checks are passed.
     * Subclasses should override this to provide custom behavior (e.g., adding to a list
     * or marking as done) for their specific task type.
     * </p>
     *
     * @param task    The task associated with the command.
     * @param tasks   The current task list.
     * @param ui      The user interface.
     * @param storage The storage system.
     * @return
     * @throws GingerException If internal execution fails.
     */
    protected abstract String onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return {@code true} if the command is an exit command; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
