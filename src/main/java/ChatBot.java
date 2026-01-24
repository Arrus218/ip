import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    private ArrayList<Task> taskList = new ArrayList<>(100);

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
            // Check if "bye"
            if (input.equals("bye")) {
                this.stop(); // Show generic exit message
                break; // Quit while loop
            } else if (input.equals("list")) {
                this.listTasks();
            } else {
                this.padMessage("Added: " + input);
                this.taskList.add(new Task(input));
            }
        }
    }

    private void listTasks() {
        ChatBot.addDashes();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i+1;
            Task t =  this.taskList.get(i);
            System.out.println(index + ". " + "[" + t.getStatusIcon() + "] " + t.getDescription());
        }
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
