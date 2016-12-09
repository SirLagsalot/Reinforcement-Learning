
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {

    private JPanel panel = new JPanel();
    private JButton button;
    private Container contents;

    private final char[][] track;
    private final ImageIcon agent = new ImageIcon("/images/agent.ico");
    private final ImageIcon wall = new ImageIcon("/images/wall.ico");
    private final ImageIcon pavement = new ImageIcon("/images/pavement.ico");
    private final ImageIcon start = new ImageIcon("/images/start.ico");
    private final ImageIcon finish = new ImageIcon("/images/finish.ico");
    private final ImageIcon explosion = new ImageIcon("/images/explosion.ico");

    public GUI(char[][] track) {
        super("Track Simulator");
        this.track = track;
        init();
    }

    private void init() {

        this.contents = getContentPane();
        this.contents.setLayout(new GridLayout(track.length, track[0].length));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(track.length * 100, track[0].length * 100));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void renderTrack(Position agentLoc, boolean collision) {

        this.panel.setLayout(new GridLayout(track.length, track[0].length));

        for (int i = 0; i < track.length - 1; i++) {
            for (int j = 0; j < track[i].length - 1; j++) {

                if (j == agentLoc.x && i == agentLoc.y) {
                    if (collision) {
                        panel.add(new JLabel(this.explosion));
                    } else {
                        panel.add(new JLabel(this.agent));
                    }
                } else if (track[i][j] == '#') {
                    panel.add(new JLabel(this.wall));
                } else if (track[i][j] == 'S') {
                    panel.add(new JLabel(this.start));
                } else if (track[i][j] == 'F') {
                    panel.add(new JLabel(this.finish));
                } else {
                    panel.add(new JLabel(this.pavement));
                }
            }
        }

        button = new JButton("Next Action");
        button.addActionListener((ActionEvent e) -> {
            Tester.wait = false;
        });

        this.add(panel);
        this.add(button);
        //this.pack();

        //frame.paint();
        //frame.update(g);
        //frame.pack();
    }
}
