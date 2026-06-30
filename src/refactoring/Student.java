import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Student {
    private final String id;
    private final String name;
    private final Set<String> access = new HashSet<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addAccess(String courseId) {
        access.add(courseId);
        System.out.println("Granted access to course " + courseId + " for student " + name);
    }

    public Set<String> getAccess() {
        return Collections.unmodifiableSet(access);
    }
}

