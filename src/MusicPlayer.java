import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum state {READY, OPENED, PLAYING, PAUSED, EXIT};

public class MusicPlayer extends JFrame implements ActionListener
{
    private enum actions {OPEN,PLAY,PAUSE,STOP,CLOSE,EXIT};
    private final String[] BUTTON_LABELS = {"Open", "Play", "Pause", "Stop", "Close", "Exit"};
    private JButton[] actButtons;
    private JPanel p;
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

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
