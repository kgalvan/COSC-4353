package MasterMindGui;

import mastermind.MasterMind;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MasterMindGui {

    static MasterMind mastermind;
    private static int submitList_x;
    private static int submitList_y;
    private static int numClicks = 0;
    public static void main(String[] args)
    {
        init();
    }

    private static void init()
    {
        numClicks = 0;
        submitList_x = 50;
        submitList_y = 100;
        mastermind = new MasterMind();
        ColorBox[] arrayOfColorBoxes = new ColorBox[6];
        List<Color> allAvailableColors = mastermind.getAvailableColors();
        Font sysMessageFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        Font attemptFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        Font availColorsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        JFrame frame = new JFrame("MasterMind");
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode("#FAFAFA"));
        frame.setLayout(null);
        JButton submitButton = new JButton("Submit");
        JButton forfeitButton = new JButton("Forfeit");
        submitButton.setBorder(new LineBorder(Color.BLACK));
        forfeitButton.setBorder(new LineBorder(Color.BLACK));
        ColorBox firstBox = new ColorBox();
        ColorBox secondBox = new ColorBox();
        ColorBox thirdBox = new ColorBox();
        ColorBox fourthBox = new ColorBox();
        ColorBox fifthBox = new ColorBox();
        ColorBox sixthBox = new ColorBox();
        arrayOfColorBoxes[0] = firstBox;
        arrayOfColorBoxes[1] = secondBox;
        arrayOfColorBoxes[2] = thirdBox;
        arrayOfColorBoxes[3] = fourthBox;
        arrayOfColorBoxes[4] = fifthBox;
        arrayOfColorBoxes[5] = sixthBox;
        JTextField numAttemptsLeft = new JTextField("Attempts Left: " + mastermind.getAttemptsLeft());
        JTextField systemMessage = new JTextField("Welcome! Click the Rectangles to cycle through your guesses");
        JTextField availableMessage = new JTextField("All Available Colors:");
        availableMessage.setEditable(false);
        numAttemptsLeft.setEditable(false);
        systemMessage.setEditable(false);

        numAttemptsLeft.setFont(attemptFont);
        systemMessage.setFont(sysMessageFont);
        forfeitButton.setFont(buttonFont);
        submitButton.setFont(buttonFont);
        availableMessage.setFont(availColorsFont);

        frame.setBounds(200,200,1000,1000);

        for(int x = 0; x <5; x++)
        {
            ColorBox topbox = new ColorBox(allAvailableColors.get((x)));
            ColorBox bottombox = new ColorBox(allAvailableColors.get((x+5)));
            topbox.setEnabled(false);
            bottombox.setEnabled(false);
            topbox.setBounds(612+(55*x),475,50,50);
            bottombox.setBounds(612+(55*x),550,50,50);
            frame.add(topbox);
            frame.add(bottombox);
        }

        forfeitButton.setBounds(200,850,100,100);
        forfeitButton.setBorder(new LineBorder(Color.BLACK));
        submitButton.setBounds(500,850,100,100);
        submitButton.setBorder(new LineBorder(Color.BLACK));

        forfeitButton.setBackground(Color.decode("#FAFAFA"));
        forfeitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){

               List<Color> solution = mastermind.getSolution();

               systemMessage.setText("Welcome! Click the rectangles to cycle through your guesses");
               for (int i = 0; i < 6; i++) {
                   arrayOfColorBoxes[i].setBackground(Color.WHITE);
               }
               forfeitButton.setText("Forfeit");
               numAttemptsLeft.setText("Attempts Left: " + mastermind.getAttemptsLeft());

               systemMessage.setText("Failed! Here is the solution:");
               forfeitButton.setText("Play Again");
               firstBox.setBackground(solution.get(0));
               secondBox.setBackground(solution.get(1));
               thirdBox.setBackground(solution.get(2));
               fourthBox.setBackground(solution.get(3));
               fifthBox.setBackground(solution.get(4));
               sixthBox.setBackground(solution.get(5));

               submitButton.setEnabled(false);

               submitList_x = 50;
               submitList_y = 100;
               if(numClicks > 0) {
                   numClicks = 0;
                   frame.dispose();
                   init();
               }
               else
                    numClicks++;
            }
        });
        submitButton.addActionListener(new ActionListener() {
            MasterMindGui gui = new MasterMindGui();
            public void actionPerformed(ActionEvent ae){
                if (mastermind.getGameStatus() == MasterMind.GameStatus.WON || mastermind.getGameStatus() == MasterMind.GameStatus.LOST) {
                    if(mastermind.getGameStatus() == MasterMind.GameStatus.LOST)
                    {
                        List<Color> solution = mastermind.getSolution();
                        firstBox.setBackground(solution.get(0));
                        secondBox.setBackground(solution.get(1));
                        thirdBox.setBackground(solution.get(2));
                        fourthBox.setBackground(solution.get(3));
                        fifthBox.setBackground(solution.get(4));
                        sixthBox.setBackground(solution.get(5));
                    }
                    submitButton.setEnabled(false);
                    systemMessage.setText("Game Over");
                    forfeitButton.setText("Play Again");
                }
                else {
                    Map<MasterMind.Response, Long> response;
                    List<Color> userGuess = gui.getUserGuess(firstBox, secondBox, thirdBox, fourthBox, fifthBox, sixthBox);
                    response = mastermind.guess(userGuess);
                    List<Color> responseColors = new ArrayList<Color>();
                    responseColors = gui.setResponse(response);

                    for (int i = responseColors.size(); i < 6; i++) {
                        responseColors.add(Color.WHITE);
                    }
                    for(int x = 0; x < 6; x++)
                    {
                        ColorBox userBox = new ColorBox(userGuess.get(x));
                        ColorBox resultBox = new ColorBox(responseColors.get(x));

                        userBox.setBorder(new LineBorder(Color.BLACK));
                        resultBox.setBorder(new LineBorder(Color.BLACK));
                        userBox.setBounds(submitList_x, submitList_y,30,30);
                        resultBox.setBounds(submitList_x+225,submitList_y,30,30);
                        userBox.setVisible(true);
                        resultBox.setVisible(true);
                        frame.add(userBox);
                        frame.add(resultBox);
                        submitList_x +=35;
                        frame.setVisible(true);
                        frame.repaint();
                    }
                    submitList_y +=35;
                    submitList_x = 50;
                    frame.validate();

                    if (mastermind.getGameStatus() == MasterMind.GameStatus.WON) {
                        systemMessage.setText("Congratulations! You won!");
                        numClicks++;
                    } else if (mastermind.getGameStatus() == MasterMind.GameStatus.LOST) {
                        systemMessage.setText("Sorry. You lost, Click submit to see the solution!");
                        numClicks++;
                    } else if (mastermind.getGameStatus() == MasterMind.GameStatus.IN_PROGRESS) {
                        systemMessage.setText("Keep trying!");
                    }
                    numAttemptsLeft.setText("Attempts Left: " + Integer.toString(mastermind.getAttemptsLeft()));
                }
            }
        });
        submitButton.setBackground(Color.decode("#FAFAFA"));

        firstBox.setBounds(600,100,50,300);
        firstBox.setBorder(new LineBorder(Color.BLACK));
        firstBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                firstBox.changeColor();
            }
        });
        secondBox.setBounds(650,100,50,300);
        secondBox.setBorder(new LineBorder(Color.BLACK));
        secondBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                secondBox.changeColor();
            }
        });
        thirdBox.setBounds(700,100,50,300);
        thirdBox.setBorder(new LineBorder(Color.BLACK));
        thirdBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                thirdBox.changeColor();
            }
        });
        fourthBox.setBounds(750,100,50,300);
        fourthBox.setBorder(new LineBorder(Color.BLACK));
        fourthBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                fourthBox.changeColor();
            }
        });
        fifthBox.setBounds(800,100,50,300);
        fifthBox.setBorder(new LineBorder(Color.BLACK));
        fifthBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                fifthBox.changeColor();
            }
        });
        sixthBox.setBounds(850,100,50,300);
        sixthBox.setBorder(new LineBorder(Color.BLACK));
        sixthBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                sixthBox.changeColor();
            }
        });

        numAttemptsLeft.setBounds(700,700,300,100);
        systemMessage.setBounds(200, 20, 800, 80);
        availableMessage.setBounds(665,400,200,100);
        numAttemptsLeft.setBorder(null);
        systemMessage.setBorder(null);
        availableMessage.setBorder(null);
        numAttemptsLeft.setBackground(null);
        systemMessage.setBackground(null);
        availableMessage.setBackground(null);
        frame.add(numAttemptsLeft);
        frame.add(systemMessage);
        frame.add(availableMessage);
        frame.add(firstBox);
        frame.add(secondBox);
        frame.add(thirdBox);
        frame.add(fourthBox);
        frame.add(fifthBox);
        frame.add(sixthBox);
        frame.add(submitButton);
        frame.add(forfeitButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public List<Color> getUserGuess (ColorBox box1, ColorBox box2, ColorBox box3, ColorBox box4, ColorBox box5, ColorBox box6) {
        List<Color> userGuess = new ArrayList<Color>();
        userGuess.add(box1.getBackground());
        userGuess.add(box2.getBackground());
        userGuess.add(box3.getBackground());
        userGuess.add(box4.getBackground());
        userGuess.add(box5.getBackground());
        userGuess.add(box6.getBackground());
        return userGuess;

    }

    public List<Color> setResponse (Map<MasterMind.Response, Long> response) {
        
        List<Color> colorList = new ArrayList<>();
        for (int i = 0; i < (int) (long)response.get(MasterMind.Response.POSITIONAL_MATCH); i++) {
            colorList.add(Color.BLACK);
        }
        for (int i = 0; i < (int) (long)response.get(MasterMind.Response.MATCH); i++) {
            colorList.add(Color.GRAY);
        }
        return colorList;
    }
}
class ColorBox extends JButton
{
    Color color;
    public ColorBox() {
        super();
        color = Color.WHITE;
        setBackground(color);
    }

    public ColorBox(Color currentColor) {
        super();
        color = currentColor;
        setBackground(color);
    }

    public Color[] getAvailableColors() {
        Color[]allColorArray = new Color[]{Color.RED,Color.BLUE,Color.CYAN,Color.YELLOW,Color.GREEN,Color.ORANGE,Color.PINK,Color.MAGENTA,Color.BLACK,Color.GRAY};
        return allColorArray;
    }

    public void changeColor() {
        Color[] availableColors = getAvailableColors();

        if(color.equals(Color.WHITE))
            color = Color.RED;

        else if(color.equals(Color.GRAY)) {
            color = Color.RED;
        }

        else{
            for(int x = 0; x <10; x++) {
                if (color.equals(availableColors[x])) {
                    color = availableColors[x+1];
                    break;
                }
            }
        }
        this.setBackground(color);
    }
}
