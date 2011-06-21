package mcts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import controllers.examples.LegacyTeam;
import controllers.examples.RandomMsPacMan;
import controllers.examples.RandomNonReverseMsPacMan;

import core.Game;
import core.GameStateInterface;
import core.Node;
import core.utilities.Utilities;

public class TreeNode {
    private static Random r = new Random();
    private static double epsilon = 1e-6;
    private Node prev;

    public TreeNode[] children;
    public double nVisits, totValue;
    public GameStateInterface gameState;
    
    public TreeNode(GameStateInterface gs, Node prevNode) {
		this.gameState = gs.copy();
		this.prev = prevNode;
	}

    public void selectAction() {
        List<TreeNode> visited = new LinkedList<TreeNode>();
        TreeNode cur = this;
        visited.add(this);
        while (!cur.isLeaf()) {
            cur = cur.select();
            visited.add(cur);
        }
        cur.expand();
        TreeNode newNode = cur.select();
        visited.add(newNode);
        double value = rollOut(newNode);
        for (TreeNode node : visited) {
            node.updateStats(value);
        }
    }

    public void expand() {
    	
    	ArrayList<Node> possibles = new ArrayList<Node>();
        for (Node n : gameState.getPacman().current.adj) {
            if (!n.equals(prev)){
            	possibles.add(n);
            }
        }
    	
    	int nActions = possibles.size();
        children = new TreeNode[nActions];
        for (int i=0; i<nActions; i++) {
        	Node target = possibles.get(i);
        	GameStateInterface nextGs = gameState.copy();
        	nextGs.next(Utilities.getWrappedDirection(gameState.getPacman().current, target, gameState.getMaze()), new LegacyTeam().getActions(gameState));
            children[i] = new TreeNode(nextGs, gameState.getPacman().current);
        }
    }

    private TreeNode select() {
        TreeNode selected = null;
        double bestValue = Double.MIN_VALUE;
        for (TreeNode c : children) {
            double uctValue =
                    c.totValue / (c.nVisits + epsilon) +
                            Math.sqrt(Math.log(nVisits+1) / (c.nVisits + epsilon)) +
                            r.nextDouble() * epsilon;
            // small random number to break ties randomly in unexpanded nodes
            // System.out.println("UCT value = " + uctValue);
            if (uctValue > bestValue) {
                selected = c;
                bestValue = uctValue;
            }
        }
        return selected;
    }

    public boolean isLeaf() {
        return children == null;
    }

    public double rollOut(TreeNode tn) {
        return simulateGame(tn.gameState);
    }
    
	public int simulateGame(GameStateInterface gs){
		GameStateInterface simGs = gs.copy();
	//	simGs.setLastLife();
		Game game = new Game(simGs, null, new RandomMsPacMan(), new LegacyTeam());
		try {
			return game.run(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

    public void updateStats(double value) {
    	totValue = (totValue*nVisits + value)/++nVisits;
    }

    public int arity() {
        return children == null ? 0 : children.length;
    }
}