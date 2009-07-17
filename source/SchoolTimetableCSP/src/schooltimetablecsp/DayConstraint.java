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
public class DayConstraint extends Constraint
{

    @Override
    public boolean valid(Subject ss, Dispo dd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int valid(Subject ss, Day dd)
    {
        int res = 0;
        Iterator it = ss.assigned.iterator();
        while(it.hasNext())
        {
            Dispo temp_d = (Dispo) it.next();
            if (temp_d.j == dd)
            {
                res ++;
            }
        }
        return res;
    }

    @Override
    public boolean valid(Dispo d1, Dispo d2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
