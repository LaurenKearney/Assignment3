import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MenuBar extends JFrame {
    //creates a file action instance
    MenuActions fileActions = new MenuActions();
    //Menu bar
    JMenuBar menuBar = new JMenuBar();
    
    //menu bar file dropdown elements
    JMenu fileDropdown = new JMenu("File");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem printFile = new JMenuItem("Print File");
    JMenuItem saveFile = new JMenuItem("Save File");
    JMenuItem newFile = new JMenuItem("New File");
    JMenu recentFile = new JMenu("Recent");
    
    //menu bar edit drop down elements
    JMenu editDropdown = new JMenu("Edit");
    JMenuItem replace = new JMenuItem("Replace");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem copy = new JMenuItem("Copy");
    
    //Array lists
    ArrayList<JMenuItem> displayedRecents = new ArrayList<>(); //array of items
    ArrayList<File> recentFiles = new ArrayList<>(); //array of names


    //initiates all GUI aspects
    public MenuBar(){
    	//add File dropdown and elements to menu bar
        menuBar.add(fileDropdown);
        fileDropdown.add(open);
        fileDropdown.add(printFile);
        fileDropdown.add(saveFile);
        fileDropdown.add(newFile);
        fileDropdown.add(recentFile);
        
        //add Edit dropdown and elements to menu bar
        menuBar.add(editDropdown);
        editDropdown.add(replace);
        editDropdown.add(paste);
        editDropdown.add(copy);

        //File Action listeners
        open.addActionListener(e -> { 
            fileActions.open();
            updateRecents(); });
        printFile.addActionListener(e -> fileActions.printFile());
        saveFile.addActionListener(e -> {
            fileActions.saveFile();
            updateRecents(); });
        newFile.addActionListener(e -> fileActions.newFile());

        //Edit Action Listners
        replace.addActionListener(e -> fileActions.textReplace());
        paste.addActionListener(e -> fileActions.paste());
        copy.addActionListener((e)-> fileActions.copy());


        setTitle("A Simple Notepad Tool");//title
        setJMenuBar(menuBar);
        add(new JScrollPane(fileActions.getjTextPane()));
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


    //Sets up all of the "Open Recent" file options
    public void updateRecents(){
    	//clear out previous information
        recentFile.removeAll();
        displayedRecents.clear();
        
        //get updated list of recent files
        recentFiles = fileActions.getRecentFiles();
        
        //turn list into selectable files in recent option
        for(int i = 0; i<recentFiles.size(); i++){ //iterate through recents
            JMenuItem fileName = new JMenuItem(recentFiles.get(i).getName()); //get file name
            String path = recentFiles.get(i).getAbsolutePath(); //get file path
            fileName.addActionListener(e -> { //create action listener
                fileActions.openRecentFile(path); //open recent file from path
                updateRecents(); //re-update recents
            });
            //add file names to recents menu
            displayedRecents.add(fileName);
            recentFile.add(fileName);
        }
    }
    



}


