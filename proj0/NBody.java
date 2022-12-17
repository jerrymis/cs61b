public class NBody{
	public static String imageToDraw = "./images/starfield.jpg";

	/** return the radius given by the file */
	public static double readRadius(String path){
		In in = new In(path);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/** return the array of planets given by the file  */
	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int num = in.readInt();
		in.readDouble();
		Planet[] p = new Planet[num];
		for(int i = 0;i < num;i++){
				double xxPos = in.readDouble();
				double yyPos = in.readDouble();
				double xxVel = in.readDouble();
				double yyVel = in.readDouble();
				double mass = in.readDouble();
				String imgFileName = in.readString();
				p[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return p;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r =  NBody.readRadius(filename);
		Planet[] planets = NBody.readPlanets(filename);
		In in = new In(filename);
		int num = in.readInt();
		StdDraw.setScale(-r, r);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		for(int i = 0;i < num;i++){
			planets[i].draw();
		}
		StdDraw.enableDoubleBuffering();
		double  t = 0;
		while(t != T){
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for(int k = 0;k < num;k++){
				xForces[k] = planets[k].calcNetForceExertedByX(planets);
				yForces[k] = planets[k].calcNetForceExertedByY(planets);
			}
			for(int i = 0;i < num;i++){
				planets[i].update(dt,xForces[i],yForces[i]);
		    }
		    StdDraw.picture(0, 0, imageToDraw);
		    for(Planet p : planets){
		    	p.draw();
		    }
			StdDraw.show();
			StdDraw.pause(10);
			t += 10;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < num; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

	}
}