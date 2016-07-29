package me.herobrinedobem.hmacroeditor.frames;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URI;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import me.herobrinedobem.hmacroeditor.managers.TextManager;
import me.herobrinedobem.hmacroeditor.HMacroEditor;
import me.herobrinedobem.hmacroeditor.TextLineNumber;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private final TextManager textManager = new TextManager();
	private File saveFile;
	private JTextPane txt;
	private JTextArea errorArea;

	public MainFrame() {
		this.setupFrame();
		this.setupErrorArea();
		this.setupMenu();
		this.setupTextPane();
		this.setupAutoComplete();
		this.setupAtalhos();
	}

	private void setupFrame() {
		this.setBounds(100, 100, 800, 700);
		this.setFocusable(false);
		final ImageIcon icone = new ImageIcon("icone.png");
		this.setIconImage(icone.getImage());
		this.setTitle("HMacroEditor - Versão 2.0");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				if (e.getID() == WindowEvent.WINDOW_CLOSING) {
					final int option = JOptionPane.showConfirmDialog(null, "Voce deseja salvar o arquivo antes de sair?");
					if (option == JOptionPane.YES_OPTION) {
						HMacroEditor.fileManager.salvarArquivo(MainFrame.this.txt.getText(), HMacroEditor.frame);
						System.exit(0);
					} else if (option == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
				}
			}
		});
	}

	private void setupMenu() {
		final JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 794, 26);
		this.getContentPane().add(menuBar);

		final JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		final JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				HMacroEditor.fileManager.salvarArquivo(MainFrame.this.txt.getText(), HMacroEditor.frame);
				JOptionPane.showMessageDialog(null, "O arquivo foi salvo com sucesso!");
			}
		});
		mnArquivo.add(mntmSalvar);

		final JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		mntmAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				MainFrame.this.txt.setText("");
				HMacroEditor.fileManager.abrirArquivo(HMacroEditor.frame, MainFrame.this.txt);
			}
		});
		mnArquivo.add(mntmAbrir);

		final JMenuItem mntmOpcoes = new JMenuItem("Opções");
		mntmOpcoes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		mntmOpcoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ea) {
					ea.printStackTrace();
				}
				new OptionsFrame().setVisible(true);
			}
		});
		mnArquivo.add(mntmOpcoes);

		final JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				HMacroEditor.fileManager.salvarArquivo(MainFrame.this.txt.getText(), HMacroEditor.frame);
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);

		final JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		final JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				JOptionPane.showMessageDialog(null, "O HMacroEditor é um programa criado para facilitar a vida de desenvolvedores de macros para \nservidores de minecraft, o programa ainda está em fase alpha e está sendo atualizado trazendo \ncada vez mais novidades para os nossos usuários. \n\nVersão: 2.0 \nProgramador: Herobrinedobem (Gabriel) \nDescrição Dos Comandos: TomSutter (Thomas)", "Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAjuda.add(mntmSobre);
		
		final JMenuItem mntmChangelog = new JMenuItem("Changelog");
		mntmChangelog.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				Desktop desktop = null;
				desktop = Desktop.getDesktop();
				URI uri = null;
				try {
					uri = new URI("http://www.minecraft.project273.com.br/staff/hero/macro_changelog.html");
					desktop.browse(uri);
				} catch (final Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnAjuda.add(mntmChangelog);
	}

	private void setupTextPane() {
		this.txt = new JTextPane(this.textManager.getDoc());
		this.txt.setBackground(Color.WHITE);
		this.txt.setBounds(0, 0, 640, 521);
		if (HMacroEditor.optionsManager.getOption("ativarnegrito").equalsIgnoreCase("sim")) {
			this.txt.setFont(new Font(HMacroEditor.optionsManager.getOption("fonte"), Font.BOLD, Integer.parseInt(HMacroEditor.optionsManager.getOption("tamanho"))));
		} else {
			this.txt.setFont(new Font(HMacroEditor.optionsManager.getOption("fonte"), Font.PLAIN, Integer.parseInt(HMacroEditor.optionsManager.getOption("tamanho"))));
		}
		this.txt.setText("$${\n\n}$$");

		final TextLineNumber tln = new TextLineNumber(this.txt);

		final JScrollPane scrollPane = new JScrollPane(this.txt);
		scrollPane.setBounds(0, 26, 794, 500);
		scrollPane.setRowHeaderView(tln);
		this.getContentPane().add(scrollPane);
	}

	private void setupAutoComplete() {
		final CompletionProvider provider = this.textManager.createCompletionProvider();
		final AutoCompletion ac = new AutoCompletion(provider);
		ac.setShowDescWindow(true);
		ac.install(this.txt);
	}

	private void setupErrorArea() {
		this.errorArea = new JTextArea();
		this.errorArea.setEditable(false);
		this.errorArea.setBounds(0, 525, 794, 147);
		this.errorArea.setBackground(Color.LIGHT_GRAY);
		this.errorArea.setText("Nenhum erro encontrado até o momento! \n\n Sistema desabilitado temporariamente");
		this.getContentPane().add(this.errorArea);
	}

	private void setupAtalhos() {
		this.txt.addKeyListener(new KeyAdapter() {
			boolean control = false;

			@Override
			public void keyPressed(final KeyEvent e) {
				//final Thread t = new Thread(new ErrorsManager(txt, errorArea));
				//t.start();
				
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					this.control = true;
				}

				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (this.control) {
						HMacroEditor.fileManager.salvarArquivo(MainFrame.this.txt.getText(), HMacroEditor.frame);
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_O) {
					if (this.control) {
						HMacroEditor.fileManager.abrirArquivo(HMacroEditor.frame, MainFrame.this.txt);
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_P) {
					if (this.control) {
						try {
							UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ea) {
							ea.printStackTrace();
						}
						new OptionsFrame().setVisible(true);
					}
				}

			}

			@Override
			public void keyReleased(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					this.control = false;
				}
			}

		});
	}

	public File getSaveFile() {
		return this.saveFile;
	}

}
