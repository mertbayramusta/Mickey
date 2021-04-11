package Mickey;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Mickey extends Frame implements ActionListener, MouseListener {

    int x = 250;
    int y = 300;
    int dx = 300;
    int dy = 300;
    int click = 0;
    boolean clicked = false; //to be able to set the default values for the mickey

    List<Point2D.Float> points;

    static final int windowWidth = 800;
    static final int windowHeight = 800;

    public static void main(String[] args) {
        Mickey heyMickey = new Mickey();
        heyMickey.setSize(windowWidth,windowHeight);
        heyMickey.setVisible(true);
    }
    public Mickey() {
        //Setting title
        setTitle("Mickey");

        //Creating Menu
        MenuBar mb = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem setDefault = new MenuItem("Set Default");
        menu.add(setDefault);
        mb.add(menu);
        //Program will now set the default values whenever the "Set Default" menu item is selected
        menu.addActionListener(this);
        setMenuBar(mb);

        //Adding Panel and Buttons
        Panel panel = new Panel();
        panel.setBounds(0,70,windowWidth,35);
        panel.setBackground(Color.black);

        Button button_smaller = new Button();
        button_smaller.setLabel("Smaller");
        button_smaller.addActionListener(this);
        Button button_bigger = new Button();
        button_bigger.setLabel("Bigger");
        button_bigger.addActionListener(this);

        panel.add(button_smaller);
        panel.add(button_bigger);
        add(panel);
        setLayout(null);

        points = new ArrayList<Point2D.Float>();

        //setting the background color red
        setBackground(Color.red);
        addWindowListener(new myWindow());
        addMouseListener(this);

    }

    public class myWindow extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        int mky_x, mky_y;
        Graphics2D g2d = (Graphics2D) g;
        if(!clicked){
            //mickeys face is created
            Rectangle mky = new Rectangle(this.x, this.y, this.dx, this.dy);
            drawCircle(g2d, mky);
            mky_x = mky.width / 2;
            mky_y = mky.height / 2;
            //mickeys ears are created
            Rectangle mky_ears = new Rectangle(mky.x, mky.y, mky_x, mky_y);
            mky_ears.translate(-mky_x / 2, -mky_y / 2);
            drawCircle(g2d, mky_ears);
            mky_ears.translate(mky_x * 2, 0);
            drawCircle(g2d, mky_ears);

        }else{
            for(Point2D.Float point : points){
                //mickeys face is created for every click
                Rectangle mky = new Rectangle((int)(point.x - this.dx/2), (int)(point.y- this.dy/2), this.dx, this.dy);
                drawCircle(g2d, mky);
                mky_x = mky.width / 2;
                mky_y = mky.height / 2;
                //mickeys ears are created for every click
                Rectangle mky_ears = new Rectangle(mky.x, mky.y, mky_x, mky_y);
                mky_ears.translate(-mky_x / 2, -mky_y / 2);
                drawCircle(g2d, mky_ears);
                mky_ears.translate(mky_x * 2, 0);
                drawCircle(g2d, mky_ears);
            }

        }
    }
    public void drawCircle(Graphics2D g2d, Rectangle mky) {
        g2d.setColor(Color.black);
        g2d.fillOval(mky.x, mky.y, mky.width, mky.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String chooseAction = e.getActionCommand();
        System.out.println("You selected to " + chooseAction);
        if (chooseAction.equals("Smaller")) {
            this.dy = this.dy - 80;
            this.dx = this.dx - 80;
            repaint();
        } else if (chooseAction.equals("Bigger")){
            this.dy = this.dy + 80;
            this.dx = this.dx + 80;
            repaint();
        }else if (chooseAction.equals("Set Default")){
            clicked = false;
            this.dx = 300;
            this.dy = 300;
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        clicked = true;
        Point2D.Float point = new Point2D.Float(x,y);
        points.add(point);
        click++;
        if(click > 1)
            points.remove(0);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
