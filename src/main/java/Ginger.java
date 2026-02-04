import java.io.IOException;

public class Ginger {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ginger(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (GingerException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSeparator(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GingerException e) {
                ui.showError(e);
            } finally {
                ui.showSeparator();
            }
        }
    }

    public static void main(String[] args) {
        new Ginger("./data/Ginger.txt").run();
    }
}
