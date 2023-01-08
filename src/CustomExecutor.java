import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CustomExecutor {
    private final Queue tasks;
    int max = 0;

    public CustomExecutor() {
        this.tasks = new PriorityQueue();
    }

//    public void submit(Callable task, TaskType type) {
//        Task currTask = new Task();
//        currTask.createTask(task, type);
//        tasks.add(currTask);
//    }
//
//    public void submit(Callable task) {
//        Task currTask = new Task();
//        currTask.createTask(task);
//        tasks.add(currTask);
//    }

    public <V> Future<V> submit(Task<V> task) {
        FutureTask futureTask = new FutureTask(task);
        tasks.add(futureTask);
        return futureTask;
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType type) {
        Task<V> task = Task.createTask(callable, type);
        return submit(task);
    }

    public String getCurrentMax() {
        return "Current maximum priority =" + max;
    }

    public void gracefullyTerminate() {
    }
}
