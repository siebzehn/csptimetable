/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public class Dispo implements Comparable
{
    protected int key;
    protected FlexiTime h;
	protected Day j;
    protected Subject s;

	public Dispo(Day j, FlexiTime h, int k)
    {
		this.j=j;
		this.h=h;
        this.s=null;
        this.key = k;
	}

	public int compareTo(Object o)
    {
		Dispo d=(Dispo) o;
		if (j.compareTo(d.getDay())<0) return -1;
		if (j.compareTo(d.getDay())>0) return 1;
		return h.compareTo(d.getFlexiTime());
	}

    public boolean equals(Object o)
    {
		if (o instanceof Dispo){
			Dispo d=(Dispo) o;
			if (this.j.equals(d.j) && this.h.equals(d.h)) return true;
		}
		return false;
	}

	public FlexiTime getFlexiTime()
    {
		return h;
	}

	public Day getDay()
    {
		return j;
	}

	public String toString()
    {
		return (j.toString()+h.toString());
	}


}
