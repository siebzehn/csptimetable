/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.List;
import java.util.LinkedList;
/**
 *
 * @author five_stars
 */
public class Subject
{
    public String name;
    public int n_slot;
    public boolean couple;
    public int n_slot_assigned = 0;
    public List<Dispo> dominio;
    public List<Dispo> assigned;
    //public List<List<Slot>> dominio;
    int level_hard = -1;
    int level_soft = -1;

//    public List<Object> constraint;

    public Subject (String n, int s, boolean c)
    {
        this.name   = n;
        this.n_slot = s;
        this.couple = c;
        this.dominio = new LinkedList<Dispo>();
        //this.dominio = new LinkedList<List<Slot>>();
        this.assigned = new LinkedList<Dispo>();
    }

    public int compareTo(Object o)
    {
		Subject j = (Subject) o;
		if (name.equals(j.name) ) return -1;
		if (name.equals(j.name) ) return 1;
		return 0;
	}

	public boolean equals(Object o)
    {
		if (o instanceof Subject && this.name.equals( ((Subject)o).name) )
            return true;
		return false;
	}

    public void addDomain(Slot d)
    {
        Rules rul = new Rules();
        //rul.getSubjectSlot(this);
        //this.dominio.add(d);
    }

    public String toString()
    {
		return(this.name);
	}
}
