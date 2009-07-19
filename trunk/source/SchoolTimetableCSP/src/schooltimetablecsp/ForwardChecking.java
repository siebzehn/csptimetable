/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.Iterator;
import java.util.Collection;

/**
 *
 * @author five_stars
 */
public class ForwardChecking
{

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

}
