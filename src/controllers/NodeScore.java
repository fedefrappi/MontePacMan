package controllers;

import core.GameStateInterface;
import core.Node;

public interface NodeScore {
    public double score(GameStateInterface gs, Node node);
}
