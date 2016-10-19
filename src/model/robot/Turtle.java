package model.robot;

public class Turtle implements Robot, IViewRobot {
	
	private double Xpos;
	private double Ypos;
	private double rotation;
	private boolean penDown;
	private boolean visible;
	
	public Turtle() {
		//TODO
	}

	/** SETTERS **/
	
	@Override
	public void setX(double x) {
		Xpos = x;
		
	}

	@Override
	public void setY(double y) {
		Ypos = y;
		
	}
	
	@Override
	public void setRotation(double r) {
		rotation = r; 
	}

	@Override
	public void setPenDown(boolean t) {
		penDown = t;
		
	}

	@Override
	public void setVisible(boolean t) {
		visible = t;
	}
	
	
	
	/** GETTERS **/

	@Override
	public double getX() {
		return Xpos;
	}

	@Override
	public double getY() {
		return Ypos;
	}

	@Override
	public double getRotation() {
		return rotation;
	}

	@Override
	public boolean isPenDown() {
		return penDown;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

}