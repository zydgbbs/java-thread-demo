package com.zydgbbs.thread.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException{
        //File file = new File(HttpServer.class.getResource("").getPath()+"/index.html");
        //System.out.println(file.exists());
        ServerSocket serverSocket = new ServerSocket(8888);
        while (!Thread.interrupted()){
            //不停地接收客户端的连接
            Socket client = serverSocket.accept();
            new Thread(new ServerThread(client)).start();
        }
    }
}
