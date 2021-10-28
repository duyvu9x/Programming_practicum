/*
 * TCSS 305 Homework 3: SnapShop
 * 
 */

package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The GUI for SnapShop.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @author Charles Bryan
 * @author Duy vu
 * @version Winter 2021
 */
public class SnapShopGUI extends JPanel {

    /** A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -4860201906562574893L;

    /** The window title. */
    private static final String TITLE = "TCSS 305 - Assignment 3";

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The label in which we will display the image. */
    private JLabel myImageLabel;

    /** The open button. */
    private JButton myOpenButton;

    /** The save button. */
    private JButton mySaveButton;

    /** The close button. */
    private JButton myCloseButton;

    /** The list of filter buttons. */
    private List<JButton> myFilterButtons;

    /** myPixelImage get the image. */
    private PixelImage myPixelImage;

    /** myFileChooser get the file. */
    private JFileChooser myFileChooser;

    /**
     * Initializes the GUI.
     */
    public SnapShopGUI() {
        super();
        setLayout(new BorderLayout());
        buildComponents();
        layoutComponents();
        addEvents();
    }

    /**
     * Creates a button to activate the specified filter, on the specified
     * panel.
     * 
     * @param theFilter The filter.
     * @return the created button.
     */
    private JButton createButton(final Filter theFilter) {
        final JButton button = new JButton(theFilter.getDescription());
        button.setEnabled(false);

        // go ahead and add your event handler to this button here.
        // This is the only place where you have access to both the button AND
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theX) {
                theFilter.filter(myPixelImage);
                myImageLabel.setIcon(new ImageIcon(myPixelImage));

            }

        });

        return button;
    }

    /**
     * Instantiate the graphical components (frame, image label, buttons).
     */
    private void buildComponents() {

        myImageLabel = new JLabel();

        // works on frame; top center on panel
        myImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myImageLabel.setVerticalAlignment(SwingConstants.CENTER);

        myOpenButton = new JButton("Open...");
        mySaveButton = new JButton("Save As...");
        myCloseButton = new JButton("Close Image");

        // create the filter buttons
        myFilterButtons = new ArrayList<JButton>();
        myFilterButtons.add(createButton(new EdgeDetectFilter()));
        myFilterButtons.add(createButton(new EdgeHighlightFilter()));
        myFilterButtons.add(createButton(new FlipHorizontalFilter()));
        myFilterButtons.add(createButton(new FlipVerticalFilter()));
        myFilterButtons.add(createButton(new GrayscaleFilter()));
        myFilterButtons.add(createButton(new SharpenFilter()));
        myFilterButtons.add(createButton(new SoftenFilter()));

    }
    /**
     * method to enable all button.
     */
    private void enableFilters() {
        for (final JButton filterButton : myFilterButtons) {
            filterButton.setEnabled(true);

        }
        mySaveButton.setEnabled(true);
        myCloseButton.setEnabled(true);

    }
    

    /**
     * method to disable filter Button when close.
     */
    private void disableFilters() {
        for (final JButton filterButton : myFilterButtons) {
            filterButton.setEnabled(false);

        }
    }

    /**
     * Sets up the event listeners.
     */
    private void addEvents() {
        myImageLabel.setIcon(null);
        implementOpenButton();
        implementCloseButton();
        implementSaveButton();

    }

    /**
     * create open button.
     */
    private void implementOpenButton() {
        myFileChooser = new JFileChooser();
        myFileChooser.setCurrentDirectory(new File("."));
        myOpenButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theE) {
                final int x = myFileChooser.showOpenDialog(null);
                final File file = myFileChooser.getSelectedFile();
                if (file != null && x == JFileChooser.APPROVE_OPTION) {
                    try {
                        myPixelImage = PixelImage.load(file);
                        myImageLabel.setIcon(new ImageIcon(myPixelImage));
                        enableFilters();

                    } catch (final IOException e1) {
                        JOptionPane.showMessageDialog(null,
                                    file.getName() + " format does not match image");
                    }
                }

            }

        });

    }

    private void implementSaveButton() {
        mySaveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                myFileChooser.showSaveDialog(myImageLabel);
                if (myFileChooser.getSelectedFile() != null) {
                    final File file = myFileChooser.getSelectedFile();
                    try {
                        myPixelImage.save(file);
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void implementCloseButton() {
        myCloseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                myImageLabel.setIcon(null);
                // myFileChooser.removeAll();
                disableFilters();
                myOpenButton.setEnabled(true);
                mySaveButton.setEnabled(false);
                myCloseButton.setEnabled(false);

            }
        });

    }

    /**
     * Sets up the graphical components.
     */
    private void layoutComponents() {

        // add the filter buttons to a panel
        final JPanel northPanel = new JPanel(new FlowLayout());
        for (final JButton button : myFilterButtons) {
            northPanel.add(button);
        }
        add(northPanel, BorderLayout.NORTH);

        // label added directly the Center
        add(myImageLabel, BorderLayout.CENTER);

        // south panel to hold the file Open button
        final JPanel south = new JPanel(new FlowLayout());
        south.add(myOpenButton);
        south.add(mySaveButton);
        south.add(myCloseButton);
        add(south, BorderLayout.SOUTH);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    public static void createAndShowGui() {
        final SnapShopGUI mainPanel = new SnapShopGUI();

        // A size for the JFrame.
        // final Dimension frameSize = new Dimension(400, 400);

        // set properties of the frame
        final JFrame window = new JFrame(TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension());
        window.setContentPane(mainPanel);
        window.pack();
        window.setMinimumSize(window.getSize());
        window.setLocation((int) (KIT.getScreenSize().getWidth() / 2 - window.getWidth() / 2),
                           (int) (KIT.getScreenSize().getHeight() / 2
                                  - window.getHeight() / 2));
        window.setVisible(true);

        // For now, ignore what is going on here. We will look at this
        // pattern in future lectures AND assignments.
        mainPanel.myImageLabel.addPropertyChangeListener(theEvent -> {
            if ("icon".equals(theEvent.getPropertyName())) {
                window.setMinimumSize(new Dimension());
                window.pack();
                window.setMinimumSize(window.getSize());
                window.setLocation((int) (KIT.getScreenSize().getWidth() / 2
                                          - window.getWidth() / 2),
                                   (int) (KIT.getScreenSize().getHeight() / 2
                                          - window.getHeight() / 2));
            }
        });

    }

}
