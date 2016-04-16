import java.util.*;

public class BufHashTbl {

	private LinkedList<HashNode>[] hashTable;
	private final int size = 7;
	
	public BufHashTbl(){
		this.hashTable = new LinkedList[size];
		for(int i = 0; i < size; i++){
			hashTable[i] = new LinkedList<HashNode>();
		}
		
	}
	
	public void insert(int pageNum, int frameNum){
		int key = (pageNum%size);
		hashTable[key].addFirst(new HashNode(pageNum, frameNum));
		
	}
	
	public int lookup(int pageNum){
		int key = (pageNum % size);
		LinkedList<HashNode> toCheck = this.hashTable[key];
		for(HashNode n : toCheck){
			if(n.getPage() == pageNum)
				return n.getFrame();
		}
		return -1;
		
	}
	
	public void remove(int pageNum){
		int key = (pageNum % size);
		LinkedList<HashNode> toCheck = this.hashTable[key];
		for(HashNode n : toCheck){
			if(n.getPage() == pageNum)
				toCheck.remove(n);
		}
		
	}
	
	class HashNode{
		private int page;
		private int frame;
		
		public HashNode(int pNum, int fNum){
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
