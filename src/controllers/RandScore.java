package controllers;

import core.GameStateInterface;
import core.Node;

import java.util.Random;

public class RandScore implements NodeScore {
    private static final Random r = new Random();

    public double score(GameStateInterface gs, Node node) {
        return r.nextDouble();
    }
}