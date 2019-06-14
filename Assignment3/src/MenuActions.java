import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MenuActions {

    public JTextPane textPane = new JTextPane();
    private ArrayList<String> recentFiles = new ArrayList<>();
    
    //constructor
    public MenuActions(){ }

    //returns textpane object
    public JTextPane getjTextPane(){
        return textPane;
    }
    
    
    //makes sure recents does not extend past 4
    private void checkArraySize(){
        if(recentFiles.size()>5){
            recentFiles.remove(0);
            checkArraySize();
        }
    }
    //make sure recents does not contain duplicates
    private boolean checkArrayContent(String filePath){
        boolean alreadyExists = false;
        for(String path : recentFiles){
            if(path.equals(filePath)){
                alreadyExists= true;
            }
        }
        return alreadyExists;
    }
    
    
    //File Open Action
    public void open(){
    	//choosing a file
        JFileChooser fileToOpen = new JFileChooser();
        int returnVal = fileToOpen.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            //opening a file
            File openFile = fileToOpen.getSelectedFile();
            textPane.setText(getFileContent(openFile));//puts file contents in text pane
            if(!checkArrayContent(openFile.getAbsolutePath())) {
                recentFiles.add(openFile.getAbsolutePath());
            }else{
                recentFiles.remove(openFile.getAbsolutePath());
                recentFiles.add(openFile.getAbsolutePath());
            }
            checkArraySize();//limit recents
        }
    }
   
    //this method gets file content
    public String getFileContent(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            //while file is not blank
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line+"\n"); //get contents
            }
        }catch (IOException e){
            System.out.println("File not found");
        }
        return stringBuilder.toString();
    }
    
    
    //File Print File Method
    public void printFile(){
        try{
            PrinterJob printJob = PrinterJob.getPrinterJob();
//            printJob.setJobName("Sample Command Pattern");
            printJob.setCopies(1);
            printJob.setPrintable(new Printable() {
                public int print(Graphics pg, PageFormat pf, int pageNum) {
                    if (pageNum>0)
                        return Printable.NO_SUCH_PAGE;
                    pg.drawString(textPane.getText(), 500, 500);
                    textPane.paint(pg);
                    return Printable.PAGE_EXISTS;
                }
            });
            if (printJob.printDialog() == false)
                return;
            printJob.print();
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null,
                    "Printer error" + pe, "Printing error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    //File save file
    public void saveFile(){
        File fileToWrite = fileChooser(); //gets file
        if(!checkArrayContent(fileToWrite.getAbsolutePath())) { //if file is not in recents
            recentFiles.add(fileToWrite.getAbsolutePath()); //add file to recents
        }
        checkArraySize();//limit recents
        
        try {
            PrintWriter output = new PrintWriter(new FileWriter(fileToWrite));
            output.println(textPane.getText());
            JOptionPane.showMessageDialog(null, "File saved successfully...");
            output.close();
        } catch (IOException ex) {}
    }
    
    //this spits out a menu to choose a spot for a file, and then returns that file
    public File fileChooser(){
        File fileToWrite = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileToWrite = fileChooser.getSelectedFile();
        }
        return fileToWrite;
    }
    
    
    //file newFile
    public void newFile(){
        textPane.setText(""); //clears textPane
    }

    //turns recents into an arraylist of files
    public ArrayList<File> getRecentFiles(){//specifically recent files
        ArrayList<File> allRecentFiles = new ArrayList<>();
        for(int i = 0; i<recentFiles.size(); i++){
            File file = new File(recentFiles.get(i));
            allRecentFiles.add(file);
        }
        Collections.reverse(allRecentFiles);//reverse list so most recent is on top
        return allRecentFiles;
    }

    //this method will the chosen recent file
    public void openRecentFile(String filePath){
        File file = new File(filePath);
        textPane.setText(getFileContent(file));
    }

    
    //Edit Replace Action
    public void textReplace(){
        String newText = JOptionPane.showInputDialog("Replace or insert with: ");
        if(!newText.equals("") && newText != null) {
            textPane.replaceSelection(newText);
        }
    }

    
    //Edit Paste Function
    public void paste(){
        textPane.paste();
    }

    
    //Edit copy Function
    public void copy(){
        textPane.copy();
    }

}
