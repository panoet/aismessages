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

package dk.tbsalling.aismessages.messages;

import dk.tbsalling.aismessages.decoder.Decoder;
import dk.tbsalling.aismessages.exceptions.InvalidEncodedMessage;
import dk.tbsalling.aismessages.exceptions.UnsupportedMessageType;
import dk.tbsalling.aismessages.messages.types.AISMessageType;
import dk.tbsalling.aismessages.messages.types.MMSI;
import dk.tbsalling.aismessages.messages.types.PositionFixingDevice;

@SuppressWarnings("serial")
public class UTCAndDateResponse extends DecodedAISMessage {
	public UTCAndDateResponse(
			Integer repeatIndicator, MMSI sourceMmsi, Integer year,
			Integer month, Integer day, Integer hour, Integer minute,
			Integer second, Boolean positionAccurate, Float latitude,
			Float longitude, PositionFixingDevice positionFixingDevice,
			Boolean raimFlag) {
		super(AISMessageType.UTCAndDateResponse, repeatIndicator, sourceMmsi);
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.positionAccurate = positionAccurate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.positionFixingDevice = positionFixingDevice;
		this.raimFlag = raimFlag;
	}

    @SuppressWarnings("unused")
	public final Integer getYear() {
		return year;
	}

    @SuppressWarnings("unused")
	public final Integer getMonth() {
		return month;
	}

    @SuppressWarnings("unused")
	public final Integer getDay() {
		return day;
	}

    @SuppressWarnings("unused")
	public final Integer getHour() {
		return hour;
	}

    @SuppressWarnings("unused")
	public final Integer getMinute() {
		return minute;
	}

    @SuppressWarnings("unused")
	public final Integer getSecond() {
		return second;
	}

    @SuppressWarnings("unused")
	public final Boolean getPositionAccurate() {
		return positionAccurate;
	}

    @SuppressWarnings("unused")
	public final Float getLatitude() {
		return latitude;
	}

    @SuppressWarnings("unused")
	public final Float getLongitude() {
		return longitude;
	}

    @SuppressWarnings("unused")
	public final PositionFixingDevice getPositionFixingDevice() {
		return positionFixingDevice;
	}

    @SuppressWarnings("unused")
	public final Boolean getRaimFlag() {
		return raimFlag;
	}
	
	public static UTCAndDateResponse fromEncodedMessage(EncodedAISMessage encodedMessage) {
		if (! encodedMessage.isValid())
			throw new InvalidEncodedMessage(encodedMessage);
		if (! encodedMessage.getMessageType().equals(AISMessageType.UTCAndDateResponse))
			throw new UnsupportedMessageType(encodedMessage.getMessageType().getCode());
			
		Integer repeatIndicator = Decoder.convertToUnsignedInteger(encodedMessage.getBits(6, 8));
		MMSI sourceMmsi = MMSI.valueOf(Decoder.convertToUnsignedLong(encodedMessage.getBits(8, 38)));
		Integer year = Decoder.convertToUnsignedInteger(encodedMessage.getBits(38, 52));
		Integer month = Decoder.convertToUnsignedInteger(encodedMessage.getBits(52, 56));
		Integer day = Decoder.convertToUnsignedInteger(encodedMessage.getBits(56, 61));
		Integer hour  = Decoder.convertToUnsignedInteger(encodedMessage.getBits(61, 66));
		Integer minute = Decoder.convertToUnsignedInteger(encodedMessage.getBits(66, 72));
		Integer second = Decoder.convertToUnsignedInteger(encodedMessage.getBits(72, 78));
		Boolean positionAccurate = Decoder.convertToBoolean(encodedMessage.getBits(78, 79));
		Float longitude = Decoder.convertToFloat(encodedMessage.getBits(79, 107)) / 600000f;
		Float latitude = Decoder.convertToFloat(encodedMessage.getBits(107, 134)) / 600000f;
		PositionFixingDevice positionFixingDevice = PositionFixingDevice.fromInteger(Decoder.convertToUnsignedInteger(encodedMessage.getBits(134, 138)));
		Boolean raimFlag = Decoder.convertToBoolean(encodedMessage.getBits(148, 149));

		return new UTCAndDateResponse(repeatIndicator, sourceMmsi, year, month,
				day, hour, minute, second, positionAccurate, latitude,
				longitude, positionFixingDevice, raimFlag);
	}

	private final Integer year;
	private final Integer month;
	private final Integer day;
	private final Integer hour;
	private final Integer minute;
	private final Integer second;
	private final Boolean positionAccurate;
	private final Float latitude;
	private final Float longitude;
	private final PositionFixingDevice positionFixingDevice;
	private final Boolean raimFlag;
	//radio
}
