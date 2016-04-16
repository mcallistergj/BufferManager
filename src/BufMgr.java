import java.util.*;
import java.io.*;


public class BufMgr {

	private Frame[] pages;
	private LinkedList<Integer> queue;
	private PrintWriter out;
	private BufHashTbl hashTable;
	
	public BufMgr(int length){
		this.queue = initQueue(length);
		this.pages = new Frame[length];
		this.hashTable = new BufHashTbl();
	}
	
	public LinkedList<Integer> initQueue(int size){
		LinkedList<Integer> newQueue = new LinkedList<Integer>();
		for(int i = 0; i < size; i++){
			newQueue.addLast(i);
		}
		return newQueue;
	}
	
	public void pin(int pageNum){
		int index = this.hashTable.lookup(pageNum);
		if(index != -1){
			this.pages[index].incPin();
			this.pages[index].displayPage();
		}
		else{
			if(this.queue.size() > 0){
				String pageContent = readPage(pageNum);
				if(pageContent == null){
					System.out.println(pageNum + " could not be found. Please enter a valid page.");
					return;
				}
				System.out.println(pageContent);
				int freeFrame = this.queue.getFirst();
				this.queue.remove();
				if(this.pages[freeFrame] != null && this.pages[freeFrame].isDirty()){
					this.writePage(this.pages[freeFrame].getPageNum(), this.pages[freeFrame].getContent());
				}
				this.pages[freeFrame] = new Frame(pageContent, pageNum);
				this.hashTable.insert(pageNum, freeFrame);
				
			}
			else{
				System.out.println("There are currently no empty frames in the buffer pool\n" +
									"Please relinquish a frame to read in a new page.");
			}
		}
	}
	
	public void unpin(int pageNum){
		int toRelease = this.hashTable.lookup(pageNum);
		if(toRelease == -1){
			System.out.println("The page you entered is not currently pinned.");
			return;
		}
		pages[toRelease].decPin();
		if(pages[toRelease].getPins() <= 0){
			this.queue.addLast(toRelease);
			this.hashTable.remove(pageNum);
		}
	}
	
	
	public void createPage(int pageNum)throws FileNotFoundException{
		out = new PrintWriter(new File(pageNum+".txt"));
		out.println("This is page " + pageNum + ".");
		out.close();
	}
	
	public String readPage(int pageNum){
		String input = "";
		try{
			Scanner fin = new Scanner(new File(pageNum + ".txt"));
			while(fin.hasNextLine()){
				input = input.concat(fin.nextLine());
			}
			fin.close();
		}catch(FileNotFoundException e){
			System.out.println("Could not find requested file. Please enter existing file to read.");
			return null;
		}
		return input;
	}
	
	public int display(int pageNum){
		int index = this.hashTable.lookup(pageNum);
		if(index == -1){
			System.out.println(pageNum + " is not currently pinned. Please try again.");
			return -1;
		}
		this.pages[index].displayPage();
		return index;
	}
	
	public void updatePage(int index, String toAppend){
		this.pages[index].updateContent(toAppend);
		this.pages[index].setDirty();
	}
	
	public void writePage(int pageNum, String pageContent){
		try{
			PrintWriter fout = new PrintWriter(new File(pageNum + ".txt"));
			fout.print(pageContent);
			fout.close();
		}catch(FileNotFoundException e){
			System.out.println("Error writing the page to file!");
		}
	}
	
	public Frame[] getPages(){
		return this.pages;
	}
	
}
