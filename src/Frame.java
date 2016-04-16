
public class Frame {

	private boolean dirty_bit;
	private int pins;
	private String content;
	private int frameNum;
	private int pageNum;
	
	
	
	public Frame(){
		this.pins = 1;
		this.dirty_bit = false;
		this.content = "";
	}
	
	public Frame(String newContent, int pageNum){
		this.pins = 1;
		this.dirty_bit = false;
		this.content = newContent;
		this.pageNum = pageNum;
	}
	
	public Frame(int frameNum){
		this.pins = 0;
		this.dirty_bit = false;
		this.content = "";
		this.frameNum = frameNum;
	}
	
	public void setPageNume(int pageNum){
		this.pageNum = pageNum;
	}
	
	public int getPageNum(){
		return this.pageNum;
	}
	
	public void setFrameNum(int newFrameNum){
		this.frameNum = newFrameNum;
	}
	
	public int getFrameNum(){
		return this.frameNum;
	}
	public void setDirty(){
		this.dirty_bit = true;
	}
	
	public boolean isDirty(){
		return this.dirty_bit;
	}
	
	public void decPin(){
		this.pins--;
	}
	
	public void incPin(){
		this.pins++;
	}
	
	public int getPins(){
		return this.pins;
	}
	
	public void displayPage(){
		System.out.println(this.content);
	}
	
	public void updateContent(String toWrite){
		this.content = this.content.concat(" "+toWrite);
	}
	
	public String getContent(){
		return this.content;
	}
	
}
