/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author five_stars
 */
public class Solver
{
    Dispo timetable[][] = new Dispo[6][5];
    List<Dispo> available_slot = null;

    public Solver()
    {
        FlexiTime hour_slot [] = new FlexiTime[5];
        FlexiTime h1 = new FlexiTime(8, 9, 1);
		FlexiTime h2 = new FlexiTime(9, 10, 2);
		FlexiTime h3 = new FlexiTime(10, 11, 3);
		FlexiTime h4 = new FlexiTime(11, 12, 4);
		FlexiTime h5 = new FlexiTime(12, 13, 5);
        hour_slot[0] = h1;
        hour_slot[1] = h2;
        hour_slot[2] = h3;
        hour_slot[3] = h4;
        hour_slot[4] = h5;

        Day day_slot [] = new Day[6];
   		Day d1 = new Day(1);
		Day d2 = new Day(2);
		Day d3 = new Day(3);
		Day d4 = new Day(4);
		Day d5 = new Day(5);
		Day d6 = new Day(6);
        day_slot[0] = d1;
        day_slot[1] = d2;
        day_slot[2] = d3;
        day_slot[3] = d4;
        day_slot[4] = d5;
        day_slot[5] = d6;

        int count = 1;
        available_slot = new LinkedList<Dispo>();
        for (int i = 0; i <= 5; i++)
        {
            for (int j = 0; j <= 4; j++)
            {
                //this.timetable[i][j] = new Dispo(day_slot[i], hour_slot[j]);
                available_slot.add(new Dispo(day_slot[i], hour_slot[j], count));
                count ++;
            }
        }
    }

    public Subject createSubj(String n, int s, boolean couple)
    {
        Subject ss = new Subject(n, s, couple);

        List<WeekDay> week = new LinkedList<WeekDay>();
        week.add(WeekDay.Monday);
        week.add(WeekDay.Tuestday);
        week.add(WeekDay.Wednesday);
        week.add(WeekDay.Thursday);
        week.add(WeekDay.Fryday);
        week.add(WeekDay.Saturday);

        List<Slot> day = new LinkedList<Slot>();
        day.add(new Slot(8, 9));
        day.add(new Slot(9, 10));
        day.add(new Slot(10, 11));
        day.add(new Slot(11, 12));
        day.add(new Slot(12, 13));


        Iterator<WeekDay> it = week.iterator();
        while(it.hasNext())
        {
            WeekDay wd = it.next();
            Iterator<Slot> it2 = day.iterator();
            while(it2.hasNext())
            {
                Slot temp = it2.next();
                temp.setDay(wd);
                ss.addDomain( temp );
            }
        }

        return ss;
    }

    public void visit(Node root)
    {
        Rules rl = new Rules();
        if (root.hasChildren())
        {
            Collection<Node> child = root.figli();
            Iterator<Node> it = child.iterator();
            while (it.hasNext())
            {
                Node nn = it.next();
                System.out.println(((Subject) nn.value()).name );
                rl.getSubjectSlot((Subject)nn.value(), timetable);
            }
        }
    }

    public void start()
    {
        //Node root = new Node(null, null);

        List<Subject> courses = new LinkedList<Subject>();
        courses.add(createSubj("Letteratura",4, true));
        courses.add(createSubj("Religione",1, false));
        courses.add(createSubj("Chimica",2, true));
        courses.add(createSubj("Filosofia",2, false));
        courses.add(createSubj("Storia",2, false));
        courses.add(createSubj("Inglese",3, false));
        courses.add(createSubj("Matematica",4, true));
        courses.add(createSubj("Informatica",3, true));
        courses.add(createSubj("Fisica",3, true));
        courses.add(createSubj("Biologia",2, false));
        courses.add(createSubj("Scienze della Terra",2, false));
        courses.add(createSubj("Educazione Fisica",2, true));

        Rules rl = new Rules();
        Subject first = rl.chooseFirst(courses);
        //rl.getSubjectSlot(first, timetable);
        //List<Subject> added = new LinkedList<Subject>();
        //added.add(first);
        Node root = new Node (null, first);
        DFS dfs = new DFS();
        List ssss = new LinkedList<Subject>();
        Collections.shuffle(this.available_slot);
        this.fillDomain(courses);
        boolean result = false;
        Dispo dd = null;
        try {
            //result = dfs.dfsVist(root, available_slot, courses, ssss, "", null);
            dd = dfs.dfsVist(root, available_slot, courses, ssss, "", new Dispo(new Day(7), new FlexiTime(0,1,0), 0) );
        } catch (InterruptedException ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n Result: ["+ dd +"]");
        dfs.printTT();//courses);
        /*Iterator it = courses.iterator();
        while (it.hasNext())
        {
            Subject s_temp = (Subject) it.next();
            if (!added.contains(s_temp))
            {
                root.addChildren( new Node(root, s_temp) );
            }
        }

        this.visit(root);

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 5; j++)
                if (timetable[i][j].s != null)
                    System.out.print(timetable[i][j].s.name + "\t");
                else
                    System.out.print("  ***  ");
            System.out.println(" ");
        }*/
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
                temp_s.dominio.add(temp_d);
            }
        }
    }
}
