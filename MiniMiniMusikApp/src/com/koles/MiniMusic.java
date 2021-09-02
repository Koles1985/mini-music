package com.koles;
import javax.sound.midi.*;


public class MiniMusic {
	
	protected void play() {
		
		try {
			/*
			 * получаем синтезатор и открываем его,что бы начать использовать изначально он закрыт
			 */
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			
			//конструктор синтезатора
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			
			/*
			 * запрашиваем трек у последовательности “рек содержитс€ в нутри
			 * последовательности, а MIDI данные в треке
			 */
			
			Track track = seq.createTrack();
			
			/*
			 * ѕомещаем в трек MIDI событи€
			 */
			
			//—оздаем сообщение
			//144 означает начало проигрывани€ ноты - 128 конец
			//1- канал
			// —ообщение гласит: Ќачать проигрывать ноту є44
			//100 скарость и сила "нажати€ клавиши"
			ShortMessage a = new ShortMessage();
			a.setMessage(144,1,55,100);
			/*
			 * »спользу€ сообщение создаем новое событие
			 * »нструкции хран€тс€ в сообщении, но MidiEvent дополн€ет их
			 * информацией о моменте времени. Ётот экземпл€р MidiEvent говорит, что
			 * сообщение "а" сработает на первом такте(бит є1)
			 */
			MidiEvent noteOne = new MidiEvent(a,1);
			/*
			 * ƒобавл€ем событие в трек
			 * “рек хранит в себе событи€ MidiEvent, они размещаютс€ в последовательности
			 * согласно времени срабатывани€, а синтезатор проигрывает их в заданом пор€дке
			 */
			
			ShortMessage first = new ShortMessage();
			first.setMessage(192,1,102,0);
			MidiEvent changeInstrum = new MidiEvent(first,1);
			track.add(changeInstrum);
			
			track.add(noteOne);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128,1,44,100);
			MidiEvent noteOff = new MidiEvent(b,10);
			track.add(noteOff);
			
			/*
			 * ѕередаем последовательность в синтезатор (как будто
			 * вставл€ем —D в проигрыватель)
			 */
			player.setSequence(seq);
			
			// «апускаем проигрыватель
			player.start();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
