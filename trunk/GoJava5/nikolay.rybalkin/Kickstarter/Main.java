import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	private String SPACE = " ";
    
	public Main(�ategories categories) {
		this.categories = categories;
	}

	public static void main(String[] args){
		
		Category category1 = new Category("Game");
		Category category2 = new Category("Design");
		Category category3 = new Category("Technology");
		
		�ategories categories = new �ategories();
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		
    	Main app = new Main(categories);
    	app.run();
    }

	private �ategories categories;
	private int b;
    
    private void run() {
    	
    	QuoteGenerate generate = new QuoteGenerate();
    	System.out.println(generate.quoteGenerate());
    	System.out.println(SPACE);
    	System.out.println("Select category: ");
    	System.out.println(Arrays.toString(categories.get�ategories()));
    	
    	ScanConsole scanConsole = new ScanConsole();
    	String categoryName = categories.getName(scanConsole.consoleScan(b));
    	System.out.println("You selected category: " + categoryName);
	}
}

