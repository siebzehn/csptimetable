/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public class DayConstraint extends HardConstraint
{
    public boolean valid(Dispo tt[][], int day, Subject ss)
    {
        boolean res = true;
        for (int j = 0; j < 5; j++)
        {
            if (tt[day][j].s != null)
                if (tt[day][j].s == ss)
                    res = false;
        }
        

        return res;
    }
}
