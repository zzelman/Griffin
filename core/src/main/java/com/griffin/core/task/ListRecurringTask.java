package com.griffin.core.task;

import java.io.*;
import java.util.regex.*;

import com.griffin.core.*;
import com.griffin.core.output.*;
import com.griffin.core.recurring.*;

public class ListRecurringTask extends Task {
    private RecurringManager recurringManager;
    
    public ListRecurringTask(RecurringManager recurringManager) {
        super("list recurring",
              "executes the 'command' every 'sec' on this server",
              "list recurring: success",
              "list recurring: failure");
              
        this.recurringManager = recurringManager;
    }
    
    @Override
    public Output doAction(Communication prevComm) {
        Output output = new StartingOutput(this.command);
        
        output.addOutput(this.recurringManager.list());
        
        output.addOutput(new SuccessOutput(this.success));
        return output;
    }
}
