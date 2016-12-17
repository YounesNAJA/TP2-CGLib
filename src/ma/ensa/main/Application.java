package ma.ensa.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ma.ensa.model.ICompte;

public class Application {
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		ICompte compte = (ICompte) context.getBean("compteProxy");
		compte.verser(1000);
		compte.retirer(500);
	}
}