package by.slesh.ri.cp.victoriashpak.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Recognizer {
    private static double[][] mPatterns = { 
	    { 13.0, 2.0, 2.0, 0 },// Напрасников В. В.
	    { 10.0, 0.0, 2.0, 1 },// Ковалева И. В.
	    { 9.0, 2.0, 1.0, 2 },// Кочуров В. А.
	    { 10.0, 1.0, 2.0, 3 },// Бородуля А. В.
    };

    private static String[] mNames = { "НапрасниковВВ", "КовалеваИВ",
	    "КочуровВА", "БородуляАВ", };

    private static int mQuantityEtalons = mPatterns.length;
    private static int mQuantitySigns = 3;
    private static double[] mNormFactors;

    static {
	normMatrix();
    }

    private static final void normMatrix() {
	mNormFactors = new double[mQuantitySigns];
	for (int k = 0; k < mQuantitySigns; ++k) {
	    mNormFactors[k] = mPatterns[0][k];
	}
	for (int j = 0; j < mQuantitySigns; ++j) {
	    for (int i = 0; i < mQuantityEtalons; ++i) {
		if (mPatterns[i][j] > mNormFactors[j]) {
		    mNormFactors[j] = mPatterns[i][j];
		}
	    }
	}
	for (int j = 0; j < mQuantitySigns; ++j) {
	    for (int i = 0; i < mQuantityEtalons; ++i) {
		if (mNormFactors[j] == 0) continue;
		mPatterns[i][j] /= mNormFactors[j];
	    }
	}
    }

    private static double[] normVector(double[] vector) {
	for (int k = 0; k < mQuantitySigns; ++k) {
	    if (mNormFactors[k] == 0.0) continue;
	    vector[k] /= mNormFactors[k];
	}
	return vector;
    }

    public static BufferedImage[] recognize(BufferedImage[] digits) {
	System.out
	        .println("------------------------------------------------------");
	double[] a = defineSigns(digits);
	a = normVector(a);
	int id = 0;
	double[][] d = countDistances(a);
	id = recognize(d);
	System.out.println("norm=" + Arrays.toString(a));
	System.out.println("answer==========" + mNames[id]);
	System.out
	        .println("------------------------------------------------------");
	return createAnswer(id);
    }

    private static void swap(double[][] arr, int i, int j) {
	double[] row = arr[i];
	arr[i] = arr[j];
	arr[i] = arr[j];
	arr[j] = row;
    }

    private static void bubblesort(double[][] d) {
	for (int i = d.length - 1; i >= 0; i--) {
	    for (int j = 0; j < i; j++) {
		if (d[j][0] > d[j + 1][0]) swap(d, j, j + 1);
	    }
	}
    }

    private static int recognize(double[][] d) {
	bubblesort(d);
	int[] quantities = new int[4];
	for (int k = 0; k < 3; ++k) {
	    ++quantities[(int) d[k][1]];
	}
	int digit = (int) d[0][1];
	System.out.println("================" + Arrays.toString(quantities));
	return digit;
    }


    private static final BufferedImage[] createAnswer(int id) {
	final int W0 = 45;
	final int SIZE = 70;
	String name = mNames[id];
	int length = name.length();
	BufferedImage[] answer = new BufferedImage[length];
	int h = 100;
	for (int k = 0; k < length; k++) {
	    String c = Character.toString(name.charAt(k));
	    BufferedImage symbol = new BufferedImage(W0, h,
		    BufferedImage.TYPE_3BYTE_BGR);
	    Graphics g = symbol.getGraphics();
	    g.setFont(new Font("TimesRoman", Font.PLAIN, SIZE));
	    g.setColor(Color.GREEN);
	    g.drawString(c, 0, SIZE);
	    answer[k] = symbol;
	}
	return answer;
    }

    private static double[][] countDistances(double[] a) {
	double[][] distances = new double[mQuantityEtalons][2];
	for (int y = 0; y < mQuantityEtalons; ++y) {
	    for (int x = 0; x < mQuantitySigns; ++x) {
		distances[y][0] += Math.pow(a[x] - mPatterns[y][x], 2);
	    }
	    distances[y][0] = Math.sqrt(distances[y][0]);
	    distances[y][1] = mPatterns[y][mQuantitySigns];
	}
	return distances;
    }

    private static double[] defineSigns(BufferedImage[] symbols) {
	double[] answer = new double[mQuantitySigns];

	int q = symbols.length;
	answer[0] = q;

	ContourWorker cw = null;

	cw = new ContourWorker(symbols[q - 1]);
	answer[2] = cw.getCentersOfContours().length;

	cw = new ContourWorker(symbols[q - 2]);
	answer[1] = cw.getCentersOfContours().length;

	System.out.println("signs vector not norm =" + q + "  "
	        + Arrays.toString(answer));

	return answer;
    }
}
