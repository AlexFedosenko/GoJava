package goit.nz.kickstarter;

import java.util.ArrayList;

public class Kickstarter {
	private final int EXIT = 0;
	private Console console;
	private QuoteList quotes;
	private CategoryList categories;
	private ProjectList projects;

	public Kickstarter() {
		console = new Console(System.in);
		quotes = new QuoteList();
		categories = new CategoryList();
		projects = new ProjectList();
	}

	public void run() {
		/*Question to mentor\coach - it needs to extract menu processing logic and creating object output to new classes
		 * or maybe it would be better to encapsulate output preparing in CategoryList, ProjectList and so on
		 * or maybe should i implement a strategy pattern by creating an interface (for example) Displayable
		 * and every instanse will setup and keep his own Displayable. 
		 * But anyway we have to create one DisplayBehavior class for every object (CategoryListDisplay, ProjectListDisplay, etc.)
		 * Can such logic be called a "strategy"?
		 * */
		init();
		showQuote();
		while (true) {
			showCategories(categories);
			Category selectedCategory = selectCategory(categories);
			while (true) {
				ArrayList<Project> foundProjects = projects.get(selectedCategory); 
				showProjectList(foundProjects);
				int projectIndex = selectProject(foundProjects);
				if (projectIndex == EXIT) {
					break;
				}
				showProjectFull(foundProjects.get(projectIndex - 1));
			}
		}
	}

	private Category selectCategory(CategoryList categories) {
		int startIndex = 1;
		int endIndex = categories.size();
		String prompt = "Select category (" + startIndex + " - " + endIndex
				+ "):";
		console.write(prompt);
		int menuIndex = console.readInt();
		while (menuIndex < startIndex || menuIndex > endIndex) {
			console.write("Invalid selection!");
			console.write(prompt);
			menuIndex = console.readInt();
		}
		Category selected = categories.getCategory(menuIndex - 1);
		console.write("You have choosen category: " + selected.getName());
		return categories.getCategory(menuIndex - 1);
	}

	private void showQuote() {
		console.write(quotes.getRandomQuotation());
	}

	private void showCategories(CategoryList categories) {
		String[] list = categories.getList();
		console.write("-----------------------------------------------------");
		console.write("Category List:");
		for (int index = 0; index < list.length; index++) {
			console.write("[" + (index + 1) + " - " + list[index] + "]");
		}
	}

	private int selectProject(ArrayList<Project> found) {
		int startIndex = 0;
		int endIndex = found.size();
		String prompt = "Select project (" + String.valueOf(startIndex + 1)
				+ " - " + endIndex + "):";
		if (endIndex > 0) {
			console.write(prompt);
		} 
		int menuIndex = console.readInt();
		while (menuIndex < startIndex || menuIndex > endIndex) {
			console.write("Invalid selection!");
			if (endIndex > 0) {
				console.write(prompt);
			}
			menuIndex = console.readInt();
		}
		return menuIndex;
	}

	private void showProjectList(ArrayList<Project> found) {
		// TODO fix ugly layout
		console.write("-----------------------------------------------------");
		if (found.size() > 0) {
			console.write("Project List:");
			showProjects(found);
		} else {
			console.write("Empty category!");
		}
		console.write(EXIT + " - return to categories");
	}

	private void showProjects(ArrayList<Project> found) {
		for (int index = 0; index < found.size(); index++) {
			Project p = found.get(index);
			console.write(String.valueOf(index + 1) + " - " + p.getName());
			console.write("Description: " + p.getDescription());
			console.write("Required Amount: " + p.getRequiredAmount());
			console.write("Collected Amount: " + p.getCollectedAmount());
			console.write("Days Left: " + p.getDaysLeft());
			console.write("-----------------------------------------------------");
		}
	}

	private void showProjectFull(Project p) {
		// TODO fix ugly layout
		console.write("You have choosen project - " + p.getName());
		console.write("-----------------------------------------------------");
		console.write("Name: " + p.getName());
		console.write("Description: " + p.getDescription());
		console.write("Required Amount: " + p.getRequiredAmount());
		console.write("Collected Amount: " + p.getCollectedAmount());
		console.write("Days Left: " + p.getDaysLeft());
		console.write("History: " + p.getHistory());
		console.write("Link To Video: " + p.getLink());
		console.write("Questions & Answers: " + p.getQuestionsAnswers());
		console.write("-----------------------------------------------------");
		console.write(EXIT + " - return to projects");
		int menuIndex = console.readInt();
		while (menuIndex != EXIT) {
			console.write("Invalid selection!");
			menuIndex = console.readInt();
		}
	}

	private void init() {
		quotes.add("\"Start by doing what's necessary; then do what's possible; "
				+ "and suddenly you are doing the impossible.\"\n(c) Francis of Assisi");
		quotes.add("\"The best and most beautiful things in the world cannot be seen or even touched "
				+ "- they must be felt with the heart.\"\n(c) Helen Keller");
		quotes.add("\"I can't change the direction of the wind, "
				+ "but I can adjust my sails to always reach my destination.\"\n(c) Jimmy Dean");
		Category cat1 = new Category("Art");
		Category cat2 = new Category("Comics");
		Category cat3 = new Category("Crafts");
		Category cat4 = new Category("Games");
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		categories.add(cat4);
		Project p1 = new Project("name1", "desc1", 1000, 0, 10);
		Project p2 = new Project("name2", "desc2", 10000, 100, 90);
		Project p3 = new Project("name3", "desc3", 100, 90, 15);
		Project p4 = new Project("name4", "desc4", 100000, 50000, 100);
		projects.add(p1);
		projects.add(p2);
		projects.add(p3);
		projects.add(p4);
		p1.setCategory(cat2);
		p2.setCategory(cat4);
		p3.setCategory(cat3);
		p4.setCategory(cat4);
		Project p5 = new Project("Empty project");
		projects.add(p5);
		p5.setCategory(cat4);
		p1.setHistory("We have almost finished!\nWe are going to start!");
		p1.setLink("http://www.youtube.com/jrgri74ht3h97");
		p1.setQuestionsAnswers("How can you imagine such idea?\nBecause of whisky, babe!");
		p2.setHistory("Blah-blah-blah-blah...");
	}
}