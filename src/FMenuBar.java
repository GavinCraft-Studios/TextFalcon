import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class FMenuBar extends JMenuBar
{
    public FMenuBar()
    {
        JMenu folder = new JMenu("Folder");

        JMenuItem openFolder = folder.add("Open Folder");
        folder.addSeparator();
        JCheckBoxMenuItem folderView = new JCheckBoxMenuItem("View Folder");
        folderView.addItemListener(new FolderViewListener());
        folder.add(folderView);


        JMenu file = new JMenu("File");

        JMenuItem newFile = file.add("New");
        newFile.addActionListener(new NewFileListener());
        JMenuItem openFile = file.add("Open");
        openFile.addActionListener(new OpenFileListener());
        file.addSeparator();
        JMenuItem saveFile = file.add("Save");
        saveFile.addActionListener(new SaveFileListener());
        JMenuItem saveFileAs = file.add("Save As");
        saveFileAs.addActionListener(new SaveAsFileListener());
        //JCheckBoxMenuItem autoSave = new JCheckBoxMenuItem("Auto Save"); TODO <-------------
        //file.add(autoSave);
        file.addSeparator();
        file.add(folder);
        file.addSeparator();
        JMenuItem closeFile = file.add("Close");
        closeFile.addActionListener(new CloseFileListener());
        add(file);
    }

    class FolderViewListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Editor.fileTree.setVisible(true);
            }
            else {
                Editor.fileTree.setVisible(false);
            }
        }
    }

    class NewFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.tabPane.addTTab();
        }
    }

    class OpenFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File selectedFile = Editor.fileManager.chooseFile();

            if (selectedFile != null)
            {
                if (!Editor.tabPane.checkHasFile())
                {
                    // Refile Current Tab
                    Editor.tabPane.refileTTab(selectedFile);
                    Editor.fileManager.openFile(selectedFile);
                }
                else
                {
                    // Make Tab For File
                    Editor.tabPane.addTTab(selectedFile);
                    Editor.fileManager.openFile(selectedFile);
                }
            }
        }
    }

    class SaveFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Editor.tabPane.checkHasFile() == true)
            {
                Editor.fileManager.saveFile(Editor.tabPane.getCurrentFile());
            }
            else
            {
                File location = Editor.fileManager.chooseFile();
                Editor.fileManager.saveFile(location);
                Editor.tabPane.refileTTab(location);
            }
        }
    }

    class SaveAsFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            File location = Editor.fileManager.chooseFile();

            if (Editor.tabPane.checkHasFile())
            {
                Editor.fileManager.saveFile(location);
                Editor.tabPane.addTTab(location);
                Editor.fileManager.openFile(location);
            }
            else
            {
                Editor.fileManager.saveFile(location);
                Editor.tabPane.refileTTab(location);
                Editor.fileManager.openFile(location);
            }
        }
    }

    class CloseFileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.tabPane.closeTTab();
        }
    }
}
