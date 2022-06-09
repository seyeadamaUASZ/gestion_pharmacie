import operations.Achat;
import clients.Client;
import medicament.Medicament;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Systeme de gestion des pharmacies");
        run();


    }


    //fonction interphasage de l'application

    public static void run(){
        int typeOperation;
        System.out.println("******Systeme gestion pharmacie*******");
        System.out.println("Choisissez une option ");
        System.out.println(" 1: Acheter des médicaments ");
        System.out.println(" 2: Liste des clients");
        System.out.println(" 3: Liste des médicaments");
        System.out.println(" 4: Liste des achats ");
        System.out.println(" 5: Approvisionner des médicaments ");
        System.out.println(" 6: Etat des stocks et des crédits");
        System.out.println(" 7: Quitter ");

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

        HashSet<Achat> achats = new HashSet<>();

        switch (typeOperation){
            case 1:
             deroulementCase1(clients,medicaments);
             break;
            case 2:
                affichageClients(clients);
                break;
            case 3:
                affichageMedicaments(medicaments);
                break;
            case 4:
                affichageAchat(achats);
            default:
                System.out.println("Option non pris en compte dans la gestion");
        }







    }

    //des méthodes pour l'ajout de médicament au niveau de la collection et de clients

    public static void addClient(HashSet<Client> clients,Client client){
        clients.add(client);
    }

    //pour medicament
    public static void addMedicament(HashSet<Medicament> medicaments,Medicament medicament){
        medicaments.add(medicament);
    }

    //pour les achats

    public static void addAchat(HashSet<Achat> achats,Achat achat){
        achats.add(achat);
    }



    //affichage de la liste des medicaments et des clients
    public static void affichageClients(HashSet<Client> clients){
        for(Client cl:clients){
            System.out.println(cl.getNomClient()+ " "+cl.getCredit());
        }
    }
    public static void affichageMedicaments(HashSet<Medicament> medicaments){
        for(Medicament medoc:medicaments){
            System.out.println(medoc.getNomMedicament()+ " "+medoc.getQuantite());
        }
    }

    //affichage des operations d'achat

    public static void affichageAchat(HashSet<Achat> achats){
        for(Achat a:achats){
            System.out.println("date : "+a.getDateOperation()+ "\n"+
                    " la quantité "+a.getQuantite()+" \n"+
                    " le prix total "+a.getTotalPrice());
        }
    }

    //methode de verification d'un médicament ou client

    public static Medicament verifierMedicament(HashSet<Medicament> medicaments,String medicament){
        for(Medicament m:medicaments){
            if(m.getNomMedicament().equalsIgnoreCase(medicament)){
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
                        achat.setTotalPrice(price);
                        System.out.println("le prix total est : "+price);
                        //mis à jour des éléments quantite medoc et credit client
                        int quantiteRes = medicament.getQuantite() - quantiteA;
                        medicament.setQuantite(quantiteRes);
                        miseAjourMedicament(medicaments,medicament.getNomMedicament(),quantiteRes);
                        //mise a jour client aussi
                        updateCreditClient(clients,client.getIdentifiant(),price);

                        //affichage client et affichage medicament

                        affichageMedicaments(medicaments);
                        System.out.println("**************************");
                        affichageClients(clients);
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
