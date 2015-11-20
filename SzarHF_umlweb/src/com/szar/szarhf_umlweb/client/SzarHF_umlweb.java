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
import com.szar.szarhf_umlweb.shared.Project;
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
	 * Global Values
	 */
	int leftMenuWidt = 200;
	int maxHeight = 455;
	String projectName = "No loaded project yet";
	boolean isProjectLoaded;
	TreeMap<String, Diagram> modelsTreeMap_String_Diagram;
	TreeMap<String, AbsolutePanel> testModels_String_AbsolutePanel;
	AbsolutePanel diagramAbsolutePanel;
	VerticalPanel rightVerticalPanel;
	Diagram diagram;
	MenuBar mainMenu;
	StackLayoutPanel stackPanel;
	String activeModelName;
	Button diagramElementsButton;
	Project currentProject=null;

	/**
	 * This is the entry point method.
	 */

	public void onModuleLoad() {

		modelsTreeMap_String_Diagram = new TreeMap<String, Diagram>();
		testModels_String_AbsolutePanel = new TreeMap<String, AbsolutePanel>();
		isProjectLoaded = false;
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setTitle("VPanel");

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

		diagramAbsolutePanel = new AbsolutePanel();
		diagramAbsolutePanel.setSize("800px", "450px");
		rightVerticalPanel.add(diagramAbsolutePanel);
		//test(rightVerticalPanel);		

		diagram = new Diagram(diagramAbsolutePanel);

		stackPanel = new StackLayoutPanel(Unit.EM);
		stackPanel.setPixelSize(leftMenuWidt, maxHeight);
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

		diagram = new Diagram(diagramAbsolutePanel);

		diagramAbsolutePanel.add(new Label("Connectors example for GWT 2.4"), 10, 2);
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
		diagramAbsolutePanel.add(diamond, 700, 400);

		// Add some elements that can be connected
		final Label label = new Label("LABEL");
		final Label label2 = new Label("LABEL_2");
		final Image image = new Image(
				"http://code.google.com/images/ph-logo.png");

		final Label label3 = new Label(
				"LABEL_3 Test Longer Label with rectangle connection points");

		image.setPixelSize(153, 55);

		diagramAbsolutePanel.add(label, 50, 250);
		diagramAbsolutePanel.add(label2, 450, 200);
		diagramAbsolutePanel.add(label3, 50, 200);

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
				.setWidth(Integer.toString(leftMenuWidt - 25) + "px");

		final MenuItem saveProjectMenuItem = new MenuItem(
				"Save current project", cmd);

		Command createModel = new Command() {
			public void execute() {				
				AbsolutePanel boundaryPanel = new AbsolutePanel();
				boundaryPanel.setSize("800px", "450px");
				String newModelName="New Model";
				//System.out.println("New model letrehozva");
				
				modelsTreeMap_String_Diagram.put(newModelName, new Diagram(boundaryPanel));
				testModels_String_AbsolutePanel.put(newModelName, boundaryPanel);
				activeModelName = newModelName;
				refreshModelsOrder(stackPanel);
				stackPanel.forceLayout();
				
			}
		};		
		Command saveModel = new Command(){
			public void execute(){	
				Diagram currentDiagram = new Diagram(diagramAbsolutePanel);
				for(Connector currentConnector : diagram.connectors)
				{
					currentDiagram.connectors.add(currentConnector.cloneConnector(currentDiagram));
				}
				for(Shape currentShape : diagram.shapes)
				{
					currentDiagram.shapes.add(currentShape.cloneShape());
				}
				modelsTreeMap_String_Diagram.put(activeModelName, currentDiagram);	
				testModels_String_AbsolutePanel.put(activeModelName, diagramAbsolutePanel);
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
				
				Project project=new Project();
				
				if(isProjectLoaded){
					//Ha van betoltott projekt, akkor figyelmeztessen
					isProjectLoaded = true;
					projectName = "New Project";
					projectNameMenuItem.setText(projectName);
					saveProjectMenuItem.setEnabled(true);
					saveModelMenuItem.setEnabled(true);
					
					PopupDialogWindow.makeNamingDialogWindow("Previous Project replaced with a new project", project);
//					System.out.println(makeNamingDialogWindow);
					//Window.alert("Previous Project replaced with a new project");
					
				}else{
					//Ha nincs betoltott projekt akkor hozzon letre egy ujat
					isProjectLoaded = true;
					projectName = "New Project";
					projectNameMenuItem.setText(projectName);
					saveProjectMenuItem.setEnabled(true);
					saveModelMenuItem.setEnabled(true);
					PopupDialogWindow.makeNamingDialogWindow("Click to New Project to give it a name.", project);
//					System.out.println(makeNamingDialogWindow);
					//					Window.alert("Click to New Project to give it a name.");
				}
				createModelMenuItem.setEnabled(isProjectLoaded);
				modelsTreeMap_String_Diagram.clear();
				testModels_String_AbsolutePanel.clear();
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
	
	
	

	public void createLeftMenu(StackLayoutPanel stackPanel) {
		VerticalPanel vPanel_w1_1 = new VerticalPanel();
		Button buttonW1_2 = new Button("Models");
		buttonW1_2.setWidth(Integer.toString(leftMenuWidt) + "px");
		Set<String> names = modelsTreeMap_String_Diagram.keySet();
		Iterator<String> nameIterator = names.iterator();
		while (nameIterator.hasNext()) {
			Button lb12 = new Button(nameIterator.next());
			lb12.setWidth(Integer.toString(leftMenuWidt - 10) + "px");
			vPanel_w1_1.add(lb12);
		}
		stackPanel.add(vPanel_w1_1, buttonW1_2, 2);

		VerticalPanel verticalPanel = new VerticalPanel();
		diagramElementsButton = new Button("Diagram elements");
		diagramElementsButton.setWidth(Integer.toString(leftMenuWidt) + "px");
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
		  		diagramAbsolutePanel.add(initial, 10, 10);
		  		
		  		Shape shapeForInitial = new Shape(initial, CPShapeType.DIAMOND);
		  		shapeForInitial.showOnDiagram(diagram);
		  		shapeForInitial.enableConnectionCreate(true);
		  		shapeForInitial.setTitle("Initial");
		      }
		};
		
		g.setWidth(Integer.toString(leftMenuWidt) + "px");
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
		  		diagramAbsolutePanel.add(decision, 10, 10);
		  		
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
		  		diagramAbsolutePanel.add(finalImage, 10, 10);
		  		
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
		  		diagramAbsolutePanel.add(merge, 10, 10);
		  		
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
		    		diagramAbsolutePanel.add(StateLabel, 10, 10);
			  		
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
		
		verticalPanel.add(g);
		stackPanel.add(verticalPanel, diagramElementsButton, 2);
	}

	public void refreshModelsOrder(StackLayoutPanel stackPanel) {
		VerticalPanel modelsPanel = (VerticalPanel) stackPanel.getWidget(0);

		int modelNumber = modelsPanel.getWidgetCount();
		for (int i = 0; i < modelNumber; i++) {
			modelsPanel.remove(0);
		}
		Set<String> names = modelsTreeMap_String_Diagram.keySet();

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
		    	  Diagram newdiagram = modelsTreeMap_String_Diagram.get(moduleName);
		    	  rightVerticalPanel.remove(diagramAbsolutePanel);
		    	  diagramAbsolutePanel = testModels_String_AbsolutePanel.get(moduleName);
		    	  rightVerticalPanel.add(diagramAbsolutePanel);	
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
			lb12.setWidth(Integer.toString(leftMenuWidt - 10) + "px");
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
			Set<String> names = modelsTreeMap_String_Diagram.keySet();
			//FOLYTATNI KELL
			
			//Ha megadtak egy uj nevet, modositson ra
			Diagram currentDiagram = modelsTreeMap_String_Diagram.remove(modelButton.getText());
			AbsolutePanel currentPanel = testModels_String_AbsolutePanel.remove(modelButton.getText());
			modelsTreeMap_String_Diagram.put(newTitle, currentDiagram);
			testModels_String_AbsolutePanel.put(newTitle, currentPanel);
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
