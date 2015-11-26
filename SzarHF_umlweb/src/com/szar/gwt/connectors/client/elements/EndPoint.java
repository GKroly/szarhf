package com.szar.gwt.connectors.client.elements;

import com.szar.gwt.connectors.client.ConnectionPoint;
import com.szar.gwt.connectors.client.Diagram;
import com.szar.gwt.connectors.client.Point;
import com.szar.gwt.connectors.client.images.ConnectorsBundle;
import com.szar.gwt.connectors.client.listeners.event.ElementConnectEvent;
import com.szar.gwt.connectors.client.util.ConnectorsClientBundle;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class EndPoint extends Point {

  public ConnectionPoint gluedConnectionPoint;
  public Connector connector;
  boolean gluedToConnectionPoint;
  public int top;
  public int left;
  // Defines size of connection point
  public static final int CP_MARGIN = 13;

  /**
   * {@link Connector}s are ended with EndPoints. You can drag and drop EndPoint to change its
   * position. When EndPoint is dragging its Connector is redrawing.
   * <p>
   * {@link EndPoint} is visible until the {@link Connector} is glued to {@link ConnectionPoint}.
   * EndPoints are represented by small circles.
   */
  public EndPoint(Integer left, Integer top, Connector connector) {
    super(left, top);
    
    this.left=left;
    this.top=top;

    this.connector = connector;
    this.setGluedToConnectionPoint(false);
    Widget img = createImage();
    if (img != null) {
      this.setWidget(img);
    }
    this.getElement().getStyle().setZIndex(3);
  }

  public EndPoint(Integer left, Integer top) {
    super(left, top);
    
    this.left=left;
    this.top=top;
    
    this.setGluedToConnectionPoint(false);
    Widget img = createImage();
    if (img != null) {
      this.setWidget(img);
    }
    this.getElement().getStyle().setZIndex(3);
  }

  /**
   * 
   * @param connectionPoint
   */
  public void glueToConnectionPoint(ConnectionPoint connectionPoint) {

    this.gluedConnectionPoint = connectionPoint;
    connectionPoint.glueToEndPoint(this);
    this.setGluedToConnectionPoint(true);
    this.clear();

    connector.diagram.onElementConnect(new ElementConnectEvent(connectionPoint.parentWidget, connector, this));
  }

  /**
   * 
   */
  public void unglueFromConnectionPoint() {
    this.gluedConnectionPoint.unglueFromEndPoint(this);
    this.gluedConnectionPoint = null;
    this.setGluedToConnectionPoint(false);
  }

  /**
	 * 
	 */
  public void update() {
    ((AbsolutePanel) this.getParent()).setWidgetPosition(this, this.getLeft() - (this.getOffsetWidth() / 2), this
        .getTop()
        - (this.getOffsetHeight() / 2));

  }

  /**
   * @param diagram
   */
  public void showOnDiagram(Diagram diagram) {
    // Add EndPoint to given panel
    diagram.boundaryPanel.add(this, this.getLeft() - CP_MARGIN / 2, this.getTop() - CP_MARGIN / 2);

    // Set EndPoint's cursor
    DOM.setStyleAttribute(this.getElement(), "cursor", "crosshair");

    // Make EndPoint draggable
    diagram.endPointDragController.makeDraggable(this);

  }

  public void updateOpositeEndPointOfVerticalSection() {
    // Updates position of connector after moving the EndPoint
    // Find section, then find opposite Corner Point.
    // Update position of opposite Corner Point and update Section
    Section prevSection = this.connector.prevSectionForPoint(this);
    if (prevSection != null) {
      prevSection.getStartPoint().setLeft(this.getLeft());
      this.connector.update();
    }

    Section nextSection = this.connector.nextSectionForPoint(this);
    if (nextSection != null) {
      nextSection.getEndPoint().setLeft(this.getLeft());
      this.connector.update();
    }

  }

  public void updateOpositeEndPointOfHorizontalSection() {
    // Updates position of connector after moving the EndPoint
    // Find section, then find opposite Corner Point.
    // Update position of opposite Corner Point and update Section
    Section prevSection = this.connector.prevSectionForPoint(this);
    if (prevSection != null) {
      prevSection.getStartPoint().setTop(this.getTop());
      this.connector.update();
    }

    Section nextSection = this.connector.nextSectionForPoint(this);
    if (nextSection != null) {
      nextSection.getEndPoint().setTop(this.getTop());
      this.connector.update();
    }

  }

  public boolean isGluedToConnectionPoint() {
    return gluedToConnectionPoint;
  }

  public void setGluedToConnectionPoint(boolean gluedToConnectionPoint) {
    this.gluedToConnectionPoint = gluedToConnectionPoint;
  }

  protected Widget createImage() {
    Image img = AbstractImagePrototype.create(ConnectorsBundle.INSTANCE.end_point()).createImage();
    img.addStyleName(ConnectorsClientBundle.INSTANCE.css().imageDispBlock());
    return img;
  }

}
