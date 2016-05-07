import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import javafx.scene.image.ImageView;

@SuppressWarnings("serial")
public class CollisionUnit {

	protected Rectangle rectangle;

	public CollisionUnit(int x, int y, int height, int width) {
		rectangle = new Rectangle(x, y, height, width);
	}

	@SuppressWarnings("serial")
	public int checkOverlap(CollisionUnit r, String direction, int value) {
		if (direction.equals("left")) {
			if (rectangle.intersects(r.rectangle) && rectangle.getX() > r.rectangle.getX()) {
				return 0;
			}
			for (int i = 0; i < r.rectangle.getHeight() - 1; i++) {
				if (rectangle.contains(new Point((int)(r.rectangle.getX() + r.rectangle.getWidth()) + value, (int)(r.rectangle.getY() - i)))) {
					if (rectangle.contains(new Point((int)(r.rectangle.getX() + r.rectangle.getWidth()), (int)(r.rectangle.getY() - i)))) {
						return 1;
					} else {
						return (int) (rectangle.getX() - r.rectangle.getX() - r.rectangle.getWidth());
					}
				}
			}
		} else if (direction.equals("right")) {
			if (rectangle.intersects(r.rectangle) && rectangle.getX() < r.rectangle.getX()) {
				return 0;
			}
			for (int i = 0; i < r.rectangle.getHeight() - 1; i++) {
				if (rectangle.contains(new Point((int)(r.rectangle.getX() - value - rectangle.getWidth()), (int)(r.rectangle.getY() - i)))) {
					if (rectangle.contains(new Point((int)(r.rectangle.getX() - rectangle.getWidth()), (int)(r.rectangle.getY() - i)))) {
						return 1;
					} else {
						return (int) (r.rectangle.getX() - rectangle.getX() - rectangle.getWidth());
					}
				}
			}
		} else if (direction.equals("up")) {
			if (rectangle.intersects(r.rectangle) && rectangle.getY() < r.rectangle.getY()) {
				return 0;
			}
			for (int i = 0; i < r.rectangle.getWidth(); i++) {
				if (rectangle.contains(new Point((int)(r.rectangle.getX() - i), (int)(r.rectangle.getY() - r.rectangle.getHeight() - value)))) {
					if (rectangle.contains(new Point((int)(r.rectangle.getX() - i), (int)(r.rectangle.getY() - r.rectangle.getHeight())))) {
						return 1;
					} else {
						return (int) (r.rectangle.getY() - rectangle.getY() - r.rectangle.getHeight());
					}
				}
			}
		} else {
			if (rectangle.intersects(r.rectangle) && rectangle.getY() > r.rectangle.getY()) {
				return 0;
			}
			for (int i = 0; i < r.rectangle.getWidth(); i++) {
				if (rectangle.contains(new Point((int)(r.rectangle.getX() - i), (int)(r.rectangle.getY() + value)))) {
					if (rectangle.contains(new Point((int)(r.rectangle.getX() - i), (int)(r.rectangle.getY())))) {
						return 1;
					} else {
						return (int) (rectangle.getY() - rectangle.getHeight() - r.rectangle.getY());
					}
				}
			}
		}

		return -1;
	}

	public int findHorizontalDistance(CollisionUnit r) {
		if (rectangle.intersects(r.rectangle) || rectangle.contains(r.rectangle)) {
			return 0;
		} else if (rectangle.getX() > r.rectangle.getX()) {
			return (int)(rectangle.getX() - r.rectangle.getX() - r.rectangle.getWidth());
		} else {
			return (int)(r.rectangle.getX() - rectangle.getX() - rectangle.getWidth());
		}
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public ImageView getImageView() {
		return null;
	}

	/*
	public static void main(String[] args) {
		CollisionUnit main = new CollisionUnit(72, 50, 20, 20);
		System.out.println(main.checkOverlap(new CollisionUnit(55,50,10,10), "left", 10));
		System.out.println(main.rectangle.intersects(new Rectangle(55,55,10,10)));
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			int a = random.nextInt(100);
			int b = random.nextInt(100);
			int c = random.nextInt(20);
			int d = random.nextInt(20);
			System.out.println(a+" "+b+" "+c+" "+d+"\n");
			System.out.println(main.checkOverlap(new CollisionUnit(a,b,c,d), "left", 10));
		}
	}
	*/

}
