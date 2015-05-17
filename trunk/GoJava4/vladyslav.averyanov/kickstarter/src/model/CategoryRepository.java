package model;

import java.util.ArrayList;

import model.Category;

public class CategoryRepository {
	
	private ArrayList <Category> category;
	
	public CategoryRepository(){
		category = new ArrayList<>();
		
		category.add(new Category("Video",	"Any video you can imagine. Start from multibillionairy "
				+ "blockbasters and ending your home videos"));
		category.add(new Category("Audio",	"Any audio you can imagine. New DJ-set or special musical "
				+ "instrument - all of it here"));
	}
	
	public ArrayList <Category> getCategoies(){
		return category;
	}

}
