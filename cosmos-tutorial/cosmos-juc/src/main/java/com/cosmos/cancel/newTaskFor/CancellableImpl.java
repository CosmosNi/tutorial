package com.cosmos.cancel.newTaskFor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 10:31
 * @Modified By：
 */
public class CancellableImpl extends SocketUsingTask<String> {


    @Override
    public String call() throws Exception {
        return "调用成功";
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        CancellingExecutor cancellingExecutor = new CancellingExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        ServerSocket serverSocket = new ServerSocket(8080);
        CancellableImpl cancellableImpl = new CancellableImpl();
        Socket socket = serverSocket.accept();
        cancellableImpl.setSocket(socket);

        //关闭通道
        cancellingExecutor.newTaskFor(cancellableImpl).cancel(true);

    }
}
