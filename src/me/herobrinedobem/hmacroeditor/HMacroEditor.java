package me.herobrinedobem.hmacroeditor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import me.herobrinedobem.hmacroeditor.frames.InstallFrame;
import me.herobrinedobem.hmacroeditor.frames.MainFrame;
import me.herobrinedobem.hmacroeditor.managers.FileManager;
import me.herobrinedobem.hmacroeditor.managers.MessagesManager;
import me.herobrinedobem.hmacroeditor.managers.OptionsManager;

public class HMacroEditor {

	public static FileManager fileManager = new FileManager();
	public static OptionsManager optionsManager = new OptionsManager();
	public static MessagesManager messagesManager = new MessagesManager();
	public static InstallFrame installFrame;
	public static MainFrame frame;

	public HMacroEditor() {
		HMacroEditor.installFrame = new InstallFrame();
		HMacroEditor.installFrame.setVisible(true);
	}

	public static void main(final String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new HMacroEditor();
	}

}
