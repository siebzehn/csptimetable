/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */

public class Day implements Comparable
{

	protected int numDay;

	public Day(int nd)
    {
		this.numDay = nd;
	}

	public int compareTo(Object o)
    {
		Day j = (Day) o;
		if (numDay < j.getNum()) return -1;
		if (numDay >j.getNum()) return 1;
		return 0;
	}

	public boolean equals(Object o)
    {
		if (o instanceof Day && this.numDay==((Day)o).numDay)
            return true;
		return false;
	}

	public int getNum()
    {
		return numDay;
	}

	public String toString()
    {
		return ("Day "+numDay+" ");
    }
}