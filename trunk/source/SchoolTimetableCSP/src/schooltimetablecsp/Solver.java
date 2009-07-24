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
public class Solver
{
    Dispo timetable[][] = new Dispo[6][5];
    List<Dispo> available_slot = null;

    Teacher t1 = new Teacher(1, "a", new Day(1)); // letteratura
    Teacher t2 = new Teacher(2, "b", new Day(2)); // matematica
    Teacher t3 = new Teacher(3, "c", new Day(3)); // filosofia - storia
    Teacher t4 = new Teacher(4, "d", new Day(6)); // chimica - fisica
    Teacher t5 = new Teacher(5, "e", new Day(3)); // inglese
    Teacher t6 = new Teacher(6, "f", new Day(4)); // biologia - scienze
    Teacher t7 = new Teacher(7, "g", new Day(5)); // religione
    Teacher t8 = new Teacher(8, "h", new Day(1)); // educazione fisica
    Teacher t9 = new Teacher(9, "i", new Day(6)); // informatica

    Teacher t11 = new Teacher(1, "a", new Day(1)); // letteratura
    Teacher t21 = new Teacher(2, "b", new Day(2)); // matematica
    Teacher t31 = new Teacher(3, "c", new Day(3)); // filosofia - storia
    Teacher t41 = new Teacher(4, "d", new Day(6)); // chimica - fisica
    Teacher t51 = new Teacher(5, "e", new Day(3)); // inglese
    Teacher t61 = new Teacher(6, "f", new Day(4)); // biologia - scienze
    Teacher t71 = new Teacher(7, "g", new Day(5)); // religione
    Teacher t81 = new Teacher(8, "h", new Day(1)); // educazione fisica
    Teacher t91 = new Teacher(9, "i", new Day(6)); // informatica

    Teacher t12 = new Teacher(1, "a", new Day(1)); // letteratura
    Teacher t22 = new Teacher(2, "b", new Day(2)); // matematica
    Teacher t32 = new Teacher(3, "c", new Day(3)); // filosofia - storia
    Teacher t42 = new Teacher(4, "d", new Day(6)); // chimica - fisica
    Teacher t52 = new Teacher(5, "e", new Day(3)); // inglese
    Teacher t62 = new Teacher(6, "f", new Day(4)); // biologia - scienze
    Teacher t72 = new Teacher(7, "g", new Day(5)); // religione
    Teacher t82 = new Teacher(8, "h", new Day(1)); // educazione fisica
    Teacher t92 = new Teacher(9, "i", new Day(6)); // informatica

    Teacher t13 = new Teacher(1, "a", new Day(1)); // letteratura
    Teacher t23 = new Teacher(2, "b", new Day(2)); // matematica
    Teacher t33 = new Teacher(3, "c", new Day(3)); // filosofia - storia
    Teacher t43 = new Teacher(4, "d", new Day(6)); // chimica - fisica
    Teacher t53 = new Teacher(5, "e", new Day(3)); // inglese
    Teacher t63 = new Teacher(6, "f", new Day(4)); // biologia - scienze
    Teacher t73 = new Teacher(7, "g", new Day(5)); // religione
    Teacher t83 = new Teacher(8, "h", new Day(1)); // educazione fisica
    Teacher t93 = new Teacher(9, "i", new Day(6)); // informatica

    public Solver()
    {
        available_slot = new LinkedList<Dispo>();
    }

    public Subject createSubj(int index, String n, int s, boolean couple, Teacher t, LeisureClass l)
    {
        Subject ss = new Subject(index, n, s, couple, t, l);
        return ss;
    }

