package com.liulishuo.coins.monitor;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jisongzhu on 2017/8/27.
 */
@ConfigurationProperties(prefix="endpoints.jstack")
public class JstackEndPoint extends AbstractEndpoint<String> {

    public JstackEndPoint(){
        super("jstack");
    }
    @Override
    public String invoke() {
        StringBuffer result= new StringBuffer();
        Map<Thread, StackTraceElement[]> allStacks = Thread.getAllStackTraces();
        Map<Thread.State, Map<Thread, StackTraceElement[]>> classifyThreads = new HashMap<Thread.State, Map<Thread, StackTraceElement[]>>();
        if (allStacks != null) {
            Iterator<Map.Entry<Thread, StackTraceElement[]>> stackIterator = allStacks.entrySet().iterator();
            while (stackIterator.hasNext()) {
                Map.Entry<Thread, StackTraceElement[]> stackEntry = stackIterator.next();
                Thread thread = stackEntry.getKey();
                Thread.State state = thread.getState();
                StackTraceElement[] traceElements = stackEntry.getValue();
                Map<Thread, StackTraceElement[]> threadStacks = classifyThreads.get(state);
                if(threadStacks == null) {
                    threadStacks = new HashMap<Thread, StackTraceElement[]>();
                    classifyThreads.put(state, threadStacks);
                }
                threadStacks.put(thread, traceElements);

            }
        }

        for(Thread.State state : Thread.State.values()) {
            Map<Thread, StackTraceElement[]> threadStacks = classifyThreads.get(state);
            if(threadStacks != null) {
                Iterator<Map.Entry<Thread, StackTraceElement[]>> stackIterator = threadStacks.entrySet().iterator();
                result.append(state.name()+"("+threadStacks.size());
                while (stackIterator.hasNext()) {
                    Map.Entry<Thread, StackTraceElement[]> stackEntry = stackIterator.next();
                    Thread thread = stackEntry.getKey();
                    StackTraceElement[] traceElements = stackEntry.getValue();
                    if(traceElements != null) {
                        result.append("thread"+thread.getId());
                        for(StackTraceElement stack : traceElements) {
                            result.append("    " + stack + " - " + stack.getFileName());
                        }

                    }

                }

            }
        }
        return result.toString();
    }
}