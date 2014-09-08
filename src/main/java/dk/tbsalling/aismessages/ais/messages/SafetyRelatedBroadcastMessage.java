/*
 * AISMessages
 * - a java-based library for decoding of AIS messages from digital VHF radio traffic related
 * to maritime navigation and safety in compliance with ITU 1371.
 * 
 * (C) Copyright 2011-2013 by S-Consult ApS, DK31327490, http://s-consult.dk, Denmark.
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * For details of this license see the nearby LICENCE-full file, visit http://creativecommons.org/licenses/by-nc-sa/3.0/
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 * 
 * NOT FOR COMMERCIAL USE!
 * Contact sales@s-consult.dk to obtain a commercially licensed version of this software.
 * 
 */

package dk.tbsalling.aismessages.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.STRING_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

@SuppressWarnings("serial")
public class SafetyRelatedBroadcastMessage extends AISMessage {

    public SafetyRelatedBroadcastMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected SafetyRelatedBroadcastMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.SafetyRelatedBroadcastMessage;
    }

    @SuppressWarnings("unused")
	public Integer getSpare() {
        if (spare == null) {
            spare = UNSIGNED_INTEGER_DECODER.apply(getBits(38, 40));
        }
		return spare;
	}

    @SuppressWarnings("unused")
	public final String getText() {
        if (text == null) {
            int extraBitsOfChars = ((getNumberOfBits() - 40) / 6) * 6;
            text = STRING_DECODER.apply(getBits(40, 40 + extraBitsOfChars));
        }
		return text;
	}

    @Override
    public String toString() {
        return "SafetyRelatedBroadcastMessage{" +
                "messageType=" + getMessageType() +
                ", spare=" + getSpare() +
                ", text='" + getText() + '\'' +
                "} " + super.toString();
    }

    private transient Integer spare;
	private transient String text;
}
