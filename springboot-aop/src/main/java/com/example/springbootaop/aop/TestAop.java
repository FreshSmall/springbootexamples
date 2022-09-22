package com.example.springbootaop.aop;


import com.example.springbootaop.request.AopRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

/**
 * @author: yinchao
 * @ClassName: TestAop
 * @Description: 测试 aop 切片
 * @team wuhan operational dev.
 * @date: 2022/9/22 09:34
 */
@Aspect
@Service
public class TestAop {

    @Before("execution(* com.example.springbootaop.web.AopController.testAop(..))")
    public void runAop(JoinPoint joinPoint){
        /*Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数："+arg);
        }
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
*/
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();

        //获取代理方法的参数
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        //获取参数名
        String[] argsName = methodSignature.getParameterNames();

        if (null != argsName) {
            if ("request".equalsIgnoreCase(argsName[0])) {
                AopRequest dto = (AopRequest) args[0];
                dto.setAddress("北京");
                args[0] = dto;
            }
        }
    }
}
