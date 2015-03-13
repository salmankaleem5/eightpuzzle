package eightpuzzle;

public class EightPuzzle {
	private int[][] board;
	
	public EightPuzzle(){
		board = new int[3][3];
		setupBoard();
	}
	
	public int random(int min, int max){
		int range = (max-min) + 1;
		int num = (int) (Math.random() * range) + min;
		return num;
	}
	
	public void setupBoard(){
		while( isBoardFull() == false ){
			int row = random(0,2);
			int col = random(0,2);
			//System.out.println(row+","+col);
			board[row][col] = 1;
		}
		printBoard();
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
    	
    }
}