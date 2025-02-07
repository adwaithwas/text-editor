package notepadClone;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class notepadClone extends JFrame implements ActionListener {
    JTextArea area = new JTextArea();

    notepadClone(){
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1280, 720);
        setTitle("Notepad Clone");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("./assests/icon.jpg"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(226,207,226));

        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL", Font.ITALIC, 12));

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.ALT_MASK));
        exit.addActionListener(this);

        menubar.add(file);
        file.add(newdoc);
        file.add(save);
        file.add(open);
        file.add(exit);

        setJMenuBar(menubar);

        area.setFont(new Font("AERIAL",Font.PLAIN, 17));
        area.setBackground(new Color(225, 219, 225));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());

        add(pane);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")) {
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int action = chooser.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }

            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }
            catch (Exception e){
                System.err.println(e);
            }
        }

        else if(ae.getActionCommand().equals("Save")){
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("save");

            int action = saveas.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }

            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public static void main(String[] args){
        new notepadClone();
    }
}