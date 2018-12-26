/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 14:44
 * @Modified By：
 */
public class Apple {

    private String name;
    private String type;


    public void show(String name) {
        System.out.println("这是" + name);
    }

    public void showType(String type) {
        System.out.println("这是" + type);
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
