/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

/**
 *
 * @author five_stars
 */
public class DoubleHourConstraint extends Constraint{

    @Override
    public boolean valid(Subject ss, Dispo dd)
    {
        return ss.couple;
    }

    @Override
    public int valid(Subject ss, Day dd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean valid(Dispo d1, Dispo d2)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
