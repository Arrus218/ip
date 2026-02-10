package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents an abstract command that operates on a specific task identified by its index.
 * <p>
 * This class serves as a base for specific indexing-based commands. It encapsulates the
 * common logic of updating the task list, notifying the user via the UI,
 * and persisting changes to storage after a task is created.
 * </p>
 */
public abstract class IndexCommand extends Command {
    protected int index;

    public IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Validates the task index before proceeding with execution.
     * <p>
     * This method checks if the index is within the valid range of the
     * {@code TaskList}. If the index is invalid, it prevents further action
     * by throwing a {@code GingerException}.
     * </p>
     *
     * @param tasks   The list used to check the valid index range.
     * @param ui      The user interface.
     * @param storage The storage system.
     * @throws GingerException If the provided index is negative or exceeds
     * the current list size.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new GingerException("Meow! That task is not in my list!");
        }
    }

    /**
     * Implements the task-specific logic.
     * <p>
     * This method is the internal "hook" called during the execution of the indexing-based command.
     * It handles the actual update of the {@code Task} in the list and triggers
     * the necessary UI and Storage updates.
     * </p>
     *
     * @param task    The specific task (Todo, Deadline, or Event) to be updated.
     * @param tasks   The list where the task is updated.
     * @param ui      The interface used to confirm the update to the user.
     * @param storage The storage handler to save the list after the modification.
     * @throws GingerException If the task cannot be saved to the hard drive.
     */
    @Override
    protected void onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        storage.save(tasks);
    }
}
