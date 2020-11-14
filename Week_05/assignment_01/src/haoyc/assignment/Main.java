package haoyc.assignment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws Exception{
        Worker itWorker = new ItWorker();

        ClassLoader classLoader = itWorker.getClass().getClassLoader();;
        
        Class[] interfaces = itWorker.getClass().getInterfaces();

        InvocationHandler beforeInvocationHandler = InvocationHandlerFactory.build(itWorker, WorkerBeforeInvocationHandler.class);
        InvocationHandler aroundInvocationHandler = InvocationHandlerFactory.build(itWorker, WorkerAroundInvocationHandler.class);
        InvocationHandler afterInvocationHandler = InvocationHandlerFactory.build(itWorker, WorkerAfterInvocationHandler.class);

        Worker beforeWorkerProxy = (Worker) Proxy.newProxyInstance(classLoader, interfaces, beforeInvocationHandler);
        Worker aroundWorkerProxy = (Worker) Proxy.newProxyInstance(classLoader, interfaces, aroundInvocationHandler);
        Worker afterWorkerProxy = (Worker) Proxy.newProxyInstance(classLoader, interfaces, afterInvocationHandler);

        beforeWorkerProxy.work();
        System.out.println("=================================================");
        aroundWorkerProxy.work();
        System.out.println("=================================================");
        afterWorkerProxy.work();
    }
}
