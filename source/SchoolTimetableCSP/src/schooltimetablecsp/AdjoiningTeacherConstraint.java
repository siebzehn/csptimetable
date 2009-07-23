/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * @author five_stars
 */
public class AdjoiningTeacherConstraint extends Constraint
{

    double level = 0.4;

    public AdjoiningTeacherConstraint(double adj)
    {
        this.level = adj;
    }

    public boolean valid(Subject ss, Dispo dd)
    {
        boolean result = true;
        Iterator it = ss.insegnante.teach_time.iterator();
        while(it.hasNext())
        {
            Dispo temp_d = (Dispo) it.next();
            if (temp_d.j.numDay == dd.j.numDay)
            {
                if (temp_d.h.h_end == dd.h.h_start || temp_d.h.h_start == dd.h.h_end)
                {
                    return true;
                }
                else
                {
                    if (temp_d.h.h_end +1 == dd.h.h_start || temp_d.h.h_start+1 == dd.h.h_end)
                    {
                        return true;
                    }
                    else
                    {
                        result = false;
                    }
                }
            }
        }

        return result;
    }

    private void addSorted(List<Dispo> ddl, Dispo dd)
    {
        int index = 0;
        Iterator it = ddl.iterator();
        if (ddl.size() < 1)
        {
            ddl.add(dd);
        }
        else
        {
            while(it.hasNext())
            {
                Dispo temp_d = (Dispo) it.next();
                if (dd.h.h_start < temp_d.h.h_start)
                {
                    ddl.add(index, dd);
                    break;
                }
                index += 1;
            }
        }
    }
    
    public int valid(Subject ss, Day day)
    {
        double teach_hour = 0;
        double hole_between_hour = 0;
        Iterator it = ss.insegnante.teach_time.iterator();
        List<Dispo> teach_day = new LinkedList<Dispo>();

        while(it.hasNext())
        {
            Dispo temp_d = (Dispo) it.next();
            if (temp_d.j.numDay == day.numDay)
            {
                this.addSorted(teach_day, temp_d);
            }
        }

        teach_hour = teach_day.size();
        if (teach_hour > 0)
        {
            int start = -1;
            int end = -1;
            int contiguous = 0;
            int hole = 0;
            Dispo previous = null;
            Iterator it2 = teach_day.iterator();
            if ( teach_day.size() > 1 )
            {
                if (it2.hasNext())
                {
                    previous = (Dispo) it2.next();
                    start = previous.h.h_start;
                    end = previous.h.h_end;
                }
                while (it2.hasNext())
                {
                    Dispo temp_d = (Dispo)it2.next();

                    if (temp_d.h.h_start < start)
                        start = temp_d.h.h_start;
                    if (temp_d.h.h_end > end)
                        end = temp_d.h.h_end;
                    
                    if (previous.h.h_end == temp_d.h.h_start)
                    {
                        contiguous += 1;
                    }
                    else
                    {
                        hole += 1;
                    }
                }
                int tot_span = end - start;
                double val = ((double) hole)/((double) tot_span);
                if (val > this.level)
                    return -1;
                else
                    return 0;
            }
            else
            {
                //ce pensamo
            }
        }
        return 0;
    }

    @Override
    public boolean valid(Dispo d1, Dispo d2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
