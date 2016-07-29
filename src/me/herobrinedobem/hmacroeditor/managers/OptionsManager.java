package me.herobrinedobem.hmacroeditor.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

public class OptionsManager {

	public ArrayList<String> defaultOptions = new ArrayList<String>();
	private File optionsFile;

	public OptionsManager() {
		this.setDefaultOptions();
		this.createFileOptions();
	}

	public void createFileOptions() {
		try {
			final String caminho = System.getProperty("user.dir") + System.getProperty("file.separator") + "options.txt";
			this.optionsFile = new File(caminho);
			if (!this.optionsFile.exists()) {
				this.optionsFile.createNewFile();
				FileWriter writer = null;
				writer = new FileWriter(this.optionsFile, true);
				for (final String s : this.defaultOptions) {
					writer.write(s + "\n");
				}
				writer.close();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public String getOption(final String option) {
		FileInputStream fis = null;
		String texto = "";
		try {
			fis = new FileInputStream(this.optionsFile);
			int content;
			while ((content = fis.read()) != -1) {
				texto += (char) content;
			}
			final String[] partes = texto.split("\n");
			for (final String s : partes) {
				if (s.contains(option)) {
					return s.split(";")[1];
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveNewOptions(final String sa) {
		try {
			FileWriter writer = null;
			writer = new FileWriter(this.optionsFile, false);
			writer.write(sa);
			writer.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void saveDefaultOptions() {
		try {
			FileWriter writer = null;
			writer = new FileWriter(this.optionsFile, false);
			for (final String s : this.defaultOptions) {
				writer.write(s + "\n");
			}
			writer.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void setDefaultOptions() {
		this.defaultOptions.add("fonte;Dialog");
		this.defaultOptions.add("tamanho;14");
		this.defaultOptions.add("ativarnegrito;sim");
		this.defaultOptions.add("corlogicos;#DAA520");
		this.defaultOptions.add("corsintaxe;#B452CD");
		this.defaultOptions.add("cor@;#698B22");
		this.defaultOptions.add("cor#;#CD3278");
		this.defaultOptions.add("cor%;#228B22");
		this.defaultOptions.add("cor&;#D02090");
		this.defaultOptions.add("coroutros;#6959CD");
	}

}
