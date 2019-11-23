package com.zydgbbs.thread.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer3 {
    public static void main(String[] args) throws IOException{
        ExecutorService pool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(8888);
        while (!Thread.interrupted()){
            //不停地接收客户端的连接
            Socket client = serverSocket.accept();
            //new Thread(new ServerThread(client)).start();
            pool.execute(new ServerThread(client));
        }
    }
}
