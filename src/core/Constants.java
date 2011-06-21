package core;

import java.util.Random;

public interface Constants {
    Random rand = new Random();
    int delay = 40;
    int limit = 2000;
    int nLives = 3;
    int nGhosts = 4;
    int MAG = 2;
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int NEUTRAL = 4;
    int[] dx = {0, 1, 0, -1, 0};
    int[] dy = {-1, 0, 1, 0, 0};
    int[] initGhostDelay = {0, 10, 20, 30};
    int agentOverlapDistance = 2;
    int pillScore = 10;
    int powerScore = 50;
    int edibleGhostStartingScore = 200;

    String mazea = "maze-a.png";
    String mazeb = "maze-b.png";
    String mazec = "maze-c.png";
    String mazed = "maze-d.png";
    String imagepath = "images/";
}