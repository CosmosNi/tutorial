package com.cosmos.design.strategy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
 * 在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。
 * 策略对象改变 context 对象的执行算法。
 * 优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
 * 缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。
 * @Date: Create in 2018-12-19 21:04
 * @Modified By：
 */
public class Application {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        int num1 = 100;
        int num2 = 10;
        Calculator calculator = new Calculator(new AdditionStrategy());
        System.out.println("people正在做加法" + calculator.operation(num1, num2));

        Calculator calculator2 = new Calculator(new SubtrationStrategy());
        System.out.println("people2正在做加法" + calculator2.operation(num1, num2));
    }
}
