package sidescroller.scene;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import sidescroller.animator.AnimatorInterface;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.sprite.tile.BackgroundTile;
import sidescroller.entity.sprite.tile.FloraTile;
import sidescroller.entity.sprite.tile.PlatformTile;
import utility.Tuple;

public class MapScene implements MapSceneInterface {
	private Tuple count;
	private Tuple size;
	private double scale;
	private AnimatorInterface animator;
	private List<Entity> players;
	private List<Entity> staticShapes;
	private BooleanProperty drawBounds;
	private BooleanProperty drawFPS;
	private BooleanProperty drawGrid;
	private Entity background;

	public MapScene() {
		players = new ArrayList<>();
		staticShapes = new ArrayList<>();
		drawFPS = new SimpleBooleanProperty();
		drawGrid = new SimpleBooleanProperty();
		drawBounds = new SimpleBooleanProperty();
	}

	public BooleanProperty drawFPSProperty() {
		return drawFPS;
	}

	public boolean getDrawFPS(){
		return drawFPS.get();
	}

	public BooleanProperty drawBoundsProperty() {
		return drawBounds;
	}

	public boolean getDrawBounds() {
		return drawBounds.get();
	}
	public BooleanProperty drawGridProperty()
	{
		return drawGrid;
	}

	public boolean getDrawGrid()
	{
		return drawGrid.get();

	}
	public MapScene setRowAndCol(Tuple count, Tuple size, double scale) {
		this.count = count;
		this.size = size;
		this.scale = scale;

		return this;

	}
	public Tuple getGridCount() {
		return count;

	}
	public Tuple getGridSize(){
		return size;
	}
	public void start() {
		if(animator != null) {
			animator.start();
		}


	}
	public void stop() {
		if(animator != null) {
			animator.stop();
		}

	}
	public List<Entity> players(){
		return players;

	}
	public MapScene createScene(Canvas canvas) {
		MapBuilder mb = new MapBuilder();
		mb.setCanvas(canvas).setGrid(count, size).setGridScale(scale).buildBackground((x, y) -> {
			return BackgroundTile.NIGHT_TOP;
		}).buildLandMass(9,5,4,5).buildTree(2,5, FloraTile.TREE)
		.buildLandMass(9,15,3,18).buildTree(4,28, FloraTile.TREE_DEAD)
		.buildPlatform(4,12,4,PlatformTile.GRASS).buildPlatform(6,17,3,PlatformTile.STONE)
		.buildPlatform(6,8,4,PlatformTile.STONE);
		
		background = mb.getBackground();
		mb.getEntities(staticShapes);
		
		return this;

	}
	public boolean inMap(HitBox hitbox) {
		boolean inMap = background.getHitBox().containsBounds(hitbox);
		return inMap;

	}
	public MapScene setAnimator(AnimatorInterface newAnimator) {
		if(animator != null) {
			animator.stop();
		}
		this.animator = newAnimator;
		return this;

	}

	@Override
	public double getScale() {
		return scale;
	}

	@Override
	public Entity getBackground() {
		return background;
	}

	@Override
	public List<Entity> staticShapes() {
		return staticShapes;
	}

}
