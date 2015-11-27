package com.szar.szarhf_umlweb.client;

import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.rhino.head.tools.debugger.Main;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.szar.szarhf_umlweb.shared.Model;
import com.szar.szarhf_umlweb.shared.Project;

public class PopupDialogWindow {

	static SzarHF_umlweb mainProgram=null;
	public PopupDialogWindow(SzarHF_umlweb mainProgram){
		this.mainProgram=mainProgram;
	}
	
	// Ez a Window.alert() hivashoz hasonlo. Csak szerintem szebben nez ki
	// es nincs pittyeges a felugrasnal.
	public static void makePopupDialogWindow(String text) {

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Warning");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		// final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new Label(text));
		// dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		// Ez jeleniti meg
		dialogBox.center();
	}

	public static void loadXMLDialogWindow(String text,
			final TreeMap<String, Model> modelsTreeMap_String_Model,
			final Project project) {
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Warning");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Button loadButton = new Button("Load");
		loadButton.getElement().setId("loadButton");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new Label(text));
		final TextArea textArea = new TextArea();
		dialogVPanel.add(textArea);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		HorizontalPanel dialogHPanel = new HorizontalPanel();
		dialogHPanel.add(loadButton);
		dialogHPanel.add(closeButton);
		dialogVPanel.add(dialogHPanel);
		dialogBox.setWidget(dialogVPanel);

		loadButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String XML = textArea.getText();
				try {
					// parse the XML document into a DOM
					Document messageDom = XMLParser.parse(XML);

					Node projectNode = messageDom.getElementsByTagName(
							"projectname").item(0);
					NodeList models = ((Element) projectNode)
							.getElementsByTagName("model");
					modelsTreeMap_String_Model.clear();
					for (int i = 0; i < models.getLength(); i++) {
						Node currentModelXMLNode = models.item(i);
						Model currentModel = new Model();
						currentModel = currentModel
								.fromXML(currentModelXMLNode);
						modelsTreeMap_String_Model.put(
								currentModel.getModelName(), currentModel);
						project.setProjectName(projectNode.getNodeValue());
					}

				} catch (DOMException e) {
					Window.alert("Could not parse XML document.");
				}
			}
		});
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		// Ez jeleniti meg
		dialogBox.center();
	}

	public static void makeNamingDialogWindow(String text,
			String defaultPreferedName, final Project project,
			final MenuItem projectNameMenuItem) {

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
		final TextBox textBox = new TextBox();
		textBox.setText(defaultPreferedName);
		dialogVPanel.add(textBox);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// System.out.println(tb.getValue());
				project.setProjectName(textBox.getValue());
				projectNameMenuItem.setText((project.getProjectName()));
				dialogBox.hide();
			}
		});

		// Ez jeleniti meg
		dialogBox.center();

		// return tb.getValue();
	}

	public static void makeTextAreaForXMLSave(String xML) {

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Saved XML text");
		dialogBox.setAnimationEnabled(true);
		// dialogBox.setSize("300px", "400px");
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		closeButton.setSize("290px", "30px");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setSize("300px", "400px");
		dialogVPanel.addStyleName("dialogVPanel");
		// Label label = new Label("LabelText");
		// label.setSize("100%", "10px");
		// dialogVPanel.add(label);
		// final TextBox textBox = new TextBox();
		final TextArea textArea = new TextArea();
		textArea.setSize("290px", "360px");
		// textArea.setHeight("");
		textArea.setText(xML);
		dialogVPanel.add(textArea);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);

		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// System.out.println(tb.getValue());
				// project.setProjectName(textBox.getValue());
				// projectNameMenuItem.setText((project.getProjectName()));
				dialogBox.hide();
			}
		});

		// Ez jeleniti meg
		dialogBox.center();

	}

	public static void makeTextAreaForXMLLoad(String string) {

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Load XML text");
		dialogBox.setAnimationEnabled(true);
		// dialogBox.setSize("300px", "400px");
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		closeButton.setSize("290px", "30px");
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setSize("300px", "400px");
		dialogVPanel.addStyleName("dialogVPanel");
		// Label label = new Label("LabelText");
		// label.setSize("100%", "10px");
		// dialogVPanel.add(label);
		// final TextBox textBox = new TextBox();
		final TextArea textArea = new TextArea();
		textArea.setSize("290px", "360px");
		// textArea.setHeight("");
		textArea.setText("Copy XML Text here");
		dialogVPanel.add(textArea);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);

		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// System.out.println(tb.getValue());
				// project.setProjectName(textBox.getValue());
				// projectNameMenuItem.setText((project.getProjectName()));
				mainProgram.loadXML(textArea.getText());
//				xML2.append(textArea.getText());
				
				dialogBox.hide();
			}
		});

		// Ez jeleniti meg
		dialogBox.center();

	}

}
