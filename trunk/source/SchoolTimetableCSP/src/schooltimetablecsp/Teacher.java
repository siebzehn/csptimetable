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
public class Teacher {
    protected int id;
    protected String name;
    protected Day free_day;
    protected int tot_slot;
    protected List<Subject> modules;

    public Teacher(int i, String n, Day d, int s)
    {
        this.id = i;
        this.name = n;
        this.free_day = d;
        this.tot_slot = s;
        modules = new LinkedList<Subject>();
    }

    

}
