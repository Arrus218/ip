package ginger.command;

import ginger.GingerException;
import ginger.Storage;
import ginger.Ui;
import task.Event;
import task.Task;
import task.TaskList;

public class EventCommand extends AddCommand {
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GingerException {
        Task t = new Event(super.description, this.from, this.to);
        super.onExecute(t, tasks, ui, storage);
    }
}
