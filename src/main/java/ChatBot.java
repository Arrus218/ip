import java.util.Scanner;

public class ChatBot {
    public static void start() {
        ChatBot.padMessage("Hello! I'm Ginger.\nWhat can I do for you?");
    }

    public static void stop() {
        ChatBot.padMessage("Bye. Hope to see you again soon!");
    }

    public static void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            // Get user input
            String input = sc.nextLine();
            // Check if "bye"
            if (input.equals("bye")) {
                ChatBot.stop(); // Show generic exit message
                break; // Quit while loop
            }

            ChatBot.padMessage(input);
        }
    }

    private static void padMessage(String s) {
        ChatBot.addDashes();
        System.out.println(s);
        ChatBot.addDashes();
    }

    private static void addDashes() {
        System.out.println("------------------------------------------------");
    }
}
