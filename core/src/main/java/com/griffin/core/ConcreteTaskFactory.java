package com.griffin.core;

import java.util.*;

import com.griffin.core.*;
import com.griffin.core.task.*;

public class ConcreteTaskFactory extends TaskFactory {
    private Griffin griffin;
    
    public ConcreteTaskFactory(Griffin griffin) {
        this.griffin = griffin;
    }
    
    public List<Task> getAll(Output output) {
        List<Task> tasks = new LinkedList<Task>();

        // parameterized
        // NOTE: it might not be a good idea to have
        //       parameterized tasks in the common domain

        // not parameterized
        tasks.add(new PrintHelpTask(output, this.griffin));
        tasks.add(new StopServerTask(output));
        
        return tasks;
    }
}
