package belskii.artem.kickstarter;

import belskii.artem.kickstarter.mvc.controller.CategoryController;
import belskii.artem.kickstarter.mvc.controller.ProjectController;
import belskii.artem.kickstarter.mvc.model.CategoryModel;
import belskii.artem.kickstarter.mvc.model.ProjectModel;
import belskii.artem.kickstarter.mvc.view.CategoryView;
import belskii.artem.kickstarter.mvc.view.ProjectView;

public class DispatcherController {
	private String MOTIVATION_QUOTE="Берись и делай!";
	private int currentPosition=0;
	private int userInput=-1;
	private int userInputTmp=-1;
	private int currentProjectId=-1;
	private Output out = new Output();
	private Input in = new Input();
	private CategoryController category = new CategoryController(new CategoryModel(), new CategoryView());
	private ProjectController project = new ProjectController(new ProjectModel(), new ProjectView());
	
	
	public void start(){
		this.checkInput();
		
	}
	
	private  void checkInput(){
		while (currentPosition!=-1){
			if (currentPosition == 0 && userInput == -1 ){
				showCategory();
			}
			if (userInput>=1 && currentPosition == 0 ){
				showProjectFromCategoryId(userInput);
				currentPosition+=1;
				userInputTmp=userInput;
				userInput=-2;
			}
			if (userInput>=1 && currentPosition == 1){
				showProjectDetails(userInput);
				currentProjectId=userInput;
				currentPosition+=1;
				userInputTmp=userInput;
				userInput=-2;
			}
			if (userInput==1 && currentPosition == 2){
				project.addPayment(currentProjectId);
				userInputTmp=userInput;
				userInput=-2;
			}
			if (userInput==2 && currentPosition == 2){
				project.asqAQuestion(currentProjectId);
				userInputTmp=userInput;
				userInput=-2;
			}
			if (userInput == 0 ){
				if(currentPosition==2){
					showProjectDetails(userInputTmp);
				}
				if (currentPosition==1){
					showProjectFromCategoryId(userInputTmp);
				}
				if (currentPosition==0){
					showCategory();
				}
				if (currentPosition<0){
					System.exit(0);
				}
				currentPosition-=1;
			}
			userInput=in.read();
		}
	}
	
	private void showCategory(){
		out.show("The Daily Motivator:");
		out.show(MOTIVATION_QUOTE);
		out.show("====================================");
		out.showCategory(category.printCategoryList());
	}
	
	private void showProjectFromCategoryId(int id){
		out.showProjectList(project.getProjectFromCategory(id));
	}
	
	private void showProjectDetails(int id){
		out.showProjectDetails(project.printProjectDetails(userInput));		
		out.show("put 1 to make payment");
		out.show("put 2 to asq a question");
		out.show("put 0 to back to project list");
	}
	
	
}
