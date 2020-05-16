package sidescroller.animator;

import java.util.Iterator;
import java.util.function.Consumer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sidescroller.entity.player.Player;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;


public class Animator extends AbstractAnimator{

	private Color background = Color.ANTIQUEWHITE;
	
	public void handle(GraphicsContext gc, long now) {
		
		updateEntities();  //1.1
		clearAndFill(gc, background);  //1.2
		drawEntities(gc);  //1.3
		
	}

//	public void drawEntities(GraphicsContext gc){
//// another way to write anonymous class
//	Consumer<Entity> draw = new Consumer<Entity>() {
//
//		@Override
//		public void accept(Entity e) {
//			if(e!=null && e.isDrawable()) { 
//				e.getDrawable().draw(gc);                //2.1 & 2.2
//				if(map.getDrawBounds() && e.hasHitbox()) {  
//					e.getHitBox().getDrawable().draw(gc);      //2.3, 2.4 & 2.5
//				}
//			}
//			
//		}
//		
//	};  //finish method accept(e:Entity):void
		
	public void drawEntities(GraphicsContext gc){
		Consumer<Entity> draw = (Entity e) -> { //1.1 and 2: create accept(e:Entity):void
			if(e!=null && e.isDrawable()) { 
				e.getDrawable().draw(gc);                //2.1 & 2.2
				if(map.getDrawBounds() && e.hasHitbox()) {  
					e.getHitBox().getDrawable().draw(gc);      //2.3, 2.4 & 2.5
				}
			}
			
		}; //finish method accept(e:Entity):void
		
		draw.accept(map.getBackground());             //1.2
			
		for (int i = 0; i<map.staticShapes().size(); i++) {
			draw.accept(map.staticShapes().get(i));                      //1.3
		}
		 
		for(int i = 0; i<map.players().size(); i++) {
			draw.accept(map.players().get(i));                        //1.4
		}
		
		/*
		 * We can write 3ways :
		 * for(Entity e :map.players())
		 * draw.accept(e);
		 * or 
		 * map.players().forEach(draw);
		 */
				
	}
	
	@Override
	public void updateEntities() {
		
		for(int i = 0; i<map.players().size(); i++) {
			map.players().get(i).update();                        //1.1
		}
		
//		for(int i = 0; i<map.staticShapes().size(); i++) {
//		map.staticShapes().get(i).update();                        //1.2
//	}		
		for(Entity e : map.staticShapes()) {
			e.update();                        //1.2
		}
		

		
//		if (map.getDrawBounds()) {
//			for(int i = 0; i<map.players().size(); i++) {
//				map.players().get(i).getHitBox().getDrawable().setStroke(Color.RED);  //1.3, 1.4, 1.5
//			}
		
		if (map.getDrawBounds()) {
			for(Entity e1:map.players()) {
				e1.getHitBox().getDrawable().setStroke(Color.RED);  //1.3, 1.4, 1.5
			}
		}
		
		
		for(int i = 0; i<map.staticShapes().size(); i++) {
			proccessEntityList(map.players().iterator(),map.staticShapes().get(i).getHitBox());    //1.6
		}                        
	}

 
	@Override
	public void proccessEntityList(Iterator<Entity> iterator, HitBox shapeHitBox) {
		while (iterator.hasNext()) {
			Entity entity = iterator.next();        //1.1, 1.2
			HitBox bounds = entity.getHitBox();     //1.3, 1.4
			if(!map.inMap( bounds)) {
				updateEntity(entity, iterator);     //1.5
				
			}else if(shapeHitBox!=null && bounds.intersectBounds( shapeHitBox)) {
				if(map.getDrawBounds()) {
					bounds.getDrawable().setStroke(Color.BLUEVIOLET);      //1.6, 1.7
			
				}
				updateEntity(entity, iterator);    //1.8
			}
		}
		
	}

	@Override
	public void updateEntity(Entity entity, Iterator<Entity> iterator) {
		if (entity instanceof Player) {
			((Player) entity).stepBack();  //2.1
			
		}
		
	}
	
}
