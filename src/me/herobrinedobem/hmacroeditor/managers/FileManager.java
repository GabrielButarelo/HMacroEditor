package me.herobrinedobem.hmacroeditor.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class FileManager {

	public void salvarArquivo(final String texto, final JFrame frame) {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Salvar Arquivo");
		if (fc.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File arquivo;
		if (fc.getSelectedFile().getAbsolutePath().endsWith(".txt")) {
			arquivo = fc.getSelectedFile();
		} else {
			arquivo = new File(fc.getSelectedFile().getAbsolutePath().concat(".txt"));
		}
		if (arquivo == null) {
			return;
		} else if (arquivo.exists()) {
			arquivo.delete();
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(arquivo);
			writer.write(texto);
			writer.close();
			JOptionPane.showMessageDialog(null, "O arquivo foi salvo com sucesso!");
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	public void abrirArquivo(final JFrame frame, final JTextPane txt) {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Abrir Arquivo");
		fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public boolean accept(final File f) {
				return (f.getName().endsWith(".txt")) || f.isDirectory();
			}

			@Override
			public String getDescription() {
				return "*.ret";
			}
		});
		if (fc.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		txt.setText("");
		final File file = fc.getSelectedFile();
		FileInputStream fis = null;
		String texto = "";
		try {
			fis = new FileInputStream(file);
			int content;
			while ((content = fis.read()) != -1) {
				texto += (char) content;
			}
			txt.setText(texto);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void abrirArquivo(final String caminho, final JTextPane txt) {
		final File file = new File(caminho);
		FileInputStream fis = null;
		String texto = "";
		try {
			fis = new FileInputStream(file);
			int content;
			while ((content = fis.read()) != -1) {
				texto += (char) content;
			}
			txt.setText(texto);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
