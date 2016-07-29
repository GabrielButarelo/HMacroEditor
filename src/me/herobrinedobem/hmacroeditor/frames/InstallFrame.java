package me.herobrinedobem.hmacroeditor.frames;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import me.herobrinedobem.hmacroeditor.managers.InstallManager;

public class InstallFrame extends JFrame {

	private final InstallManager installManager;
	private static final long serialVersionUID = 1L;
	private final JLabel infoLabel;
	private final JProgressBar progressBar;
	private final JPanel contentPane;

	public InstallFrame() {
		final ImageIcon icone = new ImageIcon("icone.png");
		this.setIconImage(icone.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 550, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(135, 206, 250));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.contentPane.setLayout(null);

		this.progressBar = new JProgressBar();
		this.progressBar.setMaximum(100);
		this.progressBar.setBackground(new Color(135, 206, 250));
		this.progressBar.setIndeterminate(true);
		this.progressBar.setValue(50);
		this.progressBar.setForeground(new Color(30, 144, 255));
		this.progressBar.setBounds(10, 223, 514, 24);
		this.contentPane.add(this.progressBar);

		this.infoLabel = new JLabel();
		this.infoLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.infoLabel.setBounds(10, 188, 514, 24);
		this.contentPane.add(this.infoLabel);

		final JLabel lblNewLabel_1 = new JLabel(new ImageIcon("HMacroEditor.png"));
		lblNewLabel_1.setBounds(57, 0, 415, 177);
		this.contentPane.add(lblNewLabel_1);

		this.installManager = new InstallManager(this, this.infoLabel);
		final Thread t = new Thread(this.installManager);
		t.start();
	}

	public JProgressBar getProgressBar() {
		return this.progressBar;
	}

	public JLabel getInfoLabel() {
		return this.infoLabel;
	}

	public InstallManager getInstallManager() {
		return this.installManager;
	}

}
