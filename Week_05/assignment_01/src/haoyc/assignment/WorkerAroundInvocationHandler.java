package haoyc.assignment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkerAroundInvocationHandler implements InvocationHandler {
    private Object traget;

    public WorkerAroundInvocationHandler(Object traget) {
        this.traget = traget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------前置通知_around----------");
        Object result = method.invoke(traget, args);
        System.out.println("-----------后置通知_around----------");
        return result;
    }
}
