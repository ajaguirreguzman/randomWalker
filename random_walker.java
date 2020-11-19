/*
Andres J. Aguirre G.
March 4, 2013

Random walker animation

***********
Run with 3000 random steps (e.g.)

$ java random_walker 3000

*/

import java.util.*;

public class random_walker {

	public static void main(String args[]) {
		double xmin=0, xmax=0, ymin=0, ymax=0, j;
		int N=0, i=0;
		double xr, yr, R;
		double rsuma=0, rm;
		
		Locale.setDefault(new Locale("en", "US"));
		
		try {
			N = Integer.parseInt(args[0]);
		}
		
		catch(java.lang.ArrayIndexOutOfBoundsException exe) {
			System.err.println("Parameters: Number of steps (e.g. 3000)");
			System.exit(1);
		}
		
		// BEGIN: DETERMINE THE XY-PATH OF THE RANDOM WALKER
		
		// Define random numbers generator
		Random generator = new Random();
		
		// Create the vectors (arrays)
		double[] x=new double[N+1], y=new double[N+1], r = new double[N+1];// +1 because the initial condition
		
		// Initial position
		x[0] = 0;
		y[0] = 0;
		
		for (i=0; i<N; ++i) {
			
			// Generate the ramdon step
			xr = 2*generator.nextDouble() - 1;
			yr = 2*generator.nextDouble() - 1;
			
			// The next step start from the last step
			x[i+1] = x[i] + xr;
			y[i+1] = y[i] + yr;
			
			// Determine the xmax, xmin, ymax and ymin for the domensions of the window
			if (x[i+1]<xmin) xmin = x[i+1];
			if (x[i+1]>xmax) xmax = x[i+1];
			if (y[i+1]<ymin) ymin = y[i+1];
			if (y[i+1]>ymax) ymax = y[i+1];
		}
		
		// END: DETERMINE THE XY-PATH OF THE RANDOM WALKER
		
		// BEGIN: SIMULATION WINDOW
		
		// Window
		StdDraw.clear(StdDraw.WHITE);
		StdDraw.setXscale(xmin-3, xmax+0.5);
		StdDraw.setYscale(ymin-1, ymax+2);
		
		// Title
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text((xmin+xmax)*0.5, ymax+2, "Random Walker");
		
		// Set axis
		StdDraw.setPenColor(StdDraw.RED);
		double Xo = (xmax+xmin)*0.5;
		double Yo = (ymax+ymin)*0.5;
		double W = Math.abs(xmax)+Math.abs(xmin);
		double H = Math.abs(ymax)+Math.abs(ymin);
		StdDraw.rectangle(Xo,Yo,W*0.5,H*0.5);
		
		// x scale
		for (j=xmin; j<=xmax; j+=W*0.1) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(j, ymin-0.5, j, ymin+0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(j, ymin-2, "" + (int)j);
		}
		
		// y scale
		for (j=ymin; j<=ymax; j+=H*0.1) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(xmin-0.5, j, xmin+0.5, j);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textRight(xmin-1, j, "" + (int)j);
		}
		
		// END: SIMULATION WINDOW
		
		// ************************************ PLOTS THE PATH OF THE RANDOM WALKER
		
		for (i=0; i<N; ++i) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
		}
		
		// ************************************ RADIAL DISTANCE FROM THE STARTING POINT AFTER N STEPS
		
		R = Math.sqrt( Math.pow(x[N],2) + Math.pow(y[N],2) );
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.line(x[0], y[0], x[N], y[N]);
		StdDraw.text((xmin+xmax)*0.5, ymax-5, "R = " + R);
		
		// ************************************ AVERAGE LENGTH OF EACH STEP (root-mean-square step size,
		
		for (i=0; i<N; ++i) {
			
			// length of i step
			r[i+1] = Math.sqrt( Math.pow( x[i+1] - x[i] ,2) + Math.pow(y[i+1] - y[i],2) );
			
			rsuma += r[i+1];
		}
		
		// Average length
		rm = rsuma*Math.pow(N,-1);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text((xmin+xmax)*0.5, ymax-7, "Average step size = " + rm);
		
	}
}

