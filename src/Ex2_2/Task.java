package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static Ex2_2.TaskType.COMPUTATIONAL;
import static Ex2_2.TaskType.IO;

public class Task<V> implements Comparable<Task<V>>, Callable<V> {
    private Callable<V> task;
     static TaskType type; //סעיף 1
//    private FutureTask<V> myvalue;

    public Task() { // default constructor
        this.type = TaskType.OTHER;
    }

    public Task(Callable task, TaskType type) { // default constructor
        this.task = task;
        this.type = type;
    }

    public Task(Callable task) { // default constructor
        this.task = task;
        this.type = TaskType.OTHER;
    }

    public static TaskType getType() {
        return type;
    }

    public Callable<V> getTask() {
        return task;
    }

    public static Task createTask(Callable task, TaskType type){
        return new Task(task,type);
    }

    public static Task createTask(Callable task){
        return new Task(task);
    }

//    public Callable<V> getTask() {
//        return task;
//    }
//
//    public void setTask(Callable<V> task) {
//        this.task = task;
//    }
//
//    public static Ex2_2.TaskType getType() {
//        return type;
//    }
//
//    public static void setType(Ex2_2.TaskType type) {
//        Ex2_2.Task.type = type;
//    }

    public int compareTo(Task other) {
        return other.type.getPriorityValue() > type.getPriorityValue() ? 1 : -1;
    }



    @Override
    public V call() throws Exception {
        return task.call();
    }

    @Override
    public String toString() {
        return "Ex2_2.Task{" +
                "task=" + task +
                "type=" + type +
                '}';
    }
}