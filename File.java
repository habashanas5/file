package File;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

//class to define GUI interface
class one extends JFrame implements ActionListener, WindowListener {

    private JLabel label, label2, label3, label4;
    private JPanel panel1, panel2, panel3;
    private JTextArea area;
    private JFileChooser choose;
    private File file;
    private JButton btn;

    public one() {
        super();
        setTitle("Files");
        label = new JLabel("Please choose a file:");
        TitledBorder titled = new TitledBorder("For File Chooser:");

        btn = new JButton("Go");
        btn.addActionListener(this);

        panel1 = new JPanel();
        panel1.setBorder(titled);
        panel1.setLayout(new FlowLayout());
        panel1.add(label);
        panel1.add(btn);
        add(panel1, BorderLayout.NORTH);

        area = new JTextArea();
        panel2 = new JPanel(new GridLayout());
        panel2.add(area);
        add(new JScrollPane(panel2), BorderLayout.CENTER);

        TitledBorder titled2 = new TitledBorder("Display:");
        area.setBorder(titled2);

        label2 = new JLabel("                                                                        "
                + "Please click on button (go) to select a file or directory");
        label3 = new JLabel("if you press twice on the directory it will be open it ,elsewise if you press one click to directory  it will selected it");
        label4 = new JLabel("note: single file and  directory will display in JTextarea ,elsewise recursively display all files and directories will display on (system.out.println)");
        label2.setSize(500, 500);

        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        panel3.add(label2);
        panel3.add(Box.createRigidArea(new Dimension(0, 15)));
        panel3.add(label3);
        panel3.add(Box.createRigidArea(new Dimension(0, 15)));
        panel3.add(label4);
        panel3.add(Box.createRigidArea(new Dimension(0, 15)));
        add(panel3, BorderLayout.SOUTH);

        setSize(820, 500);
        setLocation(400, 100);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int option = JOptionPane.showConfirmDialog(null, "Do you to exit your application?", "Asking to save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        JOptionPane.showMessageDialog(null, "Exiting the application");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        Component A[] = getComponents();
        for (int i = 0; i < A.length; i++) {
            A[i].setEnabled(false);
        }
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        Component A[] = getComponents();
        for (int i = 0; i < A.length; i++) {
            A[i].setEnabled(true);
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object T = e.getSource();
        if (T == btn) {

            try {
                JFileChooser chooser = new JFileChooser();

                chooser.setCurrentDirectory(new java.io.File("./"));
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.setAcceptAllFileFilterUsed(false);

                int t = chooser.showOpenDialog(null);
                File folder = new File(chooser.getSelectedFile().getAbsolutePath());

                System.out.println("Reading files under the folder " + folder.getPath());
                System.out.println();
                if (t == JFileChooser.APPROVE_OPTION) {
                    area.setText("Name of the file: " + "      " + chooser.getSelectedFile().getName() + "\n\n"
                            + "Size of the file: " + "      " + chooser.getSelectedFile().length() + "\n\n"
                            + "Parent of the file: " + "      " + chooser.getSelectedFile().getPath() + "\n\n"
                            + "Canonical of the file: " + "      " + chooser.getSelectedFile().getCanonicalPath() + "\n\n"
                            + "Absolute path: " + "      " + chooser.getSelectedFile().getAbsolutePath() + "\n\n"
                            + "File can read : " + "      " + chooser.getSelectedFile().canRead() + "\n\n"
                            + "File can write : " + "      " + chooser.getSelectedFile().canWrite() + "\n\n"
                            + "File can execute : " + "      " + chooser.getSelectedFile().canExecute() + "\n\n"
                            + "File is hidden : " + "      " + chooser.getSelectedFile().isHidden() + "\n\n"
                            + "File is file : " + "      " + chooser.getSelectedFile().isFile() + "\n\n"
                            + "File is directory : " + "      " + chooser.getSelectedFile().isDirectory());

                    listFilesForFolder(folder);

                }
            } catch (Exception a) {

            }
        }
    }

    public static void listFilesForFolder(final File folder) throws IOException {
        folder.listFiles();
        if (folder.isFile()) {
            System.out.println("Name of the file: " + "      " + folder.getName() + "\n\n"
                    + "Size of the file: " + "      " + folder.length() + "\n\n"
                    + "Parent of the file: " + "      " + folder.getPath() + "\n\n"
                    + "Canonical of the file: " + "      " + folder.getCanonicalPath() + "\n\n"
                    + "Absolute path: " + "      " + folder.getAbsolutePath() + "\n\n"
                    + "File can read : " + "      " + folder.canRead() + "\n\n"
                    + "File can write : " + "      " + folder.canWrite() + "\n\n"
                    + "File can execute : " + "      " + folder.canExecute() + "\n\n"
                    + "File is hidden : " + "      " + folder.isHidden() + "\n\n"
                    + "File is file : " + "      " + folder.isFile() + "\n\n"
                    + "File is directory : " + "      " + folder.isDirectory() + "\n\n"
                    + "*************************************************");

        } else if (folder.isDirectory()) {
            File xx[] = folder.listFiles();
            for (int i = 0; i < xx.length; i++) {
                listFilesForFolder(xx[i]);
            }
        }
    }
}

public class File {

    static String temp = "";

    public static void main(String[] args) throws IOException {
        new one();
    }
}
