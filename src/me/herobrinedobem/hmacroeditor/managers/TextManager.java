package me.herobrinedobem.hmacroeditor.managers;

import java.awt.Color;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import me.herobrinedobem.hmacroeditor.HMacroEditor;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class TextManager {

	private int offsetall, lenght;
	private String stringa;

	private final StyleContext cont = StyleContext.getDefaultStyleContext();
	private final AttributeSet attrSintaxe = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("corsintaxe")));
	private final AttributeSet attrLogicos = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("corlogicos")));
	private final AttributeSet attrArroba = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("cor@")));
	private final AttributeSet attrHashtag = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("cor#")));
	private final AttributeSet attrPorcentagem = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("cor%")));
	private final AttributeSet attrE = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("cor&")));
	private final AttributeSet attrOutros = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.decode(HMacroEditor.optionsManager.getOption("coroutros")));
	private final AttributeSet attrBlack = this.cont.addAttribute(this.cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);

	@SuppressWarnings("serial")
	DefaultStyledDocument doc = new DefaultStyledDocument() {
		@Override
		public void insertString(final int offset, final String str, final AttributeSet a) throws BadLocationException {
			super.insertString(offset, str, a);

			TextManager.this.lenght = this.getLength();
			final String text = this.getText(0, this.getLength());
			int before = TextManager.this.findLastNonWordChar(text, offset);

			if (before < 0) {
				before = 0;
			}

			final int after = TextManager.this.findFirstNonWordChar(text, (offset + str.length()));
			int wordL = before;
			int wordR = before;
			TextManager.this.offsetall = offset;
			TextManager.this.stringa = str;

			while (wordR <= after) {
				if ((wordR == after) || String.valueOf(text.charAt(wordR)).matches("\\W")) {
					if (text.substring(wordL, wordR).matches("(\\W)*(CRAFT|,|CRAFTANDWAIT|DISCONNECT|GETID|GETIDREL|GETITEMINFO|" + "GETSLOT|GETSLOTITEM|INVENTORYDOWN|INVENTORYUP|LOOK|LOOKS|PICK|PLACESIGN|PLAYSOUND|RESPAWN|SETSLOTITEM|SLOT|" + "SLOTCLICK|SPRINT|UNSPRINT|KEY|KEYDOWN|KEYUP|TOGGLEKEY|PRESS|TYPE|ASSIGN|CALCYAWTO|DEC|ECHO|EXEC|LOG|LOGTO|" + "PROMPT|RAMDOM|REPLACE|SQRT|STRIP|WAIT|ARRAYSIZE|INDEXOF|POP|PUSH|PUT|CONFIG|IMPORT|UNIMPORT|BIND|CHATHEIGHT|" + "CHATHEIGHTFOCUSED|CHATOPACITY|CHATSCALE|CHATVISIBLE|CHATWIDTH|FOG|FOV|GAMMA|MUSIC|RESOURCEPACK|SENSITIVITY|" + "SETRES|TEXTUREPACK|VOLUME|ACHIEVEMENTGET|BINDGUI|CLEARCHAT|GUI|SHOWGUI|STORE|STOREOVER|TIME)")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrSintaxe, false);
					} else if (text.substring(wordL, wordR).matches("(\\W)*(\\$|\\{|\\})")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrOutros, false);
					} else if (text.substring(wordL, wordR).matches("(\\W)*(DO|ELSE|ELSEIF|ENDIF|FOR|FOREACH|IF|IFBEGINSWITH|IFCONTAINS|IFENDSWITH|IFMATCHES|IIF|INC|LOOP|MATCH|NEXT|BREAK|SET|SETLABEL|STOP|TOGGLE|" + "UNSET|UNTIL|WHILE|\\;|\\:)")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrOutros, false);
					} else if (text.substring(wordL, wordR).matches("(\\W)*(craft|,|craftandwait|disconnect|getid|getidrel|getiteminfo|" + "getslot|getslotitem|inventorydown|inventoryup|look|looks|pick|placesign|playsound|respawn|setslotitem|slot|" + "slotclick|sprint|unsprint|key|keydown|keyup|togglekey|press|type|assign|calcyawto|dec|echo|exec|log|logto|" + "prompt|ramdom|replace|sqrt|strip|wait|arraysize|indexof|pop|push|put|config|import|unimport|bind|chatheight|" + "chatheightfocused|chatopacity|chatscale|chatvisible|chatwidth|fog|fov|gamma|music|resourcepack|sensitivity|" + "setres|texturepack|volume|achievementget|bindgui|clearchat|gui|showgui|store|storeover|time)")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrSintaxe, false);
					} else if (text.substring(wordL, wordR).matches("(\\W)*(do|else|elseif|endif|for|foreach|if|ifbeginswith|ifcontains|ifendswith|ifmatches|iif|inc|loop|match|next|break|set|setlabel|stop|toggle|" + "unset|until|while)")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrOutros, false);
					} else if (text.substring(wordL, wordR).startsWith("@")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrArroba, false);
					} else if (text.substring(wordL, wordR).startsWith("#")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrHashtag, false);
					} else if ((text.substring(wordL, wordR).startsWith("%"))) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrPorcentagem, false);
					} else if ((text.substring(wordL, wordR).startsWith("&"))) {
						if ((text.substring(wordL, wordR).equals("&a") || text.substring(wordL, wordR).equals("&b") || text.substring(wordL, wordR).equals("&c") || text.substring(wordL, wordR).equals("&d") || text.substring(wordL, wordR).equals("&e") || text.substring(wordL, wordR).equals("&f") || text.substring(wordL, wordR).equals("&k") || text.substring(wordL, wordR).equals("&l") || text.substring(wordL, wordR).equals("&m") || text.substring(wordL, wordR).equals("&1") || text.substring(wordL, wordR).equals("&2") || text.substring(wordL, wordR).equals("&3") || text.substring(wordL, wordR).equals("&4") || text.substring(wordL, wordR).equals("&5") || text.substring(wordL, wordR).equals("&6") || text.substring(wordL, wordR).equals("&7") || text.substring(wordL, wordR).equals("&8") || text.substring(wordL, wordR).equals("&9"))) {
							this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrBlack, false);
						} else {
							this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrE, false);
						}
					} else if (text.substring(wordL, wordR).matches("(\\W)*(&&|>|<|>=|<=|=<|\\|\\||!=|=)")) {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrLogicos, false);
					} else {
						this.setCharacterAttributes(wordL, wordR - wordL, TextManager.this.attrBlack, false);
					}
					wordL = wordR;
				}
				wordR++;
			}

		}

	};

	public CompletionProvider createCompletionProvider() {
		final DefaultCompletionProvider provider = new DefaultCompletionProvider();
		HMacroEditor.messagesManager.configCompletation(provider);
		return provider;
	}

	private int findLastNonWordChar(final String text, int index) {
		while (--index >= 0) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
		}
		return index;
	}

	private int findFirstNonWordChar(final String text, int index) {
		while (index < text.length()) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
			index++;
		}
		return index;
	}

	public int getOffsetall() {
		return this.offsetall;
	}

	public void setOffsetall(final int offsetall) {
		this.offsetall = offsetall;
	}

	public int getLenght() {
		return this.lenght;
	}

	public void setLenght(final int lenght) {
		this.lenght = lenght;
	}

	public String getStringa() {
		return this.stringa;
	}

	public void setStringa(final String stringa) {
		this.stringa = stringa;
	}

	public DefaultStyledDocument getDoc() {
		return this.doc;
	}

	public void setDoc(final DefaultStyledDocument doc) {
		this.doc = doc;
	}

	public StyleContext getCont() {
		return this.cont;
	}

}
