package pistachotodo;

import java.net.URI;

public class ToDoTask {

    private int _id;
	private String task;
	private String context;
	private String project;
	private int priority;
    private URI href;

    public int getId() {
        return _id;
    }

    public void setId(int _id){
        this._id = _id;
    }

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

    public void setHref(URI href) {
        this.href = href;
    }

    public URI getHref() {
        return href;
    }

}
