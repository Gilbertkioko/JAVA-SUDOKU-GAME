import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ACTUAL_GAME {
    ArrayList<JTextField> TextFields;
    ArrayList<JTextField> Row1;
    ArrayList<JTextField> Row2;
    ArrayList<JTextField> Row3;
    ArrayList<JTextField> Row4;
    ArrayList<JTextField> Row5;
    ArrayList<JTextField> Row6;
    ArrayList<JTextField> Row7;
    ArrayList<JTextField> Row8;
    ArrayList<JTextField> Row9;
    ArrayList<JTextField> Column1;
    ArrayList<JTextField> Column2;
    ArrayList<JTextField> Column3;
    ArrayList<JTextField> Column4;
    ArrayList<JTextField> Column5;
    ArrayList<JTextField> Column6;
    ArrayList<JTextField> Column7;
    ArrayList<JTextField> Column8;
    ArrayList<JTextField> Column9;
    ArrayList<JTextField> Box1;
    ArrayList<JTextField> Box2;
    ArrayList<JTextField> Box3;
    ArrayList<JTextField> Box4;
    ArrayList<JTextField> Box5;
    ArrayList<JTextField> Box6;
    ArrayList<JTextField> Box7;
    ArrayList<JTextField> Box8;
    ArrayList<JTextField> Box9;

    ArrayList<ArrayList<JTextField>> AllGroups; //THIS ARRAYLIST IS OF TYPE ARRAYLIST<JTEXTFIELD>
    //I WILL USE IT TO STORE ALL 27 ARRAYLISTS (ROWS, COLUMNS AND BOXES) IN ONE ARRAYLIST,
    //AND CALL THEM ONE BY ONE SO THAT I WRITE VALIDATION CODE (RULES OF THE GAME) ONCE.


    /*NOW WE MAKE ARRAYLISTS, WHERE EACH ONE OF THEM HOLDS 9 TEXTFIELDS THAT CAN HOLD THE SAME NUMBER
    * WITHOUT BREAKING SUDOKU RULES. THESE ARE 9 TEXTBOXES THAT BELONG TO DIFFERENT ROWS, COLUMNS AND BOXES*/
    ArrayList<JTextField> Solution1;
    ArrayList<JTextField> Solution2;
    ArrayList<JTextField> Solution3;
    ArrayList<JTextField> Solution4;
    ArrayList<JTextField> Solution5;
    ArrayList<JTextField> Solution6;
    ArrayList<JTextField> Solution7;
    ArrayList<JTextField> Solution8;
    ArrayList<JTextField> Solution9;

    ArrayList<ArrayList<JTextField>> AllSolutions;
    public void FillArraylist(){
        Gui gui = new Gui();
        gui.BuildGui(); //CALL THE BUILDGUI METHOD TO DISPLAY THE GUI
        TextFields = gui.GetTextFieldArrayList(); //OBTAIN THE ARRAYLIST IN GUI CLASS

        Row1 = FillOneArraylist(0,1,2,9,10,11,18,19,20);
        Row2 = FillOneArraylist(3,4,5,12,13,14,21,22,23);
        Row3 = FillOneArraylist(6,7,8,15,16,17,24,25,26);
        Row4 = FillOneArraylist(27,28,29,36,37,38,45,46,47);
        Row5 = FillOneArraylist(30,31,32,39,40,41,48,49,50);
        Row6 = FillOneArraylist(33,34,35,42,43,44,51,52,53);
        Row7 = FillOneArraylist(54,55,56,63,64,65,72,73,74);
        Row8 = FillOneArraylist(57,58,59,66,67,68,75,76,77);
        Row9 = FillOneArraylist(60,61,62,69,70,71,78,79,80);

        Column1 = FillOneArraylist(0,3,6,27,30,33,54,57,60);
        Column2 = FillOneArraylist(1,4,7,28,31,34,55,78,61);
        Column3 = FillOneArraylist(2,5,8,29,32,35,56,59,62);
        Column4 = FillOneArraylist(9,12,15,36,39,42,63,66,69);
        Column5 = FillOneArraylist(10,13,16,37,40,43,64,67,70);
        Column6 = FillOneArraylist(11,14,17,38,41,44,65,68,71);
        Column7 = FillOneArraylist(18,21,24,45,48,51,72,75,78);
        Column8 = FillOneArraylist(19,22,25,46,49,52,73,76,79);
        Column9 = FillOneArraylist(20,23,26,47,50,53,74,77,80);

        Box1 = FillOneArraylist(0,1,2,3,4,5,6,7,8);
        Box2 = FillOneArraylist(9,10,11,12,13,14,15,16,17);
        Box3 = FillOneArraylist(18,19,20,21,22,23,24,25,26);
        Box4 = FillOneArraylist(27,28,29,30,31,32,33,34,35);
        Box5 = FillOneArraylist(36,37,38,39,40,41,42,43,44);
        Box6 = FillOneArraylist(45,46,47,48,49,50,51,52,53);
        Box7 = FillOneArraylist(54,55,56,57,58,59,60,61,62);
        Box8 = FillOneArraylist(63,64,65,66,67,68,69,70,71);
        Box9 = FillOneArraylist(72,73,74,75,76,77,78,79,80);

        Solution1 = FillOneArraylist(0,23,16,38,49,35,55,75,69);
        Solution2 = FillOneArraylist(1,13,26,32,44,45,63,60,76);
        Solution3 = FillOneArraylist(2,14,24,46,31,43,54,66,80);
        Solution4 = FillOneArraylist(3,17,19,28,42,50,72,67,62);
        Solution5 = FillOneArraylist(4,15,20,33,37,48,56,68,79);
        Solution6 = FillOneArraylist(5,10,25,27,39,53,58,65,78);
        Solution7 = FillOneArraylist(6,12,18,29,41,52,64,61,77);
        Solution8 = FillOneArraylist(7,11,22,36,30,51,74,59,70);
        Solution9 = FillOneArraylist(9,21,8,47,40,34,73,57,71);

        TextFieldValidation(); //CALL THIS PRIVATE METHOD

    }

    /*THE METHOD BELOW WILL HELP ME POPULATE ALL ARRAYLISTS WITH INDEXES OF THE TEXTFIELDS
    ARRAYLIST ACCORDINGLY
     */
    private ArrayList<JTextField> FillOneArraylist( int ... Indexes){
        //DAMN!! I JUST LEARNT ABOUT THIS int... Indexes!!!!
        ArrayList <JTextField> arrayList = new ArrayList<>();
        for (int index : Indexes){
            arrayList.add(TextFields.get(index));
        }
        return arrayList;
    }

private void TextFieldValidation(){
        //IN THIS METHOD WE WILL ENSURE THAT EVERY NUMBER WRITTEN IN EACH TEXTBOX FOLLOWS SUDOKU RULES
    //WE WILL DO THAT BY LOOPING THROUGH ALL TEXTFIELDS, AND ADDING A MOUSELISTENER TO THEM
    //IN ONE OF THE MOUSE LISTENERS, WE WILL CALL METHODS THAT CARRY THE ACTUAL LOGIC THAT IMPLEMENTS
    // THESE RULES
    for (int i=0; i<TextFields.size(); i++) {
        JTextField TextContent = TextFields.get(i);
        TextContent.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                //ONLY VALID NUMBERS ARE 1 TO 9
                CheckNumbersWithinBounds();
                CheckRepetitionorNot();
                CheckCorrectSection();
                FinishGame();
            }
        });
    }

}
private void CheckNumbersWithinBounds() {
   for (JTextField tf : TextFields){
       String Text = tf.getText().trim();
       if (Text.isEmpty()){
           continue; //SKIP EMPTY SPACES
       }
       int value;
       try {
           value = Integer.parseInt(Text);
       }
       catch(NumberFormatException ex){
           continue; //SKIP FOR NON-NUMERIC VALUES
       }
       if (tf.isEditable() == false){
           tf.setForeground(Color.BLACK);
       }
       else {
           if (value > 0 && value < 10) {
               tf.setForeground(Color.BLUE);
           }
           if (value < 1 || value > 9) {
               tf.setForeground(Color.ORANGE); //WILL AUTOMATICALLY TURN BLUE IF YOU FOLLOW RULES.
           }
       }

   }
}

