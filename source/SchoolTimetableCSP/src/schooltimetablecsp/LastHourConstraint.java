/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public class LastHourConstraint extends HardConstraint
{
    public boolean valid(Dispo tt[][], int day, Subject ss)
    {
        return false;
    }
    public boolean valid(Dispo tt[][], int day, Subject ss, int hour)
    {
        boolean res = true;
        if (ss.n_slot_assigned > 0)
        {
            FlexiTime prec = null;
            int count = 0;
            for (int i = 0; i < 6; i++)
            {
                //System.out.println("\t Day: " + i);
                if (tt[i][hour].s != null)
                    if (tt[i][hour].s == ss)
                    {
                        FlexiTime ft = tt[i][hour].getFlexiTime();
                        if (ft.equals(prec))
                            count ++;
                        else
                            prec = ft;
                    }
            }
            if (( ((double)count)/((double)ss.n_slot_assigned) ) >= 0.5)
                res = false;
        }
        return res;
    }
}
