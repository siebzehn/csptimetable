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

    abstract public boolean valid(Dispo tt[][], int day, Subject ss);
}
