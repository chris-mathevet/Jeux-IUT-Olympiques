// package database;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.util.HashSet;
// import java.util.Set;

// import epreuves.Epreuve;
// import exceptions.AlreadyExistException;
// import exceptions.AlreadyInException;
// import exceptions.CanNotRegisterException;
// import exceptions.DoesntExistException;
// import exceptions.NotSameCountryException;
// import exceptions.NotSameGenderException;
// import executable.LibCreation;
// import participants.Athlete;
// import participants.Equipe;
// import participants.Pays;

// public class csv {
    
//         // CSV

//     public void csvToListe(String chemin){
//         String ligne;
//         String split =",";
//         String[] ligneElems;
//         Pays pays;
//         String nom;
//         String prenom;
//         char sexe;
//         String nomPays;
//         String sport;
//         String epreuve;
//         int force;
//         int endurance;
//         int agilite;
//         Athlete mich;
//         String nomEquipe;
//         Equipe equipe;
//         Set<Pays> lesPays= new HashSet<>();
        
//         try (BufferedReader line = new BufferedReader(new FileReader(chemin))){
//             line.readLine();
//             while ((ligne = line.readLine())!= null) {
//                 ligneElems = ligne.split(split);
//                 if(ligneElems.length >=9){
//                     try {
//                         nom= ligneElems[0];
//                         prenom= ligneElems[1];
//                         sexe= ligneElems[2].charAt(0);
//                         nomPays = ligneElems[3];
//                         try {

//                             r.insertPays(pays);

//                         } catch (Exception e) {
//                             System.out.println("Pays deja dans la base de donnée" + e);
//                         }

//                         sport = ligneElems[4];
//                         epreuve = ligneElems[5];
//                         try {
//                             force =  Integer.parseInt(ligneElems[6]);
//                             endurance = Integer.parseInt(ligneElems[7]);
//                             agilite =  Integer.parseInt(ligneElems[8]);
//                             mich = new Athlete(nom,prenom,sexe,force,endurance,agilite,pays);
//                             try {
//                                 r.insertAthlete(mich);
                                
//                             } catch (Exception e) {
//                                 System.err.println("Athlete existe deja");
//                             }
//                             try {
//                                 r.getEpreuvebyDescpt(epreuve);

//                             } 
//                             catch(Exception e){
//                                 System.err.println("l'Epreuve existe deja");

//                             }
//                         } catch (NumberFormatException e) {
//                             System.err.println("Problème format nombre, ligne : " + ligne);
//                         }
//                         // si athlete pas creee le cree, sinon l'add a une epreuve
//                         // incrire()
                        
//                     } catch (Exception e) {
//                         System.out.println("erreur format ligne : "+ligne);
//                     }
//                 }
//             }
//         }catch (Exception e) {
//             e.printStackTrace();  
//         }
//     }


// }


