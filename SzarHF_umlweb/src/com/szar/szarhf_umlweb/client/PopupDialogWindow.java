package com.szar.szarhf_umlweb.client;

import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.szar.szarhf_umlweb.shared.Model;
import com.szar.szarhf_umlweb.shared.Project;

public class PopupDialogWindow {

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
	
	public static void loadXMLDialogWindow(String text, final TreeMap<String, Model> modelsTreeMap_String_Model, final Project project)
	{
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
				final TextArea textArea = new TextArea();
				dialogVPanel.add(textArea);
				dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
				dialogVPanel.add(closeButton);
				dialogBox.setWidget(dialogVPanel);

				// Add a handler to close the DialogBox
				closeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						String XML = textArea.getText();
						XML = XML.substring(XML.indexOf("<projectname>")+13);
						GWT.log(XML);
						project.setProjectName(XML.substring(0, XML.indexOf("</projectname>")));
						GWT.log(project.getProjectName());
						XML = XML.substring(14);
						GWT.log(XML);
						String[] models = XML.split("<model>");
						modelsTreeMap_String_Model.clear();
						for(String currentModelXmlString : models)
						{
							Model currentModel = new Model();
							currentModel = currentModel.fromXML(currentModelXmlString);
							modelsTreeMap_String_Model.put(currentModel.getModelName(), currentModel);
						}
					}
				});

				// Ez jeleniti meg
				dialogBox.center();
	}

	public static void makeNamingDialogWindow(String text, String defaultPreferedName, final Project project, final MenuItem projectNameMenuItem) {

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
//				System.out.println(tb.getValue());
				project.setProjectName(textBox.getValue());
				projectNameMenuItem.setText((project.getProjectName()));
				dialogBox.hide();
			}
		});

		// Ez jeleniti meg
		dialogBox.center();

//		return tb.getValue();
	}




}
