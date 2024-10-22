package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue; // Changement ici pour utiliser JUnit Jupiter
import org.junit.jupiter.api.Test; // Changement ici pour utiliser JUnit Jupiter
import org.junit.jupiter.api.BeforeEach; // Changement ici pour utiliser JUnit Jupiter
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import epreuves.*;
import exceptions.*;
import participants.*;
import sports.Athletisme;
import sports.Escrime;
import sports.Natation;
import sports.VoleyBall;

public class TestEpreuveManche {

    private Escrime escrime;
    private VoleyBall voley;
    private Athletisme athletisme;
    private Natation natation;

    private Pays france;

    private Athlete baptiste;
    private Athlete chris;
    private Athlete julian;
    private Athlete axel;
    private Athlete michelle;
    private Athlete shanka;
    private Athlete bastien;
    private Athlete riri;
    private Athlete fifi;
    private Athlete loulou;
    private Athlete pixou;
    private Athlete donald;
    private Athlete flagada;
    private Equipe equipe1;
    private Equipe equipe2;

    private Epreuve<Athlete> mettre110H;
    private Epreuve<Athlete> mettre110F;
    private Epreuve<Equipe> epreuve;
    private Epreuve<Equipe> epreuve0;
    private Epreuve<Athlete> athletisme110;
    private Epreuve<Athlete> athletisme4x110;
    private Epreuve<Athlete> natatione110;
    private Epreuve<Athlete> natation4x110;
    private Epreuve<Athlete> epreuveVoleyTest;


    private Manche<Athlete> manchesTestH;
    private Manche<Athlete> manchesTestF;
    private Manche<Equipe> manche;
    private Manche<Athlete> manchesAthle100;
    private Manche<Athlete> manchesAthle4x100;
    private Manche<Athlete> manchesNatation100;
    private Manche<Athlete> manchesNatation4x100;
    

    @BeforeEach
    public void setUp() {
        escrime = new Escrime();
        voley = new VoleyBall();

        france = new Pays("France");
        baptiste = new Athlete("RICHARD", "Baptiste", 'M', 11, 18, 16, france);
        chris = new Athlete("MATEHEVET", "Chris", 'F', 13, 17, 15, france);
        julian = new Athlete("MARQUES", "Julian", 'M', 15, 10, 19, france);
        axel = new Athlete("MEUNIER", "Axel", 'M', 19, 18, 9, france);
        michelle = new Athlete("MICHMICH", "Michelle", 'F', 12, 16, 14, france);
        shanka = new Athlete("ClERMONT", "shanka", 'M', 19, 18, 9, france);
        bastien = new Athlete("MONET", "bastien", 'M', 19, 18, 9, france);
        mettre110H = new Epreuve<>("Athletisme relais 110 haies", escrime, 'M');
        mettre110F = new Epreuve<>("Athletisme relais 110 haies", escrime, 'F');
        epreuve = new Epreuve<>("test", voley, 'M');
        epreuve0 = new Epreuve<>("test", voley, 'M');
        riri = new Athlete("Duck", "riri", 'M', 1, 8, 19, france);
        fifi = new Athlete("Duck", "fifi", 'M', 12, 5, 9, france);
        loulou = new Athlete("Duck", "loulou", 'M', 19, 8, 9, france);
        pixou = new Athlete("Duck", "pixou", 'M', 19, 12, 9, france);
        donald = new Athlete("Duck", "donald", 'M', 19, 18, 19, france);
        flagada = new Athlete("Jones", "Flagada", 'M', 19, 18, 19, france);

        equipe1 = new Equipe("1");
        equipe2 = new Equipe("2");

        manchesTestH = new Manche<>(0, "Tour test", mettre110H);
        manchesTestF = new Manche<>(0, "Tour test", mettre110F);
        manche = new Manche<>(1, "test", epreuve);

        athletisme = new Athletisme();
        natation = new Natation();
        athletisme110 = new Epreuve<>("Athletisme 110 haies", athletisme, 'M');
        athletisme4x110 = new Epreuve<>("Athletisme relais 110 haies", athletisme, 'M');
        natatione110 = new Epreuve<>("Natation 100 libre", natation, 'M');
        natation4x110 = new Epreuve<>("Natation relais 100 libre", natation, 'M');
        manchesAthle100 = new Manche<>(3, "Tour athle 110", athletisme110);
        manchesAthle4x100 = new Manche<>(0, "Tour athle 4*110", athletisme4x110);
        manchesNatation100 = new Manche<>(0, "Tour nata 110", natatione110);
        manchesNatation4x100 = new Manche<>(0, "Tour nata 4*110", natation4x110);

        epreuveVoleyTest = new Epreuve<>("VoleyBall athlete (impossible)", voley, 'M');

    }

