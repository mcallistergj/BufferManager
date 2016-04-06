import java.util.*;

public class BufHashTbl {

	private ArrayList<LinkedList<Node>> hashTable;
	private final int size = 7;
	
	public BufHashTbl(){
		this.hashTable = new ArrayList<LinkedList<Node>>(size);
	}
	
	public void insert(int pageNum, int frameNum){
		
	}
	
	public int lookup(int pageNum){
		int key = (pageNum % size);
		LinkedList<Node> toCheck = this.hashTable.get(key);
		for(Node n : toCheck){
			if(n.getPage() == pageNum)
				return n.getFrame();
		}
		return -1;
		
	}
	
	public void remove(int frameNum, int pageNum){
		
	}
	
	public static class Node{
		private int page;
		private int frame;
		
		public Node(int pNum, int fNum){
			this.page = pNum;
			this.frame = fNum;
		}
		
		public int getPage(){
			return this.page;
		}
		
		public int getFrame(){
			return this.frame;
		}
	}
	
	
	
	
}
