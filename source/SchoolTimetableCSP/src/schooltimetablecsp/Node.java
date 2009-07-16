/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

import java.util.LinkedList;
import java.util.List;
import java.util.Collection;

public class Node
{
    private Object value;
    private Node father;
    private List<Node> childrens;

    public Node(Node father, Object valore)
    {
        this.father = father;
        this.value = valore;
        this.childrens = new LinkedList<Node>();
    }

    public Object value()
    {
        return value;
    }

    public void addChildren(Node youth)
    {
        if (childrens == null)
            childrens = new LinkedList();
        childrens.add(youth);
    }

    public void insertChildren(Node youth, int index)
    {
        if (childrens == null)
            childrens = new LinkedList();
        if (childrens.size() - 1 <= index)
            childrens.add(index, youth);
        else
            this.addChildren(youth);
    }

    public int getChildIndex(Node youth)
    {
        return childrens.indexOf(youth);
    }

    public Collection<Node> figli()
    {
        return childrens;
    }

    public boolean hasChildren()
    {
        if (childrens.size() == 0)
            return false;
        return true;
    }

}
