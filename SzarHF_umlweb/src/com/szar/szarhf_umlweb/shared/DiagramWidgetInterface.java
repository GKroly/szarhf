package com.szar.szarhf_umlweb.shared;

public interface DiagramWidgetInterface{		
	enum WidgetType
	{
		LABEL,
		IMAGE
	}		
	WidgetType getWidgetType();
}
