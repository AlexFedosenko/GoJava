package goit.vh.kickstarter.mvc.controller;


import goit.vh.kickstarter.LocationManager;
import goit.vh.kickstarter.Output;
import goit.vh.kickstarter.mvc.model.CategoryModel;
import goit.vh.kickstarter.mvc.model.ProjectModel;
import goit.vh.kickstarter.mvc.view.CategoryView;
import goit.vh.kickstarter.mvc.view.ProjectView;

/**
 * Created with IntelliJ IDEA.
 * User: Viktor
 */
public class CategoryController {
    private ProjectModel projectModel;
    private LocationManager locationManager;
    private CategoryView view;
    private ProjectView projectView;
    private CategoryModel model;
    private Output output = new Output();

    public CategoryController(CategoryView view,
                              ProjectView projectView,
                              CategoryModel model,
                              ProjectModel projectModel) {
        this.view = view;
        this.model = model;
        this.projectView = projectView;
        this.projectModel = projectModel;
    }

    public void start(int[] path) {
        if (path[0] != 0 && path[1] == 0) {

            if (model.getCategories().size() < path[0] - 1) {
                path[0] = 0;
                locationManager.setPath(path);
                locationManager.dispatch();
            }
            model.refreshModel(path[0]);
            view.render(model);

            projectModel.refreshListModel(path[0]);

            projectView.renderList(projectModel.getListOfProjectses());

            path[1] = Integer.parseInt(projectView.getInput());

            if (path[1] == 0) {
                path[0] = 0;
                locationManager.setPath(path);
                locationManager.dispatch();
            }
            if (projectModel.refreshModel(path) == null) {
                output.println("You choose not sutable variant, returning to previous menu\n");
                path[1] = 0;
            }
            // TODO exception
            if (path[1] == 0) {
                path[0] = 0;
            }
            locationManager.setPath(path);
            locationManager.dispatch();
        }
        model.refreshModel(path[0]);
        locationManager.dispatch();

    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

}
