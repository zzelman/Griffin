package com.griffin.desktop.daemon.task;

import java.io.*;

import com.griffin.core.*;

public class HelloWorldTask extends Task {
    public HelloWorldTask(Output output) {
        super(output,
              "hello world",
              "prints hello world",
              "hello world: success",
              "hello world: failure");
    }
    
    public String doAction(Communication comm) {
        this.output.addExecutionMessage("[HelloWorldTask::doAction] client communication");
        System.out.println("[HelloWorldTask::doAction] server execution");
        
        try {
            comm.send("~~ communication from the actual Task");
        } catch (IOException e) {
            this.output.addExecutionMessage("IOException trying to direct communication");
            e.printStackTrace();
        }
        
        return this.success;
    }
}
