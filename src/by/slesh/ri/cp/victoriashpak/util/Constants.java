/**
 * 
 */
package by.slesh.ri.cp.victoriashpak.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author slesh
 *
 */
public interface Constants {
    public static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;

    final int _0 = Color.WHITE.getRGB();
    final int _1 = Color.BLACK.getRGB();
    final int _05 = Color.GRAY.getRGB();
    
    final int[][] DISK_KERNEL = new int[][]{
	{_0, _1, _1, _1, _0},
	{_1, _1, _1, _1, _1},
	{_1, _1, _1, _1, _1},
	{_1, _1, _1, _1, _1},
	{_0, _1, _1, _1, _0},
};

    final int[][] RING_KERNEL = new int[][]{
    	{_0, _1, _1, _1, _0},
    	{_1, _0, _0, _0, _1},
    	{_1, _0, _0, _0, _1},
    	{_1, _0, _0, _0, _1},
    	{_0, _1, _1, _1, _0},	
    };
    
    final int[][]		RECT_KERNEL	= new int[][] {
    	{_1, _1, _1, _1, _1},
    	{_1, _1, _1, _1, _1},
    	{_1, _1, _1, _1, _1},
    	{_1, _1, _1, _1, _1},
    	{_1, _1, _1, _1, _1},
    };
    
    final int[][]		SIMPLE_KERNEL	= new int[][] {
    	{_1, _1},
    	{_1, _1},
};
}
