package eightpuzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class EightPuzzle {
	private int[][] board, goalBoard;
	private HashMap<Integer, String> solution;
	
	public EightPuzzle(){
		board = new int[3][3];
		setupSolution();
		setupBoard();
		setupGoalBoard();
	}
	
	public void setupGoalBoard(){
		goalBoard = new int[3][3];
		goalBoard[0][0] = 0;
		goalBoard[0][1] = 1;
		goalBoard[0][2] = 2;
		goalBoard[1][0] = 3;
		goalBoard[1][1] = 4;
		goalBoard[1][2] = 5;
		goalBoard[2][0] = 6;
		goalBoard[2][1] = 7;
		goalBoard[2][2] = 8;
	}
	
	public boolean isSolution(int[][] b){
		boolean flag = true;
		for( int i=0;i<3;i++ ){
			for( int j=0;j<3;j++ ){
				if( b[i][j] != goalBoard[i][j] ){
					return false;
				}
			}
		}
		return flag;
	}

	public void setupSolution(){
		solution = new HashMap<Integer, String>(8);
		solution.put(1, "0,1");
		solution.put(2, "0,2");
		solution.put(3, "1,0");
		solution.put(4, "1,1");
		solution.put(5, "1,2");
		solution.put(6, "2,0");
		solution.put(7, "2,1");
		solution.put(8, "2,2");
	}
	
	public int random(int min, int max){
		int range = (max-min) + 1;
		int num = (int) (Math.random() * range) + min;
		return num;
	}
	
	public void setupBoard(){
		Set<Integer> nums = solution.keySet();
		Iterator<Integer> itr = nums.iterator();
		while( isBoardFull() == false && itr.hasNext() ){
			int row = random(0,2);
			int col = random(0,2);
			if( board[row][col] == 0 ){
				board[row][col] = itr.next();	
			}
		}
	}
	
	public int[][] setupBoard(int[][] b){
		Set<Integer> nums = solution.keySet();
		Iterator<Integer> itr = nums.iterator();
		while( isBoardFull(b) == false && itr.hasNext() ){
			int row = random(0,2);
			int col = random(0,2);
			if( b[row][col] == 0 ){
				b[row][col] = itr.next();	
			}
		}
		return b;
	}
	
	public boolean isBoardFull(){
		boolean zero = false;
		for( int i=0; i<3; i++ ){
			for( int j=0; j<3; j++ ){
				if( board[i][j] == 0 && zero == false ){
					zero = true;
				} else if( board[i][j] == 0 && zero == true ) {
					return false;
				}
			}			
		}
		return zero;
	}
	
	public boolean isBoardFull(int[][] b){
		boolean zero = false;
		for( int i=0; i<3; i++ ){
			for( int j=0; j<3; j++ ){
				if( b[i][j] == 0 && zero == false ){
					zero = true;
				} else if( b[i][j] == 0 && zero == true ) {
					return false;
				}
			}			
		}
		return zero;		
	}
	
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    
    public void printBoard(int[][] b) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }    
    
    public int[][] copyOf(int[][] original, int[][] destination){
    	for( int i=0; i<3; i++ ){
    		for( int j=0; j<3; j++ ){
    			destination[i][j] = original[i][j];
    		}
    	}
    	return destination;
    }
    
    public Pair<Integer,Integer> positionOfZero(int[][] b){
    	for( int i=0;i<3;i++ ){
    		for( int j=0;j<3;j++ ){
    			if( b[i][j] == 0 ){
    				return new Pair<Integer, Integer>(i,j);
    			}
    		}
    	}
    	return null;
    }
    
    public int[][] generateBoard(Pair<Integer, Integer> p, int[][] b){
    	int[][] copy = new int[3][3];
    	copy = copyOf(b, copy);
    	Pair<Integer, Integer> zeroPos = positionOfZero(b);
    	copy[zeroPos.getR()][zeroPos.getC()] = copy[p.getR()][p.getC()];
    	copy[p.getR()][p.getC()] = 0;
    	
    	return copy;
    }
    
    public ArrayList<Pair<Integer, Integer>> candidates(int[][] b){
    	Pair<Integer, Integer> zeroPos = positionOfZero(b);
    	int zeroRow = zeroPos.getR(); int zeroCol = zeroPos.getC();
    	ArrayList<Pair<Integer, Integer>> a = new ArrayList<Pair<Integer, Integer>>(4);
    	if( zeroRow-1 >= 0 ){
    		a.add(new Pair<Integer, Integer>(zeroRow-1, zeroCol));
    	}
    	if( zeroRow+1 < 3 ){
    		a.add(new Pair<Integer, Integer>(zeroRow+1, zeroCol));
    	}
    	if( zeroCol-1 >= 0 ){
    		a.add(new Pair<Integer, Integer>(zeroRow, zeroCol-1));
    	}
    	if( zeroCol+1 < 3 ){
    		a.add(new Pair<Integer, Integer>(zeroRow, zeroCol+1));
    	}
    	return a;
    }
        
    public int evaluate(int[][] b){
    	int score = 0;
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			int current = b[i][j];
    			if( current != 0 ){
    				String properXY = solution.get(current);
    				String[] coord = properXY.split(",");
    				int diffRow = Math.abs( i - Integer.parseInt(coord[0]) );
    				int diffCol = Math.abs( j - Integer.parseInt(coord[1]) );
    				score += diffRow+diffCol;
    			}
    		}
    	}
    	return score;
    }    
    
    public int[][] getBestNeighbor(int[][] b){
    	int bestScore = Integer.MAX_VALUE;
    	int[][] tempBoard;
    	ArrayList<Pair<Integer, Integer>> a = candidates(b);
    	Iterator<Pair<Integer, Integer>> i = a.iterator();
    	while(i.hasNext()){
    		Pair<Integer,Integer> currentPair = i.next();
    		tempBoard = generateBoard(currentPair, b);
    		int tempScore = evaluate(tempBoard);
    		if( tempScore < bestScore ){
    			bestScore = tempScore;
    			return tempBoard;
    		}
    	}
    	
    	return board;
    }
    
    public int[][] hillClimbing(){
    	int[][] current = new int[3][3];
    	current = copyOf(board, current);
    	
    	int[][] neighbor = new int[3][3];
    	
    	boolean flag = true;
    	while(flag){
    		neighbor = getBestNeighbor(current);
    		int neighborScore = evaluate(neighbor);
    		int currentScore = evaluate(current);
    		if( neighborScore >= currentScore || isSolution(current) ){
    			flag = false;
    			System.out.println("Current is the best");
    			printBoard(current);
    			System.out.println("Final score: "+currentScore);
    		} else {
    			System.out.println("There's a better neighbor, copy it and run again");
    			current = copyOf(neighbor, current);
    			System.out.println("Neighbor score: "+neighborScore);
    			
    		}
    	}
		return null;
    }
    
    public int[][] hillClimbingRR(){
    	int[][] bestBoard = new int[3][3];
    	int bestBoardScore = Integer.MAX_VALUE;

    	long endTime = System.currentTimeMillis() + (6000);
    	while(System.currentTimeMillis() < endTime){
    		int[][] current = new int[3][3];
    		current = setupBoard(current);
    		int currentScore = evaluate(current);
    		if( currentScore < bestBoardScore ){
    			bestBoardScore = currentScore;
    			bestBoard = copyOf(current, bestBoard);
    		}
    	}

    	return bestBoard;
    }
    
    public static void main(String[] args){
    	EightPuzzle p = new EightPuzzle();

    	//p.printBoard();
    	//System.out.println("Starting Score: "+p.evaluate(p.board));
    	//p.hillClimbing();
    	p.printBoard(p.hillClimbingRR()); 
    }
}