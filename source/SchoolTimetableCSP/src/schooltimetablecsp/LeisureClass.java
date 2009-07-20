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
public class LeisureClass {

    protected int id;
    protected int year;
    protected char section;
    protected int tot_hour;
    protected List<Subject> modules;
    protected String timetable[][];

    public LeisureClass(int i, int y, char s, int h)
    {
        this.id = i;
        this.year = y;
        this.section = s;
        this.tot_hour = h;
        modules = new LinkedList<Subject>();
    }
}
