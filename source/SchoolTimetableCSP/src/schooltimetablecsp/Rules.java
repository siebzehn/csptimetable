/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.List;
import java.util.Iterator;
/**
 *
 * @author five_stars
 */
public class Rules {

    public Subject chooseFirst(List<Subject> subs)
    {
        Subject res = null;
        Iterator it = subs.iterator();
        if (it.hasNext())
            res = (Subject) it.next();
        while (it.hasNext())
        {
            Subject temp = (Subject) it.next();
            //scelgo quello con il dominio piu' piccolo
            if (temp.dominio.size() < res.dominio.size())
                res = temp;
            else
                //se sono uguali => scelgo quello con piu' ore
                if (temp.dominio.size() == res.dominio.size())
                {
                    double temp_val = ((double)temp.dominio.size())/((double)temp.n_slot);
                    double res_val = ((double)res.dominio.size())/((double)res.n_slot);
                    if (temp_val < res_val)
                    {
                        res = temp;
                    }
                    /*if (temp.level_hard > res.level_hard)
                        res = temp;
                    else
                        if (temp.level_hard == res.level_hard)
                            if (temp.level_soft > res.level_soft)
                                res = temp;*/
                }

        }
        return res;
    }

    public Subject chooseNextChildren(List<Subject> to_choose)
    {
        //TODO: scegli quello che restringe meno il dominio degli altri
        //  per ora sceglie il primo
        if (to_choose.size() > 0)
            return to_choose.get(0);
        return null;
    }
    
    public List<Subject> getChildrens(List<Subject> subs)
    {
        return null;
    }

    public List<Slot> getSubjectSlot(Subject ss, Dispo tt[][])
    {
        int to_assign = ss.n_slot;

        for (int i = 0; i < 6; i++)
        {
            System.out.println("[" + i + "]");
            if (to_assign > 0)
            {
                for (int j = 0; j < 5; j++)
                {
                    //System.out.println("\tAssignable at " + j + ":" + this.goodDispo(tt, i, j, ss));
                    if (tt[i][j].s == null)
                    {
                        if (this.goodDispo(tt, i, j, ss))
                        {
                            ss.n_slot_assigned += 1;
                            tt[i][j].s = ss;
                            to_assign -= 1;
                            break;
                        }
                        else
                            continue;
                    }
                    else
                        continue;
                }
            }
            else
                break;
        }
        //assegna la prima slot disponibile
        //assegna la successiva in modo che non sia il giorno successivo
        //se tutte le slot sono assegnate allora ok
        //altrimenti while e sceglie le meno peggio

        return null;
    }

    public boolean goodDispo(Dispo tt[][], int index_day, int index_time, Subject to_add )
    {
        boolean res = false;
        // CONSTRAINTS
        // non nello stesso giorno
/*        DayConstraint dc = new DayConstraint();
        // non giorni consecutivi
        SpreadConstraint sc = new SpreadConstraint();
        // non sempre ultima ora
        LastHourConstraint lhc = new LastHourConstraint();

        boolean b_dc = dc.valid(tt, index_day, to_add);
        boolean b_sc = sc.valid(tt, index_day, to_add);
        boolean b_lhc = lhc.valid(tt, index_day, to_add, index_time);
        res = b_dc && b_sc && b_lhc;
  */
        return res;
    }
}
