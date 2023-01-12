package Ex2_2;
/**
 The purpose of the department is to coordinate between 2 departments. The class extends the FutureTask class and implements the Comparable interface.
 The class allow the Task objects to be used as elements in the priority queue which used by the thread pool.
 The task objects are passed to the submit method of the CustomExecutor class.
 */

import java.util.concurrent.FutureTask;

public class MyFuture<V> extends FutureTask implements Comparable<MyFuture<V>> {

    private final Task<V> task;

    public MyFuture(Task<V> task) {
        super(task.getTask());
        this.task = task;
    }

    public Task<V> getTask() {
        return task;
    }
    
    /**
     * @return the priority of the task
     */
    public int getPriority() {
        return ((task).getType().getPriorityValue());
    }

    /**
     * The method comparing to objects in type MyFuture
     * @param other the other object of type MyFuture we want to compare to ours.
     * @return 0 if the two tasks have the same priority, 1 if this task has higher priority than the other task and
     * -1 if this task has lower priority than the other task
     */
    @Override
    public int compareTo(MyFuture<V> other) {
        return task.compareTo(other.getTask());
    }
}
