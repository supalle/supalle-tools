import com.supalle.tools.code.BeanWrappers;
import com.supalle.tools.code.Student;
import com.supalle.tools.code.bytecode.PropertyAccessorImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println(new PropertyAccessorImpl().get(Student.mock()));
    }
}
