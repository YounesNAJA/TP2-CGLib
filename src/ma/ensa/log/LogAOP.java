package ma.ensa.log;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import ma.ensa.model.Compte;

public class LogAOP implements MethodInterceptor, MethodBeforeAdvice, AfterReturningAdvice {
	private Logger rootLogger = Logger.getLogger(this.getClass().getName());
	private Object result;
	private Compte compte;

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		compte = (Compte) target;
		double mt = (double) args[0];

		rootLogger.fatal("Solde actuel : " + compte.getSolde());

		if (method.getName() == "retirer") {
			rootLogger.fatal("Retrait de " + mt);
		}

		if (method.getName() == "verser") {
			rootLogger.fatal("Virement de " + mt);
		}
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		compte = (Compte) target;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		compte = (Compte) invocation.getThis();
		double mt = (double) invocation.getArguments()[0];
		if (invocation.getMethod().getName() == "verser") {
			result = invocation.proceed();
			rootLogger.fatal("Solde actualisé après le virement de " + mt + " : " + compte.getSolde());
		} else if (invocation.getMethod().getName() == "retirer") {
			if (mt <= compte.getSolde()) {
				result = invocation.proceed();
				rootLogger.fatal("Solde actualisé après le retrait de " + mt + " : " + compte.getSolde());
			} else {
				rootLogger.fatal("Impossible de retirer " + mt + " du solde actuel de : " + compte.getSolde());
				result = null;
			}
		}

		return result;
	}

}
