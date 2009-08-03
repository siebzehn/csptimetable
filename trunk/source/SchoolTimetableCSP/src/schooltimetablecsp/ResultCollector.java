/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author five_stars
 */
public class ResultCollector implements Runnable
{ 
    public ResultCollector()
    {
    }

    public void run()
    {
        while(!DeepSearch.ended)
        {
            SchoolTimeTableUI.classText.setText(Integer.toString(ClassUI.all_class.size()));
            SchoolTimeTableUI.teacherText.setText(Integer.toString(TeacherUI.all_teacher.size()));
            SchoolTimeTableUI.subjectText.setText(Integer.toString(DeepSearch.subject_remaining));
            SchoolTimeTableUI.slotText.setText(Integer.toString(DeepSearch.slot_remainig));
            SchoolTimeTableUI.costText.setText(Double.toString(DeepSearch.sol_cost));
            if (Solver.start > 0)
                SchoolTimeTableUI.timeText.setText(Long.toString((System.currentTimeMillis()-Solver.start)/1000));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ResultCollector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Iterator it = ClassUI.all_class.iterator();
        while(it.hasNext())
        {
            LeisureClass temp_lc = (LeisureClass) it.next();
            SchoolTimeTableUI.addClassTab(temp_lc);
        }
        SchoolTimeTableUI.jButton1.setEnabled(false);
        SchoolTimeTableUI.jButton3.setEnabled(false);
        SchoolTimeTableUI.jButton4.setEnabled(false);

        SchoolTimeTableUI.subjectText.setText(Integer.toString(0));
        SchoolTimeTableUI.slotText.setText(Integer.toString(0));

        SchoolTimeTableUI.costText.setText(Double.toString(DeepSearch.sol_cost));
        SchoolTimeTableUI.timeText.setText(Long.toString(Solver.seconds));
    }
}
