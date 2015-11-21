package com.szar.szarhf_umlweb.shared;

public interface DiagramWidgetInterface{		
	enum WidgetType
	{
		LABEL,
		IMAGE
	}		
	WidgetType getWidgetType();
	public int getLeft();
	public int getTop();
	public void setLeft(int left);
	public void setTop(int top);

}
