import operations.Achat;
import clients.Client;
import medicament.Medicament;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Systeme de gestion des pharmacies");
        HashSet<Medicament> medicaments = new HashSet<>();
        Medicament medoc = new Medicament("MD001","paracetamol",5,100);
        addMedicament(medicaments,medoc);
        affichageMedicaments(medicaments);

        //teste pour la verification

          Medicament med= verifierMedicament(medicaments,"paracetamol");

           System.out.println("medicament trouve "+med.getNomMedicament());


    }


    //fonction interphasage de l'application

    public static void run(){
        int typeOperation;
        System.out.println("******Systeme gestion pharmacie*******");
        System.out.println("Choisissez une option ");
        System.out.println(" 1: Achat de médicaments ");
        System.out.println(" 2: Approvisionnement en médicament");
        System.out.println(" 3: Etat des stocks et des crédits");
        System.out.println(" 4: Liste des clients ");
        System.out.println(" 5: Liste des médicaments ");
        System.out.println(" 6: Quitter ");

        Scanner sc = new Scanner(System.in);
        System.out.println(" Entrer un numéro donné ");
        typeOperation = sc.nextInt();

        HashSet<Client> clients = new HashSet<>();
        HashSet<Medicament> medicaments = new HashSet<>();

        /* liste d'ajout de depart des medocs et clients
        * prochaine étape, ajout depuis un fichier
        * */
        Medicament medoc1 = new Medicament("M001","Paracetamol",5,100);
        Medicament medoc2 = new Medicament("M002","Aspirine",8,250);
        Medicament medoc3 = new Medicament("M003","Cetamyl",9,450);

        //ajout des medicaments sur la liste

        addMedicament(medicaments,medoc1);
        addMedicament(medicaments,medoc2);
        addMedicament(medicaments,medoc3);

        Client client1 = new Client("CL001","Adama seye",2500);
        Client client2 = new Client("CL002","Baba Gueye",3500);
        Client client3 = new Client("CL003","Ouleye Ndiaye",4000);

        //ajout des clients dans la liste
        addClient(clients,client1);
        addClient(clients,client2);
        addClient(clients,client3);


        switch (typeOperation){
            case 1:
             deroulementCase1(clients,medicaments);
        }





    }

    //des méthodes pour l'ajout de médicament au niveau de la collection et de clients

    public static HashSet<Client> addClient(HashSet<Client> clients,Client client){
        clients.add(client);
        return clients;
    }

    //pour medicament
    public static HashSet<Medicament> addMedicament(HashSet<Medicament> medicaments,Medicament medicament){
        medicaments.add(medicament);
        return medicaments;
    }

    //affichage de la liste des medicaments et des clients
    public static void affichageClients(HashSet<Client> clients){
        Iterator<Client> i = clients.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
    public static void affichageMedicaments(HashSet<Medicament> medicaments){
        Iterator<Medicament> i = medicaments.iterator();
        while(i.hasNext()){
            System.out.println(i.next().getNomMedicament());
        }
    }

    //methode de verification d'un médicament ou client

    public static Medicament verifierMedicament(HashSet<Medicament> medicaments,String medicament){
        for(Medicament m:medicaments){
            if(m.getNomMedicament().equals(medicament)){
                return m;
            }
        }
        return null;
    }

    public static Client verifierClient(HashSet<Client> clients, String codeClient) {
        for (Client cl : clients) {
            if (cl.getIdentifiant().equals(codeClient)) {
                return cl;
            }
        }
        return null;
    }


    public static void deroulementCase1(HashSet<Client> clients,HashSet<Medicament> medicaments){
            String nomMedicament;
            Achat achat = new Achat();
            Scanner sc = new Scanner(System.in);
            System.out.println("Quel médicament ");
            nomMedicament = sc.nextLine();

            //verifions s'il existe sur la liste des médocs
            Medicament medicament = verifierMedicament(medicaments,nomMedicament);
            if(medicament == null){
                System.out.println("Ce médicament n'existe pas dans la liste");
            }else{
                String codeClient;
                System.out.println("Entrer le code du client ");
                codeClient = sc.nextLine();
                Client client = verifierClient(clients,codeClient);
                if(client ==null){
                    System.out.println("ce client n'existe pas ");
                }else{
                    int quantiteA;
                    System.out.println("Quelle quantite à acheter");
                    quantiteA = sc.nextInt();
                    boolean verif = medicament.verifierQuantite(quantiteA);
                    if(verif){
                        achat.setMedicament(medicament);
                        achat.setClient(client);
                        achat.setQuantite(quantiteA);
                        double price = achat.getQuantite() * medicament.getPrixU();
                        System.out.println("le prix total est : "+price);
                        //mis à jour des éléments quantite medoc et credit client
                        int quantiteRes = medicament.getQuantite() - quantiteA;
                        medicament.setQuantite(quantiteRes);
                        miseAjourMedicament(medicaments,medicament.getNomMedicament(),quantiteRes);
                        //mise a jour client aussi
                        updateCreditClient(clients,client.getIdentifiant(),price);
                    }


                }
            }


    }


    // faire une mise à jour au niveau de la liste

    public static void miseAjourMedicament(HashSet<Medicament> medicaments,String nomMedoc,int quantiteR){
        //parcourir et supprimer pour le réajouter
        Medicament current = new Medicament();
        for(Medicament med : medicaments){
            if(med.getNomMedicament().equals(nomMedoc)){
                current = med;
                break;
            }
        }
        //apres sorti supprimer
        medicaments.remove(current);
        //créer à nouveau et ajouter avec les new informations
        Medicament medoc = new Medicament();
        //utilisons random pour générer le code avec MD suivi de randomUUI
        String code = "MD"+ UUID.randomUUID().toString();
        medoc.setCodeMedicament(code);
        medoc.setQuantite(quantiteR);
        medoc.setNomMedicament(nomMedoc);
        medoc.setPrixU(current.getPrixU());
        //ajout du medoc
        medicaments.add(medoc);
    }


    //mise à jour credit client

    public static void updateCreditClient(HashSet<Client> clients,String codeClient,double creditAdded){
        Client cl = new Client();
        for(Client c:clients){
            if(c.getIdentifiant().equals(codeClient)){
                cl = c;
                break;
            }
        }
        //faisons un remove de ce client
        clients.remove(cl);
        Client client1 = new Client();
        double credit = cl.getCredit() + creditAdded;
        client1.setCredit(credit);
        client1.setNomClient(cl.getNomClient());
        String code = "CL"+ UUID.randomUUID().toString();
        client1.setIdentifiant(code);

        //réajouter au niveau de la liste
        clients.add(client1);
    }

}
