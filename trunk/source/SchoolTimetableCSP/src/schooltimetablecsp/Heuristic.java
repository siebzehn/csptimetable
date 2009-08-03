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
public class Heuristic
{

    SpreadConstraint spread_day;
    DayConstraint day;
    LastHourConstraint not_last;
    DoubleHourConstraint double_hour;
    ConsecutiveHourConstraint cont_hour;
    AdjoiningTeacherConstraint happy_teacher;

    public Heuristic(double sc, double lhc)
    {
        this.spread_day    = new SpreadConstraint(sc);
        this.day           = new DayConstraint();
        this.not_last      = new LastHourConstraint(lhc);
        this.double_hour   = new DoubleHourConstraint();
        this.cont_hour     = new ConsecutiveHourConstraint();
        this.happy_teacher = new AdjoiningTeacherConstraint(0.3);
    }
    
    public Dispo assignSlot(Subject ss, List<Dispo> tried)
    {
        //scorri la lista delle slot e verifica se e' compatibile con quelle gia' assegnata a ss
        Iterator it = ss.dominio.iterator();
        while (it.hasNext())
        {
            Dispo dd = (Dispo)it.next();
            if (dd.cc == ss.classe)
            {
                double costa = this.goodSlot(ss,dd);
                if (dd.s == null && costa >= 0 && !tried.contains(dd))
                {
                    dd.added_cost = costa;
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
        }
        return null;
    }

    public Subject generateChild(Node daddy, List<Subject> ss)
    {
        Subject best = null;
        Teacher best_t = null;
        Iterator it = ss.iterator();
        if (it.hasNext())
        {
            best = (Subject) it.next();
            best_t = (Teacher) best.insegnante;
            while (it.hasNext())
            {
                Subject temp_s = (Subject) it.next();

                if ( (best_t.size_domain() >= temp_s.insegnante.size_domain()) && (temp_s.n_slot_assigned < temp_s.n_slot))
                {
                    double temp_val = (double)(temp_s.n_slot - temp_s.n_slot_assigned)/(double)temp_s.dominio.size();
                    double res_val = (double)(best.n_slot - best.n_slot_assigned)/(double)best.dominio.size();
                 //   System.out.print(temp_s + "["+temp_val+"] ");
                    if (temp_val <= res_val)
                    {
                        best = temp_s;
                        best_t = temp_s.insegnante;
                    }
                }
            }
        }
        //System.out.println();
        return best;
    }

    public double goodSlot(Subject ss, Dispo dd)
    {
        //boolean res = true;
        double res = 0;
        if ( !ss.insegnante.checkConflict(dd) )
        {
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
                                    res = this.not_last.valid(ss, dd, "") + this.spread_day.valid(ss, dd.j, ""); //this.spread_day.valid(ss, dd.j, "") +res && true ;
                                }
                                else
                                {
                                    res = -1;//false;
                                    break;
                                }
                            }
                            else
                            {
                                res = -1;//false;
                                break;
                            }
                        }
                        else
                        {
                            res = -1;//false;
                            break;
                        }
                    }
                    else
                    {

                        //verifica la distribuzione delle ore (eg: non sempre la stessa)
                        /*if ( this.not_last.valid(ss, dd) )
                        {*/
                            //TODO: deve diventare un soft constraint => peso
                            //verifica la distribuzione nei giorni (non giorni consecutivi)
                            /*if ( this.spread_day.valid(ss, dd.j)==0 )
                            {*/
                                //verifica che non ci siano gia' piu' di due ore in quel giorno
                                if (this.day.valid(ss, dd.j) < 2)
                                {
                                    if (this.happy_teacher.valid( ss, dd) ) //.j) == 0 )
                                    {
                                        res = this.not_last.valid(ss, dd, "") + this.spread_day.valid(ss, dd.j, "") ; //res = res && true;
                                    }
                                    else
                                    {
                                        res = -1;
                                        break;
                                    }
                                }
                                else
                                {
                                    res = -1;//false;
                                    break;
                                }
                            /*}
                            else
                            {
                                res = -1;//false;
                                break;
                            }*/
                        /*}
                        else
                        {
                            res = -1;//false;
                            break;
                        }*/
                    }
                }
                else
                {
                    res = -1;//false;
                    break;
                }
            
            }
        }
        else
        {
            res = -1;//false;
        }
        return res;
    }

    public boolean checkConflict(Dispo d1, Dispo d2)
    {
        // verifica se gli oggetti sono diversi
        if (d1 != d2 && d1 != null && d1.key != d2.key)
        {
            if (d1.cc.id == d2.cc.id)
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
            else
            {
                if (d1.s.insegnante.id == d2.s.insegnante.id)
                {
                    if (d1.j.getNum() == d2.j.getNum() && d1.h == d1.h)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static public Subject chooseFirst(List<Subject> subs)
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


    static public Subject chooseFirstByTeacher(List<Teacher> prof)
    {
        Subject res = null;
        Iterator it_prof = prof.iterator();

        int pre_domain = -1;
        //double pre_prof = -1;
        while(it_prof.hasNext())
        {
            Teacher temp_t = (Teacher) it_prof.next();
            int tot_hour = temp_t.tot_hour();
            int tot_domain = temp_t.size_domain();
            //double res_prof = ((double)tot_hour)/((double)tot_domain);

            if ( tot_domain > pre_domain ) //res_prof > pre_prof )
            {
                pre_domain = tot_domain;
                //pre_prof = res_prof;

                Iterator it = temp_t.modules.iterator();
                Subject temp_res = null;
                while (it.hasNext())
                {
                    Subject temp_s = (Subject) it.next();
                    if (temp_res != null)
                    {
                        //scelgo quello con il dominio piu' piccolo
                        if (temp_s.dominio.size() < temp_res.dominio.size())
                            temp_res = temp_s;
                        else
                            //se sono uguali => scelgo quello con piu' ore
                            if (temp_s.dominio.size() == temp_res.dominio.size())
                            {
                                double temp_val = ((double)temp_s.dominio.size())/((double)temp_s.n_slot);
                                double temp_res_val = ((double)temp_res.dominio.size())/((double)temp_res.n_slot);
                                if (temp_val < temp_res_val)
                                {
                                    temp_res = temp_s;
                                }
                            }
                    }
                    else
                    {
                        temp_res = temp_s;
                    }

                }
                if (temp_res!= null)
                   res = temp_res;
            }
        }
        return res;
    }
}
