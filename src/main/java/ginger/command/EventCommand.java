package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Event;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to add an {@code Event} task to the task list.
 * <p>
 * This class handles the creation of an {@code Event} object using the provided
 * description, from date/time, and to date/time, then delegates the addition process to the parent
 * {@code AddCommand} logic.
 * </p>
 */
public class EventCommand extends AddCommand {
    private String from;
    private String to;

    /**
     * Constructs a {@code DeadlineCommand} with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param from        The starting date/time of the task.
     * @param to          The ending date/time of the task.
     */
    public EventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event addition process.
     * <p>
     * This method creates a new {@code Event} task instance and passes it to
     * the {@code onExecute} hook defined in {@code AddCommand} to handle
     * list management, UI feedback, and storage persistence.
     * </p>
     *
     * @param tasks   The list where the event will be added.
     * @param ui      The user interface for displaying the "Added task" message.
     * @param storage The storage handler for saving the updated list.
     * @throws GingerException If an error occurs during the {@code onExecute} process,
     * typically due to issues saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Event(super.description, this.from, this.to);
        super.onExecute(t, tasks, ui, storage);
    }
}
