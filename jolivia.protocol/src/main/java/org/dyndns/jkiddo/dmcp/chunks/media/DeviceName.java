package org.dyndns.jkiddo.dmcp.chunks.media;
import org.dyndns.jkiddo.dmp.chunks.StringChunk;

import org.dyndns.jkiddo.dmp.IDmapProtocolDefinition.DmapChunkDefinition;
import org.dyndns.jkiddo.dmp.DMAPAnnotation;

@DMAPAnnotation(type=DmapChunkDefinition.cmnm)
public class DeviceName extends StringChunk {

	public DeviceName() {
		this("");
	}

	public DeviceName(String value) {

		super("cmnm", "unknown-nm", value);
	}

}
