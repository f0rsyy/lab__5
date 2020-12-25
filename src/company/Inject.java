package company;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

public class Inject {
    public <T> T inject(T object) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/com/company/testData.properties "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof AutoInject) {
                    String fieldClass = field.getType().toString();
                    fieldClass = fieldClass.substring(fieldClass.indexOf(" ") + 1);
                    String classToInject = properties.getProperty(fieldClass);
                    field.set(object, Class.forName(classToInject).newInstance());
                }
            }
        }
        return object;
    }
}
