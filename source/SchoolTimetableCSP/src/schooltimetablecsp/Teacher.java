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
public class Teacher {
    protected int id;
    protected String name;
    protected Day free_day;
    protected int tot_slot;
    protected List<Subject> modules;
    protected List<Dispo> teach_time;

    public Teacher(int i, String n, Day d)//, int s)
    {
        this.id = i;
        this.name = n;
        this.free_day = d;
//        this.tot_slot = s;
        this.modules = new LinkedList<Subject>();
        this.teach_time = new LinkedList<Dispo>();
    }

    public boolean checkConflict(Dispo dd)
    {
        //System.out.println(dd.j + " - " + this.free_day);
        if (dd.j.numDay != this.free_day.numDay)
        {
            Iterator<Dispo> it = this.teach_time.iterator();
            while (it.hasNext())
            {
                Dispo temp_d = (Dispo) it.next();
                if (temp_d.j == dd.j)
                {
                    if(temp_d.h.h_start == dd.h.h_start)
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            return true;
        }
        return false;
    }

    public int tot_hour()
    {
        int res = 0;
        Iterator it = this.modules.iterator();
        while (it.hasNext())
        {
            Subject temp_s = (Subject) it.next();
            res += temp_s.n_slot;
        }
        return res;
    }

    public int size_domain()
    {
        int res = 0;
        Iterator it = this.modules.iterator();
        while (it.hasNext())
        {
            Subject temp_s = (Subject) it.next();
            res += temp_s.dominio.size();
        }
        return res;
    }

    public void printTimeTable(List<Dispo> ddl)
    {
        String timetable [][] = new String[6][5];

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                timetable[j][i] = null;
            }
        }

        System.out.println("*********");
        Iterator it = this.teach_time.iterator();
        while ( it.hasNext() )
        {
            Dispo teach_d = (Dispo) it.next();
            if (timetable[teach_d.j.numDay-1][teach_d.h.h-1] != null)
            {
                System.out.println ("CONFLITTO: " + teach_d + "" + teach_d.cc + " " + teach_d.s + "     VS       " + timetable[teach_d.j.numDay-1][teach_d.h.h-1]);
            }
            else
            {
                timetable[teach_d.j.numDay-1][teach_d.h.h-1] = teach_d.s.name;
            }
        }

        for (int i = 0; i < 5; i++)
        {
            int len = 7;
            String empty = "";
            for (int j = 0; j < 6; j++)
            {
                if (timetable[j][i] != null)
                {
                    len = timetable[j][i].length();
                    System.out.print("[" + timetable[j][i] + "]    \t");
                }
                else
                {
                    if (empty.length() < 1)
                    {
                        for (int t = 0; t <= len; t++)
                            empty += " ";
                    }
                    System.out.print("[" + empty + "]    \t");
                }
            }
            System.out.println();
        }
        System.out.println("*********");
    }

    public String toString()
    {
		return(this.name);
	}
}
