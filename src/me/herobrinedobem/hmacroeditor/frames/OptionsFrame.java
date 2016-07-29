package me.herobrinedobem.hmacroeditor.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import me.herobrinedobem.hmacroeditor.HMacroEditor;

public class OptionsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private final JRadioButton rdbtnDesativar;
	private final JRadioButton rdbtnAtivar;
	private final JTextField fieldFonte;
	private final JTextField fieldTamanho;
	private final JTextField fieldCorLogicos;
	private final JTextField fieldCorSintaxe;
	private final JTextField fieldCorArroba;
	private final JTextField fieldCorHashtag;
	private final JTextField fieldCorPorcentagem;
	private final JTextField fieldCorOutros;
	private final JTextField textFieldE;

	public OptionsFrame() {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setBounds(100, 100, 450, 390);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.WHITE);
		this.setResizable(false);
		this.setFocusable(false);
		final ImageIcon icone = new ImageIcon("icone.png");
		this.setIconImage(icone.getImage());
		this.setTitle("HMacroEditor - Opções De Configuração");
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		final JLabel lblConfiguraes = new JLabel("CONFIGURAÇÕES");
		lblConfiguraes.setForeground(Color.DARK_GRAY);
		lblConfiguraes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguraes.setFont(new Font("Verdana", Font.BOLD, 25));
		lblConfiguraes.setBounds(10, 0, 424, 42);
		this.contentPane.add(lblConfiguraes);

		final JSeparator separator = new JSeparator();
		separator.setBounds(-1, 45, 445, 10);
		this.contentPane.add(separator);

		final JLabel lblFonteDoTexto = new JLabel("Fonte Do Texto:");
		lblFonteDoTexto.setBounds(10, 56, 85, 14);
		this.contentPane.add(lblFonteDoTexto);

		this.fieldFonte = new JTextField();
		this.fieldFonte.setText(HMacroEditor.optionsManager.getOption("fonte"));
		this.fieldFonte.setBounds(115, 53, 319, 20);
		this.contentPane.add(this.fieldFonte);
		this.fieldFonte.setColumns(10);

		final JLabel lblTamanhoDoTexto = new JLabel("Tamanho Do Texto:");
		lblTamanhoDoTexto.setBounds(10, 84, 100, 14);
		this.contentPane.add(lblTamanhoDoTexto);

		this.fieldTamanho = new JTextField();
		this.fieldTamanho.setColumns(10);
		this.fieldTamanho.setText(HMacroEditor.optionsManager.getOption("tamanho"));
		this.fieldTamanho.setBounds(115, 81, 319, 20);
		this.contentPane.add(this.fieldTamanho);

		final JLabel lblAtivarNegrito = new JLabel("Ativar Negrito:");
		lblAtivarNegrito.setBounds(10, 109, 100, 14);
		this.contentPane.add(lblAtivarNegrito);

		this.rdbtnAtivar = new JRadioButton("Ativar");
		this.rdbtnAtivar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent arg0) {
				if (OptionsFrame.this.rdbtnDesativar.isSelected()) {
					OptionsFrame.this.rdbtnDesativar.setSelected(false);
				}
			}
		});
		this.rdbtnAtivar.setBackground(Color.WHITE);
		if (HMacroEditor.optionsManager.getOption("ativarnegrito").equalsIgnoreCase("sim")) {
			this.rdbtnAtivar.setSelected(true);
		}
		this.rdbtnAtivar.setBounds(187, 105, 109, 23);
		this.contentPane.add(this.rdbtnAtivar);

		this.rdbtnDesativar = new JRadioButton("Desativar");
		if (HMacroEditor.optionsManager.getOption("ativarnegrito").equalsIgnoreCase("nao")) {
			this.rdbtnAtivar.setSelected(true);
		}
		this.rdbtnDesativar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent arg0) {
				if (OptionsFrame.this.rdbtnAtivar.isSelected()) {
					OptionsFrame.this.rdbtnAtivar.setSelected(false);
				}
			}
		});
		this.rdbtnDesativar.setBackground(Color.WHITE);
		this.rdbtnDesativar.setBounds(298, 105, 109, 23);
		this.contentPane.add(this.rdbtnDesativar);

		final JLabel lblCorSimbolosLgicos = new JLabel("Cor Simbolos Lógicos:");
		lblCorSimbolosLgicos.setBounds(10, 134, 109, 14);
		this.contentPane.add(lblCorSimbolosLgicos);

		this.fieldCorLogicos = new JTextField();
		this.fieldCorLogicos.setColumns(10);
		this.fieldCorLogicos.setText(HMacroEditor.optionsManager.getOption("corlogicos"));
		this.fieldCorLogicos.setBounds(115, 131, 319, 20);
		this.contentPane.add(this.fieldCorLogicos);

		final JLabel lblCorMtodos = new JLabel("Cor Sintaxes:");
		lblCorMtodos.setBounds(10, 162, 109, 14);
		this.contentPane.add(lblCorMtodos);

		this.fieldCorSintaxe = new JTextField();
		this.fieldCorSintaxe.setColumns(10);
		this.fieldCorSintaxe.setText(HMacroEditor.optionsManager.getOption("corsintaxe"));
		this.fieldCorSintaxe.setBounds(115, 159, 319, 20);
		this.contentPane.add(this.fieldCorSintaxe);

		final JLabel lblCor = new JLabel("Cor Do @:");
		lblCor.setBounds(10, 190, 109, 14);
		this.contentPane.add(lblCor);

		this.fieldCorArroba = new JTextField();
		this.fieldCorArroba.setColumns(10);
		this.fieldCorArroba.setText(HMacroEditor.optionsManager.getOption("cor@"));
		this.fieldCorArroba.setBounds(115, 187, 319, 20);
		this.contentPane.add(this.fieldCorArroba);

		final JLabel lblCorDo = new JLabel("Cor Do #:");
		lblCorDo.setBounds(10, 218, 109, 14);
		this.contentPane.add(lblCorDo);

		this.fieldCorHashtag = new JTextField();
		this.fieldCorHashtag.setColumns(10);
		this.fieldCorHashtag.setText(HMacroEditor.optionsManager.getOption("cor#"));
		this.fieldCorHashtag.setBounds(115, 215, 319, 20);
		this.contentPane.add(this.fieldCorHashtag);

		final JLabel lblCorDo_1 = new JLabel("Cor Do %:");
		lblCorDo_1.setBounds(10, 246, 109, 14);
		this.contentPane.add(lblCorDo_1);

		this.fieldCorPorcentagem = new JTextField();
		this.fieldCorPorcentagem.setColumns(10);
		this.fieldCorPorcentagem.setText(HMacroEditor.optionsManager.getOption("cor%"));
		this.fieldCorPorcentagem.setBounds(115, 243, 319, 20);
		this.contentPane.add(this.fieldCorPorcentagem);

		final JLabel lblOutrasCoisas = new JLabel("Outras Coisas:");
		lblOutrasCoisas.setBounds(10, 305, 109, 14);
		this.contentPane.add(lblOutrasCoisas);

		this.fieldCorOutros = new JTextField();
		this.fieldCorOutros.setColumns(10);
		this.fieldCorOutros.setText(HMacroEditor.optionsManager.getOption("coroutros"));
		this.fieldCorOutros.setBounds(115, 302, 319, 20);
		this.contentPane.add(this.fieldCorOutros);

		final JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent arg0) {
				final String s1 = "fonte;" + OptionsFrame.this.fieldFonte.getText() + "\n";
				String s2 = null;
				try {
					final int i = Integer.parseInt(OptionsFrame.this.fieldTamanho.getText());
					s2 = "tamanho;" + String.valueOf(i) + "\n";
				} catch (final NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Utilize apenas números na opção de tamanho da fonte!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String s3 = null;
				if (OptionsFrame.this.rdbtnDesativar.isSelected()) {
					s3 = "ativarnegrito;nao\n";
				} else if (OptionsFrame.this.rdbtnAtivar.isSelected()) {
					s3 = "ativarnegrito;sim\n";
				}
				final String s4 = "corlogicos;" + OptionsFrame.this.fieldCorLogicos.getText() + "\n";
				final String s5 = "corsintaxe;" + OptionsFrame.this.fieldCorSintaxe.getText() + "\n";
				final String s6 = "cor@;" + OptionsFrame.this.fieldCorArroba.getText() + "\n";
				final String s7 = "cor#;" + OptionsFrame.this.fieldCorHashtag.getText() + "\n";
				final String s8 = "cor%;" + OptionsFrame.this.fieldCorPorcentagem.getText() + "\n";
				final String s9 = "coroutros;" + OptionsFrame.this.fieldCorOutros.getText() + "\n";
				final String s10 = "coroutros;" + OptionsFrame.this.textFieldE.getText();
				final String tudo = s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + s10;
				HMacroEditor.optionsManager.saveNewOptions(tudo);
				JOptionPane.showMessageDialog(null, "As opções foram salvas com sucesso, feche e abra novamente o programa para que as mudanças ocorram!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(311, 330, 123, 23);
		this.contentPane.add(btnNewButton);

		final JButton btnRestaurarPadres = new JButton("Restaurar Padrões");
		btnRestaurarPadres.setBounds(10, 330, 123, 23);
		btnRestaurarPadres.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent arg0) {
				HMacroEditor.optionsManager.saveDefaultOptions();
				JOptionPane.showMessageDialog(null, "As opções foram resetadas com sucesso, feche e abra novamente o programa para que as mudanças ocorram!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.contentPane.add(btnRestaurarPadres);

		this.textFieldE = new JTextField();
		this.textFieldE.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent arg0) {
				Color colorNew;
				colorNew = JColorChooser.showDialog(null, "Selecione uma cor", Color.BLACK);
				if (colorNew == null) {
					colorNew = Color.getColor(HMacroEditor.optionsManager.getOption("cor&"));
				}
			}
		});
		this.textFieldE.setText(HMacroEditor.optionsManager.getOption("cor&"));
		this.textFieldE.setColumns(10);
		this.textFieldE.setBounds(115, 271, 319, 20);
		this.contentPane.add(this.textFieldE);

		final JLabel lblCorE = new JLabel("Cor Do &:");
		lblCorE.setBounds(10, 274, 109, 14);
		this.contentPane.add(lblCorE);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				if (e.getID() == WindowEvent.WINDOW_CLOSING) {
					OptionsFrame.this.dispose();
				}
			}
		});
	}
}
