package com.szar.gwt.connectors.client.listeners;

import com.szar.gwt.connectors.client.listeners.event.ConnectorClickEvent;
import com.szar.gwt.connectors.client.listeners.event.ConnectorDoubleClickEvent;

public interface ConnectorListener {

  void onConnectorClick(ConnectorClickEvent event);

  void onConnectorDoubleClick(ConnectorDoubleClickEvent event);

}
