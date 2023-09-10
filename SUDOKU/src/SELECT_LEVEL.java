import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class SELECT_LEVEL {
    JFrame Frame;
    JList Levels;
    String [] AvailableLevels = {"EASY", "MEDIUM", "HARD", "EXPERT" };
    JButton Play;

    ACTUAL_GAME ag = new ACTUAL_GAME();


    public void BuildGui(){
        Frame = new JFrame();
        Frame.setSize(300,300);
        Frame.setLocation(400,150);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setLayout(null);

        Levels = new JList(AvailableLevels);
        Levels.setBounds(100,30,100,150);
        Levels.setBackground(Color.lightGray);

        Play = new JButton("PLAY");
        Play.setBounds(180,200,100,20);
        Play.setFocusable(false);
        Play.addActionListener(new PlayButtonListener());

        Frame.add(Levels);
        Frame.add(Play);
        Frame.setVisible(true);


    }

    public class PlayButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ag.FillArraylist();
            Frame.dispose();

            if (Levels.getSelectedIndex()==0){
                //IF EASY LEVEL HAS BEEN SELECTED.
                FillTextFields(6);
            }
            else if (Levels.getSelectedIndex()==1) {
                //MEDIUM
                FillTextFields(5);
            } else if (Levels.getSelectedIndex()==2) {
                //HARD
                FillTextFields(4);
            } else if (Levels.getSelectedIndex()==3) {
                //EXPERT
                FillTextFields(3);
            }
            else {
                //NO CHOICE
                JOptionPane.showMessageDialog(null, "SELECT A LEVEL");
            }
        }
    }

    private void FillTextFields ( int To){
        //ALL TEXTFIELDS ARE FILLED WITH THE SAME CRITERIA, WITH THE ONLY DIFFERENCE BEING THE NUMBER
        //OF TEXTEFIELDS TO BE FILLED. SO WE CAN HAVE ONE METHOD THAT CAN BE CALLED ON ALL LEVELS,
        //AND DICTATE THE NUMBER OF TEXTFIELDS TO BE FILLED ON EACH LEVEL.
        int End = To;
        ArrayList<ArrayList<JTextField>> AllSolutions;
        AllSolutions = ag.GetAllSolutions();


        ArrayList<Integer> AllSudokuNumbers = new ArrayList<>();
        for (int s = 1; s <10; s++){
            AllSudokuNumbers.add(s);
        }

        for (int i=0; i<AllSolutions.size(); i++){
            ArrayList<JTextField> CurrentArrayList = AllSolutions.get(i);

            int RandomIndex = (int)(Math.random() * AllSudokuNumbers.size()-1);
            int RandomNumber = AllSudokuNumbers.get(RandomIndex);
            String RandomValue = String.valueOf(RandomNumber);

            Collections.shuffle(CurrentArrayList); //SHUFFLES THE ARRAYLIST.
            List<JTextField> RandomTextFields = CurrentArrayList.subList(0,End); //TAKE 8 TEXTFIELDS
            // IF LEVEL IS EASY, AND SO ON

            for (JTextField textField : CurrentArrayList){

                if (RandomTextFields.contains(textField)){
                    // IF A TEXTFIELD OUT OF THE 9 IS IN RandomTextFields SUB ARRAYLIST,
                    // THEN LET IT BE WORKED ON.

                    textField.setText(RandomValue);
                    textField.setEditable(false);

                }
                else{
                    textField.setText("");
                }
            }
            AllSudokuNumbers.remove(RandomIndex);
        }

    }

}
