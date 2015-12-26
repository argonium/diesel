package io.miti.diesel.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import io.miti.diesel.gui.JConsole;
import io.miti.diesel.util.Logger;
import io.miti.diesel.util.SystemInfo;
import io.miti.diesel.util.Utility;
import io.miti.diesel.util.WindowState;

/**
 * This is the main class for the application.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class DieselConsole
{
  /** The name of the properties file. */
  public static final String PROPS_FILE_NAME = "diesel.prop";
  
  /** The application frame. */
  private JFrame frame = null;
  
  /** The status bar. */
  private JLabel statusBar = null;
  
  /** The window state (position and size). */
  private WindowState windowState = null;
  
  /** The single instance of the application. */
  private static DieselConsole app = null;
  
  
  /**
   * Default constructor.
   */
  private DieselConsole()
  {
    super();
  }
  
  
  /**
   * Create the application's GUI.
   */
  private void createGUI()
  {
    // Load the properties file
    windowState = WindowState.getInstance();
    
    // Set up the frame
    setupFrame();
    
    // Create the empty middle window
    initScreen();
    
    // Set up the status bar
    initStatusBar();
    
    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }
  
  
  /**
   * Set the text in the status bar.
   * 
   * @param msg the new text for the status bar
   */
  public void setStatusBarText(final String msg)
  {
    if (msg == null)
    {
      statusBar.setText("Ready");
    }
    else
    {
      statusBar.setText(msg);
    }
  }
  
  
  /**
   * Get the single instance of the application.
   * 
   * @return the instance of the app
   */
  public static DieselConsole getApp()
  {
    return app;
  }
  
  
  /**
   * Set up the application frame.
   */
  private void setupFrame()
  {
    // Create and set up the window.
    frame = new JFrame(Utility.getAppName());
    
    // Have the frame call exitApp() whenever it closes
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter()
    {
      /**
       * Close the windows.
       * 
       * @param e the event
       */
      @Override
      public void windowClosing(final WindowEvent e)
      {
        exitApp();
      }
    });
    
    // Set up the size of the frame
    frame.setPreferredSize(windowState.getSize());
    frame.setSize(windowState.getSize());
    
    // Set the position
    if (windowState.shouldCenter())
    {
      frame.setLocationRelativeTo(null);
    }
    else
    {
      frame.setLocation(windowState.getPosition());
    }
  }
  
  
  /**
   * Initialize the main screen (middle window).
   */
  private void initScreen()
  {
    // Build the middle panel
    JConsole appPanel = JConsole.getInstance(new io.miti.diesel.gui.CmdLineInterpreter());
    appPanel.setBackground(Color.WHITE);
    frame.getContentPane().add(appPanel, BorderLayout.CENTER);
  }
  
  
  /**
   * Initialize the status bar.
   */
  private void initStatusBar()
  {
    // Instantiate the status bar
    statusBar = new JLabel("Ready");
    
    // Set the color and border
    statusBar.setForeground(Color.black);
    statusBar.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2),
                              new SoftBevelBorder(SoftBevelBorder.LOWERED)));
    
    // Add to the content pane
    frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
  }
  
  
  /**
   * Return the parent frame.
   * 
   * @return the parent frame
   */
  public JFrame getFrame()
  {
	  return frame;
  }
  
  
  /**
   * Exit the application.
   */
  public void exitApp()
  {
    // Store the window state in the properties file
    windowState.update(frame.getBounds());
    windowState.saveToFile(PROPS_FILE_NAME);
    
    // Close the application by disposing of the frame
    frame.dispose();
  }
  
  
  /**
   * Entry point to the application.
   * 
   * @param args arguments passed to the application
   */
  public static void main(final String[] args)
  {
	// Save the system information
	SystemInfo.initialize();
	  
    // Make the application Mac-compatible
    Utility.makeMacCompatible();
    
    // Instantiate the GUI console
    app = new DieselConsole();
    
    // Load the properties file data
    WindowState.load(PROPS_FILE_NAME);
    
    // Initialize the look and feel to the default for this OS
    Utility.initLookAndFeel();
    
    // Initialize the logger
    Logger.initialize(3, "stdout", true);
    
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        // Run the application
        app.createGUI();
      }
    });
  }
}
