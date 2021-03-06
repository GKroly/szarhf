package com.szar.gwt.connectors.client.listeners.event;

import com.szar.gwt.connectors.client.elements.Connector;
import com.szar.gwt.connectors.client.elements.Section;

public class ConnectorClickEvent implements ConnectorEvent {

  private Connector sourceConnector;
  private Section sourceSection;

  public ConnectorClickEvent(Connector sourceConnector, Section sourceSection) {
    this.sourceConnector = sourceConnector;
    this.sourceSection = sourceSection;
  }

  public Connector getSourceConnector() {
    return sourceConnector;
  }

  public Section getSourceSection() {
    return sourceSection;
  }

}
