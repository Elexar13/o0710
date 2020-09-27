package cdi;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;
import javax.ws.rs.client.Invocation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@InterceptorBinding
@Target( { METHOD, TYPE } )
@Retention( RUNTIME )
@interface GroupInterceptor {}



@GroupInterceptor
@javax.interceptor.Interceptor
public class GRInterceptor {

    @AroundConstruct
    private void arroundConstruct(InvocationContext invocationContext){
        System.out.println("before construct");
    }

    @PostConstruct
    private void badStudentsStudy(InvocationContext invocationContext){
        System.out.println("post construct");
    }


    @AroundInvoke
    private Object aroundMethods(InvocationContext context) throws Exception {
        System.out.println("before method");
        System.out.println(context.getMethod().getName());
        return context.proceed();
    }
}
