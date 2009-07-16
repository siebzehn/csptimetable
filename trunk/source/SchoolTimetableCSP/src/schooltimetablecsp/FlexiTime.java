/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public class FlexiTime implements Comparable
{
    protected int h_start;
	protected int h_end;
	protected int h;

	public FlexiTime(int hs, int he, int hid)
    {
		this.h_start = hs;
		this.h_end = he;
        this.h = hid;
	}

	public int compareTo(Object o)
    {
		FlexiTime h=(FlexiTime) o;
		if (h_start< h.getHourStart()) return -1;
		if (h_end>h.getHourEnd()) return 1;
		return 0;
	}
	public boolean equals(Object o)
    {
		if (o instanceof FlexiTime)
			if (h_start==((FlexiTime)o).h_start && h_end == ((FlexiTime)o).h_end)
				return true;
		return false;
	}
	public int getHourStart()
    {
		return h_start;
	}
	public int getHourEnd()
    {
		return h_end;
	}
	public String toString()
    {
		return(""+h_start+"H -"+h_end+"H ");
	}


}
