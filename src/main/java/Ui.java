import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        this.padMessage("Meow! I'm Ginger!\nWhat can I do for you?");
    }

    public String readCommand() {
        // Get user input
        return sc.nextLine();
    }

    public void padMessage(String s) {
        this.showSeparator();
        System.out.println(s);
        this.showSeparator();
    }

    public void showGoodbye() {
        this.padMessage("Bye! Hope to see you again soon!");
    }

    public void showError(GingerException e) {this.padMessage(e.getMessage());}

    public void showAddedTask(Task t, int length) {
        this.padMessage("Added new task:\n" + t.toString()
                + "\nNow you have " + length+ " task(s)!");
    }

    public void showDeletedTask(Task t, int length) {
        this.padMessage("Removed task:\n" + t.toString()
                + "\nNow you have " + length + " task(s)!");
    }

    public void showMarkSuccess(Task t) {
        this.padMessage("Yay! I have meowked this task for you!\n" + t.toString());
    }

    public void showUnmarkSuccess(Task t) {
        this.padMessage("Okay, I have unmeowked this task!\n" + t.toString());
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

        this.padMessage(sb + "Total tasks: " + tasks.size());
    }

    public void showSeparator() {
        System.out.println("------------------------------------------------");
    }
}
