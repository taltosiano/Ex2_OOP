
public class Task {
    private static Object task;
    private static TaskType type; //סעיף 1

    public Task() { // default constructor
        setType(TaskType.OTHER);
        setTask("There is no task");
    }

    public static void createTask(Object task, TaskType type){ // סעיף 4
        setTask(task);
        setType(type);
        // צריך לעשות שיהיה אפשר גם לא להכניס סוג משימה כי הגדרתי בבנאי דיפולט
    }

    private static void setTask(Object task) {
        Task.task = task;
    }

    private static void setType(TaskType type) {
        Task.type = type;
    }

    public static Object getTask() {
        return task;
    }

    public static TaskType getType() {
        return type;
    }

//    public boolean compare(Task other) { // סעיף 5
//        return other.getType().equals(getType());
//    }

    public int compareTo(Task other) {
        return other.getType().getPriorityValue() > getType().getPriorityValue() ? 1 : -1;
    }
    public static void runTask(){ // סעיף 2
        try{  // לא הבנתי איך מבצעים את המשימה

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // סעיף 3 עם פקטורי לא הבנתי מה לעשות
}