    public void start()
    {
        LeisureClass prima_a = new LeisureClass(1, 1, 'A', 30);
        LeisureClass seconda_a = new LeisureClass(2, 2, 'A', 30);
        LeisureClass terza_a = new LeisureClass(3, 3, 'A', 30);
        LeisureClass quarta_a = new LeisureClass(4, 4, 'A', 30);
        LeisureClass quinta_a = new LeisureClass(5, 5, 'A', 30);

        LeisureClass prima_b = new LeisureClass(6, 1, 'B', 30);
        LeisureClass seconda_b = new LeisureClass(7, 2, 'B', 30);
        LeisureClass terza_b = new LeisureClass(8, 3, 'B', 30);
        LeisureClass quarta_b = new LeisureClass(9, 4, 'B', 30);
        LeisureClass quinta_b = new LeisureClass(10, 5, 'B', 30);

        LeisureClass prima_c = new LeisureClass(11, 1, 'C', 30);
        LeisureClass seconda_c = new LeisureClass(12, 2, 'C', 30);
        LeisureClass terza_c = new LeisureClass(13, 3, 'C', 30);
        LeisureClass quarta_c = new LeisureClass(14, 4, 'C', 30);
        LeisureClass quinta_c = new LeisureClass(15, 5, 'C', 30);

        LeisureClass prima_d = new LeisureClass(16, 1, 'D', 30);
        LeisureClass seconda_d = new LeisureClass(17, 2, 'D', 30);
        LeisureClass terza_d = new LeisureClass(18, 3, 'D', 30);
        LeisureClass quarta_d = new LeisureClass(19, 4, 'D', 30);
        LeisureClass quinta_d = new LeisureClass(20, 5, 'D', 30);

        List<Subject> courses = new LinkedList<Subject>();

        List<LeisureClass> scuola = new LinkedList<LeisureClass>();
        scuola.add(prima_a);
        scuola.add(seconda_a);
        scuola.add(terza_a);
//        scuola.add(quarta_a);
/*        scuola.add(quinta_a);
*/
        scuola.add(prima_b);
        scuola.add(seconda_b);
        scuola.add(terza_b);
//        scuola.add(quarta_b);
/*        scuola.add(quinta_b);
*/
        scuola.add(prima_c);
        scuola.add(seconda_c);
 //       scuola.add(terza_c);
/*        scuola.add(quarta_c);
        scuola.add(quinta_c);
*/
        scuola.add(prima_d);
        scuola.add(seconda_d);
   //     scuola.add(terza_d);
       /* scuola.add(quarta_d);
        scuola.add(quinta_d);
*/
        Iterator it = scuola.iterator();

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

        List <Dispo> to_print = new LinkedList<Dispo>();

        while(it.hasNext())
        {
            LeisureClass temp = (LeisureClass) it.next();
            if (temp.section == 'A')
            {
                System.out.println(temp.section);
                this.addCorsi(temp);
            }
            else
            {
                if (temp.section == 'B')
                {
                    System.out.println(temp.section);
                    this.addCorsi2(temp);
                }
                else
                {
                    if (temp.section == 'C')
                    {
                        System.out.println(temp.section);
                        this.addCorsi3(temp);
                    }
                    else
                    {
                        System.out.println(temp.section);
                        this.addCorsi4(temp);
                    }
                }
            }

            courses.addAll(temp.modules);

            int count = 1;
            for (int i = 0; i <= 5/*1*/; i++)
            {
                for (int j = 0; j <= 4; j++)
                {
                    Dispo temp_d = new Dispo(day_slot[i], hour_slot[j], count, temp);
                    available_slot.add(temp_d);
                    to_print.add(temp_d);
                    //System.out.println(temp_d + " " + temp);
                    count ++;
                }
            }
        }

        Subject first = Heuristic.chooseFirst(courses);
        Node root = new Node (null, first);
        DeepSearch dfs = new DeepSearch( 0.5, 0.5, 20);
        List ssss = new LinkedList<Subject>();
        Collections.shuffle(this.available_slot);
        this.fillDomain(courses);
        Dispo template = new Dispo(new Day(7), new FlexiTime(0,1,0), 0, new LeisureClass(-1,-1,' ',-1));
        Dispo dd = template;
        Date start_d = new Date();

        long start = System.currentTimeMillis();
        dd = dfs.dfsVist(root, available_slot, courses, "", template, scuola );
        long end = System.currentTimeMillis();

        System.out.println("\n Result: ["+ dd +"] produced in [" + (((double)(end-start))/1000) + "]");
        System.out.println(start_d + "  -  " + new Date());
        dfs.printTT();

        System.out.println("         =====================\n");
        t1.printTimeTable(to_print);
        t2.printTimeTable(to_print);
        t3.printTimeTable(to_print);
        t4.printTimeTable(to_print);
        t5.printTimeTable(to_print);
        t6.printTimeTable(to_print);
        t7.printTimeTable(to_print);
        t8.printTimeTable(to_print);
        t9.printTimeTable(to_print);

        t11.printTimeTable(to_print);
        t21.printTimeTable(to_print);
        t31.printTimeTable(to_print);
        t41.printTimeTable(to_print);
        t51.printTimeTable(to_print);
        t61.printTimeTable(to_print);
        t71.printTimeTable(to_print);
        t81.printTimeTable(to_print);
        t91.printTimeTable(to_print);

        t12.printTimeTable(to_print);
        t22.printTimeTable(to_print);
        t32.printTimeTable(to_print);
        t42.printTimeTable(to_print);
        t52.printTimeTable(to_print);
        t62.printTimeTable(to_print);
        t72.printTimeTable(to_print);
        t82.printTimeTable(to_print);
        t92.printTimeTable(to_print);

        t13.printTimeTable(to_print);
        t23.printTimeTable(to_print);
        t33.printTimeTable(to_print);
        t43.printTimeTable(to_print);
        t53.printTimeTable(to_print);
        t63.printTimeTable(to_print);
        t73.printTimeTable(to_print);
        t83.printTimeTable(to_print);
        t93.printTimeTable(to_print);
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
                    temp_s.dominio.add(temp_d);
            }
        }
    }

    public void addCorsi(LeisureClass classe)
    {
        classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t1, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t7, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t41, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t31, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t31, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t5, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t2, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t9, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t41, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t6, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t6, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t81, classe));

      /*  classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t11, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t71, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t4, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t3, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t3, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t51, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t21, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t91, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t4, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t61, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t61, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t8, classe));
    */}

    public void addCorsi2(LeisureClass classe)
    {
    /*    classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t1, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t7, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t41, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t31, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t31, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t5, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t2, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t9, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t41, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t6, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t6, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t81, classe));
*/
        classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t11, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t71, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t4, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t3, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t3, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t51, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t21, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t91, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t4, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t61, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t61, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t8, classe));
    }

    public void addCorsi3(LeisureClass classe)
    {
        classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t12, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t72, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t43, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t33, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t33, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t52, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t22, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t92, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t42, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t62, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t62, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t83, classe));
