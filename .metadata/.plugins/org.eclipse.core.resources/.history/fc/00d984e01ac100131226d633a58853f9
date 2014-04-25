import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An incorrect implementation of iterators that filter elements.
 *
 * @author Anonymous Student
 */
public class BadFilteredIterator<T>
    implements Iterator<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The iterator we're filtering.
   */
  Iterator<T> base;

  /**
   * The predicate used to do the filtering.
   */
  Predicate<? super T> pred;

  /** 
   * The next value we should return.
   */
  T value;

  /**
   * A status related to hasNext.
   */
  boolean status;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a version of base which returns only the values for which pred holds.
   */
  public BadFilteredIterator(Iterator<T> base, Predicate<? super T> pred)
  {
    this.base = base;
    this.pred = pred;
  } // BadFilteredIterator(Iterator<T>, Predicate<? super T>)

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Determine if any elements remain for which pred holds.
   */
  public boolean hasNext()
  {
    while (base.hasNext())
      {
        value = base.next();
        if (pred.test(value))
          {
            status = true;
            return status;
          }// if
      }// while
    return false;
  } // hasNext()

  /**
   * Get the next element for which pred holds.
   * 
   * @throws NoSuchElementException
   *           when there are no more elements for which pred holds.
   */
  public T next()
    throws NoSuchElementException
  {
    if (!base.hasNext() && status == false)
      throw new NoSuchElementException();
    else
      return value;
  } // next()

  /**
   * Remove the element most recently returned by next.
   * 
   * @throws IllegalStateException
   *           If the next method has not yet been called, or the remove method
   *           has already been called after the last call to the next method,
   *           or if the hasNext method has been called after the last call to
   *           the next method.
   */
  public void remove()
    throws IllegalStateException
  {
    try
      {
        base.remove();
      } // try
    catch (IllegalStateException e)
      {
        throw new IllegalStateException();
      }// catch(IllegalStateException e)
  } // remove
} // class BadFilteredIterator<T>
