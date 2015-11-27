package com.szar.szarhf_umlweb.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import com.szar.gwt.connectors.client.ConnectionPoint;
import com.szar.gwt.connectors.client.Diagram;
import com.szar.gwt.connectors.client.elements.Connector;
import com.szar.gwt.connectors.client.elements.EndPoint;
import com.szar.gwt.connectors.client.elements.SectionDecoration;
import com.szar.gwt.connectors.client.elements.SectionDecoration.DecorationType;
import com.szar.gwt.connectors.client.elements.Shape;
import com.szar.gwt.connectors.client.elements.Shape.CPShapeType;
import com.szar.szarhf_umlweb.shared.DiagramImage.ImageType;
import com.szar.szarhf_umlweb.shared.DiagramWidgetInterface.WidgetType;

public class Model implements Serializable {

	String modelName = null;
	Diagram diagram = null;
	AbsolutePanel panel = null;
	public List<DiagramWidgetInterface> dwiList = new ArrayList<DiagramWidgetInterface>();
	public List<Connector> conList = new ArrayList<Connector>();

	public Model() {

	}

	/*
	 * public Model(String modelName) { this.modelName = modelName; }
	 */

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

	public String toXML() {
		String outXML = "<shapes>";
		for (Shape shape : this.diagram.shapes) {
			try {
				DiagramWidgetInterface interf = (DiagramWidgetInterface) shape.connectedWidget;
				if (interf.getWidgetType() == WidgetType.IMAGE) {
					outXML += "<Image>";
					DiagramImage img = (DiagramImage) shape.connectedWidget;
					outXML += "<left>" + img.getLeft() + "</left>";
					outXML += "<top>" + img.getTop() + "</top>";
					outXML += "<imageType>" + img.getType().name()
							+ "</imageType>";
					outXML += "</Image>";

				} else if (interf.getWidgetType() == WidgetType.LABEL) {
					DiagramState state = (DiagramState) shape.connectedWidget;
					outXML += "<State>";
					outXML += "<left>" + state.getLeft() + "</left>";
					outXML += "<top>" + state.getTop() + "</top>";
					outXML += "<Name>" + state.getName() + "</Name>";
					outXML += "</State>";
				} else {
					DiagramAction action = (DiagramAction) shape.connectedWidget;
					outXML += "<Action>";
					outXML += "<left>" + action.getLeft() + "</left>";
					outXML += "<top>" + action.getTop() + "</top>";
					outXML += "<Name>" + action.getName() + "</Name>";
					outXML += "</Action>";
				}
			} catch (Exception e) {
			}
		}
		outXML += "</shapes>";
		outXML += "<connectors>";
		for (Connector c : this.diagram.connectors) {
			outXML += "<connector>";
			outXML += "<startPoint>";
			outXML += "<left>" + c.startEndPoint.getLeft() + "</left>";
			outXML += "<top>" + c.startEndPoint.getTop() + "</top>";
			outXML += "</startPoint>";
			outXML += "<endPoint>";
			outXML += "<left>" + c.endEndPoint.getLeft() + "</left>";
			outXML += "<top>" + c.endEndPoint.getTop() + "</top>";
			outXML += "</endPoint>";
			if (c.startPointDecoration != null) {
				outXML += "<startPointDecoration>"
						+ c.startPointDecoration.type
						+ "</startPointDecoration>";
			}
			if (c.endPointDecoration != null) {
				outXML += "<endPointDecoration>" + c.endPointDecoration.type
						+ "</endPointDecoration>";
			}
			outXML += "</connector>";
		}
		outXML += "</connectors>";
		return outXML;
	}

