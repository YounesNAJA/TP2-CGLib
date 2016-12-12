package ma.ensa.main;

import java.lang.reflect.Proxy;
import ma.ensa.log.LogAOP;
import ma.ensa.model.Compte;
import ma.ensa.model.ICompte;

public class Application {
	public static void main(String[] args){
		ICompte compte = new Compte();
		ICompte compteProxy = (ICompte) Proxy.newProxyInstance(compte.getClass().getClassLoader(), compte.getClass().getInterfaces(), new LogAOP(compte));
		compteProxy.verser(3000);
		compteProxy.retirer(500);
	}
}