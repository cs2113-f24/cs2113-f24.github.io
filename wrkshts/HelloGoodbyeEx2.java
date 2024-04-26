import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloGoodbyeEx2 {

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setTitle("Hello/Goodbye Ex1");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("Hello clicked 0 times.");
        JButton button = new JButton("Click me!");

        Integer count = 0;

        //using an anonymous (static) class
        //avoids having to make ButtonClickListenerEx1 class above
        button.addActionListener(new ActionListener() {
                //implement the one method here
                //shares the name space with the whole class
                //has access to the label field above
                public void actionPerformed(ActionEvent e) {
                    //int count = Integer.parseInt(label.getText().split(" times.")[0].split(" ")[2]) + 1;
                    //count = new Integer(count+1);
                    System.out.println(count);
                    if (label.getText().startsWith("Hello")) {
                        label.setText("Goodbye clicked " + count + " times.");
                    }else {
                        label.setText("Hello clicked " + count + " times.");
                    }
                }
            });
        
        f.add(button, BorderLayout.SOUTH);
        f.add(label, BorderLayout.NORTH);

        f.pack();
        f.setVisible(true);
        
    }
}