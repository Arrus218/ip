package ginger;

import java.util.Scanner;

import ginger.task.Task;
import ginger.task.TaskList;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        this.showSeparator();
        System.out.println("Meow! I'm Ginger!\nWhat can I do for you?");
        this.showSeparator();
    }

    public String readCommand() {
        // Get user input
        return sc.nextLine();
    }

    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void showError(GingerException e) {System.out.println(e.getMessage());}

    public void showAddedTask(Task t, int length) {
        System.out.println("Added new task:\n" + t.toString()
                + "\nNow you have " + length+ " task(s)!");
    }

    public void showDeletedTask(Task t, int length) {
        System.out.println("Removed task:\n" + t.toString()
                + "\nNow you have " + length + " task(s)!");
    }

    public void showMarkSuccess(Task t) {
        System.out.println("Yay! I have meowked this task for you!\n" + t.toString());
    }

    public void showUnmarkSuccess(Task t) {
        System.out.println("Okay, I have unmeowked this task!\n" + t.toString());
    }

    public void showAllTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            sb.append(index)
                    .append(". ")
                    .append(tasks.getTask(i).toString())
                    .append("\n");
        }

        System.out.println(sb + "Total tasks: " + tasks.size());
    }

    public void showSeparator() {
        System.out.println("------------------------------------------------");
    }
}
