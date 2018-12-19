package com.cosmos.cancel.newTaskFor;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 10:14
 * @Modified By：
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public synchronized void cancel() {

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭socket
     */

    @Override
    public RunnableFuture newTask() {
        return new FutureTask(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                //匿名内部类使用外部类方法
                try {
                    System.out.println("Future close socket");
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }

}
