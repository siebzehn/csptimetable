/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package schooltimetablecsp;

enum WeekDay
{
    Monday,
    Tuestday,
    Wednesday,
    Thursday,
    Fryday,
    Saturday
}
/**
 *
 * @author five_stars
 */
public class Slot
{
    public WeekDay day;
    public int start;
    public int end;

    public Slot (int s, int e, WeekDay d)
    {
        this.start = s;
        this.end   = e;
        this.day   = d;
    }

    public Slot (int s, int e)
    {
        this.start = s;
        this.end   = e;
    }

    public void setDay(WeekDay d)
    {
        this.day = d;
    }

    public int durata()
    {
        return this.end - this.start;
    }
}
