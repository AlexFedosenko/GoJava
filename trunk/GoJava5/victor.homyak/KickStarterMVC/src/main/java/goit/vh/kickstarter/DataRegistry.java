package goit.vh.kickstarter;

import goit.vh.kickstarter.mvc.model.CategoryModel;
import goit.vh.kickstarter.mvc.model.ProjectModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Viktor
 */
public class DataRegistry {
    private Output output = new Output();

    private Map<Integer, ArrayList<ProjectModel>> categories;
    private Map<Integer,String> categoriesForMainPage;

    public Map<Integer,String> getCategories() {
        categoriesForMainPage = new HashMap<>();
        for (int i = 0; i <categories.size() ; i++) {
            categoriesForMainPage.put(i+1,categories.get(i+1).get(0).getParentName());
        }
//        for (Map.Entry<Integer, String> entry : categoriesForMainPage.entrySet())
//        {
//           entry.setValue()
//        }
        return categoriesForMainPage;
    }


    public void registerCategories(Map<Integer, ArrayList<ProjectModel>> categories) {
        this.categories = categories;
    }

    public ArrayList<ProjectModel> getProjectList(int index) {
        if (categories.get(index) != null) {
            return categories.get(index);
        } else {
            output.println("You choose not sutable variant, try more.\n");
            return null;
        }
    }

    public ProjectModel getProject(int[] path) {
//        try {
//            if (categories.size()<categories.get(path[0]).size()||categories.get(path[0]).size()<0)
//                return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (categories.get(path[0]).size() < path[1]  ||(path[1] - 1)<0) {
        //TODO rename, make more understandable. Use Enum
            //  output.println("You choose not sutable variant, try more.");
            return null;
        }
        return categories.get(path[0]).get(path[1] - 1);
    }
}