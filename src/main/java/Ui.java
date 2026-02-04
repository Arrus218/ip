import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN;

        public static Command fromString(String s) {
            for (Command c : Command.values()) {
                if (c.toString().equalsIgnoreCase(s)) {
                    return c;
                }
            }
            return UNKNOWN; // No match found
        }
    }

    private Storage storage = new Storage("./data/Ginger.txt");
    private ArrayList<Task> taskList = new ArrayList<>(100);
    private boolean isRunning = true;

    public void start() {
        Ui.padMessage("Meow! I'm Ginger!\nWhat can I do for you?");
        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        Ui.padMessage("Bye! Hope to see you again soon!");
        this.isRunning = false;
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (isRunning) {
            try {
                // Get user input
                String[] input = sc.nextLine().split(" ", 2);
                Command command = Command.fromString(input[0]);
                String data = input.length > 1 ? input[1] : "";

                switch (command) {
                    case BYE -> this.stop();
                    case LIST -> this.listTasks();
                    case MARK -> this.handleMark(data);
                    case UNMARK -> this.handleUnmark(data);
                    case TODO -> this.handleTodo(data);
                    case DEADLINE -> this.handleDeadline(data);
                    case EVENT -> this.handleEvent(data);
                    case DELETE -> this.deleteTask(data);
                    case UNKNOWN -> throw new GingerException("Sorry, I don't know this command!");
                }
            } catch (GingerException e) {
                Ui.padMessage(e.getMessage());
            }
        }
    }

    private void listTasks() throws GingerException {
        if (this.getNumberOfTasks() == 0) {
            throw new GingerException("No tasks found in tasklist!");
        }
        Ui.addDashes();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            System.out.println(index + ". " + t.toString());
        }
        Ui.addDashes();
    }

    private int getIndex(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("No number provided!");
        }

        int index;
        try {
            index = Integer.parseInt(data) - 1;
        } catch (NumberFormatException e) {
            throw new GingerException("Meow! That's not a number!");
        }

        if (index < 0 || index >= this.taskList.size()) {
            throw new GingerException("Meow! That task is not in my list!");
        }
        return index;
    }

    private void handleMark(String data) throws GingerException {
        Task t = this.taskList.get(this.getIndex(data));
        t.setDone();
        Ui.padMessage("Yay! I have meowked this task for you!\n" + t.toString());
    }

    private void handleUnmark(String data) throws GingerException {
        Task t = this.taskList.get(this.getIndex(data));
        t.setUndone();
        Ui.padMessage("Okay, I have unmeowked this task!\n" + t.toString());
    }

    private void handleTodo(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("Todo description not provided, meow!");
        }
        Task t = new Todo(data);
        this.addTask(t);
    }

    private void handleDeadline(String data) throws GingerException {
        int byIndex = data.indexOf("/by");
        if (byIndex == -1) {
            throw new GingerException("I need a /by date for deadlines, meow!");
        }

        String taskName = data.substring(0, byIndex).trim();
        String by = data.substring(byIndex + 3).trim(); // +3 to skip "/by"
        if (taskName.isBlank()) {
            throw new GingerException("Deadline description not provided, meow!");
        } else if (by.isBlank()) {
            throw new GingerException("Task deadline not provided, meow!");
        }

        Task t = new Deadline(taskName, by);
        this.addTask(t);
    }

    private void handleEvent(String data) throws GingerException {
        int fromIndex = data.indexOf("/from");
        int toIndex = data.indexOf("/to");

        if (fromIndex == -1) {
            throw new GingerException("I need a /from date or time, meow!");
        } else if (toIndex == -1) {
            throw new GingerException("I need a /to date or time, meow!");
        }

        String taskName = data.substring(0, fromIndex).trim();
        String from = data.substring(fromIndex + 6, toIndex).trim();
        String to = data.substring(toIndex + 4);
        if (taskName.isBlank()) {
            throw new GingerException("Event description not provided, meow!");
        } else if (from.isBlank()) {
            throw new GingerException("Event start time not provided, meow!");
        } else if (to.isBlank()) {
            throw new GingerException("Event end time not provided, meow!");
        }

        Task t = new Event(taskName, from, to);
        this.addTask(t);
    }

    private void addTask(Task t) {
        this.taskList.add(t);
        Ui.padMessage("Added new task:\n" + t.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " task(s)!");
        try {
            storage.save(this.taskList);
        } catch (IOException e) {
            Ui.padMessage("Error writing to file!");
        }
    }

    private void deleteTask(String data) throws GingerException {
        Task t = this.taskList.get(this.getIndex(data));
        this.taskList.remove(t);
        Ui.padMessage("Removed task:\n" + t.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " task(s)!");
        try {
            storage.save(this.taskList);
        } catch (IOException e) {
            Ui.padMessage("Error writing to file!");
        }
    }

    private int getNumberOfTasks() {
        return this.taskList.size();
    }

    public static void padMessage(String s) {
        Ui.addDashes();
        System.out.println(s);
        Ui.addDashes();
    }

    public void showGoodbye() {
        Ui.padMessage("Bye! Hope to see you again soon!");
    }

    public void showError(GingerException e) {Ui.padMessage(e.getMessage());}

    public void showAddedTask(Task t, int length) {
        Ui.padMessage("Added new task:\n" + t.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " task(s)!");
    }

    public void showDeletedTask(Task t, int length) {
        Ui.padMessage("Removed task:\n" + t.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " task(s)!");
    }

    public void showMarkSuccess(Task t) {
        Ui.padMessage("Yay! I have meowked this task for you!\n" + t.toString());
    }

    public void showUnmarkSuccess(Task t) {
        Ui.padMessage("Okay, I have unmeowked this task!\n" + t.toString());
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

        Ui.padMessage(sb + "Total tasks: " + tasks.size());
    }

    public static void addDashes() {
        System.out.println("------------------------------------------------");
    }
}
