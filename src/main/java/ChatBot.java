import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    public enum Command {
        BYE {
            @Override
            public void execute(ChatBot chatBot, int index) {
                chatBot.stop();
                // Show generic exit message
            };
        },
        LIST {
            @Override
            public void execute(ChatBot chatBot, int index) {
                chatBot.listTasks();
            }
        },
        MARK {
            @Override
            public void execute(ChatBot chatBot, int index) {
                chatBot.setTaskCompletion(index, true);
            }
        },
        UNMARK {
            @Override
            public void execute(ChatBot chatBot, int index) {
                chatBot.setTaskCompletion(index, false);
            }
        };

        public abstract void execute(ChatBot chatBot, int index);

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
        this.padMessage("Meow! I'm Ginger!\nWhat can I do for you?");
    }

    public void stop() {
        this.padMessage("Bye! Hope to see you again soon!");
        System.exit(0);
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            // Get user input
            String input = sc.nextLine();
            int index = 0;
            Command command = null;

            if (!input.contains(" ")) {
                 command = Command.fromString(input);
            } else {
                 command = Command.fromString(input.substring(0, input.indexOf(" ")));
                 index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            }

            // Check if null
            if (command != null) {
                command.execute(ChatBot.this, index);
            } else {
                this.padMessage("Added: " + input);
                this.taskList.add(new ToDo(input));
            }
        }
    }

    private void listTasks() {
        if (this.taskList.isEmpty()) {
            this.padMessage("No tasks found!");
            return;
        }
        ChatBot.addDashes();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            System.out.println(index + ". " + t.toString());
        }
        ChatBot.addDashes();
    }

    private void setTaskCompletion(int index, boolean b) {
        Task t = this.taskList.get(index);
        if (b) {
            t.setDone();
            ChatBot.addDashes();
            System.out.println("I have meowked this task for completion!");
        } else {
            t.setUnDone();
            ChatBot.addDashes();
            System.out.println("Okay, I have unmeowked this task!");
        }
        System.out.println(t.toString());
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
