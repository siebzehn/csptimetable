/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

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

    public Subject createSubj(int index, String n, int s, boolean couple)
    {
        Subject ss = new Subject(index, n, s, couple);
        return ss;
    }

    public void start()
    {
        List<Subject> courses = new LinkedList<Subject>();
        courses.add(createSubj(1, "Letteratura",4, true));
        courses.add(createSubj(2, "Religione",1, false));
        courses.add(createSubj(3, "Chimica",2, true));
        courses.add(createSubj(4, "Filosofia",2, false));
        courses.add(createSubj(5, "Storia",2, false));
        courses.add(createSubj(6, "Inglese",3, false));
        courses.add(createSubj(7, "Matematica",4, true));
        courses.add(createSubj(8, "Informatica",3, true));
        courses.add(createSubj(9, "Fisica",3, true));
        courses.add(createSubj(10, "Biologia",2, false));
        courses.add(createSubj(11, "Scienze della Terra",2, false));
        courses.add(createSubj(12, "Educazione Fisica",2, true));

        Subject first = Heuristic.chooseFirst(courses);
        Node root = new Node (null, first);
        DFS dfs = new DFS(0.5, 0.5);
        List ssss = new LinkedList<Subject>();
        Collections.shuffle(this.available_slot);
        this.fillDomain(courses);
        Dispo dd = null;
        try {
            dd = dfs.dfsVist(root, available_slot, courses, ssss, "", new Dispo(new Day(7), new FlexiTime(0,1,0), 0) );
        } catch (InterruptedException ex) {
            Logger.getLogger(Solver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n Result: ["+ dd +"]");
        dfs.printTT();
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
