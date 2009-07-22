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
public class DeepSearch
{
    Dispo template;
    String timetable[][][];
    private List<Dispo> backjumping;
    Heuristic euristica;
    ConstraintPropagation constr_prop;
    ForwardChecking forward;

    public DeepSearch(double sc, double lhc, int classi)
    {
        this.template = new Dispo(new Day(7), new FlexiTime(0,1,0), 0, new LeisureClass(-1,-1,' ',-1));
        this.timetable = new String[classi][6][5];
        this.backjumping = new LinkedList<Dispo>();
        this.euristica = new Heuristic(sc, lhc);
        this.constr_prop = new ConstraintPropagation(this.euristica);
        this.forward = new ForwardChecking();
    }

    public Dispo dfsVist(Node root, List<Dispo> available_slot, List<Subject> to_assign, String preprint, Dispo last_assigned, List<LeisureClass> scuola)
    {
        System.out.println("Slots: " + available_slot.size() + " Corsi: " + to_assign.size() + " Dominio: " + ((Subject)root.value()).dominio.size() );
        Dispo result = last_assigned;
        boolean removed = false;
        if (root != null)
        {
            Subject process_ss = (Subject)root.value();
            System.out.println(preprint + process_ss + " [" + process_ss.classe + "]");

            List<Dispo> tried_dd = new LinkedList<Dispo>();
            // itero tutti valori del dominio
            boolean to_try = true;
            while(to_try && result != null)
            {
                //scegli slot per corso
                List<Dispo> pre_domain = new LinkedList<Dispo>();
                pre_domain.addAll(process_ss.dominio);
                Dispo temp_ds = euristica.assignSlot(process_ss, tried_dd);
                tried_dd.add(temp_ds);

                //se la slot e' stata assegnata
                if (temp_ds != null)
                {
                    available_slot.remove(temp_ds);
                    process_ss.insegnante.teach_time.add(temp_ds);
                    this.timetable[process_ss.classe.id-1][temp_ds.j.numDay-1][temp_ds.h.h-1] = process_ss.name;
                    this.backjumping.add(0,temp_ds);
                    System.out.println(preprint + "***" + temp_ds + " [" + temp_ds.cc + "]*** ");
                    // remove the assigned Dispo from all the subject
                    constr_prop.updateDomain(to_assign, temp_ds, true);
                    // update all in-conflict Dispo from the assigned subject domain
                    constr_prop.updateSingleDomain(process_ss, available_slot);
                    // se tutte le ore del subject sono assegnate
                    if (process_ss.n_slot <= process_ss.n_slot_assigned)
                    {
                        //System.out.println(process_ss + " " + process_ss.classe);
                        to_assign.remove(process_ss);
                        removed = true;
                    }

                    if (!(to_assign.size() == 0))
                    {
                        Subject figghio = euristica.generateChild(root, to_assign);
                        if (figghio != null)
                        {
                            result = this.dfsVist(new Node (root, figghio), available_slot, to_assign, preprint + " ", temp_ds, scuola);
                            // se null allora timetable completa
                            root.deleteChildrens();
                            if (result == null)
                            {
                                to_try = false;
                            }
                            else
                            {
                                // backtracking
                                if (result != temp_ds && result != this.template)
                                {
                                    to_try = false;
                                }
                            }
                        }
                        else
                        {
                            System.out.println("no childrens!");
                        }
                    }
                    else
                    {
                        // timetable completa
                        result = null;
                        System.out.println(preprint + "Slot disponibili: " + available_slot.size() + " corsi non assegnati: " + to_assign.size());
                    }
                    if (result != null)
                    {
                        this.backjumping.remove(0);
                        this.removeSlot(process_ss, temp_ds, available_slot);
                        // re-add the assigned Dispo from all the subject
                        this.constr_prop.updateDomain(to_assign, temp_ds, false);
                        process_ss.dominio.removeAll(pre_domain);
                        process_ss.dominio.addAll(pre_domain);
                        if (removed)
                        {
                            if (!to_assign.contains(process_ss))
                                to_assign.add(process_ss);
                        }
                        if ( result != null )
                        {
                            this.timetable[process_ss.classe.id-1][temp_ds.j.numDay-1][temp_ds.h.h-1] = null;
                            process_ss.insegnante.teach_time.remove(temp_ds);
                        }
                    }
                }
                // slot orario non assegnato
                else
                {
                    //backjumping
                    result = this.backCheck(process_ss);
/*                  System.out.print(preprint + "Slot disponibili: " + available_slot.size());
                    Iterator<Dispo> itt = available_slot.iterator();
                    while(itt.hasNext())
                    {
                        Dispo tt_dd = (Dispo)itt.next();
                        System.out.print( " [" + tt_dd + " ");
                        System.out.print( tt_dd.cc + "]" );
                    }
                    System.out.print(" --- corsi non assegnati: " + to_assign.size());
                    Iterator<Subject> itte = to_assign.iterator();
                    while(itte.hasNext())
                    {
                        Subject tt_ss =  (Subject)itte.next();
                        System.out.print( " [" + tt_ss + " ");
                        System.out.print( tt_ss.classe + " " );
                        System.out.print( tt_ss.n_slot - tt_ss.n_slot_assigned + "]" );
                    }
                    System.out.println();
*/
                    if (result != null)
                       System.out.println(preprint + result + " " + result.cc);
                    return result;
                }
            }
        }
       if (result != null)
            System.out.println(preprint + result + " " + result.cc);
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
        for (int c = 0; c < this.timetable.length; c ++)
        {
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 6; j++)
                {
                    System.out.print(this.timetable[c][j][i] + "    \t");
                }
                System.out.println();
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

    public Dispo backCheck(Subject current_ss)
    {
        Dispo result = this.template;
        Iterator itc = current_ss.assigned.iterator();
        int counter_pre = -1;
        while (itc.hasNext())
        {
            Dispo last_dd = (Dispo)itc.next();
            Iterator itj = this.backjumping.iterator();
            int counter = 0;
            while(itj.hasNext())
            {
                counter ++;
                Dispo to_jump = (Dispo) itj.next();
                if ( !to_jump.s.equals(current_ss) )
                {
                    if (this.euristica.checkConflict(last_dd, to_jump) )
                    {
                        //return to_jump;
                        if (counter <= counter_pre || counter_pre == -1)
                        {
                            counter_pre = counter;
                            result = to_jump;
                            break;
                        }
                    }
                }
            }
        }
        return result;//this.template;
    }

}