    //Partie Epreuve 

    @Test
    public void testGetLesParticipants() {
        try {
            epreuveVoleyTest.inscrire(axel);
            assertTrue(epreuveVoleyTest.getLesParticipants().size() == 1);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testGetSport() {
        assertEquals( epreuveVoleyTest.getSport(), voley);
    }

    @Test
    public void testGetDescription() {
        System.out.println(epreuveVoleyTest.getDescription());
        assertEquals(epreuveVoleyTest.getDescription(),"VoleyBall athlete (impossible)" );
    }

    @Test
    public void testGetSexe() {
        assertEquals(epreuveVoleyTest.getSexe(), 'H');
    }

    @Test
    public void testGetPremier() {

        try {
         

            mettre110H.inscrire(baptiste);
            mettre110H.inscrire(julian);
            mettre110H.inscrire(axel);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        mettre110H.getLeClassement();
        mettre110H.ajoutManche(manchesTestH);

        assertTrue(mettre110H.getPremier() != null);
    }

    @Test
    public void testGetSecond() {

        try {
         

            mettre110H.inscrire(baptiste);
            mettre110H.inscrire(julian);
            mettre110H.inscrire(axel);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        mettre110H.getLeClassement();
        mettre110H.ajoutManche(manchesTestH);

        assertTrue(mettre110H.getSecond() != null);
    }

    @Test
    public void testGetTroisieme() {

        try {
         

            mettre110H.inscrire(baptiste);
            mettre110H.inscrire(julian);
            mettre110H.inscrire(axel);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        mettre110H.getLeClassement();
        mettre110H.ajoutManche(manchesTestH);

        assertTrue(mettre110H.getTroisieme() != null);
    }

    @Test
    public void testGetLesManches() {

        List<Manche<Athlete>> lesManches = new ArrayList<>();
        lesManches.add(manchesTestH);
        assertEquals(mettre110H.getLesManches(), lesManches);
    }

//Partie Manche

    @Test
    public void testGetNumeroDeTour() {
        assertEquals(manchesAthle100.getNumeroDeTour(), 3);
    }

    @Test
    public void testGetNomDeTour() {
        assertEquals(manchesAthle100.getNomDeTour(), "Tour athle 110");
    }

    @Test
    public void testGetEpreuve() {
        assertEquals(manchesAthle100.getEpreuve(), athletisme110);
    }

    @Test
    public void testGetResultatParticipants() {
        try {
            athletisme110.inscrire(axel);
            athletisme110.inscrire(chris);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }
        athletisme110.getLeClassement();
        for (Manche manches : athletisme110.getLesManches()) {
            assertTrue(manches.getResultatParticipant(axel) >= 4 && manches.getResultatParticipant(axel) <= 200);
        }
    }



    @Test
    public void testMancheAthlete() {
        try {
            mettre110F.inscrire(chris);
            mettre110F.inscrire(michelle);

            mettre110H.inscrire(baptiste);
            mettre110H.inscrire(julian);
            mettre110H.inscrire(axel);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        List<Double> resH = manchesTestH.getResultats();
        List<Double> resF = manchesTestF.getResultats();

        for (Double temps1 : resH) {
            assertTrue(temps1 >= 0);
            assertTrue(temps1 <= 200);
        }
        for (Double temps2 : resF) {
            assertTrue(temps2 >= 0);
            assertTrue(temps2 <= 200);
        }
    }

    @Test
    public void testMancheEquipe() {
        try {
            equipe1.ajouter(baptiste);
            equipe1.ajouter(chris);
            equipe1.ajouter(julian);
            equipe1.ajouter(axel);
            equipe1.ajouter(shanka);
            equipe1.ajouter(bastien);               

            equipe2.ajouter(riri);
            equipe2.ajouter(fifi);
            equipe2.ajouter(loulou);
            equipe2.ajouter(pixou);
            equipe2.ajouter(donald);
            equipe2.ajouter(flagada);
        } catch (AlreadyInException | NotSameCountryException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        try {
            epreuve.inscrire(equipe1);
            epreuve.inscrire(equipe2);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(manche.getResultats() != null);
    }

    @Test
    public void testTemps() {
        try {
            athletisme110.inscrire(baptiste);
            athletisme110.inscrire(julian);
            athletisme110.inscrire(axel);

            athletisme4x110.inscrire(baptiste);
            athletisme4x110.inscrire(julian);
            athletisme4x110.inscrire(axel);

            natatione110.inscrire(baptiste);
            natatione110.inscrire(julian);
            natatione110.inscrire(axel);

            natation4x110.inscrire(baptiste);
            natation4x110.inscrire(julian);
            natation4x110.inscrire(axel);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        List<Double> resAthle100 = manchesAthle100.getResultats();
        List<Double> resAthle4x100 = manchesAthle4x100.getResultats();
        List<Double> resNata100 = manchesNatation100.getResultats();
        List<Double> resNata4x100 = manchesNatation4x100.getResultats();

        for (Double temps1 : resAthle100) {
            assertTrue(temps1 >= 10.2);
            assertTrue(temps1 <= 20.0);
        }
        for (Double temps1 : resAthle4x100) {
            assertTrue(temps1 >= 30.2);
            assertTrue(temps1 <= 40.0);
        }
        for (Double temps1 : resNata100) {
            assertTrue(temps1 >= 42.2);
            assertTrue(temps1 <= 52.0);
        }
        for (Double temps1 : resNata4x100) {
            assertTrue(temps1 >= 182.0);
            assertTrue(temps1 <= 280.0);
        }
    }

    @Test
    public void testInscrireAthlete() {
        boolean testNormal = false;
        boolean testAlreadyIn = false;
        boolean testPeuxPasInscrire = false;
        boolean testSexe = false;

        try {
            athletisme110.inscrire(baptiste);
            athletisme110.inscrire(julian);
            athletisme110.inscrire(axel);
            testNormal = true;
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        try {
            athletisme110.inscrire(michelle);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
            testSexe = true;
        }

        try {
            athletisme110.inscrire(baptiste);
            athletisme110.inscrire(julian);
            athletisme110.inscrire(axel);
        } catch (AlreadyInException e) {
            System.err.println(e.getMessage());
            testAlreadyIn = true;
        } catch (CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
        }

        try {
            epreuveVoleyTest.inscrire(baptiste);
            epreuveVoleyTest.inscrire(julian);
            epreuveVoleyTest.inscrire(axel);
        } catch (AlreadyInException | CanNotRegisterException | NotSameGenderException e) {
            System.err.println(e.getMessage());
            if (e instanceof CanNotRegisterException) {
                testPeuxPasInscrire = true;
            }
        }

        assertTrue(testPeuxPasInscrire);
        assertTrue(testAlreadyIn);
        assertTrue(testNormal);
        assertTrue(testSexe);
    }

    @Test
    public void testToStringEquals() {
        assertEquals(epreuve.toString(), "test sport: Voley,nb par equipe :6 sexe: H");
        assertEquals(manche.toString(), "test1");
        assertEquals(epreuve, epreuve0);
        assertNotEquals(epreuve, epreuveVoleyTest);
    }
}