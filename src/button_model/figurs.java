package button_model;

import java.awt.Image;

public class figurs {
	
	private Image img;
	private int x;
	private int number;
	private int y;
    private int type;
    private int logic_of_fig;
	public figurs(Image img, int x, int y,int type,int logic_of_fig,int number) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.type=type;
		this.logic_of_fig=logic_of_fig;
		this.number=number;
	}
	public int get_number()
	{
		return this.number;
	}
  public int getLogic()
  {
	  return this.logic_of_fig;
  }
	public Image getImage() {
		return img;
	}
    public int getType()
    {
    	return this.type;
    }
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return img.getHeight(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

}
