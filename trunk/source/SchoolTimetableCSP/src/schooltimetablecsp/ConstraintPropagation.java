/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author five_stars
 */
public class ConstraintPropagation
{
    Heuristic euristica;
    
    public ConstraintPropagation(Heuristic heu)
    {
        this.euristica = heu;
    }
    
    public void updateDomain(List<Subject> ss, Dispo dd, boolean flag)
    {
        // scorre tutti i corsi
        Iterator it = ss.iterator();
        while(it.hasNext())
        {
            Subject temp_s = (Subject) it.next();
            // rimuovo la slot appena assegnata
            if (flag)
                temp_s.dominio.remove(dd);
            // o aggiungo la slot appena ritornata
            else
                temp_s.dominio.add(dd);
        }
    }

    public void updateSingleDomain(Subject temp_s, List<Dispo> ddl)
    {
        // lista slot to remove
        List<Dispo> to_remove = new LinkedList<Dispo>();
        // lista slot compatibili
        List<Dispo> to_add = new LinkedList();
        // scorre tutte le slot del dominio
        Iterator it2 = ddl.iterator();
        while (it2.hasNext())
        {
            Dispo temp_d = (Dispo) it2.next();
            // verifica se non e' piu' compatibile
            if (! this.euristica.goodSlot(temp_s, temp_d))
            {
                to_remove.add(temp_d);
            }
            else
            {
                if ((!to_add.contains(temp_d)) && temp_d.s == null)
                    to_add.add(temp_d);
            }
        }

        Iterator it3 = to_remove.iterator();
        while(it3.hasNext())
        {
            Dispo temp_d = (Dispo) it3.next();
            temp_s.dominio.remove(temp_d);
        }

        Iterator it4 = to_add.iterator();
        while(it4.hasNext())
        {
            Dispo temp_d = (Dispo) it4.next();
            temp_s.dominio.add(temp_d);
        }
    }

    public boolean contain(Dispo dd, List<Dispo> ddl)
    {
        Iterator it2 = ddl.iterator();
        while(it2.hasNext())
        {
            Dispo temp_d = (Dispo) it2.next();
            if (temp_d == dd)
            {
                return true;
            }
            else
                if (temp_d.key == temp_d.key)
                    return true;
        }
        return false;
    }
}
