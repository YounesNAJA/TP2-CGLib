package ma.ensa.model;

public interface ICompte {
	public double getSolde();

	public void retirer(double mt);

	public void verser(double mt);
}
