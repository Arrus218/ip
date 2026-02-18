package ginger;

import java.util.Scanner;

import ginger.task.Task;
import ginger.task.TaskList;

/**
 * Represents the user interface of the Ginger application.
 * <p>
 * This class handles all interactions with the user, including reading input
 * from the console and displaying messages, task details, and error alerts.
 * </p>
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome greeting to the user.
     */
    public String showWelcome() {
        return "Meow! I'm Ginger!\nWhat can I do for you?";
    }

    /**
     * Reads the next line of input from the user via the console.
     *
     * @return The raw string input entered by the user.
     */
    public String readCommand() {
        // Get user input
        return sc.nextLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user when a {@code GingerException} occurs.
     *
     * @param e The {@code GingerException} containing the error details.
     */
    public String showError(GingerException e) {
        return e.getMessage();
    }

    /**
     * Displays a confirmation message when a new task is added.
     *
     * @param t The task that was recently added.
     * @param length The current total number of tasks in the list.
     */
    public String showAddedTask(Task t, int length) {
        return ("Added new task:\n" + t.toString()
                + "\nNow you have " + length + " task(s)!");
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param t The task that was recently deleted.
     * @param length The current total number of tasks in the list.
     */
    public String showDeletedTask(Task t, int length) {
        return ("Removed task:\n" + t.toString()
                + "\nNow you have " + length + " task(s)!");
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     *
     * @param t The task that was recently marked for completion.
     */
    public String showMarkSuccess(Task t) {
        return ("Yay! I have meowked this task for you!\n" + t.toString());
    }

    /**
     * Displays a confirmation message when a task is unmarked as done.
     *
     * @param t The task that was recently unmarked as complete.
     */
    public String showUnmarkSuccess(Task t) {
        return ("Okay, I have unmeowked this task!\n" + t.toString());
    }

    /**
     * Formats and prints the entire list of tasks currently stored.
     *
     * @param tasks The {@code TaskList} object containing all tasks to be displayed.
     */
    public String showListTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return ("Uh-oh! There aren't any tasks in the list!");
        } else {
            return ("Here are all the tasks in your list:\n") + printTasks(tasks);
        }
    }

    /**
     * Formats and prints the list of tasks, filtered based on a keyword provided by the user.
     *
     * @param tasks The {@code TaskList} object containing the filtered tasks found using the keyword.
     */
    public String showFoundTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return ("Oops, seems like there are no matching tasks!");
        } else {
            return ("Here are the matching tasks in your list:\n") + printTasks(tasks);
        }
    }

    private String printTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            sb.append(index)
                    .append(". ")
                    .append(tasks.getTask(i).toString())
                    .append("\n");
        }

        return (sb + "Tasks: " + tasks.size());
    }

    /**
     * Displays a horizontal separator line to organize console output.
     */
    public String showSeparator() {
        return ("------------------------------------------------");
    }
}
