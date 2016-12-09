
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends Frame {

    JFrame frame;
    JPanel grid;
    JButton button;
    ImageIcon agent;
    ImageIcon wall;
    ImageIcon track;
    ImageIcon start;
    ImageIcon finish;
    ImageIcon explosion;

    public GUI() {
        
        this.frame = new JFrame("Track Simulator");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.agent = new ImageIcon("/images/agent.ico");
        this.wall = new ImageIcon("/images/wall.ico");
        this.track = new ImageIcon("/images/track.ico");
        this.start = new ImageIcon("/images/start.ico");
        this.finish = new ImageIcon("/images/finish.ico");
        this.explosion = new ImageIcon("/images/explosion.ico");
    }

    public void renderTrack(char[][] track, Position agentLoc, boolean collision) {

        this.frame.setSize(new Dimension(track.length * 25, track[0].length * 25));
        this.grid = new JPanel();
        this.grid.setLayout(new GridLayout(track.length, track[0].length));

        for (int i = 0; i < track.length - 1; i++) {
            for (int j = 0; j < track[i].length - 1; j++) {

                if (j == agentLoc.x && i == agentLoc.y) {
                    if (collision) {
                        grid.add(new JLabel(this.explosion));
                    } else {
                        grid.add(new JLabel(this.agent));
                    }
                } else if (track[i][j] == '#') {
                    grid.add(new JLabel(this.wall));
                } else if (track[i][j] == 'S') {
                    grid.add(new JLabel(this.start));
                } else if (track[i][j] == 'F') {
                    grid.add(new JLabel(this.finish));
                } else {
                    grid.add(new JLabel(this.track));
                }
            }
        }

        button = new JButton("Next Action");
        button.addActionListener((ActionEvent e) -> {
            Tester.wait = false;
        });

        //frame.pa
        frame.add(grid);
        frame.add(button);
        //frame.pai frame
        //.update(g);
        //frame.pack();
        frame.setVisible(true);
    }

}
