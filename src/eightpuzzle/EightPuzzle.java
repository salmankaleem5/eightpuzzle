package eightpuzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class EightPuzzle {
	private int[][] board;
	private HashMap<Integer, String> solution;
	
	public EightPuzzle(){
		board = new int[3][3];
		setupSolution();
		setupBoard();
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
    
    public int[][] copyOf(int[][] original, int[][] destination){
    	for( int i=0; i<3; i++ ){
    		for( int j=0; j<3; j++ ){
    			original[i][j] = destination[i][j];
    		}
    	}
    	return destination;
    }
    
    public int evaluate(int[][] b){
    	int score = 0;
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			int current = b[i][j];
    			if( current != 0 ){
    				String properXY = solution.get(current);
    				String[] coord = properXY.split(",");
    				int diffRow = Math.abs(Integer.parseInt(coord[0])-i);
    				int diffCol = Math.abs(Integer.parseInt(coord[1])-j);
    				score += diffRow+diffCol;
    			}
    		}
    	}
    	return score;
    }
    
    public String possibleMoves( int[][] b ){
    	
    }
    
    public int[][] getNeighbors(int[][] b){
    	int x = 0; int y = 0;
    	outerloop:
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			int current = b[i][j];
    			if( current == 0 ){
    				x=i;
    				y=j;
    				break outerloop;
    			}
    		}
    	}     	
    	return b;
    }
    
    public int[][] hillClimbing(){
    	int[][] current = new int[3][3];
    	current = copyOf(board, current);
    	int currentVal = 0; 
    	int neighborVal = 0;
    	while(true){
    		//neighbor = highest valued successor of current
    		neighborVal = 1;
    		if( neighborVal <= currentVal ){
    			return current;
    		}
    		//current = neighbor;
    	}
    }
    
    public static void main(String[] args){
    	EightPuzzle p = new EightPuzzle();
    	p.printBoard();
    	System.out.println(p.evaluate(p.board));
    }
}