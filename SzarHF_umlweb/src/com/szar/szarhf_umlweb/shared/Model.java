package com.szar.szarhf_umlweb.shared;

import java.io.Serializable;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.szar.gwt.connectors.client.Diagram;
import com.szar.gwt.connectors.client.elements.Connector;
import com.szar.gwt.connectors.client.elements.Shape;
import com.szar.szarhf_umlweb.shared.DiagramWidgetInterface.WidgetType;

public class Model implements Serializable {

	String modelName = null;
	Diagram diagram = null;
	AbsolutePanel panel = null;
	
	public Model() {

	}

	/*public Model(String modelName) {
		this.modelName = modelName;
	}*/
	
	public Model(String modelName, Diagram diagram, AbsolutePanel panel) {
		super();
		this.modelName = modelName;
		this.diagram = diagram;
		this.panel = panel;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public AbsolutePanel getPanel() {
		return panel;
	}

	public void setPanel(AbsolutePanel panel) {
		this.panel = panel;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String toXML()
	  {
		  String outXML = "<shapes>";
		  for(Shape shape : this.diagram.shapes)
		  {
			  try{
			  DiagramWidgetInterface interf = (DiagramWidgetInterface) shape.connectedWidget;
			  if(interf.getWidgetType()== WidgetType.IMAGE)
			  {
				  outXML += "<Image>";
				  DiagramImage img = (DiagramImage) shape.connectedWidget;
				  outXML += "<left>"+img.getLeft()+"</left>";
				  outXML += "<top>"+img.getTop()+"</top>";
				  outXML += "<imageType>"+img.getType().name()+"</imageType>";
				  outXML += "</Image>";

			  }
			  else
			  {
				  DiagramState state = (DiagramState)shape.connectedWidget;
				  outXML += "<State>";
				  outXML += "<left>"+state.getLeft()+"</left>";
				  outXML += "<top>"+state.getTop()+"</top>";
				  outXML += "<Name>"+state.getName()+"</Name>";
				  outXML += "</State>";
			  }
			  }
			  catch(Exception e){}
		  }
		  outXML += "</shapes>";
		  outXML += "<connectors>";
		  for(Connector c : this.diagram.connectors)
		  {
			  outXML += "<connector>";
			  outXML += "<startPoint>";
			  outXML += "<left>" + c.startEndPoint.getLeft() + "</left>";
			  outXML += "<top>"+c.startEndPoint.getTop()+"</top>";
			  outXML += "</startPoint>";
			  outXML += "<endPoint>";
			  outXML += "<left>" + c.endEndPoint.getLeft() + "</left>";
			  outXML += "<top>"+c.endEndPoint.getTop()+"</top>";
			  outXML += "</endPoint>";
			  if(c.startPointDecoration != null)
			  {
			  outXML += "<startPointDecoration>" + c.startPointDecoration.type + "</startPointDecoration>";
			  }
			  if(c.endPointDecoration != null)
			  {
			  outXML += "<endPointDecoration>" + c.endPointDecoration.type + "</endPointDecoration>";
			  }
			  outXML += "</connector>";
		  }
		  outXML += "</connectors>";
		  return outXML;
	  }
	public Model fromXML(String XML)
	{
		this.panel = new AbsolutePanel();
		panel.setSize("800px", "450px");
		this.diagram = new Diagram(panel);
		if(XML.substring(0, 8).equals("<shapes>"))
		{
			String shapes = XML.substring(8, XML.indexOf("</shapes>"));
		}
		return null;
		
	}
}