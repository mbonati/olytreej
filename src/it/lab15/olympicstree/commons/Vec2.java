package it.lab15.olympicstree.commons;

import processing.core.PApplet;

public class Vec2 {

	public float y;
	public float x;

	private static final PApplet appletSupport = new PApplet();
	private static Vec2 origin2 = new Vec2(); // origin is 0,0

	public Vec2() {// constructor with no arguments, gives you origin
		x = 0;
		y = 0;
	}

	public Vec2(float x, float y) {// constructor with 2 arguments x and y
		this.x = x;
		this.y = y;
	}

	public Vec2(Vec2 s) {// constructor with Vec as input. basically an assignment
					// operator
		if (s == null)
			return;
		this.x = s.x;
		this.y = s.y;
	}

	public Vec2(Vec2 s, Vec2 t) {// constructs a Vector from the difference of two
							// Vectors
		float dx = t.x - s.x;
		float dy = t.y - s.y;
		this.x = dx;
		this.y = dy;
	}

	public Vec2(Vec2 s, Vec2 t, float k) {// constructs a Vector from the difference of
									// two Vectors, then modifies it by k
		float dx = t.x - s.x;
		float dy = t.y - s.y;
		this.x = dx * k;
		this.y = dy * k;
	}

	// --------------BEGINNING OF VECTOR METHODS--------------
	public void disp(float ang, float magnitude) {// displacement. will offset
													// this Vector by an angle
													// and distance
		ang = appletSupport.radians(ang);
		x += appletSupport.cos(ang) * magnitude;
		y -= appletSupport.sin(ang) * magnitude;
	}

	public void rotate(float ang) {
		Vec2 temp = new Vec2();
		temp.disp(ang() + ang, mag());
		x = temp.x;
		y = temp.y;
	}

	public void rotate(Vec2 v, float ang) {
		Vec2 cen = new Vec2(v);
		Vec2 ori = new Vec2(v, this);
		ori.rotate(180 + ang);
		cen.add(ori);
		this.x = cen.x;
		this.y = cen.y;
		return;
	}

	public float ang() {// returns the angle between this Vector and origin
		return getAng(this, origin2);
	}

	public float mag() {// returns the distance between this Vector and origin
		return dist(origin2, this);
	}

	// scalar operations
	public void add(float s) {// addition operator
		x += s;
		y += s;
	}

	public void sub(float s) {// subtraction operator
		x -= s;
		y -= s;
	}

	public void mul(float s) {// multiplication operator
		x *= s;
		y *= s;
	}

	public void div(float s) {// division operator. returns 0 when division by
								// zero
		if (s == 0)
			return;
		x /= s;
		y /= s;
	}

	public void add(float x, float y) {// addition operator
		this.x += x;
		this.y += y;
	}

	public void sub(float x, float y) {// subtraction operator
		this.x -= x;
		this.y -= y;
	}

	public void mul(float x, float y) {// multiplication operator
		this.x *= x;
		this.y *= y;
	}

	public void div(float x, float y) {// division operator. returns 0 when
										// division by zero
		if (x == 0 || y == 0)
			return;
		this.x /= x;
		this.y /= y;
	}

	// Vector operators
	public void add(Vec2 s) {// addition operator
		x += s.x;
		y += s.y;
	}

	public void sub(Vec2 s) {// subtraction operator
		x -= s.x;
		y -= s.y;
	}

	public void mul(Vec2 s) {// multiplication operator
		x *= s.x;
		y *= s.y;
	}

	public void div(Vec2 s) {// division operator. returns 0 when division by
								// zero
		if (s.x == 0 || s.y == 0)
			return;
		x /= s.x;
		y /= s.y;
	}

	public void nowEquals(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void nowEquals(Vec2 s) {
		this.x = s.x;
		this.y = s.y;
	}

	public void nowEquals(Vec2 s, Vec2 t) {// constructs a Vector from the
											// difference of two Vectors

		this.x = t.x - s.x;
		this.y = t.y - s.y;
	}

	// --------------USEFUL VECTOR TOOLS--------------
	// Finds the midpoint between two positions in space
	public static Vec2 midPoint(Vec2 a, Vec2 b) {
		Vec2 d = new Vec2(a, b);
		d.mul(.5f);
		Vec2 dest = new Vec2(a);
		dest.add(d);
		// d.sub(b);
		// Vec newVec=new Vec(a);
		// newVec.add(d);
		return dest;
	}

	// Finds the average position in an array of points in space
	public static Vec2 avg(Vec2 v[]) {
		Vec2 total = new Vec2();
		for (int i = 0; i < v.length; i++) {
			total.add(v[i]);
		}
		total.div(v.length);
		return total;
	}

	// Using point a as origin, this returns the angle between a and b.
	public static float getAng(Vec2 a, Vec2 b) {
		float ang = appletSupport.atan2(-1 * (b.y - a.y), b.x - a.x);
		ang = appletSupport.degrees(ang);
		if (b.y > a.y)
			return ang = 360 + ang;
		if (b.y == a.y && b.x > a.x)
			return ang = 0;
		if (b.y == a.y && b.x < a.x)
			return ang = 180;
		return ang;
	}

	// Returns the distances between two points in space
	public static float dist(Vec2 a, Vec2 b) {
		if (a == null || b == null)
			return 0;
		return appletSupport.dist(a.x, a.y, b.x, b.y);
	}

	public Vec2 lerp(Vec2 a, Vec2 b, float k) {
		Vec2 lerpN = new Vec2();
		lerpN.x = appletSupport.lerp(a.x, b.x, k);
		lerpN.y = appletSupport.lerp(a.y, b.y, k);
		return lerpN;
	}

	public static void lerp(Vec2 v, Vec2 source, Vec2 target, float k) {
		v.x = appletSupport.lerp(source.x, target.x, k);
		v.y = appletSupport.lerp(source.y, target.y, k);
	}

	// Appends a Vector point at the end of a list of Vectors
	public static Vec2[] append(Vec2 v[], Vec2 nv) {
		Vec2 temp[] = new Vec2[v.length + 1];
		System.arraycopy(v, 0, temp, 0, v.length);
		temp[v.length] = new Vec2(nv);
		return temp;
	}
}
