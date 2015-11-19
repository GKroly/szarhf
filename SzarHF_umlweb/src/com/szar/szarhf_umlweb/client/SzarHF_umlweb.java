package com.szar.szarhf_umlweb.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import com.szar.gwt.connectors.client.CornerPoint;
import com.szar.gwt.connectors.client.Diagram;
import com.szar.gwt.connectors.client.elements.Connector;
import com.szar.gwt.connectors.client.elements.SectionDecoration;
import com.szar.gwt.connectors.client.elements.SectionDecoration.DecorationType;
import com.szar.gwt.connectors.client.elements.Shape;
import com.szar.gwt.connectors.client.elements.Shape.CPShapeType;
import com.szar.gwt.connectors.client.images.ConnectorsBundle;
import com.szar.gwt.connectors.client.util.ConnectorStyle;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SzarHF_umlweb implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * Global Values
	 */
	int leftMenuSize = 200;
	int maxHeight = 455;
	String projectName = "No loaded project yet";
	boolean isProjectLoaded;
	TreeMap<String, Diagram> models;
	TreeMap<String, AbsolutePanel> testModels;
	AbsolutePanel diagramBoundaryPanel;
	VerticalPanel rightVerticalPanel;
	Diagram diagram;
	MenuBar mainMenu;
	StackLayoutPanel stackPanel;
	String activeModelName;
	Button diagramElementsButton;

	/**
	 * This is the entry point method.
	 */

	public void onModuleLoad() {

		models = new TreeMap<String, Diagram>();
		testModels = new TreeMap<String, AbsolutePanel>();
		isProjectLoaded = false;
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setTitle("VPanel");
		// mainVerticalPanel.setStylePrimaryName("my-MainPanel");

		mainMenu = new MenuBar();
		mainMenu.setHeight("35px");

		createMainMenu(mainMenu);
		mainVerticalPanel.add(mainMenu);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		VerticalPanel leftVerticalPanel = new VerticalPanel();
		rightVerticalPanel = new VerticalPanel();
		horizontalPanel.add(leftVerticalPanel);
		horizontalPanel.add(rightVerticalPanel);
		rightVerticalPanel.setBorderWidth(1);
		rightVerticalPanel.setPixelSize(900, maxHeight);
		rightVerticalPanel.setWidth("800px");
		mainVerticalPanel.add(horizontalPanel);

		diagramBoundaryPanel = new AbsolutePanel();
		diagramBoundaryPanel.setSize("800px", "450px");
		rightVerticalPanel.add(diagramBoundaryPanel);
		//test(rightVerticalPanel);		

		diagram = new Diagram(diagramBoundaryPanel);

		stackPanel = new StackLayoutPanel(Unit.EM);
		stackPanel.setPixelSize(leftMenuSize, maxHeight);
		createLeftMenu(stackPanel);
		leftVerticalPanel.add(stackPanel);
		RootPanel.get("MainWindow").add(mainVerticalPanel);

	}

	Command cmd = new Command() {
		public void execute() {
			Window.alert("You selected...");
		}
	};

	Command cmd2 = new Command() {
		public void execute() {

		}
	};

	//Betolt egy probadiagrammot
	private void test(VerticalPanel viewPanel) {

		diagram = new Diagram(diagramBoundaryPanel);

		diagramBoundaryPanel.add(new Label("Connectors example for GWT 2.4"), 10, 2);
		Connector connector1 = new Connector(50, 80, 150, 200,
				new SectionDecoration(DecorationType.ARROW_SOLID),
				new SectionDecoration(DecorationType.ARROW_SOLID));
		connector1.showOnDiagram(diagram);

		ArrayList<CornerPoint> cp = new ArrayList<CornerPoint>();
		cp.add(new CornerPoint(370, 200));
		cp.add(new CornerPoint(370, 120));
		cp.add(new CornerPoint(270, 120));
		SectionDecoration startDecoration = new SectionDecoration(
				DecorationType.ARROW_LINE);
		SectionDecoration endDecoration = new SectionDecoration(new Image(
				"http://code.google.com/images/ph-logo.png"), new Image(
				"http://code.google.com/images/ph-logo.png"));
		Connector connector2 = new Connector(350, 200, 270, 80, cp,
				startDecoration, endDecoration);
		connector2.style = ConnectorStyle.DASHED;
		connector2.showOnDiagram(diagram);

		Connector connector3 = new Connector(450, 120, 500, 80,
				new SectionDecoration(DecorationType.ARROW_SOLID),
				new SectionDecoration(DecorationType.ARROW_SOLID));
		connector3.style = ConnectorStyle.DOTTED;
		connector3.showOnDiagram(diagram);

		FocusPanel diamond = new FocusPanel();
		Image img = AbstractImagePrototype.create(
				ConnectorsBundle.INSTANCE.diamondImg()).createImage();
		img.getElement().getStyle().setDisplay(Display.BLOCK);
		diamond.setWidget(img);
		diagramBoundaryPanel.add(diamond, 700, 400);

		// Add some elements that can be connected
		final Label label = new Label("LABEL");
		final Label label2 = new Label("LABEL_2");
		final Image image = new Image(
				"http://code.google.com/images/ph-logo.png");

		final Label label3 = new Label(
				"LABEL_3 Test Longer Label with rectangle connection points");

		image.setPixelSize(153, 55);

		diagramBoundaryPanel.add(label, 50, 250);
		diagramBoundaryPanel.add(label2, 450, 200);
		diagramBoundaryPanel.add(label3, 50, 200);

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				Shape shapeForLabel = new Shape(label, CPShapeType.DIAMOND);
				shapeForLabel.showOnDiagram(diagram);
				shapeForLabel.setTitle("shapeForLabel");
				shapeForLabel.enableConnectionCreate(true);

				Shape shapeForLabel2 = new Shape(label2, CPShapeType.OVAL);
				shapeForLabel2.showOnDiagram(diagram);
				shapeForLabel2.setTitle("shapeForLabel2");
				shapeForLabel2.enableConnectionCreate(true);

				Shape shapeForLabel3 = new Shape(label3, CPShapeType.RECTANGLE);
				shapeForLabel3.showOnDiagram(diagram);
				shapeForLabel3.setTitle("shapeForLabel");
				shapeForLabel3.enableConnectionCreate(true);
			}
		});

		/*
		 * 
		 * 
		 * 
		 * Shape shapeForDiamond = new Shape(diamond, CPShapeType.DIAMOND);
		 * shapeForDiamond.showOnDiagram(diagram);
		 * shapeForDiamond.enableConnectionCreate(true);
		 * shapeForDiamond.setTitle("shapeForDiamond");
		 * 
		 * Shape shapeForTask = new Shape(task, CPShapeType.RECTANGLE);
		 * shapeForTask.connectorsStyle = ConnectorStyle.DASHED;
		 * shapeForTask.showOnDiagram(diagram);
		 * shapeForTask.enableConnectionCreate(true);
		 * shapeForTask.setTitle("Shape for task");
		 */

	}

	public void createMainMenu(MenuBar mainMenu) {

		MenuBar fileMenu = new MenuBar(true);

		final MenuItem projectNameMenuItem = new MenuItem(projectName,
				(Command) null);

		Command editProjectName = new Command() {
			public void execute() {
				if (isProjectLoaded) {
					
					String newTitle = Window.prompt("New name of the project:",
							projectNameMenuItem.getText());
					if(newTitle==null){
						projectNameMenuItem.setText(projectName);
						
					}else{
						projectNameMenuItem.setText(newTitle);
						projectName=newTitle;
					}
					
				}
			}
		};
		projectNameMenuItem.setScheduledCommand(editProjectName);
		projectNameMenuItem
				.setWidth(Integer.toString(leftMenuSize - 25) + "px");

		final MenuItem saveProjectMenuItem = new MenuItem(
				"Save current project", cmd);

		Command createModel = new Command() {
			public void execute() {				
				AbsolutePanel boundaryPanel = new AbsolutePanel();
				boundaryPanel.setSize("800px", "450px");
				String newModelName="New Model";
				//System.out.println("New model letrehozva");
				
				models.put(newModelName, new Diagram(boundaryPanel));
				testModels.put(newModelName, boundaryPanel);
				activeModelName = newModelName;
				refreshModelsOrder(stackPanel);
				stackPanel.forceLayout();
				
			}
		};		
		Command saveModel = new Command(){
			public void execute(){	
				Diagram currentDiagram = new Diagram(diagramBoundaryPanel);
				for(Connector currentConnector : diagram.connectors)
				{
					currentDiagram.connectors.add(currentConnector.cloneConnector(currentDiagram));
				}
				for(Shape currentShape : diagram.shapes)
				{
					currentDiagram.shapes.add(currentShape.cloneShape());
				}
				models.put(activeModelName, currentDiagram);	
				testModels.put(activeModelName, diagramBoundaryPanel);
				GWT.log(activeModelName+ " saved: "+ currentDiagram.saveXML());
			}
		};
		
		MenuBar editMenu = new MenuBar(true);
		final MenuItem createModelMenuItem = new MenuItem("Create new Model",
				createModel);
		createModelMenuItem.setEnabled(isProjectLoaded);
		editMenu.addItem(createModelMenuItem);
		
		final MenuItem saveModelMenuItem = new MenuItem("Save current model",saveModel);
		saveModelMenuItem.setEnabled(isProjectLoaded);
		editMenu.addItem(saveModelMenuItem);
		
		saveProjectMenuItem.setEnabled(isProjectLoaded);
		Command createProject = new Command() {
			public void execute() {
				
				
				if(isProjectLoaded){
					//Ha van betoltott projekt, akkor figyelmeztessen
					isProjectLoaded = true;
					projectName = "New Project";
					projectNameMenuItem.setText(projectName);
					saveProjectMenuItem.setEnabled(true);
					saveModelMenuItem.setEnabled(true);
					makePopupDialogWindow("Previous Project replaced with a new project");
					//Window.alert("Previous Project replaced with a new project");
					
				}else{
					//Ha nincs betoltott projekt akkor hozzon letre egy ujat
					isProjectLoaded = true;
					projectName = "New Project";
					projectNameMenuItem.setText(projectName);
					saveProjectMenuItem.setEnabled(true);
					saveModelMenuItem.setEnabled(true);
					makePopupDialogWindow("Click to New Project to give it a name.");
//					Window.alert("Click to New Project to give it a name.");
				}
				createModelMenuItem.setEnabled(isProjectLoaded);
				models.clear();
				testModels.clear();
				refreshModelsOrder(stackPanel);
			}
		};
		MenuItem createProjectMenuItem = new MenuItem("Create new project",
				createProject);
		MenuItem loadProjectMenuItem = new MenuItem("Load existing project",
				cmd);

		fileMenu.addItem(createProjectMenuItem);
		fileMenu.addItem(loadProjectMenuItem);
		fileMenu.addItem(saveProjectMenuItem);		
		

		MenuBar helpMenu = new MenuBar(true);

		mainMenu.addItem(projectNameMenuItem);
		mainMenu.addItem("File", fileMenu);
		mainMenu.addItem("Edit", editMenu);
		mainMenu.addItem("Help", helpMenu);
	}
	
	
	//Ez a Window.alert() hivashoz hasonlo. Csak szerintem szebben nez ki
	//es nincs pittyeges a felugrasnal.
	public void makePopupDialogWindow(String text){
		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Warning");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new Label(text));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		//Ez jeleniti meg
		dialogBox.center();
	}

	public void createLeftMenu(StackLayoutPanel stackPanel) {
		VerticalPanel w1_1 = new VerticalPanel();
		Button w1_2 = new Button("Models");
		w1_2.setWidth(Integer.toString(leftMenuSize) + "px");
		Set<String> names = models.keySet();
		Iterator<String> nameIterator = names.iterator();
		while (nameIterator.hasNext()) {
			Button lb12 = new Button(nameIterator.next());
			lb12.setWidth(Integer.toString(leftMenuSize - 10) + "px");
			w1_1.add(lb12);
		}
		stackPanel.add(w1_1, w1_2, 2);

		VerticalPanel w2_1 = new VerticalPanel();
		diagramElementsButton = new Button("Diagram elements");
		diagramElementsButton.setWidth(Integer.toString(leftMenuSize) + "px");
		diagramElementsButton.setEnabled(false); //Alapbol ne lehessen rakattintani a diagram elementsre
		
		Grid g = new Grid(2, 3);
		
		ClickHandler addInitial = new ClickHandler() {
		      public void onClick(ClickEvent event) {		    	  
		    	FocusPanel initial = new FocusPanel();
		  		Image img = AbstractImagePrototype.create(
		  				ConnectorsBundle.INSTANCE.initial()).createImage();
		  		img.getElement().getStyle().setDisplay(Display.BLOCK);
		  		img.setTitle("Initial");
		  		initial.setWidget(img);
		  		diagramBoundaryPanel.add(initial, 10, 10);
		  		
		  		Shape shapeForInitial = new Shape(initial, CPShapeType.DIAMOND);
		  		shapeForInitial.showOnDiagram(diagram);
		  		shapeForInitial.enableConnectionCreate(true);
		  		shapeForInitial.setTitle("Initial");
		      }
		};
		
		g.setWidth(Integer.toString(leftMenuSize) + "px");
		FocusPanel initial = new FocusPanel();
		Image img = AbstractImagePrototype.create(
				ConnectorsBundle.INSTANCE.initial()).createImage();
		img.getElement().getStyle().setDisplay(Display.BLOCK);
		img.setTitle("Initial");
		initial.setWidget(img);
		initial.addClickHandler(addInitial);
		
		ClickHandler addDecision = new ClickHandler() {
		      public void onClick(ClickEvent event) {		    	  
		    	FocusPanel decision = new FocusPanel();
		  		Image img = AbstractImagePrototype.create(
		  				ConnectorsBundle.INSTANCE.decision()).createImage();
		  		img.getElement().getStyle().setDisplay(Display.BLOCK);
		  		img.setTitle("Decision");
		  		decision.setWidget(img);
		  		diagramBoundaryPanel.add(decision, 10, 10);
		  		
		  		Shape shapeForDecision = new Shape(decision, CPShapeType.DIAMOND);
		  		shapeForDecision.showOnDiagram(diagram);
		  		shapeForDecision.enableConnectionCreate(true);
				shapeForDecision.setTitle("Decision");
		      }
		};
		
		FocusPanel decision = new FocusPanel();
		img = AbstractImagePrototype.create(
				ConnectorsBundle.INSTANCE.decision()).createImage();
		img.getElement().getStyle().setDisplay(Display.BLOCK);
		decision.setWidget(img);
		decision.addClickHandler(addDecision);
		
		ClickHandler addFinal = new ClickHandler() {
		      public void onClick(ClickEvent event) {		    	  
		    	FocusPanel finalImage = new FocusPanel();
		  		Image img = AbstractImagePrototype.create(
		  				ConnectorsBundle.INSTANCE.finalImage()).createImage();
		  		img.getElement().getStyle().setDisplay(Display.BLOCK);
		  		img.setTitle("Final");
		  		finalImage.setWidget(img);
		  		diagramBoundaryPanel.add(finalImage, 10, 10);
		  		
		  		Shape shapeForFinal = new Shape(finalImage, CPShapeType.DIAMOND);
		  		shapeForFinal.showOnDiagram(diagram);
		  		shapeForFinal.enableConnectionCreate(true);
				shapeForFinal.setTitle("Final");
		      }
		};
		
		FocusPanel finalImage = new FocusPanel();
		img = AbstractImagePrototype.create(
				ConnectorsBundle.INSTANCE.finalImage()).createImage();
		img.getElement().getStyle().setDisplay(Display.BLOCK);
		finalImage.setWidget(img);		
		finalImage.addClickHandler(addFinal);
		
		ClickHandler addMerge = new ClickHandler() {
		      public void onClick(ClickEvent event) {		    	  
		    	FocusPanel merge = new FocusPanel();
		  		Image img = AbstractImagePrototype.create(
		  				ConnectorsBundle.INSTANCE.merge()).createImage();
		  		img.getElement().getStyle().setDisplay(Display.BLOCK);
		  		img.setTitle("Merge");
		  		merge.setWidget(img);
		  		diagramBoundaryPanel.add(merge, 10, 10);
		  		
		  		Shape shapeForMerge = new Shape(merge, CPShapeType.DIAMOND);
		  		shapeForMerge.showOnDiagram(diagram);
		  		shapeForMerge.enableConnectionCreate(true);
		  		shapeForMerge.setTitle("Merge");
		      }
		};
		
		FocusPanel merge = new FocusPanel();
		img = AbstractImagePrototype.create(
				ConnectorsBundle.INSTANCE.merge()).createImage();
		img.getElement().getStyle().setDisplay(Display.BLOCK);
		merge.setWidget(img);	
		merge.addClickHandler(addMerge);
		
		/*final DoubleClickHandler renameState = new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				Label label = (Label) event.getSource();
				String newTitle = Window.prompt("New name of the state:",
						label.getText());
				
				if(newTitle != null)
				{
					label.setText(newTitle);
					
				}
			}
		};*/	
		
		ClickHandler addState = new ClickHandler() {
		      public void onClick(ClickEvent event) {		    	  
		    	Label StateLabel = new Label();

		    	String newTitle = Window.prompt("Name of the state:","New State");
		    	if(newTitle != null)
				{
		    		StateLabel.setText(newTitle);
		    		diagramBoundaryPanel.add(StateLabel, 10, 10);
			  		
			  		Shape shapeForState = new Shape(StateLabel, CPShapeType.DIAMOND);
			  		shapeForState.showOnDiagram(diagram);
			  		shapeForState.enableConnectionCreate(true);
			  		shapeForState.setTitle("State");
				}
		  		
		      }
		};
		
		Label state = new Label();
		state.setText("State");
		state.addClickHandler(addState);
		
		g.setWidget(0, 0, initial);
		g.setWidget(0, 1, finalImage);
		g.setWidget(0, 2, decision);
		g.setWidget(1, 0, merge);
		g.setWidget(1, 1, state);
		
		w2_1.add(g);
		stackPanel.add(w2_1, diagramElementsButton, 2);
	}

	public void refreshModelsOrder(StackLayoutPanel stackPanel) {
		VerticalPanel modelsPanel = (VerticalPanel) stackPanel.getWidget(0);

		int modelNumber = modelsPanel.getWidgetCount();
		for (int i = 0; i < modelNumber; i++) {
			modelsPanel.remove(0);
		}
		Set<String> names = models.keySet();

		DoubleClickHandler rename = new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				Button b = (Button) event.getSource();
				renameModule(b);
			}
		};	
		
		ClickHandler loadDiagram = new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  String moduleName = ((Button)event.getSource()).getText();	
		    	  activeModelName = moduleName;
		    	  Diagram newdiagram = models.get(moduleName);
		    	  rightVerticalPanel.remove(diagramBoundaryPanel);
		    	  diagramBoundaryPanel = testModels.get(moduleName);
		    	  rightVerticalPanel.add(diagramBoundaryPanel);	
		    	  diagram = newdiagram;
		    	  GWT.log("Diagram loaded: " + activeModelName + " ;" +newdiagram.saveXML());
		    	  rightVerticalPanel.setVisible(true);
		    	  
		    	  //System.out.println("Eng click");
		    	  //Engedelyezzuk a Diagram elements menut
		    	  diagramElementsButton.setEnabled(true);
		        }
		      };
		
		Iterator<String> nameIterator = names.iterator();
		while (nameIterator.hasNext()) {
			Button lb12 = new Button(nameIterator.next());
			lb12.setWidth(Integer.toString(leftMenuSize - 10) + "px");
			lb12.addDoubleClickHandler(rename);
			lb12.addClickHandler(loadDiagram);
			modelsPanel.add(lb12);

		}
	}

	private void renameModule(Button modelButton) {
		String newTitle = Window.prompt("New name of the model:", modelButton.getText());
				
		if(newTitle==null){
			
		}else{
			//Nezze meg, hogy van-e mar ilyen nev
			VerticalPanel modelsPanel = (VerticalPanel) stackPanel.getWidget(0);
			int modelNumber = modelsPanel.getWidgetCount();
			
			for (int i = 0; i < modelNumber; i++) {
				//modelsPanel.getT(0);
//				modelsPanel.getWidget(i).asWidget().
			}
			Set<String> names = models.keySet();
			//FOLYTATNI KELL
			
			//Ha megadtak egy uj nevet, modositson ra
			Diagram currentDiagram = models.remove(modelButton.getText());
			AbsolutePanel currentPanel = testModels.remove(modelButton.getText());
			models.put(newTitle, currentDiagram);
			testModels.put(newTitle, currentPanel);
			modelButton.setText(newTitle);
			activeModelName = newTitle;
			refreshModelsOrder(this.stackPanel);
		}
		
		
		
	}

	/**
	 * Add a {@link TreeItem} to a root item.
	 * 
	 * @param root
	 *            the root {@link TreeItem}
	 * @param image
	 *            the icon for the new child item
	 * @param label
	 *            the label for the child icon
	 */
	private void addItem(TreeItem root, ImageResource image, String label) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.append(SafeHtmlUtils.fromTrustedString(AbstractImagePrototype
				.create(image).getHTML()));
		sb.appendEscaped(" ").appendEscaped(label);
		root.addItem(sb.toSafeHtml());
	}

	/**
	 * Create the list of Contacts.
	 * 
	 * @param images
	 *            the {@link Images} used in the Contacts
	 * @return the list of contacts
	 */

}
