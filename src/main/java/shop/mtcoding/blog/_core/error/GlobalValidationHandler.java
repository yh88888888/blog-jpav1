package shop.mtcoding.blog._core.error;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blog._core.error.ex.Exception400;

import java.util.List;

//Aspect, PointCut, Advice
@Aspect // 관점관리
@Component
public class GlobalValidationHandler {

    //관심사를 분리시킴
    //@annotaion PointCut: PostMapping 혹은 PutMapping 이 붙어있는 메서드를 실행하기 직전에 Advice를 호출하라
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping;)||@annotaion(import org.springframework.web.bind.annotation.PutMapping;)")
    public void badRequestHandler(JoinPoint jp) {//jp는 실행될 실제 메서드의 모든 것을 투영하고 있다. 꺼내서 쓰기만 하면 된다.
        Object[] args = jp.getArgs(); // 메서드의 매개변수들  1개가 있어도 배열로 리턴, 리턴 타입을 알 수 없으므로 오브젝트 타입으로 받는다.
        for(Object arg : args) {//매개변수 개수만큼 반복(어노테이션은 제외) , (@PathVariable("id") Integer id, BoardRequest.UpdateDTO reqDTO)
            //Errors 타입 매개변수에 존재하고!
            if(arg instanceof Errors){
                Errors errors = (Errors) arg;

                //에러가 존재한다면!
                if(errors.hasErrors()){
                    List<FieldError> fErrors = errors.getFieldErrors();
                    for(FieldError fieldError : fErrors){
                        throw new Exception400(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                    }
                }
            }

        }

    }
}
