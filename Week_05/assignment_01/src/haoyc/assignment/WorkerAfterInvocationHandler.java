package haoyc.assignment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkerAfterInvocationHandler implements InvocationHandler {
    private Object traget;

    public WorkerAfterInvocationHandler(Object traget) {
        this.traget = traget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(traget, args);
        System.out.println("-----------后置通知_after----------");
        return result;
    }
}
