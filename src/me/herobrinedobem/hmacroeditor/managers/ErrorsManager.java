package me.herobrinedobem.hmacroeditor.managers;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ErrorsManager implements Runnable {

	private final JTextArea textArea;
	private final JTextPane txt;

	public ErrorsManager(final JTextPane txt, final JTextArea textArea) {
		this.txt = txt;
		this.textArea = textArea;
	}

	@Override
	public void run() {
		int containsIf = 0;
		int containsEndif = 0;
		int containsDo = 0;
		int containsLoop = 0;

		if (!this.textArea.getText().isEmpty()) {
			for (final String parte : this.txt.getText().split("\n")) {
				if (parte.startsWith("if")) {
					containsIf += 1;
				}
				if (parte.startsWith("endif")) {
					containsEndif += 1;
				}
				if (parte.startsWith("do")) {
					containsDo += 1;
				}
				if (parte.startsWith("loop")) {
					containsLoop += 1;
				}
			}
			
			String[] errors = new String[10];

			if (containsIf > containsEndif) {
				errors[0] = "Você esqueceu de colocar um 'endif' pois está iniciando um 'if' mas não está encerando-o! (" + (containsIf - containsEndif) + ")";
			} else if (containsEndif > containsIf) {
				errors[0] = "Você colocou um 'endif' a mais pois não há um 'if' para ser encerrado! (" + (containsEndif - containsIf) + ")";
			}else if(containsIf == containsEndif){
				errors[0] = "";
			}

			if (containsDo > containsLoop) {
				errors[1] = "Você esqueceu de colocar um 'loop' pois está iniciando um 'do' mas não está encerando-o! (" + (containsDo - containsLoop) + ")";
			} else if (containsLoop > containsDo) {
				errors[1] = "Você colocou um 'loop' a mais pois não há um 'do' para ser encerrado! (" + (containsLoop - containsDo) + ")";
			}else if(containsLoop == containsDo){
				errors[1] = "";
			}

			textArea.setForeground(Color.RED);
			textArea.setText(errors[0] + "\n" + errors[1]);
			
		}
	}
}
