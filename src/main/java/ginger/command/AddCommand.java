package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents an abstract command to add a task to the application.
 * <p>
 * This class serves as a base for specific addition commands. It encapsulates the
 * common logic of updating the task list, notifying the user via the UI,
 * and persisting changes to storage after a task is created.
 * </p>
 */
public abstract class AddCommand extends Command {
    protected String description;

    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Implements the task-specific logic for the addition process.
     * <p>
     * This method is the internal "hook" called during the execution of an addition.
     * It handles the actual insertion of the {@code Task} into the list and triggers
     * the necessary UI and Storage updates.
     * </p>
     *
     * @param task    The specific task (Todo, Deadline, or Event) to be added.
     * @param tasks   The list where the task will be appended.
     * @param ui      The interface used to confirm the addition to the user.
     * @param storage The storage handler to save the list after the modification.
     * @return
     * @throws GingerException If the task cannot be saved to the hard drive.
     */
    @Override
    protected String onExecute(Task task, TaskList tasks, Ui ui, Storage storage) throws GingerException {
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showAddedTask(task, tasks.size());
    }
}
