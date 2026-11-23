public class Task {
    private final int id;
    private final String title;
    private boolean isComplete;

    public Task(int id, String title, boolean isComplete) {
        this.id = id;
        this.title = title;
        this.isComplete = isComplete;
    }

    public int getId(){ return id;}
    public String getTitle(){ return title;}
    public boolean getStatus(){ return isComplete;}

    public void setStatusComplete(){
        this.isComplete = true;
    }

    @Override
    public String toString(){
        String status = isComplete ? "[X]" : "[ ]";
        return String.format("%d. %s %s", id, status, title);
    }

}


