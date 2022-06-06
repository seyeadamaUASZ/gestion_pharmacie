package medicament;

public class Medicament {
    private String codeMedicament;
    private String nomMedicament;
    private int quantite;

    private double prixU;


    public Medicament(String codeMedicament, String nomMedicament, int quantite, double prixU) {
        this.codeMedicament = codeMedicament;
        this.nomMedicament = nomMedicament;
        this.quantite = quantite;
        this.prixU = prixU;
    }

    public double getPrixU() {
        return prixU;
    }

    public void setPrixU(double prixU) {
        this.prixU = prixU;
    }

    public Medicament() {
    }

    public String getCodeMedicament() {
        return codeMedicament;
    }

    public void setCodeMedicament(String codeMedicament) {
        this.codeMedicament = codeMedicament;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return " Code médicament "+this.codeMedicament + " nom médicament "+this.nomMedicament+ "\n" +
                " quantite "+this.quantite;

    }

    //affichage des infos d'un medoc

    public void affichage(){
        System.out.println(this);
    }

    //approvisionner le stock d'un medoc

    public void addStockMedoc(int quantite){
         this.quantite += quantite;
    }
}
