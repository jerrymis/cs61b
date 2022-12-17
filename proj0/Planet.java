public class Planet{
	double xxPos; //Its current x position
	double yyPos; //Its current y position
	double xxVel; //Its current velocity in the x direction
	double yyVel; //Its current velocity in the y direction
	double mass; //Its mass
	String imgFileName;//The name of the file that corresponds to the image that depicts the planet
	private static final double G = 6.67e-11; 

	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double sx = (this.xxPos - p.xxPos)*(this.xxPos - p.xxPos);
		double sy = (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos);
		return Math.sqrt(sx + sy);
	}

	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		return G * this.mass * p.mass/(r * r);
	}

	public double calcForceExertedByX(Planet p){
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		double x = p.xxPos - this.xxPos;
		return F * x / r;
	}

	public double calcForceExertedByY(Planet p){
		double r = this.calcDistance(p);
		double F = this.calcForceExertedBy(p);
		double y = p.yyPos - this.yyPos;
		return F * y / r;
	}

	public double calcNetForceExertedByX(Planet[] ps){
		double netForceX = 0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}
			else{
				netForceX += this.calcForceExertedByX(p);
			}

		}
		return netForceX; 
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double netForceY = 0;
		for(Planet p : ps){
			if(this.equals(p)){
				continue;
			}
			else{
				netForceY += this.calcForceExertedByY(p);
			}

		}
		return netForceY; 
	}

	public void update(double t,double fx,double fy){
		double ax = fx/this.mass;
		double ay = fy/this.mass;
		this.xxVel += ax * t; 
		this.yyVel += ay * t; 
		this.xxPos += xxVel * t; 
		this.yyPos += yyVel * t; 
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
	}
}