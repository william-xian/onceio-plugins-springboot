package top.onceio.plugins.springboot;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.onceio.aop.annotation.Transactional;
import top.onceio.db.dao.impl.DaoHelper;

@Component  
@Aspect
public class TransactionAspect {
	@Autowired
	private DaoHelper daoHelper;
	
    @Pointcut(value = "@annotation(top.onceio.aop.annotation.Transactional)")  
    public void access() {
    }
    @Before("access()")  
    public void deBefore(JoinPoint joinPoint) throws Throwable {  
        System.out.println("second before");  
    }  
  
    @Around("@annotation(transactional)")  
    public Object around(ProceedingJoinPoint pjp, Transactional transactional) {
        try {
        	daoHelper.beginTransaction(transactional.isolation(), transactional.readOnly());
        	Object retVal = pjp.proceed();
        	daoHelper.commit();
        	return retVal;
        } catch (Throwable throwable) {  
        	daoHelper.rollback();
        	throw new RuntimeException(throwable);
        }
    }
}
