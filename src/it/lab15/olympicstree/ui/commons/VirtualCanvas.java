package it.lab15.olympicstree.ui.commons;

import it.lab15.olympicstree.commons.Vec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualCanvas extends BasicCanvas {

	private static final Logger LOG = LoggerFactory
			.getLogger(VirtualCanvas.class);

	// Mouse
	private Vec2 mp = new Vec2(); // this holds the mouse position in a Vec2

	// Camera
	protected Vec2 camPos = new Vec2(); // camera position
	private Vec2 camPosDest = new Vec2(); // where camera wants to be, for
											// smoothing
	private float camZoom = 0; // used for camera zooming, not currently used
	private boolean draggingScreen = false; // is the screen being dragged?
	private float screenDragSpeed = 17; // how fast the screen is scrolled

	private boolean panEnabled = true;
	private boolean panLocked = false;
	
	public VirtualCanvas() {
		super();
	}

	public void setup() {
		super.setup();
	}

	@Override
	protected void drawContent() {
		manageCam();
		translate(-camPos.x, -camPos.y, 0);
		drawVirtualContent();
		draggingScreen();   
	}

	protected void drawVirtualContent() {
	}

	// manages camera
	public void manageCam() {
		if (this.panEnabled){
			// okay... if we move the camera then the mouse position gets whacky
			// this gets the mouse pos relative to screen
			mp.nowEquals(mouseX + (camPos.x), mouseY + (camPos.y));
	
			// smoothly move camera to where it should be
			lerp(camPos, camPos, camPosDest, .2f);

			// zoom is not used yet.
			// camZoom = lerp(camZoom, -200, .3);
	
			// //if a cell is selected, follow the cell with the cell at the center
			// of screen
			// if(aCellSelected && selectedCell!=null){
			// Vec2 cellCamPos = new Vec2(selectedCell.m.p);
			// cellCamPos.x -= width/2;
			// cellCamPos.y -= height/2 - 100;
			// camPosDest.nowEquals(cellCamPos);
			// }
		}
	}// end manageCam()

	protected boolean isDraggingScreen() {
		return (mousePressed && !this.panLocked && this.panEnabled /*
							 * && !mouseOverMagnet && !mouseOverDraggable &&
							 * !mouseOverArt
							 */);
	}

	// allows mouse to drag the screen space around
	public void draggingScreen() {
		if (isDraggingScreen()) {
			// if(mouseOverCell)
			// return;
			// smoothly move camera to where mouse is going
			lerp(camPosDest, camPosDest, new Vec2(camPos.x + (pmouseX - mouseX)
					* screenDragSpeed, camPos.y + (pmouseY - mouseY)
					* screenDragSpeed), .1f);
			draggingScreen = true;
		}
	}// end draggingScreen()

	// turns the value given to it into the absolute screen position
	public void absScreenVec(Vec2 v) {
		v.nowEquals(v.x + camPos.x, v.y + camPos.y);
		return;
	}// end absScreen()

	public boolean isPanEnabled() {
		return this.panEnabled;
	}

	public void setPanEnabled(boolean dragEnabled) {
		this.panEnabled = dragEnabled;
	}
	
	public void lockPan(){
		this.panLocked = true;
	}
	
	public void unlockPan(){
		this.panLocked = false;
	}
	
	
}
