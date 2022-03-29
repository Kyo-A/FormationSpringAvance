package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Declare comme aspect
@Aspect
//Déclare comme bean dans le contexte de l'application
@Component
public class EmployeServiceAspect {

	@Before(value = "execution(public * *..EmployeService.*(..)) and args(firstName,lastName)")
	public void beforeAdvice(JoinPoint joinpoint, String firstName, String lastName) {
		System.out.println("Before method: " + joinpoint.getSignature());
		System.out.println("Creating employe with firstName: " + firstName + " and lastName: " + lastName);
	}
	
	@After(value = "execution(public * *..EmployeService.*(..)) and args(firstName,lastName)")
	public void afterAdvice(JoinPoint joinpoint, String firstName, String lastName) {
		System.out.println("After method: " + joinpoint.getSignature());
		System.out.println("Successfully created employe with firstName: " + firstName + " and lastName: " + lastName);
	}
	
//	@Before(value = "execution(public * *..EmployeService.*(..)) and args(empId)")
//	public void beforeAdvice(JoinPoint joinPoint, Integer empId) {
//		System.out.println("Before method:" + joinPoint.getSignature());
//
//		System.out.println("Deleting Employe with id - " + empId);
//	}
//
//	@After(value = "execution(public * *..EmployeService.*(..)) and args(empId)")
//	public void afterAdvice(JoinPoint joinPoint, Integer empId) {
//		System.out.println("After method:" + joinPoint.getSignature());
//
//		System.out.println("Successfully deleted Employe with id - " + empId);
//	}
	
	// around : autour de l’execution de methodes (avant et apres)
	// Tout autre greffon que @Around peut declarer comme premier parametre JoinPoint
	@Around("execution(public * *..EmployeService.*(..)) and args(empId)")
	public Object frenchAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Integer empId) {
		System.out.println("Valeur du parametre name dans deleteEmploye() : " + empId);
		System.out.println("Signature : " + proceedingJoinPoint.getSignature());
		Object value = null;
		try {
			// proceedingJoinPoint.proceed() : entraine l’execution de la methode saluer2()
			// La valeur de retour de proceedingJoinPoint.proceed() est la valeur de retour de la methode
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Valeur de retour de sayHello2 : " + value);
		return value;
	}
}
