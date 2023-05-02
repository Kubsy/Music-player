import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum states {READY, OPENED, PLAYING, PAUSED, EXIT}

public class MusicPlayer extends JFrame implements ActionListener
{
    private final byte OPEN = 0, PLAY = 1, PAUSE = 2, STOP = 3, CLOSE = 4, EXIT = 5;
    private final String[] BUTTON_LABELS = {"Open", "Play", "Pause", "Stop", "Close", "Exit"};
    private JButton[] actButtons;
    private JPanel p;
    private states state = states.READY;
    private JLabel Display;
    private final int MAXWINDOWROW = 2;
    public MusicPlayer()
    {
        setLayout(new GridLayout(MAXWINDOWROW, 1));

        p = new JPanel(new GridLayout(1, BUTTON_LABELS.length));
        Display = new JLabel("0", JLabel.CENTER);

        Display.setFont(new Font("Sanserif", Font.BOLD, 20));

        add(Display);
        add(p, BorderLayout.CENTER);

        actButtons = new JButton[BUTTON_LABELS.length];
        for (int i = 0; i < BUTTON_LABELS.length; i++)
        {
            actButtons[i] = setupButton(""+BUTTON_LABELS[i], this, p);
        }

        Display.setText("Ready");
        SetAllButtonsEnabled(false);
        actButtons[OPEN].setEnabled(true);
        actButtons[EXIT].setEnabled(true);
    }

    private JComponent setupComponent(JComponent comp, Container c)
    {
        comp.setFont(new Font("Sanserif", Font.BOLD, 20));
        comp.setBackground(Color.white);
        c.add(comp);
        return comp;
    }

    private JButton setupButton (String text, ActionListener l, Container c)
    {
        JButton button = new JButton(text);
        button.addActionListener(l);
        return (JButton) setupComponent(button, c);
    }

    private void SetAllButtonsEnabled(boolean b)
    {
        for (JButton button : actButtons)
        {
            button.setEnabled(b);
        }
    }

    private void DoOpened()
    {
        Display.setText("Opened");
        SetAllButtonsEnabled(false);
        actButtons[PLAY].setEnabled(true);
        actButtons[EXIT].setEnabled(true);
        actButtons[CLOSE].setEnabled(true);
    }

    private void DoPlaying()
    {
        Display.setText("Playing");
        SetAllButtonsEnabled(false);
        actButtons[PAUSE].setEnabled(true);
        actButtons[STOP].setEnabled(true);
        actButtons[EXIT].setEnabled(true);
    }

    private void DoPaused()
    {
        Display.setText("Paused");
        SetAllButtonsEnabled(false);
        actButtons[PLAY].setEnabled(true);
        actButtons[EXIT].setEnabled(true);
        actButtons[STOP].setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(state)
        {
        case READY:
            if (e.getSource() == actButtons[OPEN])
            {
                DoOpened();
                state = states.OPENED;
            }
            else if (e.getSource() == actButtons[EXIT])
            {
                Display.setText("Exited");
            }
        case OPENED:
            if (e.getSource() == actButtons[PLAY])
            {
                DoPlaying();
                state = states.PLAYING;
            }
            else if (e.getSource() == actButtons[CLOSE])
            {
                //TODO: put this into a function
                Display.setText("Ready");
                SetAllButtonsEnabled(false);
                actButtons[OPEN].setEnabled(true);
                actButtons[EXIT].setEnabled(true);
                state = states.READY;
            }
            else if (e.getSource() == actButtons[EXIT])
            {
                Display.setText("Exited");
            }
        case PLAYING:
            if (e.getSource() == actButtons[PAUSE])
            {
                DoPaused();
                state = states.PAUSED;
            }
            else if (e.getSource() == actButtons[STOP])
            {
                DoOpened();
                state = states.OPENED;
            }
            else if (e.getSource() == actButtons[EXIT])
            {
                Display.setText("Exited");
            }
        case PAUSED:
            if (e.getSource() == actButtons[PLAY])
            {
                DoPlaying();
                state = states.PLAYING;
            }
            else if (e.getSource() == actButtons[STOP])
            {
                DoOpened();
                state = states.OPENED;
            }
            else if (e.getSource() == actButtons[EXIT])
            {
                Display.setText("Exited");
            }
        }
    }
}
