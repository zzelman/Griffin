package com.griffin.nameserver;

import java.io.*;
import java.net.*;

import com.griffin.core.*;

public class NameserverClient {
    public static void main(String[] args) {
        ServerInfo info = null;
        {
            String name = "nameserver";
            String hostName = "10.0.0.31";
            int port = 6001;
            info = new ServerInfo(name, hostName, port);
        }
        
        NameserverAction action = null;
        {
            String name = "daemon";
            String hostName = "10.0.0.31";
            int port = 6000;
            action = new NameserverAction(new ServerInfo(name, hostName, port));
        }
        
        Socket socket = null;
        Communication nextComm = null;
        try {
            socket = new Socket(info.getHostName(), info.getPort());
            nextComm = new Communication(socket);
            
            nextComm.send(action);
            
            Object ret;
            while (!Thread.currentThread().isInterrupted()) {
                ret = nextComm.receive();
                if (ret instanceof StopCommunication || ret == null) {
                    break;
                }
                
                System.out.println(ret);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                nextComm.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}