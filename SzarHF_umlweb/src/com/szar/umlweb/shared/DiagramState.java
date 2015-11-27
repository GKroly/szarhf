package com.szar.umlweb.shared;

import com.google.gwt.user.client.ui.Label;

public class DiagramState extends Label implements DiagramWidgetInterface {
	private String name;
	private int left;
	private int top;
	public WidgetType widgetType = WidgetType.LABEL;
	
	public DiagramState()
	{	}
	public DiagramState(String name)
	{
		super(name);
		setName(name);
	}
	
	public Label getState()
	{
		Label retLabel = new Label();
		return retLabel;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	@Override
	public int getLeft()
	{
		return left;
	}
	@Override
	public void setLeft(int left)
	{
		this.left = left;
	}
	@Override
	public int getTop()
	{
		return top;
	}
	@Override
	public void setTop(int top)
	{
		this.top = top;
	}

	@Override
	public WidgetType getWidgetType() {
		return widgetType;
	}
}
