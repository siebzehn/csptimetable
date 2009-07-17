/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public abstract class Constraint
{
    int level = -1;

    abstract public boolean valid(Subject ss, Dispo dd);
    abstract public int valid(Subject ss, Day dd);
    abstract public boolean valid(Dispo d1, Dispo d2);
}
