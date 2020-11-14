package haoyc.assignment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkerBeforeInvocationHandler implements InvocationHandler {
    private Object traget;

    public WorkerBeforeInvocationHandler(Object traget) {
        this.traget = traget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------前置通知_before----------");
        return method.invoke(traget, args);
    }
}
