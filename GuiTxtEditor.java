import java.swing.*;
import java.swing.text;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Hashtable;
class GuiTxtEditor extends JFrame
{
	Action openAction = new OpenAction();
	Action saveAction = new SaveAction();
	JTextComponent tComp;
	public GuiTxtEditor()
	{
		super("Sanando's Editor");
		tComp = createTextComponent();
		defineActions();
		Container cont = getContentPane();
		cont.add(tComp,BorderLayout.CENTER);
		cont.add(createToolBar(),BorderLayout.EAST);
		setJMenuBar(createMenuBar());
		setSize(400,400);
	}
	JTextComponent createTextComponent()
	{
		JTextArea tArea = new JTextArea();
		tArea.setLineWrap(true);
		return tArea;
	}
	void defineActions()
	{
		Action a;
		a=tComp.getActionMap().get(DefaultEditorKit.cutAction);
		a.putValue(Action.SMALL_ICON, new ImageIcon("icons/cut.gif"));
		a.putValue(Action.NAME,"Cut");
		a=tComp.getActionMap().get(DefaultEditorKit.copyAction);
		a.putValue(Action.SMALL_ICON, new ImageIcon("icons/copy.gif"));
		a.putValue(Action.NAME,"Copy");
		a=tComp.getActionMap().get(DefaultEditorKit.pasteAction);
		a.putValue(Action.SMALL_ICON, new ImageIcon("icons/paste.gif"));
		a.putValue(Action.NAME,"Paste");
	}
	JToolBar createToolBar()
	{
		JToolBar jtb=new JToolBar();
		jtb.add(getOpenAction()).setText("");
		jtb.add(getSaveAction()).setText("");
		jtb.addSeparator();
		jtb.add(tComp.getActionMap().get(DefaultEditorKit.cutAction)).setText("");
		jtb.add(tComp.getActionMap().get(DefaultEditorKit.copyAction)).setText("");
		jtb.add(tComp.getActionMap().get(DefaultEditorKit.pasteAction)).setText("");
		return jtb;
	}
	JMenuBar createMenuBar()
	{
		JMenuBar jmb=new JMenuBar();
		JMenu file=new JMenu("File");
		JMenu edit=new JMenu("Edit");
		jmb.add(file);
		jmb.add(edit);
		
		file.add(getOpenAction());
		file.add(getSaveAction());
		file.add(new ExitAction());
		edit.add(tComp.getActionMap().get(DefaultEditorKit.cutAction));
		edit.add(tComp.getActionMap().get(DefaultEditorKit.copyAction));
		edit.add(tComp.getActionMap().get(DefaultEditorKit.pasteAction));
		return jmb;
	}
	Action getOpenAction()
	{
		return openAction();
	}
	Action getSaveAction()
	{
		return saveAction();
	}
	JTextComponent getTextComponent()
	{
		return tComp;
	}
	public static void main(String args[])
	{
		GuiTxtEditor editor = new GuiTxtEditor();
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editor.setVisible(true);
	}
	class ExitAction extends AbstractAction
	{
		public ExitAction()
		{
			super("Exit");
		}
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
class OpenAction extends AbstractAction
{
	public OpenAction()
	{
		super("Open", new ImageIcon("icons/open.gif"));
	}
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser choice =new JFileChooser();
		if(choice.showOpenDialog(SimpleEditor.this)!=JFileChooser.APPROVE_OPTION)
			return;
		File f=choice.getSelectedFile();
		if(file==null)
			return;
		try
		{
			FileReader fr=new FileReader(f);
			tComp.read(fr,null);
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(SimpleEditor.this,"File not found!!!","Error!!!",JOptionPane.ERROR_MESSAGE);
		}
		if(fr!=null)
		{
			try
			{
				fr.close();
			}
			catch(IOException ioe)
			{}
		}
	}
}

class SaveAction extends AbstractAction
{
	public SaveAction()
	{
		super("Save", new ImageIcon("icons/save.gif"));
	}
	public void actionPerformed(ActionEvent ae)
	{
		JFileChooser = new JFileChooser();
		if(choice.showSaveDialog(SimpleEditor.this)!=JFileChooser.APPROVE_OPTION)
			return;
		File f=choice.getSelectedFile();
		if(file==null)
			return;
		FileWriter fw=null;
		try
		{
			fw=new FileWriter(file);
			tComp.write(fw);
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(SimpleEditor.this,"File not saved!!!","Error!!!",JOptionPane.ERROR_MESSAGE);
		}
		if(fw!=null)
		{
			try
			{
				fw.close();
			}
			catch (IOException ioe)
			{}
		}
	}
}
}