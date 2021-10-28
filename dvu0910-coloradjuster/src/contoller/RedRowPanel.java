/*
 * TCSS 305 - coloradjuster
 */
package contoller;

import static model.PropertyChangeEnabledMutableColor.PROPERTY_RED;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.ColorModel;
import model.PropertyChangeEnabledMutableColor;

/**
 * Represents a Panel with components used to change and display the Red value for the 
 * backing Color model.
 *
 * @author Charles Bryan
 * @author DuyVu
 * @version Winter 2021
 */
public class RedRowPanel extends JPanel implements PropertyChangeListener {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 2284116355218892348L;
    
    /** The size of the increase/decrease buttons. */
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
    
    /** The size of the text label. */
    private static final Dimension LABEL_SIZE = new Dimension(45, 26);
    
    /** The number of columns in width of the TextField. */
    private static final int TEXT_FIELD_COLUMNS = 3;
    
    /** The amount of padding for the change panel. */
    private static final int HORIZONTAL_PADDING = 30;
    
    /** The backing model for the system. */
    private final PropertyChangeEnabledMutableColor myColor;

    /** The CheckBox that enables/disables editing of the TextField. */
    private final JCheckBox myEnableEditButton;
    
    /** The TextField that allows the user to type input for the coler value. */
    private final JTextField myValueField;
    
    /** The Button that when clicked increases the color value. */
    private final JButton myIncreaseButton;
    
    /** The Button that when clicked decreases the color value. */
    private final JButton myDecreaseButton;
    
    /** The Slider that when adjusted, changes the color value. */
    private final JSlider myValueSlider;
    
    /** The panel that visually displays ONLY the red value for the color. */
    private final JPanel myColorDisplayPanel;
    
    /**
     * Creates a Panel with components used to change and display the Red value for the 
     * backing Color model. 
     * @param theColor the backing model for the system
     */
    public RedRowPanel(final PropertyChangeEnabledMutableColor theColor) {
        super();
        myColor = theColor;
        myEnableEditButton = new JCheckBox("Enable edit");
        myValueField = new JTextField();
        myIncreaseButton = new JButton();
        myDecreaseButton = new JButton();
        myValueSlider = new JSlider();
        myColorDisplayPanel = new JPanel();
        
        layoutComponents();
        addListeners();
    }
    
    /** add action for button. */    
    private void addAction() {
        /** to change in JTextField */
        myEnableEditButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theE) {
                final JCheckBox cb = (JCheckBox) theE.getSource();
                if (cb.isSelected()) {
                    myValueField.setEditable(true);
                } else {
                    myValueField.setEditable(false);
                }
            }
        });
        /** get new value and return old value when value is not satisfied. */ 
        myValueField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                final int newValue = Integer.parseInt(myValueField.getText());
                if (newValue >= ColorModel.MIN_VALUE  && newValue <= ColorModel.MAX_VALUE) {
                    myColor.setRed(newValue);
                } else {
                    myValueField.setText(Integer.toString(myColor.getRed()));
                }
            }
        });
        /** action for myIncreaseButton. */
        myIncreaseButton.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theE) {
                myColor.setRed(myColor.getRed() + 1);
            }
        });
        /** action for myDecreaseButton. */
        myDecreaseButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theE) {
                myColor.setRed(myColor.getRed() - 1);
                
            }
        });
        /** change when move  slide */
        myValueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theE) {
                myColor.setRed(myValueSlider.getValue());
                
            }
        });

    }
    
    /**
     * Setup and add the GUI compp
     * onets for this panel. 
     */
    private void layoutComponents() {
        myColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myColorDisplayPanel.setBackground(new Color(myColor.getRed(), 0, 0));
        final JLabel rowLabel = new JLabel("Red: ");
        rowLabel.setPreferredSize(LABEL_SIZE);
        myValueField.setText(String.valueOf(myColor.getRed()));
        myValueField.setEditable(false);
        myValueField.setColumns(TEXT_FIELD_COLUMNS);
        myValueField.setHorizontalAlignment(JTextField.RIGHT);
        
        final JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 
                                                             HORIZONTAL_PADDING, 
                                                             0, 
                                                             HORIZONTAL_PADDING));
        rightPanel.setBackground(rightPanel.getBackground().darker());
        myIncreaseButton.setIcon(new ImageIcon("./images/ic_increase_value.png"));
        myIncreaseButton.setPreferredSize(BUTTON_SIZE);
        myValueSlider.setMaximum(ColorModel.MAX_VALUE);
        myValueSlider.setMinimum(ColorModel.MIN_VALUE);
        myValueSlider.setValue(myColor.getRed());
        myValueSlider.setBackground(rightPanel.getBackground());
        myDecreaseButton.setIcon(new ImageIcon("./images/ic_decrease_value.png"));
        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
        rightPanel.add(myDecreaseButton);
        rightPanel.add(myValueSlider);
        rightPanel.add(myIncreaseButton);
        
        add(myColorDisplayPanel);
        add(rowLabel);
        add(myValueField);
        add(myEnableEditButton);
        add(rightPanel);
    }
    
    /**
     * Add listeners (event handlers) to any GUI components that require handling.  
     */
    private void addListeners() {
        addAction();
        //DO not remove the following statement.
        
        myColor.addPropertyChangeListener(PROPERTY_RED, this);
        
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_RED.equals(theEvent.getPropertyName())) {
            myValueField.setText(theEvent.getNewValue().toString());
            myValueSlider.setValue((Integer) theEvent.getNewValue());
            myColorDisplayPanel.
                setBackground(new Color((Integer) theEvent.getNewValue(), 0, 0));
            // to enable myDecreaseButton and myIncreaseButton 
            if (myColor.getRed() == ColorModel.MIN_VALUE) {        
                myDecreaseButton.setEnabled(false);
                myIncreaseButton.setEnabled(true);
            } else if (myColor.getRed() == ColorModel.MAX_VALUE) {
                myDecreaseButton.setEnabled(true);
                myIncreaseButton.setEnabled(false);
            } else {
                myDecreaseButton.setEnabled(true);
                myIncreaseButton.setEnabled(true);
            }
        }
        
    }
}
