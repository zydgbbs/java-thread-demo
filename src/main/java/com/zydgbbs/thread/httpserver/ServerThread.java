package com.zydgbbs.thread.httpserver;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServerThread implements Runnable{

    private Socket client;

    private InputStream ins;
    private OutputStream out;

    private PrintWriter pw;
    private BufferedReader br;

    private static final String webroot = "/Users/liangyafei/IdeaProjects/java-thread-demo/target/classes/com/zydgbbs/thread/httpserver";
    private static final Map<String,String> contentMap = new HashMap<>();

    static {
        contentMap.put("html","text/html;charset=utf-8");
        contentMap.put("jpg","image/jpeg");
    }

    public ServerThread(Socket client){
        this.client = client;
        init();
    }

    private void init(){
        try{
            ins = client.getInputStream();
            out = client.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }


    }


    @Override
    public void run() {
        //图片访问尚未实现
        //读第一行
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        try {
            String line = reader.readLine().split(" ")[1].replace("/",File.separator);
            if("/".equals(line))
                line = "/index.html";
            System.out.println(line);
            //给用户响应
            pw = new PrintWriter(out);


            br = new BufferedReader(new FileReader(webroot+line));
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: "+contentMap.get(line.substring(line.lastIndexOf(".")+1,line.length())));
            System.out.println(contentMap.get(line.substring(line.lastIndexOf(".")+1,line.length())));
            pw.println();

            String content = null;
            while ((content=br.readLine())!=null){
                pw.print(content);
            }

            pw.flush();
            br.close();
            pw.close();
            reader.close();
            client.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
