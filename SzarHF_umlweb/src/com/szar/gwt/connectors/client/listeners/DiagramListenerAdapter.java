package com.szar.gwt.connectors.client.listeners;

import com.szar.gwt.connectors.client.listeners.event.ConnectorClickEvent;
import com.szar.gwt.connectors.client.listeners.event.ConnectorDoubleClickEvent;
import com.szar.gwt.connectors.client.listeners.event.DiagramAddEvent;
import com.szar.gwt.connectors.client.listeners.event.DiagramRemoveEvent;
import com.szar.gwt.connectors.client.listeners.event.ElementConnectEvent;
import com.szar.gwt.connectors.client.listeners.event.ElementDragEvent;

public class DiagramListenerAdapter implements DiagramListener {

  @Override
public void onConnectorClick(ConnectorClickEvent event) {
  }

  @Override
public void onDiagramAdd(DiagramAddEvent event) {
  }

  @Override
public void onDiagramRemove(DiagramRemoveEvent event) {
  }

  @Override
public void onElementConnect(ElementConnectEvent event) {
  }

  @Override
public void onElementDrag(ElementDragEvent event) {
  }

  @Override
public void onConnectorDoubleClick(ConnectorDoubleClickEvent event) {
  }

}
