import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;


public class Buborek extends JFrame implements ActionListener{
    public static int mennyiseg = 150;
    public int futasokSzama = 0;
    public JPanel foPanel = new JPanel();
    public JPanel ballPanel = new GPanel();
    public JPanel jobbPanel = new JPanel();
    public boolean startbool = false;
    public JButton start = new JButton("Indit");
    public ArrayList<Integer> list = new ArrayList<>();
    public int[] sorozat = new int[mennyiseg];

    
    
    
    public Buborek() throws InterruptedException{
        //GFX
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Buborek");
        setResizable(false);
        setAlwaysOnTop(false);
        setSize(1280, 720);
        ballPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));
        jobbPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK)));
        ballPanel.setBackground(Color.BLACK);
        ballPanel.setMaximumSize(new Dimension(1095, 2200));
        ballPanel.setMinimumSize(new Dimension(1095, 100));
        ballPanel.setPreferredSize(new Dimension(1095, 670));
        jobbPanel.setMaximumSize(new Dimension(150, 2200));
        jobbPanel.setMinimumSize(new Dimension(150, 100));
        jobbPanel.setPreferredSize(new Dimension(150, 670));
        

        // Start && nev
        JPanel startPanel = new JPanel();
        start.addActionListener(this);
        startPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY)));
        jobbPanel.setLayout(new BorderLayout());
        startPanel.add(start);
        

        //add panels
        jobbPanel.add(startPanel, BorderLayout.SOUTH);
        foPanel.add(ballPanel, BorderLayout.WEST);
        foPanel.add(jobbPanel, BorderLayout.EAST);
        add(foPanel);



        Random randNum = new Random();
        int random =0;

        while (list.size() < mennyiseg) {
            random = randNum.nextInt(mennyiseg)+1;
            if (!list.contains(random)) {
                list.add(random);
            }
        }
        for (int i = 0; i < sorozat.length; i++) {
            sorozat[i] = list.get(i);
        }

    }
    
    public class GPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g.setColor(Color.WHITE);

            
            double x1 = (ballPanel.getWidth()/mennyiseg);
            for (int i = 0; i < sorozat.length; i++) {
                g2d.draw(new Rectangle2D.Double(x1, ballPanel.getHeight()-sorozat[i]*3, ballPanel.getWidth()/mennyiseg, sorozat[i]*10));
                x1 += (ballPanel.getWidth()/mennyiseg);
                ballPanel.removeAll();
                ballPanel.updateUI();
               
            }


            
            if (startbool){
                try {
                    Thread.sleep(30);
                }catch(InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                for (int j = 0; j < sorozat.length-1; j++) {
                    if (sorozat[j] > sorozat[j + 1]) {
                    int temp = sorozat[j];
                    sorozat[j] = sorozat[j + 1];
                    sorozat[j + 1] = temp;
                    }
            
                    double x2 = (ballPanel.getWidth()/mennyiseg);
                    for (int i = 0; i < sorozat.length; i++) {
                        g2d.draw(new Rectangle2D.Double(x2, ballPanel.getHeight()-sorozat[i]*3, ballPanel.getWidth()/mennyiseg, sorozat[i]*10));
                        x2 += (ballPanel.getWidth()/mennyiseg);
                        ballPanel.removeAll();
                        ballPanel.updateUI();
                    }    
                } 
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buborek frame = new Buborek();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start.getText().equals("Indit")) {
            start.setText("Futt");
            startbool = true;
            start.setEnabled(false);
        }
        


    }

    
}

