/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.awt.Color;
import java.util.List;
import java.util.LinkedList;
/**
 *
 * @author five_stars
 */
public class Subject
{
    public int id;
    public String name;
    public int n_slot;
    public boolean couple;
    public int n_slot_assigned = 0;
    public List<Dispo> dominio;
    public List<Dispo> assigned;
    public Teacher insegnante;
    public LeisureClass classe;
    int level_hard = -1;
    int level_soft = -1;
    public Color colore = Color.WHITE;

    public Subject (int i, String n, int s, boolean c)
    {
        this.id     = i;
        this.name   = n;
        this.n_slot = s;
        this.couple = c;
        this.dominio = new LinkedList<Dispo>();
        this.assigned = new LinkedList<Dispo>();
    }

    public Subject (int i, String n, int s, boolean c, Teacher t, LeisureClass lc)
    {
        this.id         = i;
        this.name       = n;
        this.n_slot     = s;
        this.couple     = c;
        this.insegnante = t;
        t.modules.add(this);
        lc.modules.add(this);
        this.classe     = lc;
        this.dominio = new LinkedList<Dispo>();
        this.assigned = new LinkedList<Dispo>();
    }

    public Subject (int i, String n, int s, boolean c, Teacher t, LeisureClass lc, Color cc)
    {
        this.id         = i;
        this.name       = n;
        this.n_slot     = s;
        this.couple     = c;
        this.insegnante = t;
        t.modules.add(this);
        lc.modules.add(this);
        this.classe     = lc;
        this.dominio = new LinkedList<Dispo>();
        this.assigned = new LinkedList<Dispo>();

        this.colore = cc;
    }

    public int compareTo(Object o)
    {
		Subject j = (Subject) o;
		if (this.id == j.id ) return -1; //name.equals(j.name) ) return -1;
		if (this.id == j.id ) return 1;//name.equals(j.name) ) return 1;
		return 0;
	}

	public boolean equals(Object o)
    {
		if (o instanceof Subject && this.id == ((Subject)o).id )
            return true;
		return false;
	}

    public String toString()
    {
		return(this.name);
	}
}
