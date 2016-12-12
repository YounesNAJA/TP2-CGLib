package ma.ensa.model;

public class Compte implements ICompte {
	private double solde;

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public void retirer(double mt){
		solde -= mt;
	}
	
	public void verser(double mt){
		solde += mt;
	}
}
