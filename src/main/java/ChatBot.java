public class ChatBot {
    public static void start() {
        ChatBot.addDashes();
        System.out.println("Hello! I'm Ginger.\nWhat can I do for you?");
        ChatBot.addDashes();
    }

    public static void stop() {
        System.out.println("Bye. Hope to see you again soon!");
        ChatBot.addDashes();
    }

    private static void addDashes() {
        System.out.println("------------------------------------------------");
    }
}
