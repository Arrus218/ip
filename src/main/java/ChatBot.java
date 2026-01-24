import java.util.Scanner;

public class ChatBot {
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
            }

            this.padMessage(input);
        }
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
