package ar.edu.unq.desapp.grupoH022021.backenddesappapi.aspects;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Order(0)
public class LogAspectAnnotation {
    static Logger logger = LoggerFactory.getLogger(LogAspectAnnotation.class);

    @Around("@annotation(Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        String params = getParams(joinPoint, codeSignature);

        logger.info("Request Captured. Method: " +  joinPoint.getSignature() + "with args: " + params + " At: " + LocalDateTime.now());

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.info("Request for " + codeSignature.getName() + " executed At: " + LocalDateTime.now() + " Execution Time: " + executionTime);

        return proceed;
    }

    private String getParams(ProceedingJoinPoint joinPoint, CodeSignature codeSignature) {
        String params = "";
        int ind= 0;

        for (String param : codeSignature.getParameterNames()) {
            Object arg = joinPoint.getArgs()[ind];

            if (arg instanceof String){
                params+= param + " " + arg + " ";
            }
            else{
                Gson gson = new Gson();
                String json = gson.toJson(arg);

                params+= param + " " + json + " ";
            }

            ind++;
        }

        return params;
    }
}
