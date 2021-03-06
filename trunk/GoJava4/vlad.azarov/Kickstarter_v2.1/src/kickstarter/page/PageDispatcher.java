package com.go_java4.alex_mirn.input_output.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import kickstarter.printer.Printer;
import kickstarter.reader.Reader;
import kickstarter.repos.Repository;



public class PageDispatcher {
    State state = State.CATEGORIES;
    private Printer printer;
    private Reader reader;
    private Repository repository;
    private IPage ipage;
    private static Map<String, String> choice = new HashMap<String, String>();
    private static Stack<IPage> pagesStack = new Stack<IPage>();
    private static boolean goBack = false;

    public PageDispatcher(Printer printer, Reader reader, Repository repository) {
	this.printer = printer;
	this.reader = reader;
	this.repository = repository;
    }

    public void run() throws IOException {
	while (true) {
	    switch (state) {
	    case CATEGORIES:
		if (goBack == true) {
		    ipage = pagesStack.pop();
		    goBack = false;
		} else {
		    ipage = new CategoriesPage(printer, reader, repository);
		}
		ipage.run(choice);
		state = ipage.makeChoice(choice);
		if (state == State.PROJECTS) {
		    pagesStack.push(ipage);
		}
		break;
	    case PROJECTS:
		if (goBack == true) {
		    ipage = pagesStack.pop();
		    goBack = false;
		} else {
		    ipage = new ProjectsPage(printer, reader, repository);
		}
		ipage.run(choice);
		state = ipage.makeChoice(choice);
		if (state == State.CATEGORIES) {
		    goBack = true;
		}
		if (state == State.ONE_PROJECT_PAGE) {
		    pagesStack.push(ipage);
		}
		break;
	    case ONE_PROJECT_PAGE:
		if (goBack == true) {
		    ipage = pagesStack.pop();
		    goBack = false;
		} else {
		    ipage = new OneProjectPage(printer, reader, repository);
		}
		ipage.run(choice);
		state = ipage.makeChoice(choice);
		if (state == State.PROJECTS) {
		    goBack = true;
		}
		break;
	    }
	}
    }
}
