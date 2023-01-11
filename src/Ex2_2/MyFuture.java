package Ex2_2; /**
 * This is an Adapter class, which extends the FutureTask class,
 * and implements the Comparable interface.
 * The main purpose of this class is to allow the Ex2_2.Task objects,
 * which are passed to the submit method of the Ex2_2.CustomExecutor class,
 * to be used as elements in the priority queue that is used by the thread pool.
 */


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyFuture<V> extends FutureTask implements Comparable<MyFuture<V>> {

    private final Task<V> task;


    public MyFuture(Callable callable) {
        super(callable);
        this.task = null;
    }


    public MyFuture(Task<V> task) {
        super(task.getTask());
        this.task = task;
    }

    public Task<V> getTask() {
        return task;
    }

    public Callable<V> getCallable() {
        return this.task.getTask();
    }

    /**
     * @return the priority of the task
     */
    public int getPriority() {
        return ((task).getType().getPriorityValue());
    }

    /**
     * This method overrides the method from Comparable , we compare two objects of type "Ex2_2.MyFuture" based
     * on their task's priority
     * @param other the object of type Ex2_2.MyFuture we compare
     * @return 1 if this task has higher priority than the other task, -1 if this task has lower priority than the other task
     * 0 if the two tasks have the same priority
     */

    @Override
    public int compareTo(MyFuture<V> other) {

        return task.compareTo(other.getTask());
//        return Integer.compare(task.getType().getPriorityValue(),
//                other.task.getType().getPriorityValue());

    }
}