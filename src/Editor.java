import javax.swing.*;
import java.awt.*;

public class Editor extends JFrame
{
    public static Editor instance;
    public static FTabPane tabPane;
    public static FileManager fileManager;

    public FMenuBar fmenuBar;

    public static void main(String[] args)
    {
        fileManager = new FileManager();
        Editor window = new Editor();
    }

    public Editor()
    {
        instance = this;

        // Application Setup
        setTitle("Text Falcon");

        Toolkit toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setBounds(screenSize.width/4,screenSize.height/4,  // Position
                screenSize.width/2,screenSize.height/2);  // Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout & Container
        BorderLayout layout = new BorderLayout(); // North, East, West, South, and Center Grid
        Container container = getContentPane();
        container.setLayout(layout);

        // Construction
        tabPane = new FTabPane();
        tabPane.addTTab();
        container.add(tabPane, BorderLayout.CENTER);

        fmenuBar = new FMenuBar();
        setJMenuBar(fmenuBar);

        // Make the Window Visible
        setVisible(true);
    }

    public void CloseEditor()
    {
        Editor.instance.setVisible(false);
        Editor.instance.dispose();
    }
}
