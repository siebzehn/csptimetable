/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
/**
 *
 * @author five_stars
 */
public class DFS
{
    Dispo template;
    String timetable[][];
    private List<Dispo> backjumping;
    Heuristic euristica;
    ConstraintPropagation constr_prop;
    ForwardChecking forward;

    public DFS(double sc, double lhc)
    {
        this.template = new Dispo(new Day(7), new FlexiTime(0,1,0), 0);
        this.timetable = new String[6][5];
        this.backjumping = new LinkedList<Dispo>();
        this.euristica = new Heuristic(sc, lhc);
        this.constr_prop = new ConstraintPropagation(this.euristica);
        this.forward = new ForwardChecking();
    }

    public Dispo dfsVist(Node root, List<Dispo> available_slot, List<Subject> to_assign, List<Subject> assigned, String preprint, Dispo last_assigned) throws InterruptedException
    {
        Dispo result = last_assigned;
        boolean removed = false;
        if (root != null)
        {
            Subject process_ss = (Subject)root.value();
            System.out.println(preprint + process_ss);

            List<Dispo> tried_dd = new LinkedList<Dispo>();
            // itero tutti valori del dominio
            boolean to_try = true;
            while(to_try && result != null)
            {
                //scegli slot per corso
                Dispo temp_ds = euristica.assignSlot(process_ss, tried_dd);
                tried_dd.add(temp_ds);
            
                //se la slot e' stata assegnata
                if (temp_ds != null)
                {
                    available_slot.remove(temp_ds);
                    this.timetable[temp_ds.j.numDay-1][temp_ds.h.h-1] = process_ss.name;
                    this.backjumping.add(0,temp_ds);
                    System.out.println(preprint + " ***" + temp_ds + "*** ");
                    // remove the assigned Dispo from all the subject
                    constr_prop.updateDomain(to_assign, temp_ds, true);
                    // update all in-conflict Dispo from the assigned subject domain
                    constr_prop.updateSingleDomain(process_ss, available_slot);
                    // se tutte le ore del subject sono assegnate
                    if (process_ss.n_slot <= process_ss.n_slot_assigned)
                    {
                        to_assign.remove(process_ss);
                        removed = true;
                    }

                    if (! assigned.contains(process_ss))
                        assigned.add(process_ss);
                    
                    if (!(to_assign.size() == 0))
                    {
                        // genera figli + corsi
                        euristica.generateChild(root, to_assign);

                        // forward checking
                        if ( this.forward.fwdChecking(root.figli()) )
                        {
                            Iterator<Node> it = root.figli().iterator();
                            if (it.hasNext())
                            {
                                Node temp_nd = it.next();
                                result = this.dfsVist(temp_nd, available_slot, to_assign, assigned, preprint + "  ", temp_ds);
                                // se null allora timetable completa
                                if (result == null)
                                    to_try = false;
                                else
                                {
                                    // backtracking
                                    if (result != temp_ds && result != this.template)
                                    {
                                        to_try = false;
                                    }
                                }
                            }
                        }
                        else
                        {
                            // backjumping
                            result = this.backCheck(process_ss, temp_ds);
                        }
                    }
                    else
                    {
                        // timetable completa
                        result = null;
                        System.out.println(preprint + "Slot disponibili: " + available_slot.size() + " corsi non assegnati: " + to_assign.size());
                    }
                    this.backjumping.remove(0);
                    this.removeSlot(process_ss, temp_ds, available_slot);
                    // re-add the assigned Dispo from all the subject
                    this.constr_prop.updateDomain(to_assign, temp_ds, false);

                    if (removed)
                        to_assign.add(process_ss);

                    if ( result != null )
                        this.timetable[temp_ds.j.numDay-1][temp_ds.h.h-1] = null;
                }
                // slot orario non assegnata
                else
                {
                    //backjumping
                    result = this.backCheck(process_ss, temp_ds);

                    System.out.print(preprint + "Slot disponibili: " + available_slot.size());
                    Iterator<Dispo> itt = available_slot.iterator();
                    while(itt.hasNext())
                    {
                        System.out.print( " [" + (Dispo)itt.next() + "]" );
                    }
                    System.out.print(" --- corsi non assegnati: " + to_assign.size());
                    Iterator<Subject> itte = to_assign.iterator();
                    while(itte.hasNext())
                    {
                        System.out.print( " [" + (Subject)itte.next() + "]" );
                    }
                    System.out.println();
                    
                    System.out.println(preprint + result);
                    return result;
                }
            }
        }
        System.out.println(preprint + result);
        return result;
    }

    public void printDispoList(List<Dispo> ddll)
    {
        Iterator it1 = ddll.iterator();
        while (it1.hasNext())
        {
            Dispo tt = ((Dispo)it1.next());
            System.out.print(" [" + tt + "] ");
        }
        //System.out.println();
    }

    public void printTT()
    {
        System.out.println();
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                System.out.print(this.timetable[j][i] + "    \t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void removeSlot(Subject ss, Dispo dd, List<Dispo> assignable)
    {
        dd.s = null;
        ss.assigned.remove(dd);
        ss.n_slot_assigned --;
        assignable.add(dd);
    } 

    public Dispo backCheck(Subject current_ss, Dispo last_dd)
    {
        if (last_dd == null)
        {
            Iterator itc = current_ss.assigned.iterator();
            while (itc.hasNext())
            {
                last_dd = (Dispo)itc.next();
            }
        }
        Iterator itj = this.backjumping.iterator();
        while(itj.hasNext())
        {
            Dispo to_jump = (Dispo) itj.next();
            if ( !to_jump.s.equals(current_ss) )
            {

                if (this.euristica.checkConflict(last_dd, to_jump) )
                {
                    return to_jump;
                }
            }
        }
        return this.template;
    }



}
