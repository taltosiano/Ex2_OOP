package Ex2_2;

import java.util.concurrent.Callable;

public class Task<V> implements Comparable<Task<V>>, Callable<V> {
    private Callable<V> task;
    private TaskType type;

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

    public TaskType getType() {
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

    public int compareTo(Task other) {
        int ans = 0;
        if(other.type.getPriorityValue() > type.getPriorityValue())
            ans = 1;
        else if(other.type.getPriorityValue() < type.getPriorityValue())
            ans = -1;
        return  ans;
    }

    @Override
    public V call() throws Exception {
        return task.call();
    }

    @Override
    public String toString() {
        return "Task{" + "task=" + task + "type=" + type + '}';
    }
}