/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
/**
 *
 * @author five_stars
 */
public class Solver implements Runnable
{
    Dispo timetable[][] = new Dispo[6][5];
    List<Dispo> available_slot = null;
    public static long seconds = -1;
    public static long start = -1;
 
    public Solver()
    {
        this.available_slot = new LinkedList<Dispo>();
    }

    public void parti()
    {
        Iterator<LeisureClass> it_school = ClassUI.all_class.iterator();
        while(it_school.hasNext())
        {
            LeisureClass temp_class = (LeisureClass)it_school.next();
            this.available_slot.addAll(temp_class.to_assign);
        }

        this.fillDomain(SubjectUI.all_subj);

        Iterator <Teacher> it_prof = TeacherUI.all_teacher.iterator();
        while(it_prof.hasNext())
        {
            Teacher temp_teach = (Teacher) it_prof.next();
            if (temp_teach.tot_hour() > 25)
                System.out.println("No solution: " + temp_teach);
            else
                System.out.print(temp_teach + "["+ temp_teach.tot_hour() + "] - ");
        }

        System.out.println("");

        Subject first = Heuristic.chooseFirstByTeacher(TeacherUI.all_teacher);
        System.out.println(first);
        Node root = new Node (null, first);
        DeepSearch.ended = false;
        DeepSearch dfs = new DeepSearch( 0.5, 0.5, ClassUI.all_class.size() );
        Collections.shuffle(this.available_slot);
        Dispo template = new Dispo(new Day(7), new FlexiTime(0,1,0), 0, new LeisureClass(-1,-1,' ',-1));
        Dispo dd = template;
        Date start_d = new Date();

        double cost = 0;
        this.start = System.currentTimeMillis();
        System.out.println("corsi : " + SubjectUI.all_subj.size() + " classi : " + ClassUI.all_class.size() + " prof: " + TeacherUI.all_teacher.size() );
        dd = dfs.dfsVist(root, this.available_slot, SubjectUI.all_subj, "", template, ClassUI.all_class, cost );
        long end = System.currentTimeMillis();

        System.out.println("\n Result: ["+ dd +"] produced in [" + (((double)(end-this.start))/1000) + "] with cost: '" + dfs.sol_cost + "'");
        System.out.println(start_d + "  -  " + new Date());
        dfs.printTT();
        DeepSearch.ended = true;
        seconds = (end-this.start)/1000;
    }


    public void fillDomain(List<Subject> ss)
    {
        Iterator it = ss.iterator();
        while(it.hasNext())
        {
            Subject temp_s = (Subject) it.next();
            Iterator it2 = available_slot.iterator();
            while (it2.hasNext())
            {
                Dispo temp_d = (Dispo) it2.next();
                if (temp_s.classe.id == temp_d.cc.id)
                {
                    temp_s.dominio.add(temp_d);
                }
            }
        }
    }

    public void run()
    {
        this.parti();
    }

}
