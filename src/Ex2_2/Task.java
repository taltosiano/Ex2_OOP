package Ex2_2;

import java.util.concurrent.Callable;

/**
 * The class contains a Callable of the task and TaskType that indicates the type of task.
 * Each type has a numerical value which determines the priority of the task.
 */
public class Task<V> implements Comparable<Task<V>>, Callable<V> {
    private Callable<V> task;
    private TaskType type;

    /**
     * A default constructor for the class.
     * @param task - the task we want to execute.
     * @param type - the type of the task.
     */
    public Task(Callable task, TaskType type) { // default constructor
        this.task = task;
        this.type = type;
    }

    /**
     * A default constructor for the class.
     * When we don't get the type of the task, we define it in default to be of type other.
     * @param task - the task we want to execute.
     */
    public Task(Callable task) { // default constructor
        this.task = task;
        this.type = TaskType.OTHER;
    }

    /**
     * @return the task.
     */
    public Callable<V> getTask() {
        return task;
    }

    /**
     * @return the task type.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Creating a task using a callable of task and a TaskType.
     * @param task - Callable of task.
     * @param type - the task type.
     * @return a new task.
     */
    public static Task createTask(Callable task, TaskType type){
        return new Task(task,type);
    }

    /**
     * Creating a task using a callable of task.
     *  When we don't get the type of the task, we define it in default to be of type other.
     * @param task  - Callable of task.
     * @return a new task.
     */
    public static Task createTask(Callable task){
        return new Task(task);
    }

    /**
     * @param other - other task we compare with the current task;
     * @return {-1,0,1}.
     * if the other TaskType bigger of this TaskType it return 1.
     * if the other TaskType smaller of this TaskType it return -1.
     * if they equals it return 0.
     */
    public int compareTo(Task other) {
        int ans = 0;
        if(other.type.getPriorityValue() > type.getPriorityValue())
            ans = 1;
        else if(other.type.getPriorityValue() < type.getPriorityValue())
            ans = -1;
        return  ans;
    }

    /**
     * @return generic return value of the task
     * @throws Exception If the task cannot be performed.
     */
    @Override
    public V call() throws Exception {
        return task.call();
    }

    /**
     * @return astring of the task.
     */
    @Override
    public String toString() {
        return "Task{" + "task=" + task + "type=" + type + '}';
    }
}