private void CheckRepetitionorNot(){
        GroupArraylists(); //I'LL HAVE ONE METHOD THAT GROUPS THE 27 ARRAYLISTS INTO 1,
    // AND JUST CALL IT
    for (int i=0; i<AllGroups.size(); i++){
        ArrayList<JTextField> CurrentArraylist = AllGroups.get(i);

        for (JTextField CurrentTextField : CurrentArraylist){
// GO THROUGH EVERY ARRAYLIST, TAKING EVERY TEXTFIELD IN THAT ARRAYLIST AND PUTTING IT INTO THE
            //CURRENTTEXTFIELD TEXTFIELD, THEN DO THE FOLLOWING
            String CurrentText = CurrentTextField.getText().trim();
            if (CurrentText.isEmpty()){
                continue;
            }
            else {
                //IF THE CURRENT TEXTFIELD WHERE THE MOUSE HAS JUST EXISTED HAS TEXT, THEN
                // DO THE FOLLOWING:

                for (JTextField OtherTextField : CurrentArraylist) {
                    //GO THROUGH THE SELECTED ARRAYLIST AGAIN AND PUT EVERY TEXTFIELD IN THAT ARRAYLIST
                    //IN THE TEXTFIELD OTHERTEXTFIELD, ONE BY ONE. THIS WILL ENABLE YOU TO COMPARE
                    //CURRENT TEXTFIELD WITH ALL OTHER TEXTFIELDS.
                    if (CurrentTextField != OtherTextField) {
                        //IF CURRENT TEXTFIELD IS NOT ALSO THE OTHER TEXTFIELD:
                        String Text1 = CurrentTextField.getText().trim();
                        int num1;
                        try {
                            num1 = Integer.parseInt(Text1);
                        } catch (NumberFormatException ex){
                            continue; //SKIP FOR NON-NUMERIC VALUES.
                        }

                        String Text2 = OtherTextField.getText().trim();
                        if (Text2.isEmpty()){
                            continue; //SKIP IF THE OTHER TEXTFIELD DOESN'T HAVE A NUMBER
                        }
                        else{
                            int num2;
                            try {
                                num2 = Integer.parseInt(Text2);
                            }
                            catch (NumberFormatException ex){
                                continue; //SKIP FOR NON NUMBERS
                            }

                            if (num1 == num2){
                                CurrentTextField.setForeground(Color.RED);
                                OtherTextField.setForeground(Color.RED);
                            }

                        }




                    }
                }
            }
        }
    }
}

