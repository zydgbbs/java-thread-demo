package com.zydgbbs.thread.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {
    public static void main(String[] args) throws IOException{
        //File file = new File(HttpServer.class.getResource("").getPath()+"/index.html");
        //System.out.println(file.exists());
        ServerSocket serverSocket = new ServerSocket(8888);
        while (!Thread.interrupted()){
            //不停地接收客户端的连接
            Socket client = serverSocket.accept();

            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();

            //要了解HTTP协议，读取请求内容
            int length = 0;
            byte[] buffer = new byte[1024];
            /*
            while ((length=inputStream.read(buffer))!=-1){
                System.out.println(new String(buffer,0,length));
            }*/
            //读第一行
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String first = bufferedReader.readLine();
            System.out.println(first);
            //给用户响应
            PrintWriter printWriter = new PrintWriter(outputStream);
            BufferedReader bf = new BufferedReader(new FileReader(HttpServer2.class.getResource("").getPath()+"index.html"));
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type: text/html;charset=utf-8");
            //printWriter.println("Content-Length: 30");
            printWriter.println();

            String content = null;
            while ((content=bf.readLine())!=null){
                printWriter.print(content);
            }
            printWriter.flush();
            printWriter.close();
            bf.close();
            bufferedReader.close();
            bufferedReader.close();
            serverSocket.close();

        }


    }
}
