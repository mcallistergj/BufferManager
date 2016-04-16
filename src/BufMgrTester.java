import java.util.*;
import java.io.*;

public class BufMgrTester {

	public static void main(String[] args) {
		int length;
		if(args.length > 0){
			try{
				length = Integer.parseInt(args[0]);
				BufMgr mgr = new BufMgr(length);
				menu(mgr);
				
			}
			catch(InputMismatchException e){
				System.out.println("Input argument must be an integer");
				System.exit(-1);
			}
		}

	}
	
	public static void menu(BufMgr mgr){
		Scanner kb = new Scanner(System.in);
		int choice, count;
		do{
			System.out.println("Please enter desired option:");
			System.out.println("1: Create initial pages");
			System.out.println("2: Request a page");
			System.out.println("3: Update a page");
			System.out.println("4: Relinquish page");
			System.out.println("-1: Exit the program");
			choice = kb.nextInt();
			switch(choice){
				case 1: 
					count = 0;
					System.out.println("Enter number of pages to create");
					int numPages = kb.nextInt();
					try{
						while(count < numPages){
							mgr.createPage(count);
							count++;
						}
					}catch(FileNotFoundException e){
						System.out.println("There was an error creating the pages!");
						System.out.println(e.getMessage());
					}
					break;
				case 2: 
					System.out.println("Enter page to pin");
					int page = kb.nextInt();
					mgr.pin(page);
					break;
				case 3: 
					System.out.println("Enter page to update:");
					int toUpdate = kb.nextInt();
					int index = mgr.display(toUpdate);
					if(index == -1){
						break;
					}
					System.out.println("Enter content to append:");
					kb.nextLine();
					String toAppend = kb.nextLine();
					mgr.updatePage(index, toAppend);
					break;
				case 4: 
					System.out.println("Enter page to unpin");
					int toUnpin = kb.nextInt();
					mgr.unpin(toUnpin);
					break;
				case -1:
					System.out.println("You will exit now");
			}
			
		}while(choice != -1);
		
	}
	

}
