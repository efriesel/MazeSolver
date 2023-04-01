/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
     * Performs a Depth-First Search to solve the Maze (Stack)
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Use a stack in order to view the last valid cell (LIFO)
        Stack <MazeCell> cellsToVisit = new Stack<>();
        // set the start cell to the beginning
        MazeCell current = maze.getStartCell();
        current.setExplored(true);
        cellsToVisit.add(maze.getStartCell());
        // do this until the end is found
        while (current != maze.getEndCell()){
            // set the current to the last added cell
            current = cellsToVisit.pop();
            // if the current is the last cell, call the get solution function to return all moves
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            // check if north is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow() - 1, current.getCol())) {
                cellsToVisit.add(maze.getCell(current.getRow() - 1, current.getCol()));
                maze.getCell(current.getRow() - 1, current.getCol()).setExplored(true);
                maze.getCell(current.getRow() - 1, current.getCol()).setParent(current);
            }
            // check if east is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow(), current.getCol() + 1)) {
                cellsToVisit.add(maze.getCell(current.getRow(), current.getCol() + 1));
                maze.getCell(current.getRow(), current.getCol() + 1).setExplored(true);
                maze.getCell(current.getRow(), current.getCol() + 1).setParent(current);
            }
            // check if south is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow() + 1, current.getCol())) {
                cellsToVisit.add(maze.getCell(current.getRow() + 1, current.getCol()));
                maze.getCell(current.getRow() + 1, current.getCol()).setExplored(true);
                maze.getCell(current.getRow() + 1, current.getCol()).setParent(current);
            }
            // check if west is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow(), current.getCol() - 1)) {
                cellsToVisit.add(maze.getCell(current.getRow(), current.getCol() - 1));
                maze.getCell(current.getRow(), current.getCol() - 1).setExplored(true);
                maze.getCell(current.getRow(), current.getCol() - 1).setParent(current);
            }
        }
        //if there is no valid cell, return null (dead end)
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Use a Queue in order to access the first cell added (FIFO)
        Queue<MazeCell> cellsToVisit = new LinkedList<>();
        // set the start cell to the beginning
        MazeCell current = maze.getStartCell();
        current.setExplored(true);
        cellsToVisit.add(maze.getStartCell());
        // do this until the end is found
        while (current != maze.getEndCell()){
            // set the current to the last added cell
            current = cellsToVisit.poll();
            // if the current is the last cell, call the get solution function to return all moves
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            // check if north is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow() - 1, current.getCol())) {
                cellsToVisit.add(maze.getCell(current.getRow() - 1, current.getCol()));
                maze.getCell(current.getRow() - 1, current.getCol()).setExplored(true);
                maze.getCell(current.getRow() - 1, current.getCol()).setParent(current);
            }
            // check if east is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow(), current.getCol() + 1)) {
                cellsToVisit.add(maze.getCell(current.getRow(), current.getCol() + 1));
                maze.getCell(current.getRow(), current.getCol() + 1).setExplored(true);
                maze.getCell(current.getRow(), current.getCol() + 1).setParent(current);
            }
            // check if south is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow() + 1, current.getCol())) {
                cellsToVisit.add(maze.getCell(current.getRow() + 1, current.getCol()));
                maze.getCell(current.getRow() + 1, current.getCol()).setExplored(true);
                maze.getCell(current.getRow() + 1, current.getCol()).setParent(current);
            }
            // check if west is a valid cell, then add and set explored to true and the parent to current
            if (maze.isValidCell(current.getRow(), current.getCol() - 1)) {
                cellsToVisit.add(maze.getCell(current.getRow(), current.getCol() - 1));
                maze.getCell(current.getRow(), current.getCol() - 1).setExplored(true);
                maze.getCell(current.getRow(), current.getCol() - 1).setParent(current);
            }
        }
        //if there is no valid cell, return null (dead end)
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
