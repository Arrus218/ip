public class Ginger {
    private Storage storage;
    private TaskList tasks;
    private ChatBot bot;

    public Ginger(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.bot = new ChatBot();
    }

    public void run() {
        this.bot.start();
        this.bot.listen();
    }

    public static void main(String[] args) {
        new Ginger("./data/Ginger.txt").run();
    }
}
