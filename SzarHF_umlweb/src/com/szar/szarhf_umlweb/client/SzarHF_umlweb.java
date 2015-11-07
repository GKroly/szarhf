package com.szar.szarhf_umlweb.client;

import java.util.ArrayList;

import com.szar.gwt.connectors.client.CornerPoint;
import com.szar.gwt.connectors.client.Diagram;
import com.szar.gwt.connectors.client.elements.Connector;
import com.szar.gwt.connectors.client.elements.SectionDecoration;
import com.szar.gwt.connectors.client.elements.SectionDecoration.DecorationType;
import com.szar.gwt.connectors.client.elements.Shape;
import com.szar.gwt.connectors.client.elements.Shape.CPShapeType;
import com.szar.gwt.connectors.client.images.ConnectorsBundle;
import com.szar.gwt.connectors.client.util.ConnectorStyle;
import com.szar.szarhf_umlweb.shared.FieldVerifier;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Tree.Resources;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.VerticalScrollbar;
import com.google.gwt.user.client.ui.Widget;

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
	String projectName = "Unknown project";
	boolean isProjectLoaded;
	/**
	 * This is the entry point method.
	 */
	
	public void onModuleLoad() {

		isProjectLoaded = false;
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setTitle("VPanel");
//		mainVerticalPanel.setStylePrimaryName("my-MainPanel");

		MenuBar mainMenu = new MenuBar();
		mainMenu.setHeight("35px");
		
		createMainMenu(mainMenu);
		mainVerticalPanel.add(mainMenu);
		
		HorizontalPanel horPan = new HorizontalPanel();
		VerticalPanel leftVerticalPanel = new VerticalPanel();
		VerticalPanel rightVerticalPanel = new VerticalPanel();
		horPan.add(leftVerticalPanel);
		horPan.add(rightVerticalPanel);
		rightVerticalPanel.setBorderWidth(1);
		rightVerticalPanel.setPixelSize(900, maxHeight);
		rightVerticalPanel.setWidth("800px");
		mainVerticalPanel.add(horPan);
		
		test(rightVerticalPanel);
		
	    StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
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
	
	private void test(VerticalPanel viewPanel)
	{
		AbsolutePanel boundaryPanel = new AbsolutePanel();
        boundaryPanel.setSize("800px", "450px");
        viewPanel.add(boundaryPanel); 
        
        final Diagram diagram = new Diagram(boundaryPanel);

        boundaryPanel.add(new Label("Connectors example for GWT 2.4"), 10, 2);
        Connector connector1 = new Connector(50, 80, 150, 200, new SectionDecoration(DecorationType.ARROW_SOLID), new SectionDecoration(DecorationType.ARROW_SOLID));
        connector1.showOnDiagram(diagram);

        ArrayList<CornerPoint> cp = new ArrayList<CornerPoint>();
        cp.add(new CornerPoint(370, 200));
        cp.add(new CornerPoint(370, 120));
        cp.add(new CornerPoint(270, 120));
        SectionDecoration startDecoration = new SectionDecoration(DecorationType.ARROW_LINE);
        SectionDecoration endDecoration = new SectionDecoration(
                        new Image("http://code.google.com/images/ph-logo.png"), 
                        new Image("http://code.google.com/images/ph-logo.png"));
        Connector connector2 = new Connector(350, 200, 270, 80, cp, startDecoration, endDecoration);
        connector2.style = ConnectorStyle.DASHED;
        connector2.showOnDiagram(diagram);

        Connector connector3 = new Connector(450, 120, 500, 80, new SectionDecoration(DecorationType.ARROW_SOLID), new SectionDecoration(DecorationType.ARROW_SOLID));
        connector3.style = ConnectorStyle.DOTTED;
        connector3.showOnDiagram(diagram);

        FocusPanel diamond = new FocusPanel();
        Image img = AbstractImagePrototype.create(ConnectorsBundle.INSTANCE.diamondImg()).createImage();
        img.getElement().getStyle().setDisplay(Display.BLOCK);
        diamond.setWidget(img);
        boundaryPanel.add(diamond, 700, 400);

        // Add some elements that can be connected
        final Label label = new Label("LABEL");     
        final Label label2 = new Label("LABEL_2");
        final Image image = new Image("http://code.google.com/images/ph-logo.png");

        final Label label3 = new Label("LABEL_3 Test Longer Label with rectangle connection points");

        image.setPixelSize(153, 55);  
        
        BPMNTask task = new BPMNTask();
        boundaryPanel.add(task, 20, 20);
        
        boundaryPanel.add(label, 50, 250);
        boundaryPanel.add(label2, 450, 200);
        boundaryPanel.add(label3, 700, 500);

        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand()
        {
            public void execute()
            {
            	Shape shapeForLabel = new Shape(label, CPShapeType.DIAMOND);                
                shapeForLabel.showOnDiagram(diagram);
                shapeForLabel.setTitle("shapeForLabel");
                shapeForLabel.enableConnectionCreate(true);
                
                Shape shapeForLabel2 = new Shape(label2, CPShapeType.OVAL);
                shapeForLabel2.showOnDiagram(diagram);
                shapeForLabel2.setTitle("shapeForLabel2");
                
                Shape shapeForLabel3 = new Shape(label3, CPShapeType.RECTANGLE);
                shapeForLabel3.showOnDiagram(diagram);
                shapeForLabel3.setTitle("shapeForLabel");
                shapeForLabel3.enableConnectionCreate(true);
            }
        });
        

       /* 
        
        

        Shape shapeForDiamond = new Shape(diamond, CPShapeType.DIAMOND);
        shapeForDiamond.showOnDiagram(diagram);
        shapeForDiamond.enableConnectionCreate(true);
        shapeForDiamond.setTitle("shapeForDiamond");
        
        Shape shapeForTask = new Shape(task, CPShapeType.RECTANGLE);
        shapeForTask.connectorsStyle = ConnectorStyle.DASHED;
        shapeForTask.showOnDiagram(diagram);
        shapeForTask.enableConnectionCreate(true);
        shapeForTask.setTitle("Shape for task");   */     

	}
	
	
	public void createMainMenu(MenuBar mainMenu)
	{
		MenuBar fileMenu = new MenuBar(true);
		
		MenuItem projectNameMenuItem = new MenuItem(projectName,cmd2);
		projectNameMenuItem.setWidth(Integer.toString(leftMenuSize-25)+"px");
		
		MenuItem createProjectMenuItem = new MenuItem("Create new Project",cmd);
		MenuItem loadProjectMenuItem = new MenuItem("Load existing project",cmd);
		MenuItem saveProjectMenuItem = new MenuItem("Save current project",cmd);
		saveProjectMenuItem.setEnabled(isProjectLoaded);
		
		fileMenu.addItem(createProjectMenuItem);
		fileMenu.addItem(loadProjectMenuItem);
		fileMenu.addItem(saveProjectMenuItem);
		
		MenuBar helpMenu = new MenuBar(true);
		
		
		mainMenu.addItem(projectNameMenuItem);
		mainMenu.addItem("File",fileMenu);
		mainMenu.addItem("Help",helpMenu);
	}
	
	public void createLeftMenu(StackLayoutPanel stackPanel)
	{
		VerticalPanel w1_1=new VerticalPanel();
	    Button w1_2=new Button("Diagram Types"); 
	    w1_2.setWidth(Integer.toString(leftMenuSize)+"px");
	    Button lbl1=new Button("new \"Class Diagram\"");
	    Button lbl2=new Button("new \"Activity Diagram\"");
	    lbl1.setWidth(Integer.toString(leftMenuSize-10)+"px");
	    lbl2.setWidth(Integer.toString(leftMenuSize-10)+"px");
	    w1_1.add(lbl1);	    
	    w1_1.add(lbl2);
	    stackPanel.add(w1_1, w1_2, 2);
	    
	    VerticalPanel w2_1=new VerticalPanel();
	    Button w2_2=new Button("1Szia");    
	    TextBox lbl2_1=new TextBox();
	    lbl2_1.setWidth(Integer.toString(leftMenuSize-10)+"px");
	    TextBox lbl2_2=new TextBox();
	    lbl2_2.setWidth(Integer.toString(leftMenuSize-10)+"px");
	    w2_1.add(lbl2_1);
	    w2_1.add(lbl2_2);
	    stackPanel.add(w2_1, w2_2, 2);   
 	    
	    VerticalPanel w3_1=new VerticalPanel();
	    Button w3_2=new Button("1Szia");    
	    TextBox lbl3_1=new TextBox();
	    lbl3_1.setWidth(Integer.toString(leftMenuSize-15)+"px");
	    TextBox lbl3_2=new TextBox();
	    lbl3_2.setWidth(Integer.toString(leftMenuSize-15)+"px");
	    w3_1.add(lbl3_1);
	    w3_1.add(lbl3_2);
	    stackPanel.add(w3_1, w3_2, 2);
		
	}
	
	/**
	   * Add a {@link TreeItem} to a root item.
	   * 
	   * @param root the root {@link TreeItem}
	   * @param image the icon for the new child item
	   * @param label the label for the child icon
	   */
	  private void addItem(TreeItem root, ImageResource image, String label) {
	    SafeHtmlBuilder sb = new SafeHtmlBuilder();
	    sb.append(SafeHtmlUtils.fromTrustedString(AbstractImagePrototype.create(
	        image).getHTML()));
	    sb.appendEscaped(" ").appendEscaped(label);
	    root.addItem(sb.toSafeHtml());
	  }

	  /**
	   * Create the list of Contacts.
	   * 
	   * @param images the {@link Images} used in the Contacts
	   * @return the list of contacts
	   */
	  
}