private void CheckCorrectSection(){
        //IF YOU'VE GOTTEN A ROW, COLUMN OR BOX CORRECT, LET IT TURN GREEN
   for (int i=0; i<AllGroups.size(); i++){
       ArrayList<JTextField> CurrentArraylist = AllGroups.get(i);

       ArrayList<JTextField> AnotherArraylist = new ArrayList<>();

       for (JTextField CurrentTextfield : CurrentArraylist){
           if (CurrentTextfield.getText().isEmpty()){
               continue;
           }

           if (CurrentTextfield.getForeground().equals(Color.ORANGE) ||
                           CurrentTextfield.getForeground().equals(Color.RED)
                   ){
               break;
           }

           else {
               AnotherArraylist.add(CurrentTextfield);
           }
       }
       if (AnotherArraylist.size() == 9){
           for (JTextField tf : AnotherArraylist){
               tf.setForeground(Color.GREEN);
           }
       }
   }

}

private void FinishGame(){
        ArrayList<JTextField> GreenTextfields = new ArrayList<>();
        for (JTextField tf : TextFields){
            //ENHANCED LOOP THAT GOES THROUGH ALL 81 TEXTFIELDS.
            if (tf.getForeground().equals(Color.GREEN)){
                GreenTextfields.add(tf);
            }
        }

        if (GreenTextfields.size() == 81){
            Gui g = new Gui();
            g.StopTimer();
        }
}

private void GroupArraylists(){
        AllGroups = new ArrayList<>();

        AllGroups.add(Row1);
    AllGroups.add(Row2);
    AllGroups.add(Row3);
    AllGroups.add(Row4);
    AllGroups.add(Row5);
    AllGroups.add(Row6);
    AllGroups.add(Row7);
    AllGroups.add(Row8);
    AllGroups.add(Row9);

    AllGroups.add(Column1);
    AllGroups.add(Column2);
    AllGroups.add(Column3);
    AllGroups.add(Column4);
    AllGroups.add(Column5);
    AllGroups.add(Column6);
    AllGroups.add(Column7);
    AllGroups.add(Column8);
    AllGroups.add(Column9);

    AllGroups.add(Box1);
    AllGroups.add(Box2);
    AllGroups.add(Box3);
    AllGroups.add(Box4);
    AllGroups.add(Box5);
    AllGroups.add(Box6);
    AllGroups.add(Box7);
    AllGroups.add(Box8);
    AllGroups.add(Box9);

}


private void GroupAllSolutions (){
        AllSolutions = new ArrayList<>();
        AllSolutions.add(Solution1);
    AllSolutions.add(Solution2);
    AllSolutions.add(Solution3);
    AllSolutions.add(Solution4);
    AllSolutions.add(Solution5);
    AllSolutions.add(Solution6);
    AllSolutions.add(Solution7);
    AllSolutions.add(Solution8);
    AllSolutions.add(Solution9);
}

public ArrayList<ArrayList<JTextField>> GetAllSolutions (){
        GroupAllSolutions();
        return AllSolutions;
}
}
