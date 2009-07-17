/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;


import java.util.Iterator;
/**
 *
 * @author five_stars
 */
public class LastHourConstraint extends Constraint
{
    double level = 0.5;

    public LastHourConstraint(double ll)
    {
        this.level = ll;
    }
    
    public boolean valid(Subject ss, Dispo dd)
    {
        boolean res = true;
        if (ss.n_slot_assigned > 0)
        {
            int count = 0;
            FlexiTime ft = dd.getFlexiTime();

            Iterator it = ss.assigned.iterator();
            while(it.hasNext())
            {
                Dispo temp_d = (Dispo) it.next();
                if (temp_d.h != null)
                {
                    if (temp_d.h == ft)
                    {
                        count ++;
                    }
                }
            }
            if (( ((double)count)/((double)ss.n_slot_assigned) ) >= this.level)
                res = false;
        }
        return res;
    }

    @Override
    public int valid(Subject ss, Day dd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean valid(Dispo d1, Dispo d2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
