package me.herobrinedobem.hmacroeditor.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class MessagesManager {

	private final ArrayList<String> defaultMessages = new ArrayList<>();
	private File optionsFile;

	public MessagesManager() {
		this.setDefaultMessages();
		this.createFileMessages();
	}

	public void createFileMessages() {
		try {
			final String caminho = System.getProperty("user.dir") + System.getProperty("file.separator") + "complete_config.txt";
			this.optionsFile = new File(caminho);
			if (!this.optionsFile.exists()) {
				this.optionsFile.createNewFile();
				FileWriter writer = null;
				writer = new FileWriter(this.optionsFile, true);
				for (final String s : this.defaultMessages) {
					writer.write(s + "\n");
				}
				writer.close();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void configCompletation(final DefaultCompletionProvider provider) {
		try {
			final BufferedReader arqIn = new BufferedReader(new InputStreamReader(new FileInputStream(this.optionsFile), "UTF-8"));
			String texto = "";
			int content;
			while ((content = arqIn.read()) != -1) {
				texto += (char) content;
			}
			final String[] partes = texto.split("\n");
			for (final String s : partes) {
				provider.addCompletion(new BasicCompletion(provider, s.split(";")[0], s.split(";")[1], s.split(";")[2]));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void saveDefaultMessages() {
		try {
			final OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(this.optionsFile), "UTF-8");
			for (final String s : this.defaultMessages) {
				out.write(s + "\n");
			}
			out.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void setDefaultMessages() {
		this.defaultMessages.add("craft;Crafta um item na presença de uma workbench;Após determinar-se o item seleciona-se a quantidade e se ele será atirado ou não ao chão. Exemplo: craft(57, 15, true). Craftaríamos 15 blocos de diamante e os jogaríamos no chão, pois está ativado (true).");
		this.defaultMessages.add("craftwandwait;Cria uma lista de itens para serem craftados;Basicamente o mesmo que craft(), apenas diferenciando que, com ele, pode-se fazer grandes listas, desde que todos os blocos desejados estejam colocados em diferentes linhas. Exemplo: craftandwait(57, 15, false) craftandwait(41, 15, true) craftandwait(89, 5, true). Teremos uma lista com blocos de diamante, ouro e glowstone para fazer.");
		this.defaultMessages.add("disconnect;Desconecta-se do servidor/mapa que voce está;Desconecta-se do jogo se estiver em um mundo singleplayer, e do servidor caso esteja no multiplayer. Exemplo: disconnect()");
		this.defaultMessages.add("getid;Retorna o ID de um bloco que está em uma coordenada;Retorna o ID de um bloco em uma coordenada específica do mundo e a guarda numa variável, além de guardar seu estado. Exemplo: getid(1002007, 63, 1001281, #id, #data). Isto vai pegar o id do bloco nestas mesmas coordenadas, e guardá-la na variável #id.");
		this.defaultMessages.add("transient;Sem Descrição;Sem Descrição");
		this.defaultMessages.add("try;Sem Descrição;Sem Descrição");
		this.defaultMessages.add("void;Sem Descrição;Sem Descrição");
		this.defaultMessages.add("volatile;Sem Descrição;Sem Descrição");
		this.defaultMessages.add("getidrel;Retorna o ID de um bloco que está a certa distancia do jogador;Retonar o ID de um bloco em uma distancia específica do player e a guarda numa variável, além de guardar seu estado. Exemplo: getidrel(1, 0, 0, #id, #data) Pegará o ID do bloco a z=+1 de distancia do player, e a guardará na variável #id.");
		this.defaultMessages.add("getslotgetslot;Localiza um item no inventario do jogador;Localiza-se um item no inventário de ID específica a partir de determinado slot e retorna o slot em que ele se encontra numa variável. Exemplo: getslot(57, #slot, 9) Procurará o bloco de ID = 57, guardará o slot na variável #slot e começará a procurar a partir do slot 9 do inventário.");
		this.defaultMessages.add("getiteminfo;Retorna informações de um determinado item;Retorna informações do item especificado pela ID e as guarda em variáveis. Exemplo: getiteminfo(57, &namevar, #maxstacksize, &type, #dropid) Pegará o nome do bloco, o numero máximo que dá para ter num stack, o tipo (no caso, TILE) e o ID ao ser dropado.");
		this.defaultMessages.add("getslotitem;Localiza um item com certo id/slot com certo item;Localiza um item de certa id, um slot contendo um item de certa id, um slot contendo certo número de itens, uma id com certo número de itens e guarda todas as informações desejadas em variáveis, podendo determiná-las ao alterar a tipografia do comando. Exemplo: getslotitem(42, #id, #stack, #data) pegará o slot 42 e guardará nas variáveis #id o id do item que encontrar, #stack o número de itens que encontrar, #data o estado do item que encontrar.");
		this.defaultMessages.add("inventorydown;Desce os slots da hotbar em um determinado numero;Desce um número determinado de slots em sua hotbar. Exemplo: inventorydown(3) descerá 3 slots.");
		this.defaultMessages.add("inventoryup;Sobe os slots da hotbar em um determinado numero;Sobe um número determinado de slots em sua hotbar. Exemplo: inventoryup(2) subirá 2 slots.");
		this.defaultMessages.add("look;Faz o personagem olhar num determinado ângulo;Faz o personagem olhar num determinado ângulo instantaneamente ou lentamente, podendo também aceitar movimentos relativos ao ângulo atual. Exemplo: look(90, 15, 5) Olhará para o ângulo 90, na altura 15 no tempo de 5 segundos. Não colocar o último valor faz com que seja um movimento instantâneo.");
		this.defaultMessages.add("looks;Faz o personagem olhar num determinado ângulo sem utlizar o mause;O mesmo que look, com exceção de que o mouse ficará inutilizado durante o processo.");
		this.defaultMessages.add("pick;Pega um item na hotbar;Pega o item de uma determinada ID na hotbar. Exemplo: pick(57) pegará um bloco de diamante.");
		this.defaultMessages.add("placesign;Coloca uma placa onde está olhando;Coloca-se uma placa na direção do olhar, podendo especificar o conteúdo de cada uma das 4 linhas. Exemplo: placesign(TomÉDemais, TomFoda, TomLindo, TomGato)");
		this.defaultMessages.add("playsound;Toca um som de seu minecraft;Toca-se um som de seu minecraft. Exemplo: playsound(mob.enderdragon.growl) Toca o rugido do enderdragon.");
		this.defaultMessages.add("respawn;Respawna o jogador quando morrer;Quando um jogador morrer esse comando será chamado fazendo-o reviver/respawnar.");
		this.defaultMessages.add("setslotitem;Spawna uma quantidade de itens em um slot;Um comando utilizável apenas no modo criativo, faz aparecer em um slot de sua hotbar um item desejado e sua quantidade. Exemplo: setslotitem(57, 42, 64) Pega um pack de blocos de diamantes no slot 42.");
		this.defaultMessages.add("slot;Seleciona um slot da sua hotbar;Seleciona um slot de sua hotbar. Exemplo: slot(2).");
		this.defaultMessages.add("slotclickslotclick;Clica em um slot da sua hotbar;Clica com o botão esquerdo ou direito, com shift ou não, num slot de seu inventário. Exemplo: slotclick(9, 1, true) Clicará no slot 9, com o botão esquerdo, com shift ligado.");
		this.defaultMessages.add("sprint;Faz o seu jogador correr;Faz o jogador a correr se sua fome estiver alta o bastante.");
		this.defaultMessages.add("unsprint;Faz o seu jogador parar de correr;Faz o jogador parar de correr caso esteja correndo.");
		this.defaultMessages.add("key;Simula o clique de um botão;Simula o clique de um botão. Exemplo: key(attack).");
		this.defaultMessages.add("keydown;Simula e segura o clique de um botão;Simula e segura o clique de um botão. Exemplo: keydown(forward).");
		this.defaultMessages.add("togglekey;Sem descrição;Sem descrição");
		this.defaultMessages.add("press;Simula o clique de uma tecla do teclado;Simula o clique de um botão específico do teclado, podendo, inclusive, iniciar outras macros a partir desta, se houver uma keybind. Exemplo: press(p).");
		this.defaultMessages.add("type;Digita uma mensagem no chat sem envia-la;Faz com que uma mensagem seja digitada no chat, pausadamente, sem enviar a mensagem. Exemplo: type(potato).");
		this.defaultMessages.add("assign;Altera ou indica o valor ou conteúdo de uma variável;Altera ou indica o valor ou conteúdo de uma variável. Exemplo: assign(#slot, 30) Fará com que o valor da variável #slot vire 30.");
		this.defaultMessages.add("calcawto;Sem descrição;Sem descrição");
		this.defaultMessages.add("dec;Reduz o valor de uma variável;Reduz o valor de uma variável por um valor determinado. Exemplo: dec(#slot,5) Reduzirá o valor de #slot por 5.");
		this.defaultMessages.add("echo;Executa um comando de chat;Executa um comando de chat qualquer. Exemplo: echo(/fly) Usará o comando fly.");
		this.defaultMessages.add("exec;Inicia um script macro de um txt fora do mod;Comando externo. Inicia um script macro de um txt fora do mod. Exemplo: exec(autoclick.txt) iniciará o script contido em autoclick.txt");
		this.defaultMessages.add("log;Manda uma mensagem para o player;Faz aparecer uma mensagem personalizável para si mesmo. Exemplo: log(&2O-TOM-PEGOU-O-JERRY)");
		this.defaultMessages.add("logto;Manda uma mensagem para um arquivo txt;Manda o texto especificado para um arquivo txt. Exemplo: logto(logdamacro.txt, O-TOM-PEGOU-O-JERRY).");
		this.defaultMessages.add("prompt;Abre uma GUI para o player;Comando interessante, faz o usuário especificar um valor para uma determinada variável, numa determinada gui, podendo aceitar apenas números ou números e letras, podendo mostrar as IDS de diversos itens, ou apenas mostrando uma gui na qual a macro pedirá o valor. Exemplos: prompt(&bloco, $$i, Que bloco voce quer?) Ou prompt(&tempo, $$?, Que tempo quer esperar?)");
		this.defaultMessages.add("random;Escolhe um valor aleatório para uma variável;Escolhe um valor aleatório para uma variável. Exemplo: random(#numero, 1, 100)");
		this.defaultMessages.add("replace;Sem descrição;Sem descrição");
		this.defaultMessages.add("sqrt;Sem descrição;Sem descrição");
		this.defaultMessages.add("strip;Da um valor para uma variável &;Dá um valor para uma variável &. Exemplo: strip(&potato, batataébom).");
		this.defaultMessages.add("wait;Faz a macro aguardar um determinado tempo para continuar;Espera um valor específico para que a macro continue, podendo ser em segundos, ms ou ticks. Exemplo: wait(1) fará esperar 1 segundo, wait(500ms) fará esperar 500ms, e wait(1t) fará esperar 1 tick.");
		this.defaultMessages.add("arraysize;Sem descrição;Sem descrição");
		this.defaultMessages.add("indexof;Sem descrição;Sem descrição");
		this.defaultMessages.add("pop;Sem descrição;Sem descrição");
		this.defaultMessages.add("push;Sem descrição;Sem descrição");
		this.defaultMessages.add("config;Sem descrição;Sem descrição");
		this.defaultMessages.add("import;Seleciona uma configuração;Seleciona uma determinada configuração para ser usada mais tarde.");
		this.defaultMessages.add("bind;Sem descrição;Sem descrição");
		this.defaultMessages.add("chatheight;Altera a altura do chat;Seleciona uma altura para o chat, entretanto não na GUI. Exemplo: chatheight(20, 5) Tornará o chat pequeno (2linhas) por 5 segundos.");
		this.defaultMessages.add("chatheightfocused;Altera a altura do chat ao abrir a GUI;Seleciona uma altura para o chat e na GUI. Exemplo: chatheightfocused(20) Tonará o chat pequeno assim que abrir a GUI.");
		this.defaultMessages.add("chatscale;Altera o tamanho do chat;Seleciona um tamanho geral para o chat, tornando-o maior ou menor. Exemplo: chatscale(40).");
		this.defaultMessages.add("chatvisible;Torna o chat visível/invisível;Torna o chat visível ou invisível. Exemplo: chatvisible().");
		this.defaultMessages.add("chatwidth;Altera a largura do chat;Seleciona a largura do chat, tornando-a maior ou menor. Exemplo: chatwidth(80).");
		this.defaultMessages.add("fov;Altera a área à mostra de sua tela;Seleciona a área à mostra de sua tela. Exemplo: fov(100).");
		this.defaultMessages.add("gamma;Altera o brilho do seu minecraft;Seleciona o brilho do seu Minecraft. Exemplo: gamma(0).");
		this.defaultMessages.add("music;Seleciona uma música do minecraft;Seleciona o volume das músicas do minecraft. Exemplo: Music(0).");
		this.defaultMessages.add("resourcepack;Sem descrição;Sem descriçãoSeleciona a sensiblidade do seu mouse no minecraft. Exemplo: sensitivity(200).");
		this.defaultMessages.add("sensitivity;Altera a sensibilidade do mause;Seleciona a sensiblidade do seu mouse no minecraft. Exemplo: sensitivity(200).");
		this.defaultMessages.add("setres;Seleciona o tamanho da janela do minecraft;Seleciona o tamanho da janela do minecraft. Exemplo: setres(180,320).");
		this.defaultMessages.add("texturepack;Seleciona uma textura;Seleciona o pacote de textura correspondente ao nome. Exemplo: texturepack(default).");
		this.defaultMessages.add("volume;Altera o volume do minecraft;Seleciona o volume geral do minecraft. Exemplo: Volume(20)");
		this.defaultMessages.add("achievementget;Mostrará a janela de obtenção de uma conquista;Mostrará a janela de obtenção de uma conquista, sendo esta totalmente personalizável com ícones e texto. Exemplo: achievementget(DIAMOND BLOCK!!, 57).");
		this.defaultMessages.add("bandgui;Sem descrição;Sem descrição");
		this.defaultMessages.add("clear;Sem descrição;Sem descrição");
		this.defaultMessages.add("gui;Mostrará/fechará uma GUI;Mostrará uma gui determinada caso possível e, se já houver uma aberta, a fechará. Exemplo: gui(inventory).");
		this.defaultMessages.add("showgui;Mostrará uma GUI customizada;Mostrará uma gui customizada. Exemplo: showgui(Nome_Da_Gui).");
		this.defaultMessages.add("store;Sem descrição;Sem descrição");
		this.defaultMessages.add("storeover;Sem descrição;Sem descrição");
		this.defaultMessages.add("time;Pegará a hora do seu computador;Salvará o tempo (de seu computador) em que a macro foi utilizada");
	}

}
