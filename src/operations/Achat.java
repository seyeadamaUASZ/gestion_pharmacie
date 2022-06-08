package operations;

import clients.Client;
import medicament.Medicament;

import java.util.Date;

public class Achat  extends Operation{
    private Client client;
    private Medicament medicament;
    private int quantite;

    public Achat(String typeOperation, Date dateOperation, Client client, Medicament medicament, int quantite, Date dateAchat) {
        super(typeOperation, dateOperation);
        this.client = client;
        this.medicament = medicament;
        this.quantite = quantite;
    }

    public Achat() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // description detaillée de l'achat
    @Override
    public String toString() {
        return "Date "+ this.getDateOperation()+ " Client "+ this.client.getNomClient() + "\n"+
                " medicament "+ this.medicament.getNomMedicament() + "\n"+
                 " prix achat :"+ this.medicament.getQuantite() * this.medicament.getPrixU();

    }
}
