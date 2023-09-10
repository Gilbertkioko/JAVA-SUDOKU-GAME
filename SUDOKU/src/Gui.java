import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.Border;

public class Gui {
JFrame Frame;
JPanel MainPanel;
ArrayList<JPanel>Boxes;
public ArrayList<JTextField>TextFields;

JButton SelectLevel;

//LET'S DECLARE VARIABLES THAT WILL HELP IN MAKING A TIMER/COUNTDOWN
    int ElapsedTime = 0;
    int Seconds = 0;
    int Minutes = 0;
    String SecondsValue;
    String MinutesValue;
    JTextField Counter = new JTextField();

    //WE ALSO NEED TO PAUSE/PLAY THE GAME AND THE TIMER
    JButton PausePlay;


public void BuildGui(){
    Frame = new JFrame();
    Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Frame.setSize(550,650);
    Frame.setLocation(400,50);
    Frame.setLayout(null);

    GridLayout Grid = new GridLayout(3,3);
    Grid.setHgap(1);
    Grid.setVgap(1);
    MainPanel = new JPanel(Grid);
    MainPanel.setBounds(50,75,450,450);
    MainPanel.setBackground(Color.BLACK);
    Border MainBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    MainPanel.setBorder(MainBorder);
    Frame.add(MainPanel);

    Boxes = new ArrayList<JPanel>();
    TextFields = new ArrayList<JTextField>();
    for (int i=0; i<=8; i++){
        JPanel SquarePanels = new JPanel(Grid); //EACH SQUARE PANEL HAS A GRID LAYOUT OF 3X3
        SquarePanels.setBorder(MainBorder);
        Boxes.add(SquarePanels);
        MainPanel.add(SquarePanels);

        /*AN INNER LOOP THAT ADDS NINE TEXTFIELDS FOR EVERY PANEL CREATED, FOR ALL 9 PANELS
        IT ALSO ADDS EVERY TEXTFIELD TO THE TEXTFIELD ARRAYLIST
         */
        for (int j=0; j<=8; j++){
            JTextField TextField = new JTextField();
            SquarePanels.add(TextField);
            Font newFont = TextField.getFont().deriveFont(25f); //USING THE TEXTFIELD EXISTING FONT, WE CREATE A NEW FONTSIZE
            TextField.setFont(newFont);
            TextField.setHorizontalAlignment(SwingConstants.CENTER); //CENTERING TEXT
            TextFields.add(TextField);

           /*This block of code will print the index of every textfield
            int index = TextFields.size() -1;
            TextField.setText(Integer.toString(index));*/
        }
    }
/* THE COMMENTED BOX OF CODE BELOW JUST SHOWS HOW YOU CAN PULL A SINGLE PANEL FROM THE
ARRAYLIST OF PANELS AND WORK ON IT. I PUT A TEXTFIELD ON THE FIRST PANEL, WHICH HAS INDEX 0
 */
    /*
    JTextField Text = new JTextField("Test");
    JPanel p = Boxes.get(0);
    p.add(Text);
    */

    SelectLevel = new JButton("NEW GAME");
    SelectLevel.setBounds(350,550,150,20);
    SelectLevel.setFocusable(false);
    SelectLevel.addActionListener(new SelectLevelListener());
    Frame.add(SelectLevel);

    Counter.setBounds(50,10,100,50);
    Font CounterFont = Counter.getFont().deriveFont(30f);
    Counter.setFont(CounterFont);
    Counter.setHorizontalAlignment(SwingConstants.CENTER);
    Counter.setEditable(false);
    Frame.add(Counter);

    PausePlay = new JButton("PAUSE");
    PausePlay.setBounds(200,10,100,50);
    PausePlay.setFocusable(false);
    PausePlay.addActionListener(new PausePlayListener());
    Frame.add(PausePlay);

    Frame.setVisible(true);
    CountTimeTaken.start(); //START THE TIMER AS SOON AS THIS FRAME APPEARS (FRAME CARRYING THE ACTUAL GAME)
}

public ArrayList<JTextField> GetTextFieldArrayList(){
    return TextFields;
    }

    public class SelectLevelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SELECT_LEVEL sl = new SELECT_LEVEL();
            sl.BuildGui();
            Frame.dispose();
        }
    }

    //LETS MAKE THE COUNTER START TICKING.
    Timer CountTimeTaken = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ElapsedTime = ElapsedTime + 1000;
            Minutes = (ElapsedTime /60000) % 60;
            Seconds = (ElapsedTime /1000) % 60;

            MinutesValue = String.format("%02d", Minutes);
            SecondsValue = String.format("%02d", Seconds);

            Counter.setText(MinutesValue +" : "+ SecondsValue);
        }
    });


public class PausePlayListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PausePlay.getText().equals("PAUSE")){
            PausePlay.setText("PLAY");
            CountTimeTaken.stop();
            MainPanel.setVisible(false);
        }

        else if (PausePlay.getText().equals("PLAY")){
            PausePlay.setText("PAUSE");
            CountTimeTaken.start();
            MainPanel.setVisible(true);
        }
    }
}

public void StopTimer (){
    CountTimeTaken.stop();
}
}
