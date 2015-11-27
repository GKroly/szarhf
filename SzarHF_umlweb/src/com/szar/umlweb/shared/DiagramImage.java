package com.szar.umlweb.shared;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.szar.gwt.connectors.client.images.ConnectorsBundle;

public class DiagramImage extends Image implements DiagramWidgetInterface {
	public enum ImageType
	{
		decision,
		Final,
		Initial,
		merge
	}
	private int left;
	private int top;
	private ImageType imageType;
	WidgetType widgetType = WidgetType.IMAGE;
	
	public DiagramImage()
	{}
	
	public DiagramImage(ImageType type)
	{
		super(getImageResource(type));
		setType(type);
	}
	
	private static ImageResource getImageResource(ImageType type)
	{
		ImageResource imgRes = null;
		switch(type)
		{
		case decision:
			imgRes = ConnectorsBundle.INSTANCE.decision();
			break;
		case Final:
			imgRes = ConnectorsBundle.INSTANCE.finalImage();
			break;
		case Initial:
			imgRes = ConnectorsBundle.INSTANCE.initial();
			break;
		case merge:
			imgRes = ConnectorsBundle.INSTANCE.merge();
			break;
		}	
		return imgRes;
	}	
	
	public ImageResource setType(ImageType type)
	{
		ImageResource retImage = getImageResource(type);
		this.imageType = type;
		return retImage;
		
	}
	
	public ImageType getType()
	{
		return this.imageType;
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
