package Problem2;

import java.io.PrintWriter;

/**
 * MatchParentheses: matching symbol: such as, parentheses, square brackets,
 * curly braces, angle brackets, and matched single quotation marks
 * 
 * @author Fengyuan Li
 * 
 */
public class MatchParentheses
{
  // Take a string as input and print out a picture that shows the nesting

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  // Make a map of opening and closing parenthesis.
  final char[] openParens = { '{', '(', '[', '<' }; // Set of open parentheses
  final char[] closeParens = { '}', ')', ']', '>' }; // Set of close parentheses

  final String unmatchedWarning = " <- UNMATCHED"; // For unmatched parens.

  final PrintWriter pen = new PrintWriter (System.out, true);

  int inputSize; // Size of the input given

  String input;
  String output;

  ArrayBasedStack<Group> working; // Working memory stack

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  public MatchParentheses (String input) throws Exception
  {
    this.input = input;
    this.inputSize = input.length ();
    this.output = input + "\n"; // Starts with the input, on its own line.
    this.working = new ArrayBasedStack<Group> (this.inputSize);

    matchParenthesis (); // Perform the main operations.

  } // MatchParentheses (String)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * matchParenthesis, takes the string input and breaks it into groups based on
   * the enclosing parentheses
   * 
   * @throws Exception
   * @pre
   */
  public void
    matchParenthesis ()
      throws Exception
  {
    for (int i = 0; i < this.inputSize; i++)
      {
        char charc = this.input.charAt (i);
        if (isOpenParen (charc))
          {
            this.working.put (new Group (charc, i)); // add to stack.
          } // if
        else if (isCloseParen (charc))
          {
            // Take last group in from the working stack
            if (!this.working.isEmpty ())
              {
                Group current = this.working.get ();

                if (closeParens[charIndexOf (this.openParens, current.braceType)] == charc)
                  {
                    current.closeBrace (i); // If valid, close that group
                    printGroup (current, i); // print it out
                  } // if
                else
                  {
                    // Make some kind of good error message from data.
                    printGroup (current, i); // print output up until this point
                    reportIllegalClose (charc, i); // print warning
                  } // else
              } // if
            else
              {
                // The stack was empty, but received a closing parenthesis.
                printUnmatchedClose (charc, i);
              } // else
          } // else if
      } // for

    // print remaining (unclosed) items
    if (!working.isEmpty ())
      {
        printUnmatchedGroups ();
      } // if
  } // matchParengthesis()

  // +---------+-----------------------------------------
  // | Methods |
  // +---------+
  /*
   * Functions to query parentheses
   */
  /**
   * getClosingParen, returns the corresponding closing paren, given an opening
   * one.
   * 
   * @param openParen
   * @return a closing parenthesis for an opening one. E.g. ']' when given '['.
   */
  public char
    getClosingParen (char openParen)
  {
    return closeParens[charIndexOf (openParens, openParen)];
  }

  /**
   * isOpenParen, returns true if a given character is in the set of known open
   * parentheses
   */

  public boolean
    isOpenParen (char character)
  {
    return (charIndexOf (this.openParens, character) >= 0);
  } // isOpenParen(char)

  /**
   * isCloseParen, returns true if a given character is in the set of known
   * close parentheses
   */
  public boolean
    isCloseParen (char character)
  {
    return (charIndexOf (this.closeParens, character) >= 0);
  } // isCloseParen(char)

  /*
   * Auxillary functions
   */
  /**
   * charIndexOf, returns the index of a character in an array, -1 if not found
   */
  public int
    charIndexOf (char[] haystack, char needle)
  {
    for (int i = 0; i < haystack.length; i++)
      {
        if (haystack[i] == needle)
          {
            return i; // in array, at this position.
          } // if
      } // for
    return -1; // not in array
  } // charIndexOf(char[], char)

  /**
   * ClearOutput, resets the output to nothing (which might change depending on
   * the type of picture)
   */
  public void
    clearOutput ()
  {
    this.output = "";
  } // clearOutput

  /**
   * println, prints a line and clears the output string for the next group.
   */
  public void
    println (String input)
  {
    // "[print] out a picture that shows the nesting"
    this.pen.println (input);
    clearOutput ();
  }

  // +-------------+------------------------------------------------------
  // | Constructor |
  // +-------------+

  public class Group
  {
    char braceType; // character
    int openIndex; // integer
    int closeIndex;

    public Group (char ch, int index)
    {
      this.braceType = ch;
      this.openIndex = index;
    } // Group (char, int)

    /**
     * closeBrace, sets the closeIndex to a given integer once the closing brace
     * has been found
     * 
     * @param closeIn
     * @post closeIndex = closeIn
     */
    public void
      closeBrace (int closeIn)
    {
      this.closeIndex = closeIn;
    } // closeBrace (int)

  } // Group

  // +--------+---------------------------------------------------------
  // | Helper |
  // +--------+
  /**
   * printGroup, prints a picture of a group of text's nesting, delinated by
   * parentheses
   * 
   * @param group
   * @param currentIndex
   * @post a picture of a section of the input text's nesting has been printed
   *       to screen
   */
  public void
    printGroup (Group group, int currentIndex)
  {
    for (int x = 0; x < group.openIndex; x++)
      {
        this.output += " ";
      } // for
    this.output += group.braceType;

    if (group.closeIndex > 0)
      {
        for (int x = 0; x < (currentIndex - group.openIndex - 1); x++)
          {
            this.output += '-';
          } // for
        this.output += getClosingParen (group.braceType);
      } // if
    else
      {
        this.output += this.unmatchedWarning;
      } // else

    println (this.output);
  } // printGroup(Group, int)

  /*
   * Functions for error reporting
   */
  /**
   * reports an illegal closing parenthesis
   * 
   * @param character
   * @param index
   * @post an error message is printed to the screen.
   */
  public void
    reportIllegalClose (char charc, int index)
  {
    println ("An illegal character, '" + charc + "', was found at position "
             + index + " of the input.");
  } // reportIllegalClose(char, int )

  /**
   * prints any unmatching parentheses left in the stack with an unmathcing
   * warning to the user
   */
  public void
    printUnmatchedGroups ()
      throws Exception
  {
    int i = 1;
    while (!this.working.isEmpty ())
      {
        printGroup (this.working.get (), this.inputSize + i);
        i++; // increment the position of this paren
      } // while
  } // printUnmatchedGroups()

  /**
   * prints out an appropriate message to indicate the failure to match a
   * parenthesis
   * 
   * @param closeParen
   * @param index
   * @post a warning has been printed to the screen
   */
  public void
    printUnmatchedClose (char closeParen, int index)
  {
    for (int i = 0; i < index; i++)
      {
        this.output += ' ';
      } // for
    this.output += closeParen + this.unmatchedWarning;
    println (output);
  } // printUnmatchedClose (char, int)

} // ParethesesGrouper
