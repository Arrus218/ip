import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class ChatBot {
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

    private final ArrayList<Task> taskList = new ArrayList<>(100);
    private boolean isRunning = true;

    public void start() {
        this.padMessage("Meow! I'm Ginger!\nWhat can I do for you?");
    }

    public void stop() {
        this.padMessage("Bye! Hope to see you again soon!");
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
                    case TODO -> this.handleToDo(data);
                    case DEADLINE -> this.handleDeadline(data);
                    case EVENT -> this.handleEvent(data);
                    case DELETE -> this.deleteTask(data);
                    case UNKNOWN -> throw new GingerException("Sorry, I don't know this command!");
                }
            } catch (GingerException e) {
                this.padMessage(e.getMessage());
            }
        }
    }

    private void listTasks() throws GingerException {
        if (this.getNumberOfTasks() == 0) {
            throw new GingerException("No tasks found in tasklist!");
        }
        ChatBot.addDashes();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            System.out.println(index + ". " + t.toString());
        }
        ChatBot.addDashes();
    }

    private Task getTaskFromIndex(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("No number provided!");
        }

        int index;
        try {
            index = Integer.parseInt(data) - 1;
        } catch (NumberFormatException e) {
            throw new GingerException("Meow! That's not a number!", e);
        }

        if (index < 0 || index >= this.taskList.size()) {
            throw new GingerException("Meow! That task is not in my list!");
        }
        return this.taskList.get(index);
    }

    private void handleMark(String data) throws GingerException {
        Task t = getTaskFromIndex(data);
        t.setDone();
        this.padMessage("Yay! I have meowked this task for you!\n" + t.toString());
    }

    private void handleUnmark(String data) throws GingerException {
        Task t = getTaskFromIndex(data);
        t.setUnDone();
        this.padMessage("Okay, I have unmeowked this task!\n" + t.toString());
    }

    private void handleToDo(String data) throws GingerException {
        if (data.isBlank()) {
            throw new GingerException("ToDo description not provided, meow!");
        }
        Task t = new ToDo(data);
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
        this.padMessage("Added new task:\n" + t.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " task(s)!");
    }

    private void deleteTask(String data) throws GingerException {
        Task t = this.getTaskFromIndex(data);
        this.taskList.remove(t);
        this.padMessage("Removed task:\n" + t.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " task(s)!");
    }

    private int getNumberOfTasks() {
        return this.taskList.size();
    }

    private void padMessage(String s) {
        ChatBot.addDashes();
        System.out.println(s);
        ChatBot.addDashes();
    }

    private static void addDashes() {
        System.out.println("------------------------------------------------");
    }
}
