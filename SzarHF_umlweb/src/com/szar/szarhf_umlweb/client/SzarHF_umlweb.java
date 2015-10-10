package com.szar.szarhf_umlweb.client;

import com.szar.szarhf_umlweb.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Tree.Resources;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
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
	 * This is the entry point method.
	 */
	
	public void onModuleLoad() {

		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setTitle("VPanel");
//		mainVerticalPanel.setPixelSize(1200, 500);
//		mainVerticalPanel.setBorderWidth(3);
		mainVerticalPanel.setStylePrimaryName("my-MainPanel");
		// mainVerticalPanel.setStyleName("center");

		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected...");
			}
		};

		MenuBar mainMenu = new MenuBar();
		mainMenu.setHeight("35px");
		MenuItem menuItem1 = new MenuItem("1", cmd);
		MenuItem menuItem2 = new MenuItem("2", cmd);
		mainMenu.addItem(menuItem1);
		mainMenu.addItem(menuItem2);

		mainVerticalPanel.add(mainMenu);

		HorizontalPanel horPan = new HorizontalPanel();
//		horPan.setHeight("465px");
		VerticalPanel leftVerticalPanel = new VerticalPanel();
//		leftVerticalPanel.setBorderWidth(1);
//		leftVerticalPanel.setPixelSize(300, 465);

		VerticalPanel rightVerticalPanel = new VerticalPanel();
		horPan.add(leftVerticalPanel);
		horPan.add(rightVerticalPanel);
		rightVerticalPanel.setBorderWidth(1);
		rightVerticalPanel.setPixelSize(900, 600);
		rightVerticalPanel.setWidth("800px");
		mainVerticalPanel.add(horPan);

		
		
		
		

	    // Create a new stack layout panel.
	    StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
	    stackPanel.setPixelSize(200, 600);
//	    stackPanel.setWidth("200px");

	    // Add the Mail folders.
//	    Widget mailHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelMailHeader(), images.mailgroup());
	    stackPanel.add(new Button("1Szia"), new Button("1111"), 2);

	    // Add a list of filters.
//	    Widget filtersHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelFiltersHeader(), images.filtersgroup());
	    stackPanel.add(new Button("2Szia"), new Button("2222"), 2);
	    stackPanel.add(new Button("3Szia"), new Button("3333"), 2);

//	    // Add a list of contacts.
//	    Widget contactsHeader = createHeaderWidget(
//	        constants.cwStackLayoutPanelContactsHeader(), images.contactsgroup());
//	    stackPanel.add(createContactsItem(images), contactsHeader, 4);

	    // Return the stack panel.
//	    stackPanel.ensureDebugId("cwStackLayoutPanel");
		
		leftVerticalPanel.add(stackPanel);
		
		RootPanel.get("MainWindow").add(mainVerticalPanel);

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
