package ie.cit.cad.aspects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TracingAspect {
	Log log = LogFactory.getLog(TracingAspect.class);
	
	@Before("execution(* *..Jdbc*.*(..)) && target(repo)")
	public void trace(JoinPoint point, Object repo) {
//		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		log.trace("method invoked:"+repo.getClass().getName()+"#"+ methodName);
	}
}
