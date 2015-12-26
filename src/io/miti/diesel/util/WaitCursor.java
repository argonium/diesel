package io.miti.diesel.util;

import java.awt.Component;
import java.awt.Cursor;

/**
 * Class to encapsulate setting the wait cursor on a Component,
 * and then restoring the original cursor when done.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class WaitCursor
{
  /**
   * The component with the cursor we're modifying.
   */
  private Component comp = null;
  
  /**
   * The component's original cursor.
   */
  private Cursor origCursor = null;
  
  /**
   * The start time.
   */
  private long startTime = 0L;
  
  /**
   * Whether the original cursor has been restored.
   */
  private boolean cursorReset = false;
  
  
  /**
   * Default constructor.
   */
  public WaitCursor()
  {
    super();
    startTime = System.currentTimeMillis();
  }
  
  
  /**
   * Constructor taking the parent component.
   * 
   * @param pComp the component
   */
  public WaitCursor(final Component pComp)
  {
    super();
    startTime = System.currentTimeMillis();
    
    if (pComp != null)
    {
      comp = pComp;
      origCursor = pComp.getCursor();
      comp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
  }
  
  
  /**
   * If the original cursor has not been restored, do so now.
   */
  public void reset()
  {
    if (!cursorReset && (comp != null) && (origCursor != null))
    {
      comp.setCursor(origCursor);
      cursorReset = true;
    }
  }
  
  
  /**
   * Log the time differential.
   */
  public void log()
  {
    final long currTime = System.currentTimeMillis();
    final long delta = currTime - startTime;
    String desc = Utility.millisToTimeSpan(delta);
    System.out.println(desc);
  }
  
  
  /**
   * Kill this object.
   * 
   * @see java.lang.Object#finalize()
   * @throws Throwable a Throwable exception
   */
  @Override
  protected void finalize() throws Throwable
  {
    super.finalize();
    reset();
  }
}
