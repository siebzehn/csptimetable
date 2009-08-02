/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author five_stars
 */

public class Simulate
{
    public Simulate()
    {
        LeisureClass lc_1 = new LeisureClass(1, 1, 'A');
        LeisureClass lc_2 = new LeisureClass(2, 2, 'A');
        LeisureClass lc_3 = new LeisureClass(3, 3, 'A');
        LeisureClass lc_4 = new LeisureClass(4, 1, 'B');
        LeisureClass lc_5 = new LeisureClass(5, 2, 'B');
        LeisureClass lc_6 = new LeisureClass(6, 3, 'B');
        LeisureClass lc_7 = new LeisureClass(7, 1, 'C');
        LeisureClass lc_8 = new LeisureClass(8, 2, 'C');
        LeisureClass lc_9 = new LeisureClass(9, 3, 'C');
        ClassUI.all_class.add(lc_1);
        ClassUI.all_class.add(lc_2);
        ClassUI.all_class.add(lc_3);
        ClassUI.all_class.add(lc_4);
        ClassUI.all_class.add(lc_5);
        ClassUI.all_class.add(lc_6);
        ClassUI.all_class.add(lc_7);
        ClassUI.all_class.add(lc_8);
        ClassUI.all_class.add(lc_9);

        Day day1 = new Day(1);
        Day day2 = new Day(2);
        Day day3 = new Day(3);
        Day day4 = new Day(4);
        Day day5 = new Day(5);
        Day day6 = new Day(6);
        List<Day> days = new LinkedList<Day>();
        days.add(day1); days.add(day2); days.add(day3);
        days.add(day4); days.add(day5); days.add(day6);

        FlexiTime h1 = new FlexiTime(8, 9, 1);
		FlexiTime h2 = new FlexiTime(9, 10, 2);
		FlexiTime h3 = new FlexiTime(10, 11, 3);
		FlexiTime h4 = new FlexiTime(11, 12, 4);
		FlexiTime h5 = new FlexiTime(12, 13, 5);
        List<FlexiTime> hours = new LinkedList<FlexiTime>();
        hours.add(h1); hours.add(h2); hours.add(h3); hours.add(h4); hours.add(h5);

        this.generateTime(ClassUI.all_class, days, hours);

        Teacher t1 = new Teacher(1, "T1", new Day(1));
        Teacher t2 = new Teacher(2, "T2", new Day(2));
        Teacher t3 = new Teacher(3, "T3", new Day(3));
        Teacher t4 = new Teacher(4, "T4", new Day(4));
        Teacher t5 = new Teacher(5, "T5", new Day(5));
        Teacher t6 = new Teacher(6, "T6", new Day(6));
        Teacher t7 = new Teacher(7, "T7", new Day(1));
        Teacher t8 = new Teacher(8, "T8", new Day(4));
        Teacher t9 = new Teacher(9, "T9", new Day(5));
        TeacherUI.all_teacher.add(t1); 
        TeacherUI.all_teacher.add(t2);
        TeacherUI.all_teacher.add(t3);
        TeacherUI.all_teacher.add(t4);
        TeacherUI.all_teacher.add(t5);
        TeacherUI.all_teacher.add(t6);
        TeacherUI.all_teacher.add(t7);
        TeacherUI.all_teacher.add(t8);
        TeacherUI.all_teacher.add(t9);

        Subject s1 = new Subject(1, "Letteratura", 3, true, t1, lc_1);
        Subject s2 = new Subject(2, "Matematica", 4, true, t2, lc_1 );
        Subject s3 = new Subject(3, "Informatica", 3, true, t3, lc_1 );
        Subject s4 = new Subject(4, "Inglese", 3, false, t4, lc_1);
        Subject s5 = new Subject(5, "Fisica", 3, false, t5, lc_1);
        Subject s6 = new Subject(6, "Chimica", 3, false, t5, lc_1);
        Subject s7 = new Subject(7, "Biologia", 2, false, t6, lc_1);
        Subject s8 = new Subject(8, "Scienze della Terra", 2, false, t6, lc_1);
        Subject s9 = new Subject(9, "Religione", 1, false, t7, lc_1);
        Subject s10 = new Subject(10, "Ed.Fisica", 2, true, t8, lc_1);
        Subject s11 = new Subject(11, "Storia", 2, false, t9, lc_1);
        Subject s12 = new Subject(12, "Filosofia", 2, false, t9, lc_1);

        Subject s13 = new Subject(13, "Letteratura", 3, true, t1, lc_2);
        Subject s14 = new Subject(14, "Matematica", 4, true, t2, lc_2 );
        Subject s15 = new Subject(15, "Informatica", 3, true, t3, lc_2 );
        Subject s16 = new Subject(16, "Inglese", 3, false, t4, lc_2);
        Subject s17 = new Subject(17, "Fisica", 3, false, t5, lc_2);
        Subject s18 = new Subject(18, "Chimica", 3, false, t5, lc_2);
        Subject s19 = new Subject(19, "Biologia", 2, false, t6, lc_2);
        Subject s20 = new Subject(20, "Scienze della Terra", 2, false, t6, lc_2);
        Subject s21 = new Subject(21, "Religione", 1, false, t7, lc_2);
        Subject s22 = new Subject(22, "Ed.Fisica", 2, true, t8, lc_2);
        Subject s23 = new Subject(23, "Storia", 2, false, t9, lc_2);
        Subject s24 = new Subject(24, "Filosofia", 2, false, t9, lc_2);

        Subject s25 = new Subject(25, "Letteratura", 3, true, t1, lc_3);
        Subject s26 = new Subject(26, "Matematica", 4, true, t2, lc_3 );
        Subject s27 = new Subject(27, "Informatica", 3, true, t3, lc_3 );
        Subject s28 = new Subject(28, "Inglese", 3, false, t4, lc_3);
        Subject s29 = new Subject(29, "Fisica", 3, false, t5, lc_3);
        Subject s30 = new Subject(30, "Chimica", 3, false, t5, lc_3);
        Subject s31 = new Subject(31, "Biologia", 2, false, t6, lc_3);
        Subject s32 = new Subject(32, "Scienze della Terra", 2, false, t6, lc_3);
        Subject s33 = new Subject(33, "Religione", 1, false, t7, lc_3);
        Subject s34 = new Subject(34, "Ed.Fisica", 2, true, t8, lc_3);
        Subject s35 = new Subject(35, "Storia", 2, false, t9, lc_3);
        Subject s36 = new Subject(36, "Filosofia", 2, false, t9, lc_3);
        SubjectUI.all_subj.add(s1); SubjectUI.all_subj.add(s19);
        SubjectUI.all_subj.add(s2); SubjectUI.all_subj.add(s20);
        SubjectUI.all_subj.add(s3); SubjectUI.all_subj.add(s21);
        SubjectUI.all_subj.add(s4); SubjectUI.all_subj.add(s22);
        SubjectUI.all_subj.add(s5); SubjectUI.all_subj.add(s23);
        SubjectUI.all_subj.add(s6); SubjectUI.all_subj.add(s24);
        SubjectUI.all_subj.add(s7); SubjectUI.all_subj.add(s25);
        SubjectUI.all_subj.add(s8); SubjectUI.all_subj.add(s26);
        SubjectUI.all_subj.add(s9); SubjectUI.all_subj.add(s27);
        SubjectUI.all_subj.add(s10); SubjectUI.all_subj.add(s28);
        SubjectUI.all_subj.add(s11); SubjectUI.all_subj.add(s29);
        SubjectUI.all_subj.add(s12); SubjectUI.all_subj.add(s30);
        SubjectUI.all_subj.add(s13); SubjectUI.all_subj.add(s31);
        SubjectUI.all_subj.add(s14); SubjectUI.all_subj.add(s32);
        SubjectUI.all_subj.add(s15); SubjectUI.all_subj.add(s33);
        SubjectUI.all_subj.add(s16); SubjectUI.all_subj.add(s34);
        SubjectUI.all_subj.add(s17); SubjectUI.all_subj.add(s35);
        SubjectUI.all_subj.add(s18); SubjectUI.all_subj.add(s36);

        Teacher t1_b = new Teacher(10, "T1_b", new Day(1));
        Teacher t2_b = new Teacher(11, "T2_b", new Day(2));
        Teacher t3_b = new Teacher(12, "T3_b", new Day(3));
        Teacher t4_b = new Teacher(13, "T4_b", new Day(4));
        Teacher t5_b = new Teacher(14, "T5_b", new Day(5));
        Teacher t6_b = new Teacher(15, "T6_b", new Day(6));
        Teacher t9_b = new Teacher(18, "T9_b", new Day(5));
        TeacherUI.all_teacher.add(t1_b); TeacherUI.all_teacher.add(t5_b);
        TeacherUI.all_teacher.add(t2_b); TeacherUI.all_teacher.add(t6_b);
        TeacherUI.all_teacher.add(t3_b); TeacherUI.all_teacher.add(t9_b);
        TeacherUI.all_teacher.add(t4_b);

        Subject s1_b = new Subject(37, "Letteratura", 3, true, t1_b, lc_4);
        Subject s2_b = new Subject(38, "Matematica", 4, true, t2_b, lc_4);
        Subject s3_b = new Subject(39, "Informatica", 3, true, t3_b, lc_4);
        Subject s4_b = new Subject(40, "Inglese", 3, false, t4_b, lc_4);
        Subject s5_b = new Subject(41, "Fisica", 3, false, t5_b, lc_4);
        Subject s6_b = new Subject(42, "Chimica", 3, false, t5_b, lc_4);
        Subject s7_b = new Subject(43, "Biologia", 2, false, t6_b, lc_4);
        Subject s8_b = new Subject(44, "Scienze della Terra", 2, false, t6_b, lc_4);
        Subject s9_b = new Subject(45, "Religione", 1, false, t7, lc_4);
        Subject s10_b = new Subject(46, "Ed.Fisica", 2, true, t8, lc_4);
        Subject s11_b = new Subject(47, "Storia", 2, false, t9_b, lc_4);
        Subject s12_b = new Subject(48, "Filosofia", 2, false, t9_b, lc_4);

        Subject s13_b = new Subject(49, "Letteratura", 3, true, t1_b, lc_5);
        Subject s14_b = new Subject(50, "Matematica", 4, true, t2_b, lc_5);
        Subject s15_b = new Subject(51, "Informatica", 3, true, t3_b, lc_5);
        Subject s16_b = new Subject(52, "Inglese", 3, false, t4_b, lc_5);
        Subject s17_b = new Subject(53, "Fisica", 3, false, t5_b, lc_5);
        Subject s18_b = new Subject(54, "Chimica", 3, false, t5_b, lc_5);
        Subject s19_b = new Subject(55, "Biologia", 2, false, t6_b, lc_5);
        Subject s20_b = new Subject(56, "Scienze della Terra", 2, false, t6_b, lc_5);
        Subject s21_b = new Subject(57, "Religione", 1, false, t7, lc_5);
        Subject s22_b = new Subject(58, "Ed.Fisica", 2, true, t8, lc_5);
        Subject s23_b = new Subject(59, "Storia", 2, false, t9_b, lc_5);
        Subject s24_b = new Subject(60, "Filosofia", 2, false, t9_b, lc_5);

        Subject s25_b = new Subject(61, "Letteratura", 3, true, t1_b, lc_6);
        Subject s26_b = new Subject(62, "Matematica", 4, true, t2_b, lc_6);
        Subject s27_b = new Subject(63, "Informatica", 3, true, t3_b, lc_6);
        Subject s28_b = new Subject(64, "Inglese", 3, false, t4_b, lc_6);
        Subject s29_b = new Subject(65, "Fisica", 3, false, t5_b, lc_6);
        Subject s30_b = new Subject(66, "Chimica", 3, false, t5_b, lc_6);
        Subject s31_b = new Subject(67, "Biologia", 2, false, t6_b, lc_6);
        Subject s32_b = new Subject(68, "Scienze della Terra", 2, false, t6_b, lc_6);
        Subject s33_b = new Subject(69, "Religione", 1, false, t7, lc_6);
        Subject s34_b = new Subject(70, "Ed.Fisica", 2, true, t8, lc_6);
        Subject s35_b = new Subject(71, "Storia", 2, false, t9_b, lc_6);
        Subject s36_b = new Subject(72, "Filosofia", 2, false, t9_b, lc_6);
        SubjectUI.all_subj.add(s1_b); SubjectUI.all_subj.add(s19_b);
        SubjectUI.all_subj.add(s2_b); SubjectUI.all_subj.add(s20_b);
        SubjectUI.all_subj.add(s3_b); SubjectUI.all_subj.add(s21_b);
        SubjectUI.all_subj.add(s4_b); SubjectUI.all_subj.add(s22_b);
        SubjectUI.all_subj.add(s5_b); SubjectUI.all_subj.add(s23_b);
        SubjectUI.all_subj.add(s6_b); SubjectUI.all_subj.add(s24_b);
        SubjectUI.all_subj.add(s7_b); SubjectUI.all_subj.add(s25_b);
        SubjectUI.all_subj.add(s8_b); SubjectUI.all_subj.add(s26_b);
        SubjectUI.all_subj.add(s9_b); SubjectUI.all_subj.add(s27_b);
        SubjectUI.all_subj.add(s10_b); SubjectUI.all_subj.add(s28_b);
        SubjectUI.all_subj.add(s11_b); SubjectUI.all_subj.add(s29_b);
        SubjectUI.all_subj.add(s12_b); SubjectUI.all_subj.add(s30_b);
        SubjectUI.all_subj.add(s13_b); SubjectUI.all_subj.add(s31_b);
        SubjectUI.all_subj.add(s14_b); SubjectUI.all_subj.add(s32_b);
        SubjectUI.all_subj.add(s15_b); SubjectUI.all_subj.add(s33_b);
        SubjectUI.all_subj.add(s16_b); SubjectUI.all_subj.add(s34_b);
        SubjectUI.all_subj.add(s17_b); SubjectUI.all_subj.add(s35_b);
        SubjectUI.all_subj.add(s18_b); SubjectUI.all_subj.add(s36_b);


        Teacher t1_c = new Teacher(19, "T1_c", new Day(1));
        Teacher t2_c = new Teacher(20, "T2_c", new Day(2));
        Teacher t3_c = new Teacher(21, "T3_c", new Day(3));
        Teacher t4_c = new Teacher(22, "T4_c", new Day(4));
        Teacher t5_c = new Teacher(23, "T5_c", new Day(5));
        Teacher t6_c = new Teacher(24, "T6_c", new Day(6));
        Teacher t7_c = new Teacher(25, "T7_c", new Day(6));
        Teacher t8_c = new Teacher(26, "T8_c", new Day(6));
        Teacher t9_c = new Teacher(27, "T9_c", new Day(5));
        TeacherUI.all_teacher.add(t1_c); TeacherUI.all_teacher.add(t5_c);
        TeacherUI.all_teacher.add(t2_c); TeacherUI.all_teacher.add(t6_c);
        TeacherUI.all_teacher.add(t3_c); TeacherUI.all_teacher.add(t9_c);
        TeacherUI.all_teacher.add(t4_c);

        Subject s1_c = new Subject(73, "Letteratura", 3, true, t1_c, lc_7);
        Subject s2_c = new Subject(74, "Matematica", 4, true, t2_c, lc_7);
        Subject s3_c = new Subject(75, "Informatica", 3, true, t3_c, lc_7);
        Subject s4_c = new Subject(76, "Inglese", 3, false, t4_c, lc_7);
        Subject s5_c = new Subject(77, "Fisica", 3, false, t5_c, lc_7);
        Subject s6_c = new Subject(78, "Chimica", 3, false, t5_c, lc_7);
        Subject s7_c = new Subject(79, "Biologia", 2, false, t6_c, lc_7);
        Subject s8_c = new Subject(80, "Scienze della Terra", 2, false, t6_c, lc_7);
        Subject s9_c = new Subject(81, "Religione", 1, false, t7_c, lc_7);
        Subject s10_c = new Subject(82, "Ed.Fisica", 2, true, t8_c, lc_7);
        Subject s11_c = new Subject(83, "Storia", 2, false, t9_c, lc_7);
        Subject s12_c = new Subject(84, "Filosofia", 2, false, t9_c, lc_7);

        Subject s13_c = new Subject(85, "Letteratura", 3, true, t1_c, lc_8);
        Subject s14_c = new Subject(86, "Matematica", 4, true, t2_c, lc_8);
        Subject s15_c = new Subject(87, "Informatica", 3, true, t3_c, lc_8);
        Subject s16_c = new Subject(88, "Inglese", 3, false, t4_c, lc_8);
        Subject s17_c = new Subject(89, "Fisica", 3, false, t5_c, lc_8);
        Subject s18_c = new Subject(90, "Chimica", 3, false, t5_c, lc_8);
        Subject s19_c = new Subject(91, "Biologia", 2, false, t6_c, lc_8);
        Subject s20_c = new Subject(92, "Scienze della Terra", 2, false, t6_c, lc_8);
        Subject s21_c = new Subject(93, "Religione", 1, false, t7_c, lc_8);
        Subject s22_c = new Subject(94, "Ed.Fisica", 2, true, t8_c, lc_8);
        Subject s23_c = new Subject(95, "Storia", 2, false, t9_c, lc_8);
        Subject s24_c = new Subject(96, "Filosofia", 2, false, t9_c, lc_8);

        Subject s25_c = new Subject(97, "Letteratura", 3, true, t1_c, lc_9);
        Subject s26_c = new Subject(98, "Matematica", 4, true, t2_c, lc_9);
        Subject s27_c = new Subject(99, "Informatica", 3, true, t3_c, lc_9);
        Subject s28_c = new Subject(100, "Inglese", 3, false, t4_c, lc_9);
        Subject s29_c = new Subject(101, "Fisica", 3, false, t5_c, lc_9);
        Subject s30_c = new Subject(102, "Chimica", 3, false, t5_c, lc_9);
        Subject s31_c = new Subject(103, "Biologia", 2, false, t6_c, lc_9);
        Subject s32_c = new Subject(104, "Scienze della Terra", 2, false, t6_c, lc_9);
        Subject s33_c = new Subject(105, "Religione", 1, false, t7_c, lc_9);
        Subject s34_c = new Subject(106, "Ed.Fisica", 2, true, t8_c, lc_9);
        Subject s35_c = new Subject(107, "Storia", 2, false, t9_c, lc_9);
        Subject s36_c = new Subject(108, "Filosofia", 2, false, t9_c, lc_9);
        SubjectUI.all_subj.add(s1_c); SubjectUI.all_subj.add(s19_c);
        SubjectUI.all_subj.add(s2_c); SubjectUI.all_subj.add(s20_c);
        SubjectUI.all_subj.add(s3_c); SubjectUI.all_subj.add(s21_c);
        SubjectUI.all_subj.add(s4_c); SubjectUI.all_subj.add(s22_c);
        SubjectUI.all_subj.add(s5_c); SubjectUI.all_subj.add(s23_c);
        SubjectUI.all_subj.add(s6_c); SubjectUI.all_subj.add(s24_c);
        SubjectUI.all_subj.add(s7_c); SubjectUI.all_subj.add(s25_c);
        SubjectUI.all_subj.add(s8_c); SubjectUI.all_subj.add(s26_c);
        SubjectUI.all_subj.add(s9_c); SubjectUI.all_subj.add(s27_c);
        SubjectUI.all_subj.add(s10_c); SubjectUI.all_subj.add(s28_c);
        SubjectUI.all_subj.add(s11_c); SubjectUI.all_subj.add(s29_c);
        SubjectUI.all_subj.add(s12_c); SubjectUI.all_subj.add(s30_c);
        SubjectUI.all_subj.add(s13_c); SubjectUI.all_subj.add(s31_c);
        SubjectUI.all_subj.add(s14_c); SubjectUI.all_subj.add(s32_c);
        SubjectUI.all_subj.add(s15_c); SubjectUI.all_subj.add(s33_c);
        SubjectUI.all_subj.add(s16_c); SubjectUI.all_subj.add(s34_c);
        SubjectUI.all_subj.add(s17_c); SubjectUI.all_subj.add(s35_c);
        SubjectUI.all_subj.add(s18_c); SubjectUI.all_subj.add(s36_c);
    }

    public void generateTime(List<LeisureClass> lcl, List<Day> dl, List<FlexiTime> ftl)
    {
        int index = 0;
        Iterator it = lcl.iterator();
        while(it.hasNext())
        {
            LeisureClass lc = (LeisureClass)it.next();
            Iterator it2 = dl.iterator();
            while(it2.hasNext())
            {
                Day dd = (Day)it2.next();
                index = lc.generateSlots(dd, ftl, index);
            }
        }
    }
}