package ginger.command;

import java.time.LocalDate;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import ginger.task.Deadline;
import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents a command to add a {@code Deadline} task to the task list.
 * <p>
 * This class handles the creation of a {@code Deadline} object using the provided
 * description and date, then delegates the addition process to the parent
 * {@code AddCommand} logic.
 * </p>
 */

public class DeadlineCommand extends AddCommand {
    private LocalDate by;

    /**
     * Constructs a {@code DeadlineCommand} with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param by          The date by which the task must be completed.
     */
    public DeadlineCommand(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Executes the deadline addition process.
     * <p>
     * This method creates a new {@code Deadline} task instance and passes it to
     * the {@code onExecute} hook defined in {@code AddCommand} to handle
     * list management, UI feedback, and storage persistence.
     * </p>
     *
     * @param tasks   The list where the deadline will be added.
     * @param ui      The user interface for displaying the "Added task" message.
     * @param storage The storage handler for saving the updated list.
     * @throws GingerException If an error occurs during the {@code onExecute} process,
     * typically due to issues saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Deadline(super.description, this.by);
        super.onExecute(t, tasks, ui, storage);
    }
}
