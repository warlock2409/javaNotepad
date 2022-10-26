import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

public class AppFrame extends JFrame implements ActionListener,KeyListener {
    JFrame frame;
    JTextArea textArea;
    JLabel label;
    JLabel lableWords;
    AppFrame(){
//        frame=new JFrame();
        ImageIcon icon = new ImageIcon("src/P-amazing-3D-logo-design-concept-in-20211.jpg");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setTitle("Java gui project");
        this.setIconImage(icon.getImage());

//        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(246, 221, 204 ));
        // text area
        textArea = new JTextArea();
        this.setResizable(false);
        textArea.setBounds(10,35,1000,1000);
        //menu bar
        JMenuBar menuBar = new JMenuBar();

        //button
        JButton button = new JButton("Refresh");
        button.setBounds(10,5,80, 20);//x axis, y axis, width, height
        //label
        label=new JLabel();
        lableWords=new JLabel("Words");
        lableWords.setBounds(410,5,100,20);
        label.setBounds(300,5,100,20);
        button.setBounds(10,5,80, 20);
        label.setText("Character");


        JMenu fileMenu = new JMenu("File");
        JMenuItem f1=new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Save");
        JMenuItem f3 = new JMenuItem("Open");
        JMenuItem f4 = new JMenuItem("Print");
        fileMenu.add(f1);
        fileMenu.add(f2);
        fileMenu.add(f3);
        fileMenu.add(f4);
        JMenu editMenu = new JMenu("Edit");
        JMenuItem e1 = new JMenuItem("UnDo");
        JMenuItem e2 = new JMenuItem("Cut");
        JMenuItem e3 = new JMenuItem("Copy");
        JMenuItem e4 = new JMenuItem("Paste");
        editMenu.add(e1);
        editMenu.add(e2);
        editMenu.add(e3);
        editMenu.add(e4);
        JMenu helpMenu = new JMenu("Help");
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);
        e4.addActionListener(this);
        textArea.addKeyListener((KeyListener) this);
        button.addActionListener(this);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
        this.setVisible(true);
        this.add(button);
        this.add(label);
        this.add(lableWords);
        this.setLayout(null);
        this.add(textArea);



    }
    public void actionPerformed (ActionEvent e) {
        // your code
        System.out.print(e.getActionCommand());
        String characters = textArea.getText();
        String words[]=characters.split("\\s");
        int len = characters.length();
        String req=e.getActionCommand();
        if(req == "New"){
            new AppFrame();
        }else if (req == "Save") {
            JFileChooser directory = new JFileChooser("y:");
            int res = directory.showSaveDialog(null);
            System.out.println(res);

            if(res == JFileChooser.APPROVE_OPTION){
//                File file = new File(directory.getSelectedFile().getAbsoluteFile().toURI());
                String file = String.valueOf(directory.getSelectedFile());

                System.out.print(file);
                try{
                    FileWriter write = new FileWriter(file,false);
                    BufferedWriter BWriter = new BufferedWriter(write);
                    BWriter.write(textArea.getText());
                    BWriter.flush();
                    BWriter.close();

                } catch ( IOException ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }
            }

        }else if (req == "Open") {
            JFileChooser directory = new JFileChooser("D:");
            int res = directory.showOpenDialog(null);
            if(res == JFileChooser.APPROVE_OPTION){
                String s1 ="";
                String s2 ="";
                String file = String.valueOf(directory.getSelectedFile());
                try{
                    FileReader read = new FileReader(file);
                    BufferedReader BRead = new BufferedReader(read);
                    while((s2=BRead.readLine()) != null){
                        s1+="\n"+s2;
                    }
                    textArea.setText(s1);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }else if (req == "Print") {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        }else if (req == "UnDo") {

        }else if (req == "Cut") {
            textArea.cut();
        }else if (req == "Copy") {
            textArea.copy();
        }else if (req == "Paste") {
            textArea.paste();
        }else if (req == "Refresh") {
            label.setText("Characters"+":"+len);
            lableWords.setText("Words"+":"+words.length);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        String characters = textArea.getText();
        String words[]=characters.split("\\s");
        int len = characters.length();
        label.setText("Characters"+":"+len);
        lableWords.setText("Words"+":"+words.length);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
