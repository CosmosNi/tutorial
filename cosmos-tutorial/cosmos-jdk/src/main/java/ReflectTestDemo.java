import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 14:46
 * @Modified By：
 */
public class ReflectTestDemo {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //获取class对象
        Class<?> clazz = Class.forName("Apple");
        //获取该类所有的方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            //执行showType方法
            if (method.getName().equals("showType")) {
                method.invoke(clazz.newInstance(), "123");
            }
        }
        System.out.println("----------------------------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
        }
    }
}
