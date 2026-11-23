# Task Manager

A command-line task manager written in Java. Tasks are saved to a file, so they are still there the next time you run it.

## Features

- Add, list, complete, and delete tasks
- Tasks are saved to `tasks.txt` after every change and loaded on startup
- Input validation: blank titles, non-numeric input, and unknown task IDs are handled without crashing
- Clear error messages if the data file can't be read or written

## Requirements

- Java 25

## How to run

From the project folder:

```
javac -d out src/*.java
java -cp out Main
```

Tested in Windows PowerShell. `tasks.txt` is created in the folder you run the program from.

## Project structure

The code is split so that each class has one job:

- `Main`: creates the objects and starts the app. It only wires things together.
- `App`: runs the main loop and decides what to do for each menu choice.
- `UI`: all console input and output. It's the only class that prints or reads. 
Error text comes from the exceptions that `TaskStorage` throws, and `UI` displays it.
- `TaskManager`: the task logic (add, complete, delete, ID tracking). It never prints or reads input.
- `TaskStorage`: reads and writes the data file. It's the only class that knows the file format.
- `Task`: a plain data class for a single task.

Dependencies are passed in through constructors (`App` receives `UI` and `TaskManager`, `TaskManager` receives `TaskStorage`) instead of being created inside the classes. Storage failures are reported with exceptions and shown to the user by `App` and `Main`.

## Known limitations

- Task IDs can be reused after a restart if the task with the highest ID was deleted.
- Task titles can't contain the `|` character, because it is the field separator in the data file.
- A hand-edited or corrupted `tasks.txt` can stop the app from starting.