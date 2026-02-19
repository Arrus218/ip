# Ginger User Guide

> “Tell me and I forget, teach me and I may remember, involve me and I learn." - Xunzi

Ginger frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~CUTE~~ SUPER CUTE!

All you need to do is,

1. download it from [here](https://github.com/Arrus218/ip/releases/tag/A-Release).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉

And it is **FREE**!

Features:

- [x] Managing tasks
- [x] Search using keywords
- [x] Delete tasks
- [x] Set completion
- [x] Cute cat GUI

## Adding todos

Adds a todo task. Format: `todo <task>`

Example: `todo homework`

```
Added new task:
[T][ ] homework
Now you have 1 task(s)!
```

## Adding deadlines

Adds a deadline task. Format: `deadline <task> /by <DD-MM-YYYY>`

Example: `deadline homework /by 20-02-2026`

```
Added new task:
[D][ ] homework (by: 20-02-2026)
Now you have 1 task(s)!
```

## Adding events

Adds an event task. Format: `event <task> /from <DD-MM-YYYY> /to <DD-MM-YYYY>`

Example: `event open house /from 20-02-2026 /to 21-02-2026`

```
Added new task:
[E][ ] open house (from: 20-02-2026 to: 21-02-2026)
Now you have 1 task(s)!
```

## Listing tasks

`list` shows all tasks in your list.

```
1. [T][ ] homework
Tasks: 1
```

## Deleting tasks

Deletes a task from the list. Format: `delete <index>`

Assuming you have the following list currently:
```
1. [T][ ] homework
Tasks: 1
```
Example: `delete 1`

```
Removed task:
[T][ ] homework
Now you have 0 task(s)!
```

## Marking and Unmarking tasks

Mark or unmark a task from the list as done. Format: `mark <index` for marking, `unmark <index>` for unmarking.

Assuming you have the following list currently:
```
1. [T][ ] homework
Tasks: 1
```

Example: `mark 1`

```
Yay! I have meowked this task for you!
[T][X] homework
```

Example: `unmark 1`

```
Okay, I have unmeowked this task!
[T][ ] homework
```

## Finding tasks

Searches the task list using a specified keyword. 
Format: `find <keyword>`

Assuming you have the following list currently:
```
1. [T][ ] homework
Tasks: 1
```

Example: `find work`

```
Here are the matching tasks in your list:
1. [T][ ] homework
Tasks: 1
```

## Exiting the program

Enter `bye` or click the `X` at the top right of the GUI to exit the program.

## In-App Guide

Still lost? Use `help` to access the command guide in-app!

All credit for images taken from:
https://www.catster.com/cat-breeds/lazy-cat-breeds/
https://www.rover.comg/uk/blog/orange-tabby-cat/