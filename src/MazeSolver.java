/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        ArrayList<MazeCell> solution = new ArrayList<>();
        Stack<MazeCell> endToFirst = new Stack<>();
        MazeCell current = maze.getEndCell();
        endToFirst.push(current);
        while (current.getParent() != null){
            current = current.getParent();
            endToFirst.push(current);
        }
        while (endToFirst.size() > 0){
            solution.add(endToFirst.pop());
        }
        // Should be from start to end cells
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        Stack <MazeCell> cellsToVisit = new Stack<>();
        MazeCell current = maze.getStartCell();
        cellsToVisit.add(maze.getStartCell());
        while (current != maze.getEndCell()){
            current = cellsToVisit.pop();
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            if (maze.isValidCell(current.getRow() - 1, current.getCol())) {
                cellsToVisit.add(maze.getCell(current.getRow() - 1, current.getCol()));
                maze.getCell(current.getRow() - 1, current.getCol()).setParent(current);
            }
            if (maze.isValidCell(current.getRow(), current.getCol() + 1)) {
                cellsToVisit.add(maze.getCell(current.getRow(), current.getCol() + 1));
                maze.getCell(current.getRow(), current.getCol() + 1).setParent(current);
            }
            if (maze.isValidCell(current.getRow() + 1, current.getCol())) {
                cellsToVisit.add(maze.getCell(current.getRow() + 1, current.getCol()));
                maze.getCell(current.getRow() + 1, current.getCol()).setParent(current);
            }
            if (maze.isValidCell(current.getRow(), current.getCol() - 1)) {
                cellsToVisit.add(maze.getCell(current.getRow(), current.getCol() - 1));
                maze.getCell(current.getRow(), current.getCol() - 1).setParent(current);
            }
        }
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
