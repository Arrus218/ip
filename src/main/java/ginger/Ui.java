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
    public void showWelcome() {
        this.showSeparator();
        System.out.println("Meow! I'm Ginger!\nWhat can I do for you?");
        this.showSeparator();
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
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user when a {@code GingerException} occurs.
     *
     * @param e The {@code GingerException} containing the error details.
     */
    public void showError(GingerException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays a confirmation message when a new task is added.
     *
     * @param t The task that was recently added.
     * @param length The current total number of tasks in the list.
     */
    public void showAddedTask(Task t, int length) {
        System.out.println("Added new task:\n" + t.toString()
                + "\nNow you have " + length + " task(s)!");
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param t The task that was recently deleted.
     * @param length The current total number of tasks in the list.
     */
    public void showDeletedTask(Task t, int length) {
        System.out.println("Removed task:\n" + t.toString()
                + "\nNow you have " + length + " task(s)!");
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     *
     * @param t The task that was recently marked for completion.
     */
    public void showMarkSuccess(Task t) {
        System.out.println("Yay! I have meowked this task for you!\n" + t.toString());
    }

    /**
     * Displays a confirmation message when a task is unmarked as done.
     *
     * @param t The task that was recently unmarked as complete.
     */
    public void showUnmarkSuccess(Task t) {
        System.out.println("Okay, I have unmeowked this task!\n" + t.toString());
    }

    /**
     * Formats and prints the entire list of tasks currently stored.
     *
     * @param tasks The {@code TaskList} object containing all tasks to be displayed.
     */
    public void showListTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Uh-oh! There aren't any tasks in the list!");
            return;
        } else {
            System.out.println("Here are all the tasks in your list: ");
            printTasks(tasks);
        }
    }

    /**
     * Formats and prints the list of tasks, filtered based on a keyword provided by the user.
     *
     * @param tasks The {@code TaskList} object containing the filtered tasks found using the keyword.
     */
    public void showFoundTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Oops, seems like there are no matching tasks!");
            return;
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            printTasks(tasks);
        }
    }

    private void printTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            sb.append(index)
                    .append(". ")
                    .append(tasks.getTask(i).toString())
                    .append("\n");
        }

        System.out.println(sb + "Tasks: " + tasks.size());
    }

    /**
     * Displays a horizontal separator line to organize console output.
     */
    public void showSeparator() {
        System.out.println("------------------------------------------------");
    }
}
