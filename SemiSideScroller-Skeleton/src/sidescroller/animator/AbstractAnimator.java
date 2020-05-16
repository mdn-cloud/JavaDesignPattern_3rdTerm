package sidescroller.animator;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sidescroller.entity.FpsCounter;
import sidescroller.entity.Grid;
import sidescroller.entity.property.Drawable;
import sidescroller.scene.MapSceneInterface;
import utility.Tuple;

public abstract class AbstractAnimator extends AnimationTimer implements AnimatorInterface{
	protected MapSceneInterface map;
	protected Tuple mouse;
	private Canvas canvas;
	private FpsCounter fps;
	private Grid grid;
	
	public AbstractAnimator(){
		mouse = new Tuple();  //1.1
		fps = new FpsCounter(10, 25); //1.2
		Drawable<?> fpsSprite = fps.getDrawable(); //1.3-1.4
		
		fpsSprite.setFill(Color.BLACK).setStroke(Color.WHITE).setWidth(1); //1.7
				
//		fpsSprite.setFill(Color.BLACK); //1.5
//		fpsSprite.setStroke(Color.WHITE); //1.6
//		fpsSprite.setWidth(1); //1.7
		
	}
	
	public void clearAndFill(GraphicsContext gc, Color background){
		gc.setFill(background); //1.1
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //1.2
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); //1.3

			
	}
	public void handle(long now) {
		GraphicsContext gc = canvas.getGraphicsContext2D(); //1.1 & 1.2
		
		if (map.getDrawFPS()) {
			fps.calculateFPS(now);        //1.3
		}
		
		handle(gc, now);   //1.4
		
		if (map.getDrawGrid()) {
			if(grid==null) {
				grid = new Grid(map.getGridCount(), canvas.getWidth(), canvas.getHeight()); //1.5
				Drawable <?> gridSprite = grid.getDrawable(); //1.6 & 1.7
				gridSprite.setStroke(Color.BLACK); //1.8
				gridSprite.setWidth(1); //1.9
				gridSprite.setScale(map.getScale()); //1.10
				gridSprite.setTileSize(map.getGridSize()); //1.11
			}
			grid.getDrawable().draw(gc);   //1.12 & 2.13
			
		}
		
		if(map.getDrawFPS()) {
			fps.getDrawable().draw(gc); //1.14 &1.5
		}
	}
	
	public void setMapScene(MapSceneInterface map){
		this.map = map;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas =canvas;
	}
}