import java.util.Scanner;

public class ChatBot {
    public static void start() {
        ChatBot.addDashes();
        System.out.println("Hello! I'm Ginger.\nWhat can I do for you?");
        ChatBot.addDashes();
    }

    public static void stop() {
        ChatBot.addDashes();
        System.out.println("Bye. Hope to see you again soon!");
        ChatBot.addDashes();
    }

    public static void echo() {
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

            ChatBot.addDashes();
            System.out.println(input);
            ChatBot.addDashes();
        }

    }
    private static void addDashes() {
        System.out.println("------------------------------------------------");
    }
}
