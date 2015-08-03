package belskii.artem.kickstarter.mvc.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import belskii.artem.kickstarter.dao.project.Project;
import belskii.artem.kickstarter.mvc.model.ProjectModel;
import belskii.artem.kickstarter.mvc.view.ProjectView;

public class ProjectControllerTest {
	private ProjectController project;
	private ArrayList<Project> projectForEquals = new ArrayList<Project>();
	private HashMap<Long, String> paymentVariants;
	@Before
	public void setUp() throws Exception {
		paymentVariants = new HashMap<Long, String>();
		paymentVariants.put(new Long(10), "some bonus");
		paymentVariants.put(new Long(30), "other bonus");
		paymentVariants.put(new Long(50), "extra bonus");
		project = new ProjectController(new ProjectModel(), new ProjectView());
		projectForEquals.add(new Project("My test project from Art category",new Long(1), new Long(1),"28.07.2015","30.07.2015","https://www.youtube.com/watch?v=uC0pqWX3yB8", 1, "Project details",paymentVariants));
		projectForEquals.add(new Project("My test project1 from Comics category",new Long(2), new Long(2),"29.07.2015","31.07.2015","https://www.youtube.com/watch?v=uC0pqWX3yB8", 2,"Project details",paymentVariants));
		projectForEquals.add(new Project("My test project2 from Crafts category",new Long(3), new Long(3),"30.07.2015","01.08.2015","https://www.youtube.com/watch?v=uC0pqWX3yB8", 3, "Project details",paymentVariants));

	}

	@Test
	public void testProjectController() {
		assertNotNull(project);
	}

	@Test
	public void testAddProject() {
		project.addProject(new Project("testAddProject", new Long(1),new Long(1),"01.01.2015","31.12.2015","https://somwurl.com",1,"project for testAddProject test",paymentVariants)); //TODO: return new projectId
	}

	@Test
	public void testGetProjectList() {
		boolean answer=true;
		for (int i=0; i<project.getProjectList().size(); i++){
			if (!project.getProjectList().get(i).getName().equals(projectForEquals.get(i).getName())){
				answer=false;
			}
		}
		assertTrue(answer);
	}


	@Test
	public void testPrintProjectDetails() {
		assertEquals("Project details",project.printProjectDetails(0).getDetails());

	}

	@Test
	public void testGetProjectFromCategory() {
		assertEquals("My test project from Art category", project.getProjectFromCategory(1).get(0).getName());
	}

}
