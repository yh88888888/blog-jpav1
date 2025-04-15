package shop.mtcoding.blog._core.error;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component //메모리 IOC에 띄운다.
@Aspect //Proxy가 된다.
public class GlobalValidationHandler {

    @Before("@annotation(shop.mtcoding.blog._core.error.anno.MyBefore)")
    public void beforeAdvice(JoinPoint jp)//jp에 해당 리플렉션정보가 모두 투영되어 있다.
    {
        String name = jp.getSignature().getName();
        System.out.println("Before Advice: " + name);
    }
    @After("@annotation(shop.mtcoding.blog._core.error.anno.MyAfter)")
    public void afterAdvice(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("After Advice: " + name);
    }
    @Around("@annotation(shop.mtcoding.blog._core.error.anno.MyAround)")
    public Object aroundAdvice(ProceedingJoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("Around Advice 직전: " + name);

        try {
            Object result = jp.proceed(); //컨트롤러 함수가 호출, Before와 After는 안됨
            //Before와 After 사이에서 호출되며 어떤값이 리턴될지 알 수 없으므로 Object가 된다.
            System.out.println("Around Advice 직후: " + name);
            System.out.println("result 값: " + result.);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
