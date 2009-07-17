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

    SpreadConstraint spread_day;
    DayConstraint day;
    LastHourConstraint not_last;
    DoubleHourConstraint double_hour;
    ConsecutiveHourConstraint cont_hour;

    public DFS(double sc, double lhc)
    {
        this.spread_day = new SpreadConstraint(sc);
        this.day        = new DayConstraint();
        this.not_last   = new LastHourConstraint(lhc);
        this.double_hour= new DoubleHourConstraint();
        this.cont_hour  = new ConsecutiveHourConstraint();

        this.template = new Dispo(new Day(7), new FlexiTime(0,1,0), 0);
        this.timetable = new String[6][5];
        this.backjumping = new LinkedList<Dispo>();
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
            boolean to_try = true;
            while(to_try && result != null)
            {
                //scegli slot per corso
                Dispo temp_ds = this.assignSlot(process_ss, tried_dd);
                tried_dd.add(temp_ds);
            
                //se la slot e' stata assegnata
                if (temp_ds != null)
                {
                    available_slot.remove(temp_ds);
                    this.timetable[temp_ds.j.numDay-1][temp_ds.h.h-1] = process_ss.name;
                    System.out.println(preprint + " ***" + temp_ds + "*** ");
                    // remove the assigned Dispo from all the subject
                    this.updateDomain(to_assign, temp_ds, true);
                    // update all in-conflict Dispo from the assigned subject domain
                    this.updateSingleDomain(process_ss, available_slot);
                    // se tutte le ore del subject sono assegnate
                    if (process_ss.n_slot <= process_ss.n_slot_assigned)
                    {
                        to_assign.remove(process_ss);
                        removed = true;
                    }

                    if (! assigned.contains(process_ss))
                        assigned.add(process_ss);
                    
                    this.backjumping.add(0,temp_ds);

                    if (!(to_assign.size() == 0))
                    {
                        //genera figli + corsi
                        this.generateChild(root, to_assign);

                        //forward checking
                        if ( this.fwdChecking(root.figli()) )
                        {
                            //scorri figli
                            Iterator<Node> it = root.figli().iterator();
                            if (it.hasNext())
                            {
                                Node temp_nd = it.next();
                                result = this.dfsVist(temp_nd, available_slot, to_assign, assigned, preprint + "  ", temp_ds);
                                if (result == null)
                                    to_try = false;
                                else
                                {
                                    if (result != temp_ds && result != this.template)
                                    {
                                        to_try = false;
                                    }
                                }
                            }
                        }
                        else
                        {
                            result = this.backCheck(process_ss, temp_ds);
                        }
                    }
                    else
                    {
                        result = null;
                        System.out.println(preprint + "Slot disponibili: " + available_slot.size() + " corsi non assegnati: " + to_assign.size());
                    }
                    this.backjumping.remove(0);
                    this.removeSlot(process_ss, temp_ds, available_slot);
                    this.updateDomain(to_assign, temp_ds, false);

                    if (removed)
                        to_assign.add(process_ss);

                    if ( result != null )
                        this.timetable[temp_ds.j.numDay-1][temp_ds.h.h-1] = null;
                }
                else
                {
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
    
    public Dispo assignSlot(Subject ss, List<Dispo> tried)
    {
        //scorri la lista delle slot e verifica se e' compatibile con quelle gia' assegnata a ss
        Iterator it = ss.dominio.iterator();
        while (it.hasNext())
        {
            Dispo dd = (Dispo)it.next();
            if (dd.s == null && this.goodSlot(ss,dd) && !tried.contains(dd))
            {
                dd.s = ss;
                ss.assigned.add(dd);
                ss.n_slot_assigned ++;
                ss.dominio.remove(dd);
                return dd;
            }
            else
            {
            }
        }
        return null;
    }

    public void generateChild(Node daddy, List<Subject> ss)
    {
        Iterator it = ss.iterator();
        while (it.hasNext())
        {
            Subject temp_s = (Subject) it.next();
            if (temp_s.n_slot_assigned < temp_s.n_slot)
            {
                Node child = new Node(daddy, temp_s);
                this.addChild(daddy, child);
            }
        }
    }

    public void addChild(Node daddy, Node child)
    {
        boolean added = false;
        if (daddy.hasChildren())
        {
            Collection<Node> daddy_child = daddy.figli();
            Subject child_ss = (Subject)child.value();
            Iterator it = daddy_child.iterator();
            while (it.hasNext())
            {
                Node temp_n = (Node) it.next();
                Subject temp_s = (Subject)temp_n.value();
                //// numero possibili assegnazioni / numero ore da assegnare
                double temp_val = (double)temp_s.dominio.size()/(double)(temp_s.n_slot - temp_s.n_slot_assigned);
                //// numero possibili assegnazioni / numero ore da assegnare
                double res_val = (double)child_ss.dominio.size()/(double)(child_ss.n_slot - child_ss.n_slot_assigned);
                //System.out.print(" (" +child_ss.dominio.size()+")/("+child_ss.n_slot+"-"+child_ss.n_slot_assigned+")="+res_val);
                //System.out.print(" (" +temp_s.dominio.size()+")/("+temp_s.n_slot+"-"+temp_s.n_slot_assigned+")="+temp_val);
                //System.out.println();
                if (temp_val <= res_val)
                {
                    //System.out.println(" - >  assign " + (child_ss) + " after " + temp_s);
                    daddy.insertChildren(child, daddy.getChildIndex(temp_n) ); //addChildren(child);
                    added = true;
                    break;
                }
            }
        }
        if (!added)
        {
            //System.out.println(" - >  assign " + ((Subject)child.value()) + " at the end");
            daddy.addChildren(child);
        }
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
            if (! this.goodSlot(temp_s, temp_d))
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

    public boolean goodSlot(Subject ss, Dispo dd)
    {
        boolean res = true;

        Iterator it = ss.assigned.iterator();
        while (it.hasNext())
        {
            Dispo temp_d = (Dispo) it.next();
            // verifica se gli oggetti sono diversi
            if (temp_d != dd && dd != null && temp_d.key != dd.key)
            {
                //verifica se sono nello stesso giorno
                if (temp_d.j.getNum() == dd.j.getNum())
                {
                    //verifica se il corso ammette piu' di due ore consecutive
                    if ( this.double_hour.valid(ss, dd) )
                    {
                        //verifica se le ore sono consecutive
                        if ( this.cont_hour.valid(temp_d, dd) )
                        {
                            //verifica che non ci siano gia' piu' di due ore in quel giorno
                            if ( this.day.valid(ss, temp_d.j) < 2 )
                            {
                                //se non ci sono => OK
                                res = true ;
                            }
                            else
                            {
                                res = false;
                                break;
                            }
                        }
                        else
                        {
                            res = false;
                            break;
                        }
                    }
                    else
                    {
                        res = false;
                        break;
                    }
                }
                else
                {
                    //verifica la distribuzione delle ore (eg: non sempre la stessa)
                    if ( this.not_last.valid(ss, dd) )
                    {
                        //TODO: deve diventare un soft constraint => peso
                        //verifica la distribuzione nei giorni (non giorni consecutivi)
                        if ( this.spread_day.valid(ss, dd.j)==0 )
                        {
                            //verifica che non ci siano gia' piu' di due ore in quel giorno
                            if (this.day.valid(ss, dd.j) < 2)
                            {
                                res = res && true;
                            }
                            else
                            {
                                res = false;
                                break;
                            }
                        }
                        else
                        {
                            res = false;
                            break;
                        }
                    }
                    else
                    {
                        res = false;
                        break;
                    }
                }
            }
            else
            {
                res = false;
                break;
            }
        }
        return res;
    }

    public boolean checkConflict(Dispo d1, Dispo d2)
    {
        
        // verifica se gli oggetti sono diversi
        if (d1 != d2 && d1 != null && d1.key != d2.key)
        {
            /**
            * STESSO GIORNO
            */
            //verifica se sono nello stesso giorno
            if (d1.j.getNum() == d2.j.getNum())
            {//verifica se il corso ammette piu' di due ore consecutive
                if ( this.double_hour.valid(d1.s, d1) )
                {
                    if ( this.cont_hour.valid(d1, d2) )
                    {
                        return true;
                    }
                }
            }
            /**
             * GIORNI ADIACENTI AD ORE DIVERSE
             */
            else
            {
                //verifica la distribuzione delle ore (eg: non sempre la stessa)
                if ( this.not_last.valid(d1.s, d2) )
                {
                    if ( this.spread_day.valid(d1.s, d2.j)==0 )
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean fwdChecking(Collection<Node> ss)
    {
        Iterator it = ss.iterator();
        while (it.hasNext())
        {
            Node temp_n = (Node) it.next();
            Subject temp_s = (Subject) temp_n.value();
            if (temp_s.dominio.size() < 1)
                return false;
        }
        return true;
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

                if (this.checkConflict(last_dd, to_jump) )
                {
                    return to_jump;
                }
            }
        }
        return this.template;
    }
}