	public Model fromXML(Node ModelXMLNode) {
		this.panel = new AbsolutePanel();
		panel.setSize("800px", "450px");
		this.diagram = new Diagram(panel);
		try {
			Node modelNameNode = ((Element) ModelXMLNode).getElementsByTagName(
					"modelname").item(0);
			Node shapesNode = ((Element) ModelXMLNode).getElementsByTagName(
					"shapes").item(0);
			NodeList images = ((Element) shapesNode)
					.getElementsByTagName("Image");
			NodeList states = ((Element) shapesNode)
					.getElementsByTagName("State");
			NodeList actions = ((Element) shapesNode)
					.getElementsByTagName("Action");
			this.modelName = modelNameNode.getFirstChild().getNodeValue();
			for (int i = 0; i < images.getLength(); i++) {
				Node imagesNode = images.item(i);
				Node leftNode = ((Element) imagesNode).getElementsByTagName(
						"left").item(0);
				Node topNode = ((Element) imagesNode).getElementsByTagName(
						"top").item(0);
				int left = Integer.parseInt(leftNode.getFirstChild()
						.getNodeValue());
				int top = Integer.parseInt(topNode.getFirstChild()
						.getNodeValue());
				Node imageTypeNode = ((Element) imagesNode)
						.getElementsByTagName("imageType").item(0);
				ImageType type = null;
				switch (imageTypeNode.getFirstChild().getNodeValue()) {
				case ("decision"):
					type = ImageType.decision;
					break;
				case ("Final"):
					type = ImageType.Final;
					break;
				case ("Initial"):
					type = ImageType.Initial;
					break;
				case ("merge"):
					type = ImageType.merge;
					break;
				}
				final DiagramImage currentImage = new DiagramImage(type);
				currentImage.getElement().getStyle().setDisplay(Display.BLOCK);
				currentImage.setTop(top);
				currentImage.setLeft(left);
				this.panel.add(currentImage, left, top);
				dwiList.add(currentImage);
			}
			for (int i = 0; i < states.getLength(); i++) {
				Node statesNode = states.item(i);
				Node leftNode = ((Element) statesNode).getElementsByTagName(
						"left").item(0);
				Node topNode = ((Element) statesNode).getElementsByTagName(
						"top").item(0);
				int left = Integer.parseInt(leftNode.getFirstChild()
						.getNodeValue());
				int top = Integer.parseInt(topNode.getFirstChild()
						.getNodeValue());
				Node nameNode = ((Element) statesNode).getElementsByTagName(
						"Name").item(0);
				String name = nameNode.getFirstChild().getNodeValue();
				final DiagramState currentState = new DiagramState(name);
				this.panel.add(currentState, left, top);
				currentState.setTop(top);
				currentState.setLeft(left);
				dwiList.add(currentState);
			}
			for (int i = 0; i < actions.getLength(); i++) {
				Node actionNode = actions.item(i);
				Node leftNode = ((Element) actionNode).getElementsByTagName(
						"left").item(0);
				Node topNode = ((Element) actionNode).getElementsByTagName(
						"top").item(0);
				int left = Integer.parseInt(leftNode.getFirstChild()
						.getNodeValue());
				int top = Integer.parseInt(topNode.getFirstChild()
						.getNodeValue());
				Node nameNode = ((Element) actionNode).getElementsByTagName(
						"Name").item(0);
				String name = nameNode.getFirstChild().getNodeValue();
				final DiagramAction currentAction = new DiagramAction(name);
				this.panel.add(currentAction, left, top);
				currentAction.setTop(top);
				currentAction.setLeft(left);
				dwiList.add(currentAction);
			}
			Node connectorsNode = ((Element) ModelXMLNode)
					.getElementsByTagName("connectors").item(0);
			NodeList connectors = ((Element) connectorsNode)
					.getElementsByTagName("connector");
			for (int i = 0; i < connectors.getLength(); i++) {
				Node connector = connectors.item(i);
				Node startPointNode = ((Element) connector)
						.getElementsByTagName("startPoint").item(0);
				Node startPointLeftNode = ((Element) startPointNode)
						.getElementsByTagName("left").item(0);
				Node startPointTopNode = ((Element) startPointNode)
						.getElementsByTagName("top").item(0);

				final int startPointLeft = Integer.parseInt(startPointLeftNode
						.getFirstChild().getNodeValue());
				final int startPointTop = Integer.parseInt(startPointTopNode
						.getFirstChild().getNodeValue());

				final EndPoint startPoint = new EndPoint(startPointLeft,
						startPointTop);

				Node endPointNode = ((Element) connector).getElementsByTagName(
						"endPoint").item(0);
				Node endPointLeftNode = ((Element) endPointNode)
						.getElementsByTagName("left").item(0);
				Node endPointTopNode = ((Element) endPointNode)
						.getElementsByTagName("top").item(0);

				final int endPointLeft = Integer.parseInt(endPointLeftNode
						.getFirstChild().getNodeValue());
				final int endPointTop = Integer.parseInt(endPointTopNode
						.getFirstChild().getNodeValue());

				final EndPoint endPoint = new EndPoint(endPointLeft,
						endPointTop);

				Node startPointDecorationNode = ((Element) connector)
						.getElementsByTagName("startPointDecoration").item(0);
				SectionDecoration startPointDecoration = null;
				if (startPointDecorationNode != null) {
					String startPointDecorationText = startPointDecorationNode
							.getFirstChild().getNodeValue();
					DecorationType startPointDecorationType = null;
					switch (startPointDecorationText) {
					case ("ARROW_SOLID"):
						startPointDecorationType = DecorationType.ARROW_SOLID;
						break;
					case ("ARROW_LINE"):
						startPointDecorationType = DecorationType.ARROW_LINE;
						break;
					case ("USER"):
						startPointDecorationType = DecorationType.USER;
						break;
					}
					startPointDecoration = new SectionDecoration(
							startPointDecorationType);
				}
				Node endPointDecorationNode = ((Element) connector)
						.getElementsByTagName("endPointDecoration").item(0);
				SectionDecoration endPointDecoration = null;
				if (endPointDecorationNode != null) {
					String endPointDecorationText = endPointDecorationNode
							.getFirstChild().getNodeValue();
					DecorationType endPointDecorationType = null;
					GWT.log(endPointDecorationText);
					switch (endPointDecorationText) {
					case ("ARROW_SOLID"):
						endPointDecorationType = DecorationType.ARROW_SOLID;
						break;
					case ("ARROW_LINE"):
						endPointDecorationType = DecorationType.ARROW_LINE;
						break;
					case ("USER"):
						endPointDecorationType = DecorationType.USER;
						break;
					}
					endPointDecoration = new SectionDecoration(
							endPointDecorationType);
				}
				final Connector currentConnector = new Connector(
						startPointLeft, startPointTop, endPointLeft,
						endPointTop, startPointDecoration, endPointDecoration);
				currentConnector.diagram = diagram;
				startPoint.connector = currentConnector;
				endPoint.connector = currentConnector;

				conList.add(currentConnector);

			}
		} catch (DOMException e) {
			Window.alert("Could not parse XML document.");
			GWT.log(e.getMessage());
		}

		return this;

	}

