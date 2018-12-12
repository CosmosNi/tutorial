package com.cosmos.Timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: Timer工具管理任务的延迟执行以及周期执行。
 *  缺陷：1.只创建唯一的线程来执行所有的timer任务。如果一个timer任务的执行很耗时，那么会导致其他的
 *        TimerTask的时效准确性出现问题。
 *        2.如果TimerTask抛出未检查的异常，Timer将会产生无法预料的行为。Timer线程并不捕获异常，所以TimerTask抛出的
 *        未检查的异常会终止timer线程。
 *  建议：能不用就不要用，newScheduledThreadPool能实现Timer所有功能。
 *
 * @Date: Create in 2018-12-11 23:19
 * @Modified By：
 */
public class OutOfTime {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(1000);
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(1000);
    }

    static class ThrowTask extends TimerTask{

        public void run() {
            throw new RuntimeException();
        }
    }
}
