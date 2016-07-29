package me.herobrinedobem.hmacroeditor.managers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import me.herobrinedobem.hmacroeditor.frames.MainFrame;
import me.herobrinedobem.hmacroeditor.HMacroEditor;

public class InstallManager implements Runnable {

	private final String version = "2.1.0";
	private final JFrame frame;
	private final JLabel labelMensagem;
	private long timeInitial, timeFinal;

	public InstallManager(final JFrame frame, final JLabel labelMensagem) {
		this.frame = frame;
		this.labelMensagem = labelMensagem;
	}

	@Override
	public void run() {
		this.timeInitial = System.currentTimeMillis();
		this.labelMensagem.setText("Verificando arquivo de configuração...");
		HMacroEditor.optionsManager.createFileOptions();
		this.sleep(2);
		this.labelMensagem.setText("Verificando arquivo de mensagens...");
		HMacroEditor.messagesManager.createFileMessages();
		this.sleep(2);
		this.labelMensagem.setText("Procurando por atualizações recentes...");
		this.verifyVersionUpdate();
		this.sleep(2);
		this.timeFinal = System.currentTimeMillis();
		this.labelMensagem.setText("Tempo total demorado para startar o programa: " + (this.timeFinal - this.timeInitial) + "ms");
		this.sleep(2);
		this.frame.dispose();
		HMacroEditor.frame = new MainFrame();
		HMacroEditor.frame.setVisible(true);
	}

	private void verifyVersionUpdate() {
		try {
			final URL url = new URL("http://www.minecraft.project273.com.br/staff/hero/macro_editor_version.html");

			final InputStream is = url.openStream();
			final InputStreamReader isReader = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isReader);

			String linha = br.readLine();

			while (linha != null) {
				if (linha.equalsIgnoreCase(this.version)) {
					this.labelMensagem.setText("Nenhuma atualização recente encontrada...");
				} else {
					this.labelMensagem.setText("Existe uma nova versão(" + linha + ") disponivel para download...");
				}
				linha = br.readLine();
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void sleep(final long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public JLabel getLabelMensagem() {
		return this.labelMensagem;
	}

}
