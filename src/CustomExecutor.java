import java.util.*;

public class CustomExecutor {
    Queue tasks = new PriorityQueue();
    int max = 0;
    public void submit(Object task, TaskType type) {
        Task currTask = new Task();
        currTask.createTask(task, type);
        tasks.add(currTask);
    }

    public String getCurrentMax() {
        return "Current maximum priority =" + max;
    }

    public void gracefullyTerminate() {
    }
}
