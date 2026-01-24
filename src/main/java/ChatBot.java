import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK;

        public static Command fromString(String s) {
            for (Command c : Command.values()) {
                if (c.toString().equalsIgnoreCase(s)) {
                    return c;
                }
            }
            return null; // No match found
        }
    }

    private final ArrayList<Task> taskList = new ArrayList<>(100);

    public void start() {
        this.padMessage("Hello! I'm Ginger.\nWhat can I do for you?");
    }

    public void stop() {
        this.padMessage("Bye. Hope to see you again soon!");
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            // Get user input
            String input = sc.nextLine();
            String cmdWord = (!input.contains(" ")) ? input : input.substring(0, input.indexOf(" "));
            Command command = Command.fromString(cmdWord);
            // Check if null
            if (command != null) {
                switch (command) {
                    case BYE:
                        this.stop(); // Show generic exit message
                        break; // Quit while loop
                    case LIST:
                        this.listTasks();
                        continue;
                    case MARK: {
                        int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
                        this.setTaskCompletion(index, true);
                        continue;
                    }
                    case UNMARK: {
                        int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
                        this.setTaskCompletion(index, false);
                        continue;
                    }
                }
            } else {
                this.padMessage("Added: " + input);
                this.taskList.add(new Task(input));
            }

        }
    }

    private void listTasks() {
        ChatBot.addDashes();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            System.out.println(index + ". " + "[" + t.getStatusIcon() + "] " + t.getDescription());
        }
        ChatBot.addDashes();
    }

    private void setTaskCompletion(int index, boolean b) {
        Task t = this.taskList.get(index);
        t.setDone(b);
        ChatBot.addDashes();
        if (b) {
            System.out.println("I have meowked this task for completion!");
        } else {
            System.out.println("Okay, I have unmeowked this task!");
        }
        System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
        ChatBot.addDashes();
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
