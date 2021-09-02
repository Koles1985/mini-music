package com.koles;
import javax.sound.midi.*;


public class MiniMusic {
	
	protected void play() {
		
		try {
			/*
			 * �������� ���������� � ��������� ���,��� �� ������ ������������ ���������� �� ������
			 */
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			
			//����������� �����������
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			
			/*
			 * ����������� ���� � ������������������ ���� ���������� � �����
			 * ������������������, � MIDI ������ � �����
			 */
			
			Track track = seq.createTrack();
			
			/*
			 * �������� � ���� MIDI �������
			 */
			
			//������� ���������
			//144 �������� ������ ������������ ���� - 128 �����
			//1- �����
			// ��������� ������: ������ ����������� ���� �44
			//100 �������� � ���� "������� �������"
			ShortMessage a = new ShortMessage();
			a.setMessage(144,1,55,100);
			/*
			 * ��������� ��������� ������� ����� �������
			 * ���������� �������� � ���������, �� MidiEvent ��������� ��
			 * ����������� � ������� �������. ���� ��������� MidiEvent �������, ���
			 * ��������� "�" ��������� �� ������ �����(��� �1)
			 */
			MidiEvent noteOne = new MidiEvent(a,1);
			/*
			 * ��������� ������� � ����
			 * ���� ������ � ���� ������� MidiEvent, ��� ����������� � ������������������
			 * �������� ������� ������������, � ���������� ����������� �� � ������� �������
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
			 * �������� ������������������ � ���������� (��� �����
			 * ��������� �D � �������������)
			 */
			player.setSequence(seq);
			
			// ��������� �������������
			player.start();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
