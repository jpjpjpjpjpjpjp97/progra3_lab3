package Lab3Models;

public class Department {
    private String id;
    private String name;
    private String manager;

    public Department() {
    }

    public Department(String id, String name, String manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String[] getValues() {
        return new String[] {this.getId(), this.getName(), this.getManager()};
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}
