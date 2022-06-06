package clients;

public class Client {
    private String identifiant;
    private String nomClient;
    private double credit;

    public Client(String identifiant, String nomClient, double credit) {
        this.identifiant = identifiant;
        this.nomClient = nomClient;
        this.credit = credit;
    }

    public Client() {
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return " Identifiant client: "+ this.identifiant+ " Nom client :"+ this.nomClient;
    }

    // methode affichage des informations d'un client

    public void affichage(){
        System.out.println(this);
    }

    // methodes utiles au besoin de verification

    public boolean verifierCredit(){
        return  this.credit > 0;
    }


}
