package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Executable.*;
import epreuves.Epreuve;
import epreuves.Match;
import exceptions.AlreadyInException;
import exceptions.NotSameCountryException;
import exceptions.NotSameGenderException;
import participants.Athlete;
import participants.Equipe;
import participants.Pays;
import sports.HandBall;
import sports.VoleyBall;

public class TestExec {
    
    private JO jo;

    @BeforeEach
    public void setUp() {
        jo = new JO();
        jo.addPays(new Pays("France"));
        jo.addSport("VolleyBall");
    }

    @Test
    public void testGetPays() {
        assertEquals(jo.getPays("VolleyBall"), new Pays("VolleyBall"));
    }
}