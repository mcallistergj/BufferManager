
public class Frame {

	private boolean dirty_bit;
	private int pins;
	private String content;
	
	
	
	public Frame(){
		this.pins = 0;
		this.dirty_bit = false;
		this.content = "";
	}
	
	public boolean isDirty(){
		return this.dirty_bit;
	}
	
	public void setPin(int pinCount){
		this.pins = pinCount;
	}
	
	public int getPins(){
		return this.pins;
	}
	
	public void writeContent(String toWrite){
		
	}
	
}
