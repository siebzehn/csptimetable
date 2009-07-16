/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public class SpreadConstraint extends HardConstraint
{
   /* public SpreadConstraint(int ll)
    {
        this.level = ll;
    }*/
    
    public boolean valid(Dispo tt[][], int day, Subject ss)
    {
        boolean res = true;
        if (day > 0)
        {
            int temp = day - 1;
            for (int j = 0; j < 5; j++)
            {
                if (tt[temp][j].s != null)
                    if (tt[temp][j].s == ss)
                        res = false;
            }
        }
        if (day < 5)
        {
            int temp = day + 1;
            for (int j = 0; j < 5; j++)
            {
                if (tt[temp][j].s != null)
                    if (tt[temp][j].s == ss)
                        res = false;
            }
        }
        return res;
    }
}
