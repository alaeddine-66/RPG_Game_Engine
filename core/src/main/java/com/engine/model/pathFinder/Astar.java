package com.engine.model.pathFinder;

import com.badlogic.gdx.math.Vector2;

import java.util.*;

/**
 * A Star Algorithm
 *
 * @author Marcelo Surriabre
 * @version 2.1, 2017-02-23
 */
public class Astar implements IPathfindingStrategy{
    private static final int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    private static final int DEFAULT_DIAGONAL_COST = 14;
    private int hvCost;
    private int diagonalCost;
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node initialNode;
    private Node finalNode;
    private int[][] blocks;

    public Astar(int rows, int cols, int[][] blocks ,  int hvCost, int diagonalCost ) {
        this.hvCost = hvCost;
        this.diagonalCost = diagonalCost;
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        this.closedSet = new HashSet<>();
        this.blocks = blocks;

    }

    public Astar(int rows, int cols,int[][] blocks ) {
        this(rows, cols, blocks, DEFAULT_HV_COST, DEFAULT_DIAGONAL_COST );
    }


    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    public void setBlocks(int[][] blocksArray) {
        for (int[] ints : blocksArray) {
            int row = ints[0];
            int col = ints[1];
            setBlock(row, col);
        }
    }

    @Override
    public List<Vector2> findPath(Node initialNode, Node finalNode, int entityWidth, int entityHeight) {
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        setNodes();
        setBlocks(blocks);

        openList.add(initialNode);
        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode, entityWidth, entityHeight);
            }
        }
        return new ArrayList<>();
    }


    private List<Vector2> getPath(Node currentNode) {
        List<Vector2> path = new ArrayList<>();
        path.add(currentNode.getPos() );
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0,parent.getPos());
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode, int entityWidth, int entityHeight) {
        addAdjacentUpperRow(currentNode, entityWidth, entityHeight);
        addAdjacentMiddleRow(currentNode, entityWidth, entityHeight);
        addAdjacentLowerRow(currentNode, entityWidth, entityHeight);
    }


    private void addAdjacentLowerRow(Node currentNode, int entityWidth, int entityHeight) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, lowerRow, getDiagonalCost(), entityWidth, entityHeight); // Comment this line if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, lowerRow, getDiagonalCost(), entityWidth, entityHeight); // Comment this line if diagonal movements are not allowed
            }
            checkNode(currentNode, col, lowerRow, getHvCost(), entityWidth, entityHeight);
        }
    }

    private void addAdjacentMiddleRow(Node currentNode, int entityWidth, int entityHeight) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0 && middleRow >= 0 && middleRow < getSearchArea().length) {
            checkNode(currentNode, col - 1, middleRow, getHvCost(), entityWidth, entityHeight);
        }
        if (col + 1 < getSearchArea()[0].length && middleRow >= 0 && middleRow < getSearchArea().length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost(), entityWidth, entityHeight);
        }
    }

    private void addAdjacentUpperRow(Node currentNode, int entityWidth, int entityHeight) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0 ) {
                checkNode(currentNode, col - 1, upperRow, getDiagonalCost(), entityWidth, entityHeight);
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, upperRow, getDiagonalCost(), entityWidth, entityHeight);
            }
            checkNode(currentNode, col, upperRow, getHvCost(), entityWidth, entityHeight);
        }
    }

    // Nouvelle méthode pour vérifier la collision entre les nœuds en diagonale
    private boolean hasObstacleBetween(Node startNode, Node endNode) {
        // Vérifier que les limites sont respectées et si une tuile est bloquée
        if (startNode.getRow() >= searchArea.length || startNode.getCol() >= searchArea[0].length
        || endNode.getRow() >= searchArea.length || endNode.getCol() >= searchArea[0].length) {
            return true; // Sort de la zone, donc considéré comme une collision
        }
        // Vérifie s'il y a un obstacle dans les cases traversées pour un mouvement en diagonale
        if (startNode.getRow() != endNode.getRow() && startNode.getCol() != endNode.getCol()) {
            // Mouvement en diagonale : vérifier les cases intermédiaires
            Node node1 = searchArea[startNode.getRow()][endNode.getCol()];
            Node node2 = searchArea[endNode.getRow()][startNode.getCol()];
            return node1.isBlock() || node2.isBlock();
        }
        return false;
    }

    // Nouvelle méthode pour vérifier si une entité de grande taille peut se déplacer sans collision
    private boolean hasObstacleInArea(Node startNode, Node endNode, int entityWidth, int entityHeight) {
        int tileWidth = 32; // Taille de la tuile en pixels
        int tileHeight = 32;

        // Calculer le nombre de tuiles en largeur et en hauteur pour couvrir l'entité
        int tilesWide = (int) Math.ceil((double) entityWidth / tileWidth);
        int tilesHigh = (int) Math.ceil((double) entityHeight / tileHeight);

        // Vérifier toutes les tuiles occupées par l'entité dans sa nouvelle position
        for (int i = 0; i < tilesWide; i++) {
            for (int j = 0; j < tilesHigh; j++) {
                int checkRow = endNode.getRow() + j;
                int checkCol = endNode.getCol() + i;

                // Vérifier que les limites sont respectées et si une tuile est bloquée
                if (checkRow >= searchArea.length || checkCol >= searchArea[0].length) {
                    return true; // Sort de la zone, donc considéré comme une collision
                }

                Node checkNode = searchArea[checkRow][checkCol];
                if (checkNode.isBlock()) {
                    return true; // Collision détectée
                }
            }
        }
        return false;
    }

    // Modification de checkNode pour inclure la vérification de collision de coins
    private void checkNode(Node currentNode, int col, int row, int cost, int entityWidth, int entityHeight) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)
            && !hasObstacleBetween(currentNode, adjacentNode)
            && !hasObstacleInArea(currentNode, adjacentNode, entityWidth, entityHeight)
        ) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }


    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }

    public int getHvCost() {
        return hvCost;
    }

    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }

    private int getDiagonalCost() {
        return diagonalCost;
    }

    private void setDiagonalCost(int diagonalCost) {
        this.diagonalCost = diagonalCost;
    }
}