	protected boolean isGlued(ConnectionPoint point, int left, int top) {
		if (myAbs(point.getCenterLeft(), left) > 2) {
			return false;
		} else if (myAbs(point.getCenterTop(), top) > 2) {
			return false;
		}
		return true;
	}

	protected int myAbs(int a, int b) {
		if (a > b) {
			Integer val1 = a - b;
			GWT.log(val1.toString());
			return a - b;
		} else {
			Integer val2 = b - a;
			GWT.log(val2.toString());
			return b - a;
		}
	}

	public void ShapeDrawer(DiagramWidgetInterface currentImage) {

		Shape newShape = new Shape((Widget) currentImage, CPShapeType.DIAMOND);
		newShape.showOnDiagram(diagram);
		newShape.top = ((DiagramWidgetInterface) currentImage).getTop();
		newShape.left = ((DiagramWidgetInterface) currentImage).getLeft();
		// currentImage.set
		// System.out.println("left: "+newShape.left+" top: "+newShape.top);

		if (currentImage.getWidgetType() == WidgetType.ACTION) {
			newShape.addStyleName("Action");
		}

		newShape.enableConnectionCreate(true);

	}

	public void ConnectionsDrawer(Connector currentConnector) {
		boolean isGlued = false;
		for (Shape shape : currentConnector.diagram.shapes) {
			for (ConnectionPoint point : shape.connectionPoints) {
				Integer i = point.getAbsoluteLeft();
				if (isGlued(point, currentConnector.startEndPoint.left,
						currentConnector.startEndPoint.top)) {
					GWT.log("Glued");
					currentConnector.startEndPoint.glueToConnectionPoint(point);
					currentConnector.startEndPoint
							.setGluedToConnectionPoint(true);
					point.glueToEndPoint(currentConnector.startEndPoint);
					isGlued = true;
					break;
				}
			}
			if (isGlued) {
				break;
			}
		}
		isGlued = false;
		for (Shape shape : currentConnector.diagram.shapes) {
			for (ConnectionPoint point : shape.connectionPoints) {
				if (isGlued(point, currentConnector.endEndPoint.left,
						currentConnector.endEndPoint.top)) {
					GWT.log("Glued");
					currentConnector.endEndPoint.glueToConnectionPoint(point);
					point.glueToEndPoint(currentConnector.endEndPoint);
					isGlued = true;
					break;
				}
			}
			if (isGlued) {
				break;
			}
		}

		currentConnector.showOnDiagram(diagram);

	}
}