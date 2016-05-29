package com.griffin.core;

import java.util.*;

import com.griffin.core.*;

public class Griffin {
    private final String noExistErrorMsg;
    private final String startingMsg;
    private final String endingMsg;
    
    private Output output;
    private List<Task> tasks;
    
    public Griffin(TaskFactory taskFactory) {
        this.noExistErrorMsg = "command does not exist";
        this.startingMsg = "starting message";
        this.endingMsg = "ending message";
        
        this.output = new Output();
        this.tasks = taskFactory.getAll();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public Output getOutput() {
        return this.output;
    }
    
    public String doCommand(String command) {
        // check for command
        boolean exists = this.doesCommandExist(command);
        if (!exists) {
            this.output.addMessage(this.noExistErrorMsg);
            this.output.addMessage(command);
            return this.output.getMessages();
        }
        
        // say the command is about to start
        this.output.addMessage(this.startingMsg);
        
        // execute the task
        String returnMsg = this.doTask(command);
        
        // say the return value of the task
        this.output.addMessage(returnMsg);
        
        // say all tasks have been completed
        this.output.addMessage(this.endingMsg);
        
        return this.output.getMessages();
    }
    
    private boolean doesCommandExist(String command) {
        for (Task t : this.tasks) {
            if (command.contains(t.getCommand())) {
                return true;
            }
        }
        return false;
    }
    
    private String doTask(String command) {
        for (Task t : this.tasks) {
            if (command.contains(t.getCommand())) {
                return t.doAction();
            }
        }
        return null;
    }
}
