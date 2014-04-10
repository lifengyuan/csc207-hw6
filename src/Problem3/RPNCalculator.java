package Problem3;

import java.io.PrintWriter;

import Problem3.ArrayBasedStack;
import Problem3.ArrayBasedStackIterator;

/**
 * @author Fengyuan Li
 */

public class RPNCalculator
{

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * An ArrayBasedStack
   */
  ArrayBasedStack<Double> RPNStack;

  /**
   * Size of the ArrayBasedStack
   */
  int size;

  /**
   * A string used to compare input with possible operands
   */
  String checker = "+/*nxa";

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * RPNCalc creates a new RPN calculator with size size
   * 
   * @param size
   * @throws Exception
   */
  public RPNCalculator (int size) throws Exception
  {
    this.size = size;
    this.RPNStack = new ArrayBasedStack<Double> (this.size);
  } // RPNCalculator(int)

  // +----------+-------------------------------------------------
  // | Methods  |
  // +----------+

  /**
   * Takes a string expression of an RPN calculator entry and parses through the
   * line, pushing to and popping from the stack as necessary and evaluating
   * expressions as they appear.
   */
  public boolean
    evaluate (String str, PrintWriter pen)
      throws Exception
  {
    int len = str.length ();
    // An integer used to keep track of position in the string
    int i = 0;
    // Note: We decided that, if it encounters incorrect or improperly
    // formatted input, the calculator should give the user a warning and
    // continue running instead of throwing an exception. Hence the calls to
    // isEmpty when an 's' or 'p' is found (even though peek already checks
    // if the stack is empty). We didn't want to change peek so that the
    // underlying stack could be more easily adapted to other uses.
    while (i < len && size >= 0)
      {
        char current = str.charAt (i);
        if (current == 'p')
          {
            if (RPNStack.isEmpty ())
              {
                pen.println ("Nothing on the stack!");
              }
            else
              {
                pen.println (RPNStack.peek ());
              } // else
          }
        else if (current == 's')
          {
            if (RPNStack.isEmpty ())
              {
                pen.println ("Nothing on the stack!");
              }
            else
              {
                // We used an iterator to look at the whole stack without
                // altering it as pop would have.
                ArrayBasedStackIterator<Double> it = new ArrayBasedStackIterator<Double> (RPNStack);
                while (it.hasNext ())
                  {
                    pen.println (it.next ());
                  } // while
              } // else
          }
        else if (current == 'c')
          {
            clear ();
          }
        else if (current == 'q')
          {
            return false;
          }
        else if (checker.indexOf (current) != -1)
          {
            performOperation (i, current, pen);
          } // else if
            // These next two statements are a hasty fix for a problem we
            // noticed just before the assignment was due: we had neglected to
            // include support for negative numbers. Oops!
        else if ((current == '-') && (i == len - 1))
          {
            subtract ();
          }
        else if ((current == '-')
                 && ((str.charAt (i + 1) == '\n') || str.charAt (i + 1) == ' '))
          {
            subtract ();
          }
        else if (current != ' ')
          {
            int index = i;
            while (i < len && str.charAt (i) != ' ' && str.charAt (i) != '\n')
              {
                i++;
              } // while
                // if input is a number, put it on the stack
            try
              {
                RPNStack.push (Double.valueOf (str.substring (index, i)));
              }
            catch (NumberFormatException e)
              {
                // otherwise, warn user, ignore input, and continue
                pen.println ("Invalid entry!");
              } // try/catch
          } // if/else
        i++;
      } // while
    return true;
  } // evaluate(str, PrintWriter)

  /**
   * performOperation performs the given operation on the stack
   * 
   * @param int i
   * @param char c
   * @param PrintWriter
   *          pen
   * @pre i is 0 <= int < size, c is a character, pen is a PrintWriter
   * @throws Exception
   */
  public void
    performOperation (int i, char c, PrintWriter pen)
      throws Exception
  {
    ArrayBasedStackIterator<Double> it = new ArrayBasedStackIterator<Double> (RPNStack);
    // These two if statements make sure there are at least two numbers on
    // the stack--the minimum number required to perform any of the
    // operations we defined
    if (it.hasNext ())
      {
        it.next ();
        if (it.hasNext ())
          {
            if (c == '+')
              {
                add ();
              }
            else if (c == '*')
              {
                multiply ();
              }
            else if (c == '/')
              {
                divide ();
              }
            else if (c == 'n')
              {
                min ();
              }
            else if (c == 'x')
              {
                max ();
              }
            else if (c == 'a')
              {
                avg ();
              } // else if
            return;
          } // if
      } // if
    pen.println ("Invalid entry! Insufficient numbers on the stack.");
  } // performOperation(int, char, PrintWriter)

  /**
   * add pops the top two items off the stack, adds them, then pushes the result
   * back onto the stack
   * 
   * @throws Exception
   */
  public void
    add ()
      throws Exception
  {
    this.RPNStack.push (this.RPNStack.pop () + this.RPNStack.pop ());
  } // add()

  /**
   * subtract pops the top two items off the stack, subtracts them, and pushes
   * the result back onto the stack
   * 
   * @throws Exception
   */
  public void
    subtract ()
      throws Exception
  {
    this.RPNStack.push (-1 * this.RPNStack.pop () + this.RPNStack.pop ());
  } // subtract()

  /**
   * multiply pops the top two items off the stack, multiplies them, and pushes
   * the result back onto the stack
   * 
   * @throws Exception
   */
  public void
    multiply ()
      throws Exception
  {
    this.RPNStack.push (this.RPNStack.pop () * this.RPNStack.pop ());
  } // multiply()

  /**
   * divide pops the top two items off the stack, divides the second by the
   * first, and pushes the result back onto the stack
   * 
   * @throws Exception
   */
  public void
    divide ()
      throws Exception
  {
    this.RPNStack.push ((1 / this.RPNStack.pop ()) * this.RPNStack.pop ());
  } // divide()

  /**
   * min pops the top two items off the stack, finds the smaller of the two, and
   * pushes the result back onto the stack
   * 
   * @throws Exception
   */
  public void
    min ()
      throws Exception
  {
    this.RPNStack.push (Math.min (this.RPNStack.pop (), this.RPNStack.pop ()));
  } // min()

  /**
   * max pops the top two items off the stack, finds the greater of the two, and
   * pushes the result back onto the stack
   * 
   * @throws Exception
   */
  public void
    max ()
      throws Exception
  {
    this.RPNStack.push (Math.max (this.RPNStack.pop (), this.RPNStack.pop ()));
  } // min()

  /**
   * average pops the top two items off the stack, finds their average, and
   * pushes the result back onto the stack
   * 
   * @throws Exception
   */
  public void
    avg ()
      throws Exception
  {
    this.RPNStack.push ((this.RPNStack.pop () / 2) + (this.RPNStack.pop () / 2));
  } // avg()

  /**
   * clear clears the stack of all values
   * 
   * @throws Exception
   */
  public void
    clear ()
      throws Exception
  {
    while (!this.RPNStack.isEmpty ())
      {
        RPNStack.pop ();
      } // while
  } // clear()
} // RPNCalc