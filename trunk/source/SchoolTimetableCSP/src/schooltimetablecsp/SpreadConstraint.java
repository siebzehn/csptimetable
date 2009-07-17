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
public class SpreadConstraint extends Constraint
{
    double level = 0.5;

    public SpreadConstraint(double ll)
    {
        this.level = ll;
    }
    
    public int valid(Subject ss, Day day)
    {
        double counter = 0;
        Iterator it = ss.assigned.iterator();
        while(it.hasNext())
        {
            Dispo temp_d = (Dispo) it.next();
            if (temp_d.j.numDay == (day.numDay+1))
            {
                counter += 1;
            }
            else if (temp_d.j.numDay == (day.numDay-1))
            {
                counter += 1;
            }
            else if(temp_d.j.numDay == day.numDay)
            {
                counter += 1;
            }
        }
        double val = 0;
        if (counter > 0)
            val = counter/((double)ss.assigned.size());

        if (val > this.level)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean valid(Subject ss, Dispo dd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean valid(Dispo d1, Dispo d2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