/*
        classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t11, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t71, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t4, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t3, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t3, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t51, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t21, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t91, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t4, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t61, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t61, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t8, classe));
 */   }
    
    public void addCorsi4(LeisureClass classe)
    {
    /*    classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t1, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t7, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t41, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t31, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t31, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t5, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t2, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t9, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t41, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t6, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t6, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t81, classe));
*/
        classe.modules.add(createSubj(1+(12*classe.id), "Letteratura",4, true, t13, classe));
        classe.modules.add(createSubj(2+(12*classe.id), "Religione",1, false, t73, classe));
        classe.modules.add(createSubj(3+(12*classe.id), "Chimica",2, true, t42, classe));
        classe.modules.add(createSubj(4+(12*classe.id), "Filosofia",2, false, t32, classe));
        classe.modules.add(createSubj(5+(12*classe.id), "Storia",2, false, t32, classe));
        classe.modules.add(createSubj(6+(12*classe.id), "Inglese",3, false, t53, classe));
        classe.modules.add(createSubj(7+(12*classe.id), "Matematica",4, true, t23, classe));
        classe.modules.add(createSubj(8+(12*classe.id), "Informatica",3, true, t93, classe));
        classe.modules.add(createSubj(9+(12*classe.id), "Fisica",3, true, t42, classe));
        classe.modules.add(createSubj(10+(12*classe.id), "Biologia",2, false, t63, classe));
        classe.modules.add(createSubj(11+(12*classe.id), "Scienze della Terra",2, false, t63, classe));
        classe.modules.add(createSubj(12+(12*classe.id), "Educazione Fisica",2, true, t82, classe));
    }

}
